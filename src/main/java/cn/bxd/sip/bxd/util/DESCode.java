package cn.bxd.sip.bxd.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @Author haomeiling
 * @Date 2017/2/28
 */
public class DESCode {
    public static final String ALGORITHM = "DES";/**这么写会自动补位*/
//    public static final String ALGORITHM = "DES/ECB/NoPadding";


    /**
     *   加密算法
     * @param ming
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String ming, String key) throws Exception {
        //对key进行base64编码
        byte[] byteArray = (new BASE64Decoder()).decodeBuffer(key);
        DESKeySpec dks = new DESKeySpec(byteArray);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey k = keyFactory.generateSecret(dks);
        //加密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        byte[] finalByte=cipher.doFinal(ming.getBytes());
        //对结果进行base64编码，然后返回
        return new String(Base64.encodeBase64(finalByte));
    }



    /**
     * 解密算法
     * @param mi
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String mi, String key) throws Exception {
        //对数据进行base64解码
        byte[] data= Base64.decodeBase64(mi.getBytes());

        //对key进行base64编码
        byte[] byteArray = (new BASE64Decoder()).decodeBuffer(key);
        DESKeySpec dks = new DESKeySpec(byteArray);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey k = keyFactory.generateSecret(dks);

        //解码
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        byte[] finalByte=cipher.doFinal(data);

        return new String(finalByte);
    }

}
