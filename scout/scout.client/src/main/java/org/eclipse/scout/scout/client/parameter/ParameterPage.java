package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class ParameterPage extends AbstractPageWithNodes {

  @Override
  protected boolean getConfiguredLeaf() {
    return Boolean.TRUE;
  }

  @Override
  protected boolean getConfiguredTableVisible() {
    return Boolean.FALSE;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Parameter");
  }

  @Override
  protected Class<? extends IForm> getConfiguredDetailForm() {
    return ParameterForm.class;
  }
}
