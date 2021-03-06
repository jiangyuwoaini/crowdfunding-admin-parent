package com.lblz.crowd.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lblz
 * @deacription 加密工具类(有md5加密和3-DES加密)
 * @date 2021/10/31 7:38
 **/
public class EndecryptUtils {

    private static final String key = "lblz";

    private static final Logger logger = LoggerFactory.getLogger(EndecryptUtils.class);

    /**
     * 进行MD5加密
     *
     * @param strSrc
     *            原始的SPKEY
     * @return byte[] 指定加密方式为md5后的byte[]
     */

    private static byte[] md5(String strSrc) {

        byte[] returnByte = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            returnByte = md5.digest(strSrc.getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return returnByte;
    }

    /**
     * 获取md5加密串
     *
     * @param str
     * @return
     */
    public static String getMd5(String str) {
        return Hex.encodeHexString(md5(str));
    }

    /**
     * 得到3-DES的密钥匙 根据接口规范，密钥匙为24个字节，md5加密出来的是16个字节，因此后面补8个字节的0
     *
     * @param spKey
     *            原始的SPKEY
     * @return byte[] 指定加密方式为md5后的byte[]
     */

    private static byte[] getEnKey(String spKey) {
        byte[] desKey = null;
        try {
            byte[] desKey1 = md5(spKey);
            desKey = new byte[24];
            int i = 0;
            while (i < desKey1.length && i < 24) {
                desKey[i] = desKey1[i];
                i++;
            }
            if (i < 24) {
                desKey[i] = 0;
                i++;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }

        return desKey;
    }

    /**
     * 3-DES加密
     *
     * @param src src 要进行3-DES加密的byte[]
     * @param enKey 3-DES加密密钥
     * @return byte[] 3-DES加密后的byte[]
     */

    public static byte[] Encrypt(byte[] src, byte[] enKey) {
        byte[] encryptedData = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(enKey);
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("DESede");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedData = cipher.doFinal(src);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return encryptedData;
    }

    /**
     * 对字符串进行Base64编码
     * @param src 要进行编码的字符
     * @return String 进行编码后的字符串
     */

    public static String getBase64Encode(byte[] src) {
        String requestValue = "";
        try {
            requestValue = Base64.encodeBase64String(src);
            // System.out.println(requestValue);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }

        return requestValue;
    }

    /**
     * 对Base64编码字符串进行解码
     *
     * @param base64String 要进行解码的字符
     *
     * @return String 解码后的字符串
     */

    public static String getBase64Decode(String base64String) {
        String requestValue = "";
        try {
            byte [] byteArray = Base64.decodeBase64(base64String);
            requestValue = new String(byteArray);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }

        return requestValue;
    }

    /**
     * 去掉字符串的换行符号 base64编码3-DES的数据时，得到的字符串有换行符号 ，一定要去掉，否则uni-wise平台解析票根不会成功，
     * 提示“sp验证失败”。在开发的过程中，因为这个问题让我束手无策， 一个朋友告诉我可以问联通要一段加密后 的文字，然后去和自己生成的字符串比较，
     * 这是个不错的调试方法。我最后比较发现我生成的字符串唯一不同的 是多了换行。 我用c#语言也写了票根请求程序，没有发现这个问题。
     *
     */

    private static String filter(String str) {
        String output = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13) {
                sb.append(str.subSequence(i, i + 1));
            }
        }
        output = new String(sb);
        return output;
    }

    /**
     * 对字符串进行URLDecoder.encode(strEncoding)编码
     * @param src 要进行编码的字符串
     * @return String 进行编码后的字符串
     */
    public static String getURLEncode(String src) {
        String requestValue = "";
        try {
            requestValue = URLEncoder.encode(src, "UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }

        return requestValue;
    }

    /**
     * 3-DES加密
     *
     * @param src 要进行3-DES加密的String
     * @param spkey 分配的SPKEY
     * @return String 3-DES加密后的String
     */
    public static String get3DESEncrypt(String src, String spkey) {
        if(Objects.isNull(spkey)) {
            spkey = EndecryptUtils.key;
        }
        String requestValue = "";
        try {
            // 得到3-DES的密钥匙
            byte[] enKey = getEnKey(spkey);
            // 要进行3-DES加密的内容在进行/"UTF-16LE/"取字节
            byte[] src2 = src.getBytes("UTF-16LE");
            // 进行3-DES加密后的内容的字节
            byte[] encryptedData = Encrypt(src2, enKey);
            // 进行3-DES加密后的内容进行BASE64编码
            String base64String = getBase64Encode(encryptedData);
            // BASE64编码去除换行符后
            String base64Encrypt = filter(base64String);
            // 对BASE64编码中的HTML控制码进行转义的过程
            requestValue = getURLEncode(base64Encrypt);
            // System.out.println(requestValue);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return requestValue;
    }

    /**
     * 对字符串进行URLDecoder.decode(strEncoding)解码
     *
     * @param src 要进行解码的字符串
     * @return String 进行解码后的字符串
     */
    public static String getURLDecoderdecode(String src) {
        String requestValue = "";
        try {
            requestValue = URLDecoder.decode(src, "UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return requestValue;
    }

    /**
     * 进行3-DES解密（密钥匙等同于加密的密钥匙）。
     * @param debase64 要进行3-DES解密byte[]
     * @param spKey 分配的SPKEY
     * @return String 3-DES解密后的String
     */
    public static String deCrypt(byte[] debase64, String spKey) {
        String strDe = null;
        Cipher cipher = null;
        if(Objects.isNull(spKey)){
            spKey = EndecryptUtils.key;
        }
        try {
            cipher = Cipher.getInstance("DESede");
            byte[] key = getEnKey(spKey);
            DESedeKeySpec dks = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("DESede");
            SecretKey sKey = keyFactory.generateSecret(dks);
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            byte ciphertext[] = cipher.doFinal(debase64);
            strDe = new String(ciphertext, "UTF-16LE");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return strDe;
    }

    /**
     * 3-DES解密
     * @param src 要进行3-DES解密的String
     * @param spkey 分配的SPKEY
     * @return String 3-DES加密后的String
     */

    public static String get3DESDecrypt(String src, String spkey) {
        String requestValue = "";
        if(Objects.isNull(spkey)){
            spkey = EndecryptUtils.key;
        }
        try {
            // 得到3-DES的密钥匙
            // URLDecoder.decodeTML控制码进行转义的过程
            String URLValue = getURLDecoderdecode(src);
            // 进行3-DES加密后的内容进行BASE64编码
            byte[] base64DValue = Base64.decodeBase64(URLValue);
            // 要进行3-DES加密的内容在进行/"UTF-16LE/"取字节
            requestValue = deCrypt(base64DValue, spkey);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return requestValue;
    }

    public static void main(String[] args) {
        // String oldString = "1";

        /*
         * String SPKEY = EndecryptUtils.key;
         * System.out.println("1。分配的SPKEY为:  " + SPKEY);
         * System.out.println("2。的内容为:  " + oldString); String reValue =
         * EndecryptUtils.get3DESEncrypt(oldString, SPKEY); reValue =
         * reValue.trim().intern(); System.out.println("进行3-DES加密后的内容: " +
         * reValue); String reValue2 = EndecryptUtils.get3DESDecrypt(reValue,
         * SPKEY); System.out.println("进行3-DES解密后的内容: " + reValue2);
         */
//        String get3desDecrypt = EndecryptUtils.get3DESEncrypt("abc123456", EndecryptUtils.key);
//        System.out.println(get3desDecrypt);
//        System.out.println(EndecryptUtils.getMd5("abc123456"));
        System.out.println(EndecryptUtils.get3DESEncrypt("123456789",null));
        System.out.println(EndecryptUtils.get3DESDecrypt("Z04%2Bf8S6ob84Z%2F49ocoOh8LYli%2BGbdDi",null));
    }

}
