package cn.bxd.sip.bxd.hispay.common;

import cn.bxd.sip.bxd.hispay.constant.CharacterConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Description:RSA验证数字签名
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:35
 */
public class RSA {
    /** 加锁对象 */
    public static Object lock = new Object();

    /** 验签对象 */
    private static RSA rsa;

    /*** 平台公钥 */
    private RSAPublicKey publicKey;

    /*** 本身私钥 */
    private RSAPrivateKey privateKey;

    /*** 日志对象 */
    protected static final Log LOG = LogFactory.getLog(RSA.class);

    /** 对外不可见 */
    private RSA() {
        loadKeyByFile();
    }

    /**
     * 获取单例对象
     *
     * @return
     * @throws
     */
    public static RSA getInstance() {
        if (rsa == null) {
            synchronized (lock) {
                if (rsa == null) {
                    rsa = new RSA();
                }
            }
        }
        return rsa;
    }

    /**
     * 获取数字签名
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public String sign(String content) throws UnsupportedEncodingException, Exception {
        return signBase64(this.privateKey, encodeBase64(content));
    }

    /**
     * 验签入口
     *
     * @param content
     * @param dsignContent
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public boolean verify(String content, String dsignContent) throws UnsupportedEncodingException, Exception {
        return this.verifyBase64(this.publicKey, encodeBase64(content), dsignContent);
    }

    /**
     * 公钥加密
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public String encrypt(String content) throws UnsupportedEncodingException, Exception {
        return rsa.encryptBase64(this.publicKey, encodeBase64(content));
    }

    /**
     * 私钥解密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public String decrypt(String content) throws Exception {
        return decodeBase64(rsa.decryptBase64(this.privateKey, content));
    }

    private void loadKeyByFile() {
        try {
            String thisClassPath = this.getClass().getResource("").getPath().substring(1);
            String filePath = File.separator + thisClassPath.substring(0, thisClassPath.lastIndexOf("classes"))
                    + "classes";
            // String privateFileName = filePath + File.separator + "key" +
            // File.separator + ConfigConstant.Constant.PRIVATE_FILE;
            // String publicFileName = filePath + File.separator + "key" +
            // File.separator + ConfigConstant.Constant.PUBLIC_FILE;
            String privateFileName = filePath + File.separator + "key" + File.separator + "rsa_private_key.pem";
            String publicFileName = filePath + File.separator + "key" + File.separator + "rsa_public_key.pem";
            // String privateFileName = filePath + File.separator + "key" +
            // File.separator + "rsa_private_key.pem";
            // String publicFileName = filePath + File.separator + "key" +
            // File.separator + "rsa_public_key.pem";
            this.loadPrivateKeyPKCS1(new FileInputStream(privateFileName));
            this.loadPublicKey(new FileInputStream(publicFileName));
        } catch (FileNotFoundException e) {
            LOG.error("未找到文件：", e);
        } catch (Exception e) {
            LOG.error("RSA-获取钥匙失败：", e);
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in
     *            公钥输入流
     * @throws Exception
     *             加载公钥时产生的异常
     */
    private void loadPublicKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    private void loadPublicKey(String publicKeyStr) throws Exception {
        byte[] buffer = Base64.decodeBase64(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 从文件中加载私钥PKCS#1
     *
     * @param in
     * @throws Exception
     */
    private void loadPrivateKeyPKCS1(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKeyPKCS1(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    private void loadPrivateKeyPKCS1(String privateKeyStr) throws Exception {
        // System.out.println("load-privateKey-PKCS#1");
        LOG.info("RSA--------------------privateKeyStr：" + privateKeyStr);
        byte[] buffer = Base64.decodeBase64(privateKeyStr);
        LOG.info("RSA--------------------buffer：" + buffer);
        RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure(
                (ASN1Sequence) ASN1Sequence.fromByteArray(buffer));
        RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(),
                asn1PrivKey.getPrivateExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(rsaPrivKeySpec);
    }

    /**
     * 从文件中加载私钥PKCS#8
     *
     * @param keyFileName
     *            私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    private void loadPrivateKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    private void loadPrivateKey(String privateKeyStr) throws Exception {
        // System.out.println("load-privateKey-PKCS#8");
        byte[] buffer = Base64.decodeBase64(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    /**
     * @Description: RAS-publicKey-加密
     * @author
     * @date 2014-11-1
     * @time 下午12:00:55
     * @param publicKey
     * @param plainTextDataBase64
     *            【明文数据-base64编码字符串】
     * @return 返回base64编码字符串
     * @throws Exception
     */
    private String encryptBase64(RSAPublicKey publicKey, String plainTextDataBase64) throws Exception {
        byte[] plainTextData = Base64.decodeBase64(plainTextDataBase64);
        byte[] enBytes = this.encrypt(publicKey, plainTextData);
        return Base64.encodeBase64String(enBytes);
    }

    /**
     * @Description: RAS-publicKey-加密
     * @author
     * @date 2014-11-1
     * @time 下午12:00:34
     * @param publicKey
     * @param plainTextData
     * @return 返回字节数组
     * @throws Exception
     */
    private byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] enBytes = cipher.doFinal(plainTextData);
            return enBytes;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("RSA-publicKey-加密异常");
        } catch (NoSuchPaddingException e) {
            throw new Exception("RSA-publicKey-加密异常");
        }
    }

    /**
     * @Description: RSA-privateKey-解密
     * @author
     * @date 2014-11-1
     * @time 下午12:01:57
     * @param privateKey
     * @param cipherTextDataBase64
     * @return 返回base64字符串
     * @throws Exception
     */
    private String decryptBase64(RSAPrivateKey privateKey, String cipherTextDataBase64) throws Exception {
        byte[] cipherTextData = Base64.decodeBase64(cipherTextDataBase64);
        byte[] deBytes = this.decrypt(privateKey, cipherTextData);
        return Base64.encodeBase64String(deBytes);
    }

    /**
     * @Description: RSA-privateKey-解密
     * @author
     * @date 2014-11-1
     * @time 下午12:01:31
     * @param privateKey
     * @param cipherTextData
     * @return 返回字节数组
     * @throws Exception
     */
    private byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherTextData) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] deBytes = cipher.doFinal(cipherTextData);
            return deBytes;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("RSA-privateKey-解密异常");
        } catch (NoSuchPaddingException e) {
            throw new Exception("RSA-privateKey-解密异常");
        }
    }

    /**
     * @Description: RSA-privateKey-签名
     * @author
     * @date 2014-11-1
     * @time 下午12:09:12
     * @param privateKey
     * @param contentBase64
     *            【验证签名原文-base64编码字符串】
     * @return 返回base64字符串
     * @throws Exception
     */
    private String signBase64(RSAPrivateKey privateKey, String contentBase64) throws Exception {
        byte[] content = Base64.decodeBase64(contentBase64);
        byte[] signResult = this.sign(privateKey, content);
        return Base64.encodeBase64String(signResult);
    }

    /**
     * @Description: RSA-privateKey-签名
     * @author
     * @date 2014-11-1
     * @time 下午12:07:39
     * @param privateKey
     * @param content
     * @return 返回字节数组
     * @throws Exception
     */
    private byte[] sign(RSAPrivateKey privateKey, byte[] content) throws Exception {
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(content);
            byte[] signResult = signature.sign();
            return signResult;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("RSA-privateKey-签名异常");
        }
    }

    /**
     * @Description: RSA-publicKey-验证签名
     * @author
     * @date 2014-11-1
     * @time 下午12:18:52
     * @param publicKey
     * @param contentBase64
     *            【签名原文-base64编码字符串】
     * @param signBase64
     *            【待验证签名-base64编码字符串】
     * @return 签名结果
     * @throws Exception
     */
    private boolean verifyBase64(RSAPublicKey publicKey, String contentBase64, String signBase64) throws Exception {
        byte[] content = Base64.decodeBase64(contentBase64);
        byte[] sign = Base64.decodeBase64(signBase64);
        return this.verify(publicKey, content, sign);
    }

    /**
     * @Description: RSA-publicKey-验证签名
     * @author
     * @date 2014-11-1
     * @time 下午12:15:52
     * @param publicKey
     * @param content
     *            【签名原文-字节数组】
     * @param sign
     *            【待验证签名-字节数组】
     * @return 签名结果
     * @throws Exception
     */
    private boolean verify(RSAPublicKey publicKey, byte[] content, byte[] sign) throws Exception {
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKey);
            signature.update(content);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("RSA-publicKey-验证签名异常");
        }
    }

    /**
     * 转成base64编码
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    private String encodeBase64(String content) throws UnsupportedEncodingException {
        return Base64.encodeBase64String(content.getBytes(CharacterConstant.GBK));
    }

    /**
     * 转成base64编码
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    private String decodeBase64(String content) throws UnsupportedEncodingException {
        return new String(Base64.decodeBase64(content));
    }

    public static void main(String[] args) throws Exception {
        RSA rsa = RSA.getInstance();

        // 验签
        String orgStr = "111121212的";
        String dsignContent = rsa.sign(orgStr);
        System.out.println("dsignContent：" + dsignContent);
        boolean diff = rsa.verify(orgStr, dsignContent);
        System.out.println("diff:" + diff);

        // //加密解密
        String miwen = rsa.encrypt(orgStr);
        System.out.println("miwen:" + miwen);
        String mingwen = rsa.decrypt(miwen);
        System.out.println("mingwen:" + mingwen);
    }
    /*
     * org.bouncycastle.asn1.pkcs.RSAPrivateKey rsaPrivateKey =
     * org.bouncycastle.asn1.pkcs.RSAPrivateKey.getInstance((ASN1Sequence)
     * ASN1Sequence.fromByteArray(buffer)); RSAPrivateKeySpec rsaPrivKeySpec =
     * new RSAPrivateKeySpec(rsaPrivateKey.getModulus(),
     * rsaPrivateKey.getPrivateExponent());
     */
}
