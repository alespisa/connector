package org.eclipse.scout.scout.client;

import java.net.URL;

import org.eclipse.scout.rt.client.services.common.icon.AbstractIconProviderService;

/**
 * @author alespisa
 */
public class DefaultIconProviderService extends AbstractIconProviderService {
  @Override
  protected URL findResource(String relativePath) {
    return ResourceBase.class.getResource("icons/" + relativePath);
  }
}
