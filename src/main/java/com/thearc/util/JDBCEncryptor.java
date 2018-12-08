package com.thearc.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JDBCEncryptor {
    public static void main(String[] args) {
        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
            //패스워드 넣어주고 써야함(필수)
            enc.setPassword("rktwlsrud");

//            System.out.println(enc.encrypt("jdbc:log4jdbc:mysql://jeongwon.me/thearc2"));
            System.out.println(enc.encrypt("755792594137-ds9engajnsjvip5mvpetccoql5568af9.apps.googleusercontent.com"));
            System.out.println(enc.encrypt("aP8wZE8SuNf6A1Q43qrKeT6U"));
            System.out.println(enc.encrypt("755792594137-44ffp2ghof9gkjt3ua8b6a797n5v6dop.apps.googleusercontent.com"));
            System.out.println(enc.encrypt("moIgU14cGNtS9TcTfXOymJpp"));


//        enc.setPassword("rktwlsrud");
//        System.out.println(enc.encrypt("C:\\zzz\\upload"));
//        System.out.println(enc.encrypt("ekflrktmaajfl"));
//        System.out.println(enc.encrypt(""));
    }
}
