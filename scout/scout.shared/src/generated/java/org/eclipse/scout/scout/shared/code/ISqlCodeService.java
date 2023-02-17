package org.eclipse.scout.scout.shared.code;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.code.ICodeRow;

import java.util.List;

@TunnelToServer
public interface ISqlCodeService extends IService {

  List<? extends ICodeRow<Long>> getCodesOfCodeType(Long codeType) throws ProcessingException;

  List<? extends ICodeRow<Long>> getCodesOfCodeType(Long codeType, List<Long> builtInIds) throws ProcessingException;

}
