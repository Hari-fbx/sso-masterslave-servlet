package com.csvmodifer;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Hashing {
    public static String checksum(MessageDigest digest, Path file) throws IOException {
        try {
            FileInputStream nFileInputStream = new FileInputStream(file.toFile());
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;
            while ((bytesCount = nFileInputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            nFileInputStream.close();
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
    public static BigInteger checkSum(MessageDigest digest, Path file) throws IOException {
        try {
            FileInputStream nFileInputStream = new FileInputStream(file.toFile());
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;
            while ((bytesCount = nFileInputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            nFileInputStream.close();
            byte[] bytes = digest.digest();
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i < bytes.length; i++) {
            //     sb.append(BigInteger(1,bytes).toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            // }

            return new BigInteger(1,bytes);
        } catch (Exception e) {
            return null;
        }
    }
    public static String checkSumString(MessageDigest digest, ArrayList<String> fArrayList) throws Exception{
        String in = fArrayList.toString().replaceAll("\\[|\\]|,|\\s","");
        byte[] bytes=digest.digest(in.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return fArrayList.size()!=0?sb.toString():"NULL";
    }


    
    public static BigInteger checkSumBigInteger(ArrayList<BigInteger> fArrayList) throws Exception{
        BigInteger n = BigInteger.ZERO;
        for(BigInteger i:fArrayList){
            System.out.println(i);
            n.add(i);
        }
        // String in = fArrayList.toString().replaceAll("\\[|\\]|,|\s","");
        // byte[] bytes=digest.digest(in.getBytes(StandardCharsets.UTF_8));
        // StringBuilder sb = new StringBuilder();
        // for(int i=0;i<bytes.length;i++){
        //     sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        // }
        return n;
    }
}
