package org.eclipse.scout.scout.shared.entities;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.BooleanUtility;
import org.eclipse.scout.scout.shared.code.CodeUtility;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;
import org.eclipse.scout.scout.shared.code.StatusCodeType;
import org.eclipse.scout.scout.shared.entities.common.*;

import java.math.BigDecimal;
import java.util.Date;

@TableDef(name = "dev.PARAMETER")
public interface Parameter extends Table {

    @ColumnDef(name = "PARAMETER_NR", type = SqlDataType.BIGSERIAL)
    @Sequence(name = "PARAMETER_PARAMETER_NR_SEQ")
    @PrimaryKey
    @Order(10.0)
    Column<Long> parameterNr();

    @ColumnDef(name = "PARAMETER_NO", type = SqlDataType.VARCHAR_4000)
    @Order(20.0)
    Column<String> parameterNo();

    @ColumnDef(name = "NAME", type = SqlDataType.VARCHAR_4000)
    @Order(30.0)
    Column<String> parameterName();

    @ColumnDef(name = "PARAMETER_TYPE_UID", type = SqlDataType.UID, codeType = ParameterTypeCodeType.class)
    @Order(40.0)
    Column<Long> parameterTypeUid();

    @ColumnDef(name = "NUMBER_VALUE", type = SqlDataType.DECIMAL_19_5)
    @Nullable
    @Order(50.0)
    Column<BigDecimal> numberValue();

    @ColumnDef(name = "BOOLEAN_VALUE", type = SqlDataType.BOOLEAN)
    @Nullable
    @Order(60.0)
    Column<Boolean> booleanValue();

    @ColumnDef(name = "TIME_VALUE", type = SqlDataType.TIMESTAMP)
    @Nullable
    @Order(70.0)
    Column<Date> timeValue();

    @ColumnDef(name = "STRING_VALUE", type = SqlDataType.VARCHAR_4000)
    @Nullable
    @Order(80.0)
    Column<String> stringValue();

    @ColumnDef(name = "BINARY_VALUE", type = SqlDataType.BLOB)
    @Nullable
    @Order(90.0)
    Column<byte[]> binaryValue();

    @ColumnDef(name = "SMART_VALUE", type = SqlDataType.UID)
    @Nullable
    @Order(100.0)
    Column<Long> smartValue();

    @ColumnDef(name = "STATUS_UID", type = SqlDataType.UID, codeType = StatusCodeType.class)
    @Order(100.0)
    @Indexed
    Column<Long> statusUid();

  /* **************************************************************************
   * GENERATED CONVENIENCE METHODS
   * *************************************************************************/

  default Long getParameterNr() {
    return parameterNr().value();
  }

  default String getParameterNo() {
    return parameterNo().value();
  }

  default String getParameterName() {
    return parameterName().value();
  }

  default Long getParameterTypeUid() {
    return parameterTypeUid().value();
  }

  default String getParameterTypeText() {
    return CodeUtility.getText(ParameterTypeCodeType.class, parameterTypeUid().value());
  }

  default BigDecimal getNumberValue() {
    return numberValue().value();
  }

  default boolean getBooleanValue() {
    return BooleanUtility.nvl(booleanValue().value());
  }

  default Date getTimeValue() {
    return timeValue().value();
  }

  default String getStringValue() {
    return stringValue().value();
  }

  default byte[] getBinaryValue() {
    return binaryValue().value();
  }

  default Long getSmartValue() {
    return smartValue().value();
  }

  default Long getStatusUid() {
    return statusUid().value();
  }

  default String getStatusText() {
    return CodeUtility.getText(StatusCodeType.class, statusUid().value());
  }

}
