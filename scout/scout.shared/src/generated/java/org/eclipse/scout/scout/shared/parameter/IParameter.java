package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.Bean;

import java.security.Permission;

@Bean
public interface IParameter<T> {

  String getLabel();

  String getName();

  Long getTypeUid();

  Long getCategoryUid();

  Long getConfiguredCategoryUid();

  Long getParameterCode();

  T getDefaultValue();

  T getValue();

  void setValue(T value);

  boolean isVisible();

  Permission getVisiblePermission();

  Permission getConfiguredVisiblePermission();

  boolean hasVisiblePermission();

}
