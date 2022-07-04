package com.csvmodifer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;

public class VerifyToken extends HttpServlet {
    private static HttpURLConnection connection; 
    public static boolean verifyToken(String token){
        token =token.replaceAll(" ", "+");
        System.out.println("verify token call "+ token);
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();
        try{
            URL url = new URL("http://localhost:8085/app/verifytoken?sessionid="+token);
            connection =    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int respCode = connection.getResponseCode();
            if(respCode>299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line= reader.readLine())!=null){
                    response.append(line);

                }
                reader.close();
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line= reader.readLine())!=null){
                    response.append(line);

                }
                reader.close();
                System.out.println(response.toString());
                return response.toString().contains("true");
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return false;
    }
    public static void main(String[] args) {
        verifyToken("KhPkG+iqJgWVAiT04jtrtXxIml6+apcnDKM/QKBUISDVFn7UJq/cqCj+wCXucrVQSSP45H3ueMxNa/TW5Ss09UHBA0iTL/ylbspE2CixqoX5grcWyl9YYa1v7WErIm0w9O2iM2LOD/S3Gq/ATDqsc1ZPxXFZXtdi5Ehg9ngDsY=");
    }
}
