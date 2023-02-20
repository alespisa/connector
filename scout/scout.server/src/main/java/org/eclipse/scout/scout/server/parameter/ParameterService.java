package org.eclipse.scout.scout.server.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.scout.server.DatabaseUtility;
import org.eclipse.scout.scout.server.sql.TableAliases;
import org.eclipse.scout.scout.server.sql.qdl.QDL;
import org.eclipse.scout.scout.shared.code.AbstractSQLCodeType;
import org.eclipse.scout.scout.shared.code.CodeUtility;
import org.eclipse.scout.scout.shared.entities.Parameter;
import org.eclipse.scout.scout.shared.entities.Uc;
import org.eclipse.scout.scout.shared.entities.UcText;
import org.eclipse.scout.scout.shared.entities.common.LanguageCodeType;
import org.eclipse.scout.scout.shared.entities.common.WotanUtility;
import org.eclipse.scout.scout.shared.parameter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.eclipse.scout.scout.server.sql.qdl.QDL.*;
import static org.eclipse.scout.scout.shared.entities.common.WotanUtility.toPrimitiveByteArray;

public class ParameterService implements IParameterService {

  @Override
  public ParameterFormData store(ParameterFormData formData) throws ProcessingException {
    @SuppressWarnings("unchecked")
    IParameter<Object> parameter = (IParameter<Object>) getParameter(formData.getParameterName());

    if (parameter != null) {
      parameter.setValue(formData.getValue());
    }
    return formData;
  }

  private static final Logger LOG = LoggerFactory.getLogger(ParameterService.class);

  private Parameter p = TableAliases.create(Parameter.class);
  private static Map<String, String> m_stringParameterCache = new HashMap<>();
  private static Map<String, Number> m_numberParameterCache = new HashMap<>();
  private static Map<String, Integer> m_integerParameterCache = new HashMap<>();
  private static Map<String, Boolean> m_booleanParameterCache = new HashMap<>();
  private static Map<String, Long> m_longParameterCache = new HashMap<>();

  private Uc uc = TableAliases.create(Uc.class);
  private UcText ucText = TableAliases.create(UcText.class);

  @Override
  public boolean parameterExists(final String parameterName) throws ProcessingException {
    Object[][] select = QDL.select(p.parameterNr()).from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .executeSelectUnlimited();

    return select != null && select.length > 0;
  }

  @Override
  public ParameterFormData load(ParameterFormData formData) throws ProcessingException {
    IParameter<?> parameter = getParameter(formData.getParameterName());
    if (parameter != null) {
      formData.setValue(parameter.getValue());
      formData.setParameterTypeUid(parameter.getTypeUid());
    }
    return formData;
  }

  @Override
  public <T> IParameter<T> getParameter(Class<? extends IParameter<T>> parameterClass) {
    return BEANS.get(parameterClass);
  }

  @Override
  public IParameter<?> getParameter(String parameterName) {
    List<IParameter<?>> allParameters = getAllParameters();
    for (IParameter<?> parameter : allParameters) {
      if (parameter.getName().equals(parameterName)) {
        return parameter;
      }
    }
    return null;
  }

  @Override
  public List<IParameter<?>> getAllParameters() {
    List<IParameter<?>> parameters = new ArrayList<IParameter<?>>();
    for (IParameter<?> parameter : BEANS.all(IParameter.class)) {
      parameters.add(parameter);
    }
    return parameters;
  }

  @Override
  public String getStringValue(final String parameterName) throws ProcessingException {
    if (m_stringParameterCache.containsKey(parameterName)) {
      return m_stringParameterCache.get(parameterName);
    }

    Object[][] result = QDL.select(p.stringValue()).from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .executeSelectUnlimited();

    String value = null;
    if (result.length > 0 && result[0].length > 0 && result[0][0] instanceof String) {
      value = (String) result[0][0];
    }
    m_stringParameterCache.put(parameterName, value);
    return value;
  }

  @Override
  public void storeStringParameter(AbstractStringParameter param) throws ProcessingException {
    storeStringParameterInternal(param.getConfiguredParameterName(), param.getValue(),
      param.getConfiguredParameterTypeUid());
    m_stringParameterCache.clear();
  }

  private void storeStringParameterInternal(String name, String value, Long parameterUid) throws ProcessingException {
    if (parameterExists(name)) {
      QDL.update(p).set(assign(p.stringValue(), bindString(value))).where(eq(p.parameterName(), bindString(name)))
        .executeUpdate();
    }
    else {
      Long parameterNr = DatabaseUtility.createParameterNr();

      QDL.insertInto(p)
        .columns(p.parameterNr(), p.parameterNo(), p.parameterName(), p.parameterTypeUid(), p.stringValue())
        .values(bindLong(parameterNr), bindLong(parameterNr), bindString(name), bindLong(parameterUid),
          bindString(value))
        .executeInsert();
    }
  }

  @Override
  public Number getNumberValue(String parameterName) throws ProcessingException {
    if (m_numberParameterCache.containsKey(parameterName)) {
      return m_numberParameterCache.get(parameterName);
    }
    Number value = QDL.select(p.numberValue()).from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .singleResult(Number.class);
    m_numberParameterCache.put(parameterName, value);
    return value;
  }

  @Override
  public void storeNumberParameter(AbstractNumberParameter param) throws ProcessingException {
    storeNumberParameterInternal(param.getConfiguredParameterName(), param.getValue(),
      param.getConfiguredParameterTypeUid());
    m_numberParameterCache.clear();

    if (param instanceof AbstractCodeNumberedValueParameter) {
      storeCode((AbstractCodeNumberedValueParameter) param);
    }
  }

  private void storeCode(AbstractCodeNumberedValueParameter param) throws ProcessingException {
    Class<? extends ICodeType<Long, Long>> codeTypeClass = CodeUtility.getCodeTypeClassById(param.getCodeTypeId());
    ICode<Long> code = CodeUtility.getCode(codeTypeClass, param.getCodeId());
    Number value = param.getValue();
    if (code == null) {
      String msg = new StringBuilder("Code with uid ").append(param.getCodeId()).append(" not found in codeType ").append(codeTypeClass.getSimpleName()).toString();
      LOG.error(msg);
      throw new ProcessingException(msg);
    }
    Long languageUid = LanguageCodeType.GermanCode.ID;

    //update code value
    QDL.update(uc)
      .set(assign(uc.value(), value))
      .where(eq(uc.ucUid(), code.getId()))
      .executeUpdate();

    //update code text
    QDL.update(ucText)
      .set(assign(ucText.text(), bindString(value.toString())))
      .where(
        eq(ucText.ucUid(), code.getId()),
        eq(ucText.languageUid(), languageUid))
      .executeUpdate();

    ((AbstractSQLCodeType) BEANS.get(codeTypeClass)).reloadCodes();
  }

  @Override
  public Integer getIntegerValue(String parameterName) throws ProcessingException {
    if (m_integerParameterCache.containsKey(parameterName)) {
      return m_integerParameterCache.get(parameterName);
    }

    Integer value = null;
    Number singleResult = QDL
      .select(p.numberValue())
      .from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .singleResult(Number.class);
    if (singleResult != null) {
      value = singleResult.intValue();
    }
    m_integerParameterCache.put(parameterName, value);
    return value;
  }

  @Override
  public void storeIntegerParameter(AbstractIntegerParameter param) throws ProcessingException {
    storeNumberParameterInternal(param.getConfiguredParameterName(), param.getValue(),
      param.getConfiguredParameterTypeUid());
    m_integerParameterCache.clear();
  }

  private void storeNumberParameterInternal(String name, Number value, Long parameterUid) throws ProcessingException {
    if (parameterExists(name)) {
      QDL.update(p).set(assign(p.numberValue(), bindNumber(value))).where(eq(p.parameterName(), bindString(name)))
        .executeUpdate();
    }
    else {
      Long parameterNr = DatabaseUtility.createParameterNr();

      QDL.insertInto(p)
        .columns(p.parameterNr(), p.parameterNo(), p.parameterName(), p.parameterTypeUid(), p.numberValue())
        .values(bindLong(parameterNr), bindLong(parameterNr), bindString(name), bindLong(parameterUid),
          bindNumber(value))
        .executeInsert();
    }
  }

  @Override
  public Boolean getBooleanValue(String parameterName) throws ProcessingException {
    if (m_booleanParameterCache.containsKey(parameterName)) {
      return m_booleanParameterCache.get(parameterName);
    }

    Boolean value = QDL.select(p.booleanValue()).from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .singleResult(Boolean.class);
    m_booleanParameterCache.put(parameterName, value);
    return value;
  }

  @Override
  public void storeBooleanParameter(AbstractBooleanParameter param) throws ProcessingException {
    storeBooleanParameterInternal(param.getConfiguredParameterName(), param.getValue(),
      param.getConfiguredParameterTypeUid());
    m_booleanParameterCache.clear();
  }

  private void storeBooleanParameterInternal(String name, Boolean value, Long parameterUid) throws ProcessingException {
    if (parameterExists(name)) {
      QDL.update(p).set(assign(p.booleanValue(), bindBoolean(value))).where(eq(p.parameterName(), bindString(name)))
        .executeUpdate();
    }
    else {
      Long parameterNr = DatabaseUtility.createParameterNr();

      QDL.insertInto(p)
        .columns(p.parameterNr(), p.parameterNo(), p.parameterName(), p.parameterTypeUid(), p.booleanValue())
        .values(bindLong(parameterNr), bindLong(parameterNr), bindString(name), bindLong(parameterUid),
          bindBoolean(value))
        .executeInsert();
    }
  }

  @Override
  public Byte[] getBinaryValue(String parameterName) throws ProcessingException {
    Object result = QDL.select(p.binaryValue()).from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .singleResult(Object.class);

    return WotanUtility.toObjectByteArray((byte[]) result);
  }

  @Override
  public void storeBinaryParameter(AbstractBinaryParameter param) throws ProcessingException {
    storeBinaryParameterInternal(param.getConfiguredParameterName(), param.getValue(),
      param.getConfiguredParameterTypeUid());
    storeStringParameterInternal(param.getConfiguredParameterName(), param.getFileName(),
      param.getConfiguredParameterTypeUid());
  }

  private void storeBinaryParameterInternal(String name, Byte[] value, Long parameterUid) throws ProcessingException {
    if (parameterExists(name)) {
      QDL.update(p).set(assign(p.binaryValue(), bindBlob(toPrimitiveByteArray(value))))
        .where(eq(p.parameterName(), bindString(name))).executeUpdate();
    }
    else {
      Long parameterNr = DatabaseUtility.createParameterNr();

      QDL.insertInto(p)
        .columns(p.parameterNr(), p.parameterNo(), p.parameterName(), p.parameterTypeUid(), p.binaryValue())
        .values(bindLong(parameterNr), bindLong(parameterNr), bindString(name), bindLong(parameterUid),
          bindBlob(toPrimitiveByteArray(value)))
        .executeInsert();
    }
  }

  @Override
  public Long getLongValue(String parameterName) throws ProcessingException {
    if (m_longParameterCache.containsKey(parameterName)) {
      return m_longParameterCache.get(parameterName);
    }

    Long value = null;
    Number result = QDL
      .select(p.smartValue())
      .from(p).where(eq(p.parameterName(), bindString(parameterName)))
      .singleResult(Number.class);
    if (result != null) {
      value = result.longValue();
    }
    m_longParameterCache.put(parameterName, value);
    return value;
  }

  @Override
  public void storeLongValue(AbstractSmartParameter param) throws ProcessingException {
    storeLongParameterInternal(param.getConfiguredParameterName(), param.getValue(),
      param.getConfiguredParameterTypeUid());
    m_longParameterCache.clear();
  }

  private void storeLongParameterInternal(String name, Long value, Long parameterUid) throws ProcessingException {
    if (parameterExists(name)) {
      QDL.update(p).set(assign(p.smartValue(), bindLong(value))).where(eq(p.parameterName(), bindString(name)))
        .executeUpdate();
    } else {
      Long parameterNr = DatabaseUtility.createParameterNr();

      QDL.insertInto(p)
        .columns(p.parameterNr(), p.parameterNo(), p.parameterName(), p.parameterTypeUid(), p.smartValue())
        .values(bindLong(parameterNr), bindLong(parameterNr), bindString(name), bindLong(parameterUid), bindLong(value))
        .executeInsert();
    }
  }

}
