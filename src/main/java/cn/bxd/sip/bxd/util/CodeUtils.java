package cn.bxd.sip.bxd.util;

import java.util.Random;

/**
 * @author : cRyann
 * @create 2018-09-18
 **/
public class CodeUtils {
    public static String getVerification(int digit) {
        String verification = "";
        for (int i = 0; i < digit; i++) {
            Random random = new Random();
            int v = random.nextInt(10);
            verification += v;
        }
        return verification;
    }
}
