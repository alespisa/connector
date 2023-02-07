package org.eclipse.scout.scout.client.search;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractSearchOutline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.scout.scout.shared.Icons;

/**
 * @author alespisa
 */
@Order(2000)
public class SearchOutline extends AbstractSearchOutline {

  private static final Logger LOG = LoggerFactory.getLogger(SearchOutline.class);

  @Override
  protected void execSearch(final String query) {
    LOG.info("Search started");
    // TODO [alespisa]: Implement search
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Search;
  }
}
