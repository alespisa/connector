package org.eclipse.scout.scout.shared.code;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;
import org.eclipse.scout.rt.shared.services.common.code.ICodeRow;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSQLCodeType extends AbstractCodeType<Long, Long> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<? extends ICodeRow<Long>> execLoadCodes(Class<? extends ICodeRow<Long>> codeRowType) throws ProcessingException {
    return BEANS.get(ISqlCodeService.class).getCodesOfCodeType(getId(), getBuiltInIds());
  }

  public void reloadCodes() throws ProcessingException {
    loadCodes();
  }

  public abstract boolean getConfiguredShowInAdministrationOutline();

  private List<Long> getBuiltInIds() {
    Class<?>[] declaredClasses = getClass().getDeclaredClasses();
    List<Long> builtInIds = new ArrayList<Long>();
    for (Class<?> clazz : declaredClasses) {
      try {
        builtInIds.add((Long) clazz.getDeclaredField("ID").get(Long.class));
      }
      catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
        continue;
      }
    }
    return builtInIds;
  }

}
