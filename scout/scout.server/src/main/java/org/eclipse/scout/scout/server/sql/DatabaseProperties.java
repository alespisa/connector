package org.eclipse.scout.scout.server.sql;

import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;

public class DatabaseProperties {

  public static class JdbcMappingNameProperty extends AbstractStringConfigProperty{

    @Override
    public String getKey() {
      return "scout.database.jdbc.mapping.name";
    }

    @Override
    public String description() {
      return getKey();
    }
  }

  public static class JdbcUsernameProperty extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "scout.database.jdbc.username";
    }

    @Override
    public String description() {
      return getKey();
    }
  }

  public static class JdbcPasswordProperty extends AbstractStringConfigProperty {

    @Override
    public String getKey() {
      return "scout.database.jdbc.password";
    }

    @Override
    public String description() {
      return getKey();
    }
  }



}
