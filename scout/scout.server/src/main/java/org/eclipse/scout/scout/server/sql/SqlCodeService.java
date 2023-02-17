package org.eclipse.scout.scout.server.sql;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.common.code.CodeRow;
import org.eclipse.scout.rt.shared.services.common.code.ICodeRow;
import org.eclipse.scout.scout.server.sql.qdl.QDL;
import org.eclipse.scout.scout.shared.code.ISqlCodeService;

import java.util.ArrayList;
import java.util.List;

public class SqlCodeService implements ISqlCodeService {

  private Uc uc = TableAliases.create(Uc.class);
  private UcText ut = TableAliases.create(UcText.class);

  @Override
  public List<? extends ICodeRow<Long>> getCodesOfCodeType(Long codeType) throws ProcessingException {
    return getCodesOfCodeType(codeType, new ArrayList<Long>());
  }

  @Override
  public List<? extends ICodeRow<Long>> getCodesOfCodeType(Long codeType, List<Long> builtInIds)
    throws ProcessingException {
    List<CodeRow<Long>> list = new ArrayList<>();
    Long languageUid = LanguageCodeType.GermanCode.ID;    //TODO support other languages

    Object[][] select = QDL
      .select(
        uc.ucUid(),
        ut.text(),
        uc.statusUid(),
        uc.parentUid(),
        uc.extKey(),
        uc.iconId(),
        uc.toolTip(),
        uc.foregroundColor(),
        uc.backgroundColor(),
        uc.font(),
        uc.value())
      .from(uc, ut)
      .where(
        eq(uc.ucUid(), ut.ucUid()),
        eq(ut.languageUid(), bindLong(languageUid)),
        eq(uc.codeType(), bindLong(codeType)),
        eq(uc.isBuiltin(), "FALSE"))
      .orderBy(ut.text())
      .executeSelectUnlimited();

    for (Object[] objects : select) {
      Long uid = (Long) objects[0];
      String text = (String) objects[1];
      boolean active = ActiveCode.ID.equals(objects[2]);
      Long parentUid = (Long) objects[3];
      if (parentUid != null && parentUid.equals(0l)) {
        parentUid = null;
      }
      long partitionUid = builtInIds.contains(uid) ? 0 : 100;
      String extKey = (String) objects[4];
      String iconId = StringUtility.emptyIfNull(objects[5]);
      String toolTip = StringUtility.emptyIfNull(objects[6]);
      String foregroundColor = StringUtility.emptyIfNull(objects[7]);
      String backgroundColor = StringUtility.emptyIfNull(objects[8]);
      String font = StringUtility.emptyIfNull(objects[9]);
      Double value = (Double) objects[10];

      list.add(createCodeRow(uid, text, active, parentUid, extKey, iconId, toolTip, foregroundColor, backgroundColor,
        font, partitionUid, value));
    }
    return list;
  }

  private CodeRow<Long> createCodeRow(Long key, String text, boolean active, Long parentKey, String extKey,
                                      String iconId, String toolTip, String foregroundColor, String backgroundColor, String font, long partitionUid, Double value) {
    String cssClass = "";
    boolean enabled = true;

    FontSpec fontSpec = FontSpec.parse(font);
    return new CodeRow<Long>(key, text, iconId, toolTip, backgroundColor, foregroundColor, fontSpec, cssClass, enabled, parentKey,
      active, extKey, value, partitionUid);
  }

}
