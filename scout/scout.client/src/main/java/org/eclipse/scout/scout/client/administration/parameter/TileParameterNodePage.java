package org.eclipse.scout.scout.client.administration.parameter;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.scout.client.parameter.TileParameterForm;
import org.eclipse.scout.scout.shared.Icons;

public class TileParameterNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Parameter");
  }

  @Override
  protected IForm createDetailForm() {
    return new TileParameterForm();
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
    return false;
  }

}
