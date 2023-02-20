package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.scout.shared.code.CodeUtility;
import org.eclipse.scout.scout.shared.code.StatusCodeType;

public interface EntityTable extends Table{
  static final String STAUS_UID = "STATUS_UID";

  @ColumnDef(name = STAUS_UID, type = SqlDataType.UID, codeType = StatusCodeType.class)
  @DefaultValue(WotanUtility.ACTIVE_STRING)
  @Order(10000)
  @Indexed
  Column<Long> statusUid();

  /* **************************************************************************
   * GENERATED CONVENIENCE METHODS
   * *************************************************************************/

  default Long getStatusUid() {
    return statusUid().value();
  }

  default String getStatusText() {
    return CodeUtility.getText(StatusCodeType.class, statusUid().value());
  }

  default boolean isActive() {
    return WotanUtility.ACTIVE == NumberUtility.nvl(getStatusUid(), 0);
  }

}
