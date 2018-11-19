package com.thearc.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Slf4j
public class EncryptUtil {
    
    //Logger java.util.logging.Logger.getLogger(String name)

    //Type1
    
  //MD5
    public static String getMD5(String str){
        
        String rtnMD5 = "";
        log.info("str:"+str);
        log.info("str.getBytes():"+Arrays.toString(str.getBytes()));
        
        try {
            //MessageDigest 인스턴스 생성
            MessageDigest md = MessageDigest.getInstance("MD5");
            //해쉬값 업데이트
            md.update(str.getBytes());
            //해쉬값(다이제스트) 얻기
            byte byteData[] = md.digest();

            log.info("byteData[]:"+Arrays.toString(byteData));
            
            StringBuffer sb = new StringBuffer();
            
            //출력
            for(byte byteTmp : byteData) {
                sb.append(Integer.toString((byteTmp&0xff) + 0x100, 16).substring(1));
                
                int tmp1 = (byteTmp & 0xff);
                int tmp2 = ((byteTmp&0xff) + 0x100);
                
                System.out.println(byteTmp +" : "+ tmp1 +" : "+ tmp2 
                        +" : "+Integer.toString((byteTmp&0xff)+0x100, 16)
                        +" : "+(Integer.toString((byteTmp&0xff) + 0x100, 16).substring(1)));
                
                
                // byte 타입의 범위 : -128~127 ( -2^7 ~ 2^7-1 )
                //-129 + 256 = 127
                // 0x100 = 256
            }
            rtnMD5 = sb.toString();
        } catch (Exception e) {
            e.printStackTrace(); 
            rtnMD5 = null; 
        }
        return rtnMD5;
    }
    /* */
    
    //SHA-256    
    //이것을 쓸것이다.
    public static String getSHA256(String str) {
        String rtnSHA = "";
        
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
            sh.update(str.getBytes()); 
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer(); 
            
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            rtnSHA = sb.toString();
            
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace(); 
            rtnSHA = null; 
        }
        return rtnSHA;
    }
    

    //Type2. BigInteger를 이용하여 구현.
    
    public static String getEncryptMD5(String a_origin) throws UnsupportedEncodingException{
        String encryptedMD5 = "";
        MessageDigest md = null;
                
        try {
            md = MessageDigest.getInstance("MD5");
//            md.update(a_origin.getBytes());
            md.update(a_origin.getBytes(), 0, a_origin.getBytes().length);            
            byte byteData[] = md.digest();
            log.info("byteData[]:"+(Arrays.toString(byteData)));
            encryptedMD5 = new BigInteger(1, byteData).toString(16); 
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return encryptedMD5;
    }
        
    public static String getEncryptSHA256(String a_origin){
        String encryptedSHA256 = "";
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(a_origin.getBytes(), 0, a_origin.length());
            encryptedSHA256 = new BigInteger(1, md.digest()).toString(16); 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return encryptedSHA256;
    }
    /*   */
}//class