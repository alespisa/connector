package org.eclipse.scout.scout.shared.code;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.code.ICodeService;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeUtility {

  private static final Logger LOG = LoggerFactory.getLogger(CodeUtility.class);

  public static String getText(Class<? extends ICodeType<Long, Long>> codeTypeClass, Long uid) {
    if (uid == null) {
      return "";
    }
    ICode<Long> code = BEANS.get(ICodeService.class).getCodeType(codeTypeClass).getCode(uid);
    if (code == null) {
      LOG.warn("Code with uid \"" + uid + "\" not found in codeType \"" + codeTypeClass.getSimpleName() + "\"");
      return "";
    }
    return code.getText();
  }

  public static ICode<Long> getCode(Class<? extends ICodeType<Long, Long>> codeTypeClass, Long uid) {
    if (uid == null || codeTypeClass == null) {
      return null;
    }
    return loadCode(codeTypeClass, uid);
  }

  private static ICode<Long> loadCode(Class<? extends ICodeType<Long, Long>> codeTypeClass, Long uid) {
    ICode<Long> code = BEANS.get(ICodeService.class).getCodeType(codeTypeClass).getCode(uid);
    if (code == null) {
      LOG.warn("Code with uid \"" + uid + "\" not found in codeType \"" + codeTypeClass.getSimpleName() + "\"");
    }
    return code;
  }

  @SuppressWarnings("unchecked")
  public static Class<? extends ICodeType<Long, Long>> getCodeTypeClassById(Long codeTypeId) {
    return (Class<? extends ICodeType<Long, Long>>) BEANS.get(ICodeService.class).findCodeTypeById(codeTypeId).getClass();
  }

}
