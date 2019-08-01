package cn.bxd.sip.bxd.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {


    public static String MD5Digest(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(str.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static boolean checkPassword(String password) {
        Pattern charPattern = Pattern.compile("[A-Za-z]+");
        Pattern numPattern = Pattern.compile("[0-9]+");
        Pattern notCharPatter = Pattern.compile("[^A-Za-z0-9]+");

        return password.length() >= 8 && charPattern.matcher(password).find()
                && numPattern.matcher(password).find()
                && notCharPatter.matcher(password).find();
    }

    public static String html(String string) {
        if ((string == null || string.length() < 1)) return "";

        string = Utils.replaceAll(string, "<", "&lt;");
        string = Utils.replaceAll(string, ">", "&gt;");
        string = Utils.replaceAll(string, "\"", "&quot;");
        string = Utils.replaceAll(string, " ", "&nbsp;");
        string = string.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        string = string.replaceAll("\r|\n", "<br>");
        string = Utils.replaceAll(string, "\\", "&#92;");
//      string = replaceAll(string, "$", "&#36");
        return string;
    }

    public static String filterFileFolderName(String str) {
        String fileName = "";
        if (str != null) {
            Pattern p = Pattern.compile("([\t]|[\r]|[\n]|[\\|]|[\\\\]|[?])*");
            Matcher m = p.matcher(str);
            fileName = m.replaceAll("");
        }
        return fileName;
    }

    @Deprecated
    public static String insertChar(String s) {
        String seekString = "\\\"'";

        for (int n = 0; n < seekString.length(); n++) {
            int i, indexStart = 0;
            char seekChar = seekString.charAt(n);

            while ((i = s.indexOf(seekChar, indexStart)) >= 0) {
                s = s.substring(0, i) + "\\" + s.substring(i, s.length());
                indexStart = i + 2;
            }
        }

        return s;
    }

    public static boolean match(String input, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.find();
    }


    private static class Utils {

        public static String replaceAll(String string, String oldSubstring, String newSubstring) {
            //Local Variables
            String result = string;

            if ((string == null || string.length() < 1)) return string;

            if ((result.indexOf(oldSubstring) > -1)
                    && (oldSubstring.length() > 0)
                    && (!oldSubstring.equals(newSubstring))) {
                while (result.indexOf(oldSubstring) > -1) {
                    result = replacefirst(result, oldSubstring, newSubstring);
                }
            }

            return result;
        }

        public static String replacefirst(String string, String oldSubstring, String newSubstring) {
            String result = string;

            if ((string != null) && (string.length() > 0)
                    && (oldSubstring != null) && (oldSubstring.length() > 0)
                    && (newSubstring != null)) {
                int pos = string.indexOf(oldSubstring);
                if (pos > -1)
                    result = string.substring(0, pos)
                            + newSubstring
                            + string.substring(pos + oldSubstring.length());
            }

            return result;
        }
    }
}
