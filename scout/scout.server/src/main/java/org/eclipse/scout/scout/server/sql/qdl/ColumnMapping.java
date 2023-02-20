package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.scout.shared.entities.common.Column;

public interface ColumnMapping {

  Column<?> column();

  String tableRowId();

  String fullyQualifiedTableRowId();

}
