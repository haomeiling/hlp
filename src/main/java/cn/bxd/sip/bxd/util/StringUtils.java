package cn.bxd.sip.bxd.util;

import java.util.*;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-12-25
 * Time: 14:12
 */
public class StringUtils {
    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    /**
     * 报告详情处理
     * 序号|项目名称|检查结果|检验状态(↑,↓等)|结果单位|参考值|特殊描述^
     * 1|血糖|偏高|↑|mmol/L|5.0|糖尿病^……
     * @param inStr
     * lisheng 2019/4/24
     * @return
     */
    public static TreeSet reportItemsUtils(String inStr) {
        String[] array = inStr.split("\\^");
        int length = array.length;
        String[] resultArray = new String[length];
        for (int i = 0; i < length; i++) {
            if (array[i].contains("/L") && !array[i].contains("g/L")) {
                resultArray[i - 1] = array[i - 1] + "^" + array[i];
            } else {
                resultArray[i] = array[i];
            }
        }
        TreeSet treeSet = new TreeSet<String>();
        for (String s : resultArray) {
            if (s != null && !s.equals("")) {
                treeSet.add(s);
            }
        }
        return treeSet;
    }

    public static void main(String[] args) {
        String inStr = "1|嗜碱细胞绝对值|0.02|正常||10^9/L|0--1^2|嗜碱细胞百分比|0.3|正常||%|0--1^3|嗜酸细胞绝对值|0.57|高|↑|10^9/L|0.02--0.5^4|嗜酸细胞百分比|7.8|高|↑|%|0.5--5^5|红细胞压积|39.5|正常||%|38--50.8^6|*血红蛋白|129.0|低|↓|g/L|131--172^7|淋巴细胞绝值|3.04|正常||10^9/L|0.8--4^8|淋巴细胞百分比|41.70|高|↑|%|20--40^9|平均红细胞血红蛋白含量|25.3|低|↓|pg|27.8--33.8^10|平均红细胞血红蛋白浓度|327.0|正常||g/L|320--355^11|平均红细胞体积|77.50|低|↓|fL|83.9--99.1^12|单核细胞绝对值|0.65|正常||10^9/L|0.12--1^13|单核细胞百分比|8.90|正常||%|3--10^14|血小板平均体积|9.3|正常||fL|7.6--13.2^15|中性粒细胞绝对值|3.01|正常||10^9/L|2--7^16|中性粒细胞百分比|41.3|低|↓|%|46--64^17|大血小板比率|18.3|正常||%|13--43^18|血小板比积|0.36|高|↑|%|0.11--0.28^19|血小板分布宽度|9.4|低|↓|fL|10.5--18.1^20|*血小板|381.0|高|↑|10^9/L|85--303^21|*红细胞|5.10|正常||10^12/L|4.09--5.74^22|红细胞分布宽度-CV|12.6|正常||%|10.9--15.4^23|红细胞分布宽度-SD|34.8|低|↓|fL|37.1--45.7^24|*白细胞|7.29|正常||10^9/L|3.97--9.15";
        String[] array = inStr.split("\\^");
        int length = array.length;
        String[] resultArray = new String[length];

        for (int i = 0; i < length; i++) {
            if (array[i].contains("/L")&&!array[i].contains("g/L")) {
                resultArray[i - 1] = array[i - 1] + "^" + array[i];
            } else {
                resultArray[i] = array[i];
            }
        }

        TreeSet treeSet = new TreeSet<String>();
        for (String s : resultArray) {
            if (s != null && !s.equals("")) {
                treeSet.add(s);
            }
        }

        // 遍历TreeSet
        for (Iterator iter = treeSet.iterator(); iter.hasNext(); ) {
            System.out.printf("iter : %s\n", iter.next());
        }


    }
}
