package cn.bxd.sip.bxd.util;

/**
 * @author haomeiling
 * @description
 * @vesion 1.0
 * @date 2018/12/28
 */

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证号码
 * 15位：6位地址码+6位出生年月日（900101代表1990年1月1日出生）+3位顺序码
 * 18位：6位地址码+8位出生年月日（19900101代表1990年1月1日出生）+3位顺序码+1位校验码
 * 地区码：
 * 1、 第一、二位表示省（自治区、直辖市、特别行政区）。
 * 2、 第三、四位表示市（地级市、自治州、盟及国家直辖市所属市辖区和县的汇总码）。
 *      其中，01-20，51-70表示省直辖市；21-50表示地区（自治州、盟）。
 * 3、 第五、六位表示县（市辖区、县级市、旗）。01-18表示市辖区或地区（自治州、盟）辖县级市；21-80表示县（旗）；81-99表示省直辖县级市。
 * 顺序码：
 * 顺序码奇数分给男性，偶数分给女性。
 * 校验码：
 * 作为尾号的校验码，是由号码编制单位按统一的公式计算出来的，
 * 如果某人的尾号是0-9，都不会出现X，但如果尾号是10，那么就得用X来代替，
 * 因为如果用10做尾号，那么此人的身份证就变成了19位，而19位的号码违反了国家标准，
 * 并且中国的计算机应用系统也不承认19位的身份证号码。
 * Ⅹ是罗马数字的10，用X来代替10，可以保证公民的身份证符合国家标准。
 * <p>
 */
public class IDCardUtils {

    /**
     * 通过身份证号码获取出生日期、性别、年龄
     *
     * @param certificateNo 身份证号码
     * @return 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     */
    public static Map<String, String> getBirAgeSex(String certificateNo) {
        String birthday = "";
        String age = "";
        String sexCode = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;

    }
}
