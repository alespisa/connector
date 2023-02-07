package org.eclipse.scout.scout.shared.text;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.AbstractDynamicNlsTextProviderService;

/**
 * @author alespisa
 */
@Order(-2000)
public class DefaultTextProviderService extends AbstractDynamicNlsTextProviderService {
  @Override
  public String getDynamicNlsBaseName() {
    return "org.eclipse.scout.scout.shared.texts.Texts";
  }
}
