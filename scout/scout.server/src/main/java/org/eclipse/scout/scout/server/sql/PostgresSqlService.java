package org.eclipse.scout.scout.server.sql;


import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.server.jdbc.postgresql.AbstractPostgreSqlService;
import org.eclipse.scout.scout.shared.IPostgresSqlService;

public class PostgresSqlService extends AbstractPostgreSqlService implements IPostgresSqlService {

  @Override
  protected String getConfiguredJdbcMappingName() {
    String env = System.getenv("JdbcMappingName");
    return env != null ? env : CONFIG.getPropertyValue(DatabaseProperties.JdbcMappingNameProperty.class);
  }

  @Override
  protected String getConfiguredUsername() {
    String env = System.getenv("JdbcUsername");
    return env != null ? env : CONFIG.getPropertyValue(DatabaseProperties.JdbcUsernameProperty.class);
  }

  @Override
  protected String getConfiguredPassword() {
    String env = System.getenv("JdbcPassword");
    return env != null ? env : CONFIG.getPropertyValue(DatabaseProperties.JdbcPasswordProperty.class);
  }

  @Override
  public String getDatabaseName() {
    String[] jdbcProppertyParts= getConfiguredJdbcMappingName().split("/");
    return jdbcProppertyParts[jdbcProppertyParts.length - 1];
  }
}
