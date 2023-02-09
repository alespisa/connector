package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.scout.shared.Icons;

public class TileParameterPage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Parameter");
  }

  @Override
  protected Class<? extends IForm> getConfiguredDetailForm() {
    return TileParameterForm.class;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Parameter;
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return getConfiguredIconId();
  }

  @Override
  protected boolean getConfiguredTableVisible() {
    return Boolean.FALSE;
  }
}
