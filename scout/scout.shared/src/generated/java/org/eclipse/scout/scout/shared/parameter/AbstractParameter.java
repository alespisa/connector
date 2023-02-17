package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.security.ACCESS;

import java.io.Serializable;
import java.security.Permission;

public abstract class AbstractParameter<T> implements IParameter<T>, Serializable {

  private static final long serialVersionUID = 1L;

  protected T m_overriddenValue = null;
  protected boolean m_hasOverriddenValue = false;

  protected boolean getConfiguredVisible() {
    return false;
  }

  @Override
  public boolean isVisible() {
    return getConfiguredVisible();
  }

  public abstract String getConfiguredLabel();

  public abstract String getConfiguredParameterName();

  public abstract Long getConfiguredParameterTypeUid();

  public abstract Long getParameterCode();

  public abstract T getConfiguredDefaultValue();

  @Override
  public String getLabel() {
    return getConfiguredLabel();
  }

  @Override
  public String getName() {
    return getConfiguredParameterName();
  }

  @Override
  public Long getTypeUid() {
    return getConfiguredParameterTypeUid();
  }

  @Override
  public Long getCategoryUid() {
    return getConfiguredCategoryUid();
  }

  @Override
  public Long getConfiguredCategoryUid() {
    return ParameterCategoryCodeType.GeneralCode.ID;
  }

  @Override
  public T getDefaultValue() {
    return getConfiguredDefaultValue();
  }

  @Override
  public T getValue() {
    if (m_hasOverriddenValue) {
      return m_overriddenValue;
    }
    try {
      return execFetchValue();
    } catch (ProcessingException e) {
      return null;
    }
  }

  @Override
  public void setValue(T value) {
    m_overriddenValue = value;
    m_hasOverriddenValue = true;
    try {
      execStore();
    } catch (ProcessingException e) {
    } finally {
      m_hasOverriddenValue = false;
      m_overriddenValue = null;
    }
  }

  @Override
  public Permission getVisiblePermission() {
    return getConfiguredVisiblePermission();
  }

  @Override
  public Permission getConfiguredVisiblePermission() {
    return null;
  }

  @Override
  public boolean hasVisiblePermission() {
    boolean b = true;
    if (getVisiblePermission() != null) {
      b = ACCESS.check(getVisiblePermission());
    }
    return b;
  }

  protected abstract T execFetchValue() throws ProcessingException;

  protected abstract void execStore() throws ProcessingException;

}
