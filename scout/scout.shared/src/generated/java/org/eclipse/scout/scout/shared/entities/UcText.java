package org.eclipse.scout.scout.shared.entities;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.scout.shared.code.CodeUtility;
import org.eclipse.scout.scout.shared.entities.common.*;

@TableDef(name = "dev.UC_TEXT")
public interface UcText extends Table {

  @ColumnDef(name = "UC_UID", type = SqlDataType.UID)
  @PrimaryKey
  @Order(10)
  Column<Long> ucUid();

  @ColumnDef(name = "LANGUAGE_UID", type = SqlDataType.UID, codeType = LanguageCodeType.class)
  @PrimaryKey
  @Order(20)
  @Indexed
  Column<Long> languageUid();

  @ColumnDef(name = "TEXT", type = SqlDataType.TEXT)
  @Order(30)
  Column<String> text();

  /* **************************************************************************
   * GENERATED CONVENIENCE METHODS
   * *************************************************************************/

  default Long getUcUid() {
    return ucUid().value();
  }

  default Long getLanguageUid() {
    return languageUid().value();
  }

  default String getLanguageText() {
    return CodeUtility.getText(LanguageCodeType.class, languageUid().value());
  }

  default String getText() {
    return text().value();
  }
}
