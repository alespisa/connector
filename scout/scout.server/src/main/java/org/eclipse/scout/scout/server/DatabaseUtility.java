package org.eclipse.scout.scout.server;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.scout.server.sql.qdl.QDL;
import org.eclipse.scout.scout.shared.entities.common.Column;
import org.eclipse.scout.scout.shared.entities.common.Table;

import static org.eclipse.scout.scout.server.sql.qdl.QDL.max;

public class DatabaseUtility{

  public static String escapePgSqlString(String string) {
    if (string == null) {
      return "";
    }
    return string.replaceAll("'", "''");
  }

  //TODO: get sequence names from TableDefs, write utility
  public static Long createUid() throws ProcessingException {
    return QDL.nextVal("uc_uc_uid_seq");
  }

  public static Long createAddressNr() throws ProcessingException {
    return QDL.nextVal("address_address_nr_seq");
  }

  public static Long createPersonNr() throws ProcessingException {
    return QDL.nextVal("person_person_nr_seq");
  }

  public static Long createAnimalNr() throws ProcessingException {
    return QDL.nextVal("animal_animal_nr_seq");
  }

  public static Long createCashBookEntryNr() throws ProcessingException {
    return QDL.nextVal("cash_book_entry_cash_book_entry_nr_seq");
  }

  public static Long createInvoiceNr() throws ProcessingException {
    return QDL.nextVal("invoice_invoice_nr_seq");
  }

  public static Long createInvoicePositionNr() throws ProcessingException {
    return QDL.nextVal("invoice_position_invoice_position_nr_seq");
  }

  public static Long createDocumentNr() throws ProcessingException {
    return QDL.nextVal("document_document_nr_seq");
  }

  public static Long createInvoiceChangeNr() throws ProcessingException {
    return QDL.nextVal("invoice_change_invoice_change_nr_seq");
  }

  public static Long createTreatmentNr() throws ProcessingException {
    return QDL.nextVal("treatment_treatment_nr_seq");
  }
  public static Long createServiceNr() throws ProcessingException {
    return QDL.nextVal("service_service_nr_seq");
  }

  public static Long createServiceCategoryNr() throws ProcessingException {
    return QDL.nextVal("service_category_service_category_nr_seq");
  }

  public static Long createServiceUnitNr() throws ProcessingException {
    return QDL.nextVal("service_unit_service_unit_nr_seq");
  }

  public static Long createSubUnitNr() throws ProcessingException {
    return QDL.nextVal("sub_unit_sub_unit_nr_seq");
  }

  public static Long createLaboratoryFindingNr() throws ProcessingException {
    return QDL.nextVal("laboratory_finding_laboratory_finding_nr_seq");
  }

  public static Long createCityNr() throws ProcessingException {
    return QDL.nextVal("city_city_nr_seq");
  }

  public static Long createParameterNr() throws ProcessingException {
    return QDL.nextVal("parameter_parameter_nr_seq");
  }

  public static Long createAppointmentNr() throws ProcessingException {
    return QDL.nextVal("appointment_appointment_nr_seq");
  }

  public static Long createRoleNr() throws ProcessingException {
    return QDL.nextVal("role_role_nr_seq");
  }

  public static Long createServiceUnitProviderNr() throws ProcessingException {
    return QDL.nextVal("service_unit_provider_service_unit_provider_nr_seq");
  }

  public static Long createPackNr() throws ProcessingException {
    return QDL.nextVal("pack_pack_nr_seq");
  }

  public static Long createServicePackageNr() throws ProcessingException {
    return QDL.nextVal("SERVICE_PACKAGE_SERVICE_PACKAGE_NR_SEQ");
  }

  public static Long createPackStatusChangeNr() throws ProcessingException{
    return QDL.nextVal("pack_status_change_pack_status_change_nr_seq");
  }

  public static Long createBookmarkNr() {
    return QDL.nextVal("bookmark_bookmark_nr_seq");
  }

  public static Long createResourceNr() {
    return QDL.nextVal("resource_resource_nr_seq");
  }

  public static Long createIsAbvLogNr() {
    return QDL.nextVal("is_abv_log_is_abv_log_nr_seq");
  }

  public static Long createDrugReportNr() {
    return QDL.nextVal("drug_report_document_report_nr_seq");
  }

  public static Long getMaxSequenceNumber(Table table, Column<?> column) {
    return NumberUtility.nvl(QDL.select(max(column)).from(table).singleResult(Long.class), 0) + 1;
  }

}
