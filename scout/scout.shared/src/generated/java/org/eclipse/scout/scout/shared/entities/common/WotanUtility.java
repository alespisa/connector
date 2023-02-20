package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ExceptionHandler;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.resource.MimeType;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.scout.shared.code.StatusCodeType;
import org.eclipse.scout.scout.shared.parameter.PARAMETERS;
import org.eclipse.scout.scout.shared.parameter.RoundToParameter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WotanUtility {

  public static final String PDF_MIMETYPE = "application/pdf";
  public static final long ACTIVE = StatusCodeType.ActiveCode.ID;
  public static final String ACTIVE_STRING = "101";

  private static final Pattern POSITIVE_INTEGER_IN_BEGINNING = Pattern.compile("^\\d+");
  private static final Pattern POSITIVE_INTEGER_IN_END = Pattern.compile("\\d+$");

  public static boolean isActive(Long statusUid) {
    return WotanUtility.ACTIVE == NumberUtility.nvl(statusUid, 0);
  }

  public static boolean notEmpty(String string) {
    if (string == null) {
      return false;
    }
    return !string.trim().isEmpty();
  }

  public static boolean isLinux() {
    return System.getProperty("os.name").startsWith("Linux");
  }

  public static String getMimeType(BinaryResource resource) {
    String mimeType = "";
    try {
      mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(resource.getContent()));
    }
    catch (IOException e) {
      BEANS.get(ExceptionHandler.class).handle(e);
    }
    // guessContentTypeFromStream might return null
    if (mimeType == null || mimeType.isEmpty()) {
      mimeType = "";
      MimeType m = MimeType.findByFileExtension(getFileExtension(resource.getFilename()));
      if (m != null) {
        mimeType = m.getType();
      }
    }
    if (mimeType.isEmpty()) {
      mimeType = resource.getContentType();
    }
    return mimeType;
  }

  public static String getFileExtension(String filename) {
    String extension = "";
    int i = filename.lastIndexOf('.');
    if (i > 0) {
      extension = filename.substring(i + 1);
    }
    return extension;
  }

  /**
   * Finds a positive integer in the beginning or at the end of a string.<br />
   * <br/>
   * Returns an entry of the integer and the rest of the string.<br />
   * If no integer is found it returns defaultValue and the whole string.<br />
   * <br/>
   * If there is a integer in the beginning and the end, only consider the number in the beginning.<br/>
   */
  public static Map.Entry<Integer, String> extractNumberFromString(String input, int defaultValue) {
    if (input == null) {
      return new AbstractMap.SimpleEntry<Integer, String>(defaultValue, input);
    }
    Matcher piib = POSITIVE_INTEGER_IN_BEGINNING.matcher(input);
    if (piib.find()) {
      int integer = Integer.parseInt(piib.group());
      String rest = piib.replaceAll("").trim();
      return new AbstractMap.SimpleEntry<Integer, String>(integer, rest);
    }
    Matcher piie = POSITIVE_INTEGER_IN_END.matcher(input);
    if (piie.find()) {
      int integer = Integer.parseInt(piie.group());
      String rest = piie.replaceAll("").trim();
      return new AbstractMap.SimpleEntry<Integer, String>(integer, rest);
    }
    return new AbstractMap.SimpleEntry<Integer, String>(defaultValue, input);
  }

  public static String removeTrailigAsterisk(String s) {
    if (s == null) {
      return s;
    }
    return s.replaceAll("\\*$", "");
  }

  public static String toCamelCaseFirstLower(String s) {
    String camelCase = toCamelCase(s);
    return camelCase.substring(0, 1).toLowerCase() +
      camelCase.substring(1);
  }

  public static String toCamelCase(String s) {
    String[] parts = s.split("_");
    String camelCaseString = "";
    for (String part : parts) {
      camelCaseString = camelCaseString + toProperCase(part);
    }
    return camelCaseString;
  }

  public static String toProperCase(String s) {
    return s.substring(0, 1).toUpperCase() +
      s.substring(1).toLowerCase();
  }

  public static byte[] toPrimitiveByteArray(Byte[] objectBytes) {
    if (objectBytes == null) {
      return null;
    }
    byte[] bytes = new byte[objectBytes.length];
    for (int i = 0; i < objectBytes.length; i++) {
      bytes[i] = objectBytes[i];
    }
    return bytes;
  }

  public static Byte[] toObjectByteArray(byte[] primitiveBytes) {
    if (primitiveBytes == null) {
      return null;
    }
    Byte[] bytes = new Byte[primitiveBytes.length];
    int i = 0;
    for (byte b : primitiveBytes) {
      bytes[i++] = b;
    }
    return bytes;
  }

  public static Long zeroIfNegative(Long number) {
    if (number < 0) {
      return 0L;
    }
    return number;
  }

  public static BigDecimal zeroIfNegative(BigDecimal number) {
    if (number.compareTo(BigDecimal.ZERO) == -1) {
      return BigDecimal.ZERO;
    }
    return number;
  }

  /**
   *
   * @param bigDecimal1
   * @param bigDecimal2
   * @return returns true if bigDecimal1 is lower than (<) bigDecimal2 <br>
   *         returns false if bigDecimal1 is greater than or equals to (>=) bigDecimal2
   */
  public static boolean bigDecimalLT(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
    return bigDecimal1.compareTo(bigDecimal2) < 0;
  }

  /**
   *
   * @param bigDecimal1
   * @param bigDecimal2
   * @return returns true if bigDecimal1 is lower than or equals to (<=) bigDecimal2 <br>
   *         returns false if bigDecimal1 is greater than (>) bigDecimal2
   */
  public static boolean bigDecimalLTE(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
    return bigDecimal1.compareTo(bigDecimal2) <= 0;
  }

  /**
   *
   * @param bigDecimal1
   * @param bigDecimal2
   * @return returns true if bigDecimal1 is greater than or equals to (>=) bigDecimal2 <br>
   *         returns false if bigDecimal1 is lower than (<) bigDecimal2
   */
  public static boolean bigDecimalGTE(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
    return bigDecimal1.compareTo(bigDecimal2) >= 0;
  }

  /**
   *
   * @param bigDecimal1
   * @param bigDecimal2
   * @return returns true if bigDecimal1 is greater than (>) bigDecimal2 <br>
   *         returns false if bigDecimal1 is lower than or equals to (<=) bigDecimal2
   */
  public static boolean bigDecimalGT(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
    return bigDecimal1.compareTo(bigDecimal2) > 0;
  }

  /**
   *
   * @param bigDecimal1
   * @param bigDecimal2
   * return returns true if bigDecimal1 is equal to bigDecimal2
   */
  public static boolean bigDecimalEQ(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
    return bigDecimal1.compareTo(bigDecimal2) == 0;
  }

  /**
   *
   * @param long1
   * @param long2
   * @return returns true if long1 is lower than (<) long2 <br>
   *         returns false if long1 is greater than or equals to (>=) long2
   */
  public static boolean longLT(Long long1, Long long2) {
    return long1.compareTo(long2) < 0;
  }

  /**
   *
   * @param long1
   * @param long2
   * @return returns true if long1 is lower than or equals to (<=) long2 <br>
   *         returns false if long1 is greater than (>) long2
   */
  public static boolean longLTE(Long long1, Long long2) {
    return long1.compareTo(long2) <= 0;
  }

  /**
   *
   * @param long1
   * @param long2
   * @return returns true if long1 is greater than or equals to (>=) long2 <br>
   *         returns false if long1 is lower than (<) long2
   */
  public static boolean longGTE(Long long1, Long long2) {
    return long1.compareTo(long2) >= 0;
  }

  /**
   *
   * @param long1
   * @param long2
   * @return returns true if long1 is greater than (>) long2 <br>
   *         returns false if long1 is lower than or equals to (<=) long2
   */
  public static boolean longGT(Long long1, Long long2) {
    return long1.compareTo(long2) > 0;
  }

  /**
   *
   * @param long1
   * @param long2
   * @return returns true if long1 is equal to long2
   */
  public static boolean longEq(Long long1, Long long2) {
    return long1.compareTo(long2) == 0;
  }

  @SafeVarargs
  public static <T> List<T> flatten(Collection<T>... collections) {
    if (collections == null || collections.length < 1) {
      return CollectionUtility.emptyArrayList();
    }
    List<T> result = new ArrayList<>();
    for (Collection<? extends T> c : collections) {
      if (c == null || c.isEmpty()) {
        continue;
      }
      result.addAll(CollectionUtility.arrayListWithoutNullElements(c));
    }
    return result;
  }

  public static Date addCalendarDay(Date d) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(d);
    calendar.add(Calendar.DAY_OF_YEAR, 1);

    // Clear time components
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static boolean longNotNullOrZero(Long number) {
    return number != null && number != 0L;
  }

  /**
   * Rounds the value using the configurable RoundTo-parameter
   * @link {RoundToParameter.class}
   * @param price
   */
  public static BigDecimal round(BigDecimal price) {
    if(price == null) {
      return price;
    }
    BigDecimal roundToParameter = (BigDecimal) PARAMETERS.get(RoundToParameter.class).getValue();
    Double roundHelper = 1 / roundToParameter.doubleValue();
    Double finalPriceDouble = Math.round(price.doubleValue() * (int) roundHelper.doubleValue()) / roundHelper;
    BigDecimal finalPrice = new BigDecimal(finalPriceDouble);

    return finalPrice;
  }

  /**
   * Returns the base string repeated as many times, as given in count
   * @param base
   * @param count
   * @return
   */
  public static String repeatString(String base, int count){
    String returnValue = "";
    for(int i = 0; i < count; i++){
      returnValue = returnValue.concat(base);
    }
    return returnValue;
  }

  /**
   * Fills a given string up to the given length with zeros.
   *
   * The fillLeft parameter declares, if the zeros are appended in front
   * or in the back of the string
   *
   * @param string to fill up
   * @param length to be filled with zeros
   * @param fillLeft append zeros before (true) or behind (false) the given string
   * @return
   */
  public static String fillUpWithChar(String string, int length, boolean fillLeft, String charToFillIn) throws NullPointerException {
    if(string == null || string.isEmpty()) {
      throw new NullPointerException();
    }
    String filled = string;
    if(fillLeft) {
      filled = filled.length() > length ? filled.substring(0, length) : "00000000".substring(0, length-filled.length()).concat(filled);

    } else {
      filled = filled.length() > length ? filled.substring(string.length()-length) : string.concat(WotanUtility.repeatString(charToFillIn, length-string.length()));
    }
    return filled;
  }

}
