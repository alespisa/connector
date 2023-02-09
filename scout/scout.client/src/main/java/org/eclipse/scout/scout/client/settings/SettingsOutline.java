package org.eclipse.scout.scout.client.settings;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.platform.text.TEXTS;

import org.eclipse.scout.scout.client.parameter.TileParameterPage;
import org.eclipse.scout.scout.shared.Icons;

import java.util.List;

/**
 * @author alespisa
 */
@Order(3000)
public class SettingsOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Settings");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Gear;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new TileParameterPage());
  }
}
