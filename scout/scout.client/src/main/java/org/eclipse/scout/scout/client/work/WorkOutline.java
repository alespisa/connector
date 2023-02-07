package org.eclipse.scout.scout.client.work;

import java.util.List;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;

import org.eclipse.scout.scout.client.api.ApiPage;
import org.eclipse.scout.scout.client.helloworld.HelloWorldPage;
import org.eclipse.scout.scout.client.parameter.ParameterPage;
import org.eclipse.scout.scout.shared.Icons;

/**
 * @author alespisa
 */
@Order(1000)
public class WorkOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.add(new HelloWorldPage());
    pageList.add(new ApiPage());
    pageList.add(new ParameterPage());
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Work");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Pencil;
  }
}
