package com.thearc.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JDBCEncryptor {
    public static void main(String[] args) {
        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
            //패스워드 넣어주고 써야함(필수)
            enc.setPassword("rktwlsrud");

            System.out.println(enc.encrypt("jdbc:log4jdbc:mysql://jeongwon.me/thearc2"));


//        enc.setPassword("rktwlsrud");
//        System.out.println(enc.encrypt("C:\\zzz\\upload"));
//        System.out.println(enc.encrypt("ekflrktmaajfl"));
//        System.out.println(enc.encrypt(""));
    }
}
