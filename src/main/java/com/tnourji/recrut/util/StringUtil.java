package com.tnourji.recrut.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * String utilities
 * 
 * @author Michael Tnourji
 *         
 */
public final class StringUtil {
//    
//    private static final RegExp AMP_RE = RegExp.compile("&", "g");
//    
//    private static final RegExp GT_RE = RegExp.compile(">", "g");
//    
//    private static final RegExp LT_RE = RegExp.compile("<", "g");
//    
//    private static final RegExp SQUOT_RE = RegExp.compile("\'", "g");
//    
//    private static final RegExp QUOT_RE = RegExp.compile("\"", "g");
    
    private static final String EMPTY_STRING = "";
    
    public static final String VALUE_DASH = " - ";
    
    public static final String ENCODE_UTF8 = "UTF-8";
    
    private static final Random RANDOM = new Random();
    
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);
    
    /***
     * private constructor for final classes
     */
    private StringUtil() {
    }
    
    /***
     * replace accents with normal letters
     * 
     * @param string
     *            string to replace
     * @return replaced string
     */
    public static String stripAccents(String string) {
        String s = Normalizer.normalize(string, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").trim();
        return s;
    }
    
    
    /***
     * Transform the given string to an URLEncoded string. e.g : > will be converted to %3C, spaces to %20
     * 
     * @param toEncode
     *            string to encode
     * @return encoded string
     */
    public static String encodeHTML(String toEncode) {
        String encoded;
        try {
            encoded = URLEncoder.encode(toEncode, ENCODE_UTF8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Couldn't parse to UTF-8", e);
            encoded = toEncode.replaceAll(" ", "+").replaceAll("&", " and ");
        }
        return encoded;
    }
    
    /**
     * HTML-escapes a string.
     *
     * Note: The following variants of this function were profiled on FF36, Chrome6, IE8:
     * <ol>
     * <li>For each case, check indexOf, then use s.replace(regex, string)</li>
     * <li>For each case, check indexOf, then use s.replaceAll()</li>
     * <li>Check if any metachar is present using a regex, then use #1</li>
     * <li>For each case, use s.replace(regex, string)</li>
     * </ol>
     *
     * #1 was found to be the fastest, and is used below.
     *
     * @param s
     *            the string to be escaped
     * @return the input string, with all occurrences of HTML meta-characters replaced with their corresponding HTML
     *         Entity References
     */
//    public static String htmlEscape(String s) {
//        if (s.indexOf("&") != -1) {
//            s = AMP_RE.replace(s, "&amp;");
//        }
//        if (s.indexOf("<") != -1) {
//            s = LT_RE.replace(s, "&lt;");
//        }
//        if (s.indexOf(">") != -1) {
//            s = GT_RE.replace(s, "&gt;");
//        }
//        if (s.indexOf("\"") != -1) {
//            s = QUOT_RE.replace(s, "&quot;");
//        }
//        if (s.indexOf("'") != -1) {
//            s = SQUOT_RE.replace(s, "&#39;");
//        }
//        return s;
//    }
    
    /***
     * convert a BigDecimal to String return empty string for null values
     * 
     * @param bgValue
     *            BigDecimal value
     * @return String representation
     */
    public static String toSafeString(BigDecimal bgValue) {
        return null == bgValue ? EMPTY_STRING : bgValue.toString();
    }
    
    /***
     * convert a String to a non null String return empty string for null values
     * 
     * @param stringValue
     *            String value
     * @return String representation
     */
    public static String toSafeString(String stringValue) {
        return null == stringValue ? EMPTY_STRING : stringValue;
    }
    
    /***
     * convert a String to a non null String return empty string for null values
     * 
     * @param longValue
     *            BigDecimal value
     * @return String representation
     */
    public static String toSafeString(Long longValue) {
        return null == longValue ? EMPTY_STRING : longValue.toString();
    }
    
    /***
     * check whether a value other than null/""/- is filled in
     * 
     * @param stringValue
     *            string value to test
     * @return true if not empty
     */
    public static boolean hasValue(String stringValue) {
        return !StringUtils.isEmpty(stringValue) && !VALUE_DASH.equals(stringValue);
    }
    
    /***
     * return the text or its first maxChars if too long
     * 
     * @param text
     *            text
     * @param maxChars
     *            number of char MAX
     * @return string with max "maxChars" characters
     */
    public static String getFirstNCharacters(final String text, final int maxChars) {
        Validate.notNull(text, "The text to retrieve must not be null");
        return text.substring(0, Math.min(text.length(), maxChars));
    }
    
    /****
     * get string from input stream
     * 
     * @param is
     *            input stream
     * @return string
     */
    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        
        String line;
        try {
            
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error("couldn't close BufferedReader", e);
                }
            }
        }
        return sb.toString();
    }
    
    /***
     * get token
     * 
     * @param length
     *            length of the string to generate
     * @return random string
     */
    public static String getRandomString(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return token.toString();
    }
}
