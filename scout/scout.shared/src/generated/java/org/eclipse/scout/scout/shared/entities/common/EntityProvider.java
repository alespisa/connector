package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.Bean;

@Bean
public interface EntityProvider<TO, FROM extends Table> {

  TO getEntity(FROM entity);

  /**
   * Workaround for proxy elements that don't know the generic attributes FROM and TO. Overriding is not recommended.
   */
  @SuppressWarnings("unchecked")
  default TO getEntity(Object proxy) {
    return getEntity((FROM)proxy);
  }
}
