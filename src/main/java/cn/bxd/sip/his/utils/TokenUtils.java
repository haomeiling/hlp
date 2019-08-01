package cn.bxd.sip.his.utils;

import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class TokenUtils {

    public static int[] getIntArray(String s, String separator) {
        StringTokenizer tokens = new StringTokenizer(s, separator, false);
        int[] intArray = new int[tokens.countTokens()];

        for (int i = 0; tokens.hasMoreTokens(); i++) {
            intArray[i] = Integer.parseInt(tokens.nextToken());
        }

        return intArray;
    }

    public static long[] getLongArray(String s, String separator) {
        StringTokenizer tokens = new StringTokenizer(s, separator, false);
        long[] longArray = new long[tokens.countTokens()];

        for (int i = 0; tokens.hasMoreTokens(); i++) {
            longArray[i] = Long.parseLong(tokens.nextToken());
        }

        return longArray;
    }

    public static Long[] getLongObjectArray(String s, String separator) {
        StringTokenizer tokens = new StringTokenizer(s, separator, false);
        Long[] longArray = new Long[tokens.countTokens()];

        for (int i = 0; tokens.hasMoreTokens(); i++) {
            longArray[i] = Long.parseLong(tokens.nextToken());
        }

        return longArray;
    }

    public static String[] getStringArray(String s, String separator) {
        StringTokenizer tokens = new StringTokenizer(s, separator, false);
        String[] stringArray = new String[tokens.countTokens()];

        for (int i = 0; tokens.hasMoreTokens(); i++) {
            stringArray[i] = tokens.nextToken();
        }

        return stringArray;
    }

    public static String getTokenString(int[] array, String separator) {
        String result = "";
        if (array == null) return result;

        for (int i = 0; i < array.length; i++)
            if (i == array.length - 1)
                result += new Integer(array[i]).toString();
            else
                result += new Integer(array[i]).toString() + separator;
        return result;
    }

    public static String getTokenString(long[] array, String separator) {
        String result = "";
        if (array == null) return result;

        for (int i = 0; i < array.length; i++)
            if (i == array.length - 1)
                result += new Long(array[i]).toString();
            else
                result += new Long(array[i]).toString() + separator;
        return result;
    }

    public static String getTokenString(Long[] array, String separator) {
        String result = "";
        if (array == null) return result;

        for (int i = 0; i < array.length; i++)
            if (i == array.length - 1)
                result += array[i];
            else
                result += array[i] + separator;
        return result;
    }

    public static String getTokenString(String[] array, String separator) {
        String result = "";
        if (array == null) return result;

        for (int i = 0; i < array.length; i++)
            if (i == array.length - 1)
                result += array[i];
            else
                result += array[i] + separator;
        return result;
    }

    public static <T> String getTokenString(List<T> list, String separator) {
        String result = "";
        if (list == null) return result;

        for (int i = 0; i < list.size(); i++)
            if (i == list.size() - 1)
                result += list.get(i);
            else
                result += list.get(i) + separator;
        return result;
    }

    public static String getTokenStringByIntegerSet(Set<Integer> set, String separator) {
        String result = "";
        if (set == null) return result;

        Integer[] array = set.toArray(new Integer[0]);
        for (int i = 0; i < array.length; i++)
            if (i == array.length - 1)
                result += array[i];
            else
                result += array[i] + separator;
        return result;
    }

    public static String getTokenStringByLongSet(Set<Long> set, String separator) {
        String result = "";
        if (set == null) return result;

        Long[] array = set.toArray(new Long[0]);
        for (int i = 0; i < array.length; i++)
            if (i == array.length - 1)
                result += array[i];
            else
                result += array[i] + separator;
        return result;
    }

    public static String getTokenStringByStringSet(Set<String> set, String separator) {
        String result = "";
        if (set == null) return result;

        for (String s : set) result += (result.length() == 0 ? "" : separator) + s;
        return result;
    }
}
