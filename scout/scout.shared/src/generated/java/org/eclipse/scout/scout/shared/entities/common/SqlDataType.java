package org.eclipse.scout.scout.shared.entities.common;

import java.math.BigDecimal;
import java.util.Date;

public enum SqlDataType {

  UID("BIGINT", SqlMetaType.BIGINT, Long.class),
  BIGINT("BIGINT", SqlMetaType.BIGINT, Long.class),
  BIGSERIAL("BIGSERIAL", SqlMetaType.BIGINT, Long.class),
  INTEGER("INTEGER", SqlMetaType.INTEGER, Long.class),
  DECIMAL_6_2("DECIMAL(5,2)", SqlMetaType.NUMERIC, BigDecimal.class),
  DECIMAL_19_5("DECIMAL(19,5)", SqlMetaType.NUMERIC, BigDecimal.class),
  DOUBLE("DOUBLE PRECISION", SqlMetaType.DOUBLE, Double.class),
  TIMESTAMP("TIMESTAMP", SqlMetaType.TIMESTAMP, Date.class),
  DATE("DATE", SqlMetaType.DATE, Date.class),
  TEXT("TEXT", SqlMetaType.TEXT, String.class),
  BOOLEAN("BOOLEAN", SqlMetaType.BOOLEAN, Boolean.class),
  VARCHAR_255("VARCHAR(255)", SqlMetaType.VARCHAR, String.class),
  VARCHAR_4000("VARCHAR(4000)", SqlMetaType.VARCHAR, String.class),
  BLOB("BYTEA", SqlMetaType.BYTEA, (new byte[0]).getClass()),
  FLOAT("FLOAT", SqlMetaType.FLOAT, Float.class);

  private final String m_identifier;
  private final SqlMetaType m_metaType;
  private final Class<?> m_javaType;

  SqlDataType(String identifier, SqlMetaType metaType, Class<?> javaType) {
    m_identifier = identifier;
    m_metaType = metaType;
    m_javaType = javaType;
  }

  public String identifier() {
    return m_identifier;
  }

  public SqlMetaType metaType() {
    return m_metaType;
  }

  public Class<?> javaType() {
    return m_javaType;
  }

  public enum SqlMetaType {
    BIGINT("bigint"),
    INTEGER("integer"),
    NUMERIC("numeric"),
    DOUBLE("doubke precision"),
    TIMESTAMP("timestamp without time zone"),
    DATE("date"),
    TEXT("text"),
    BOOLEAN("boolean"),
    VARCHAR("character varying"),
    BYTEA("bytea"),
    FLOAT("float");

    private final String m_typeName;

    SqlMetaType(){
      m_typeName = "";
    }

    SqlMetaType(String typeName){
      m_typeName = typeName;
    }

    public String typeName() {
      return m_typeName;
    }
  }

}
