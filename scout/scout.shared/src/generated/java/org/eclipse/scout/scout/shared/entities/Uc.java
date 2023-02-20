package org.eclipse.scout.scout.shared.entities;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.BooleanUtility;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.scout.shared.code.CodeUtility;
import org.eclipse.scout.scout.shared.code.StatusCodeType;
import org.eclipse.scout.scout.shared.entities.common.*;

@TableDef(name = "dev.UC")
public interface Uc extends Table {

  @ColumnDef(name = "UC_UID", type = SqlDataType.BIGSERIAL)
  @Sequence(name = "UC_UC_UID_SEQ")
  @PrimaryKey
  @Order(10)
  Column<Long> ucUid();

  @ColumnDef(name = "CODE_TYPE", type = SqlDataType.BIGINT)
  @Order(20)
  @Indexed
  Column<Long> codeType();

  @ColumnDef(name = "EXT_KEY", type = SqlDataType.VARCHAR_4000)
  @Nullable
  @Order(30)
  Column<String> extKey();

  @ColumnDef(name = "VALUE", type = SqlDataType.DOUBLE)
  @Nullable
  @Order(40)
  Column<Double> value();

  @ColumnDef(name = "ICON_ID", type = SqlDataType.VARCHAR_255)
  @Nullable
  @Order(50)
  Column<String> iconId();

  @ColumnDef(name = "TOOL_TIP", type = SqlDataType.VARCHAR_4000)
  @Nullable
  @Order(60)
  Column<String> toolTip();

  @ColumnDef(name = "FOREGROUND_COLOR", type = SqlDataType.VARCHAR_255)
  @Nullable
  @Order(70)
  Column<String> foregroundColor();

  @ColumnDef(name = "BACKGROUND_COLOR", type = SqlDataType.VARCHAR_255)
  @Nullable
  @Order(80)
  Column<String> backgroundColor();

  @ColumnDef(name = "FONT", type = SqlDataType.VARCHAR_255)
  @Nullable
  @Order(90)
  Column<String> font();

  @ColumnDef(name = "PARENT_UID", type = SqlDataType.UID)
  @Nullable
  @Indexed
  @Order(100)
  Column<Long> parentUid();

  @ColumnDef(name = "IS_BUILTIN", type = SqlDataType.BOOLEAN)
  @Nullable
  @DefaultValue("FALSE")
  @Order(110)
  Column<Boolean> isBuiltin();

  @ColumnDef(name = "STATUS_UID", type = SqlDataType.UID, codeType = StatusCodeType.class)
  @DefaultValue(WotanUtility.ACTIVE_STRING)
  @Indexed
  @Order(120)
  Column<Long> statusUid();

  /* **************************************************************************
   * GENERATED CONVENIENCE METHODS
   * *************************************************************************/

  default Long getUcUid() {
    return ucUid().value();
  }

  default Long getCodeType() {
    return codeType().value();
  }

  default String getExtKey() {
    return extKey().value();
  }

  default Double getValue() {
    return value().value();
  }

  default String getIconId() {
    return iconId().value();
  }

  default String getToolTip() {
    return toolTip().value();
  }

  default String getForegroundColor() {
    return foregroundColor().value();
  }

  default String getBackgroundColor() {
    return backgroundColor().value();
  }

  default String getFont() {
    return font().value();
  }

  default Long getParentUid() {
    return parentUid().value();
  }

  default boolean getIsBuiltin() {
    return BooleanUtility.nvl(isBuiltin().value());
  }

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
