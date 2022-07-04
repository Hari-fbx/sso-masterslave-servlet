package com.csvmodifer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Table {
    static String createTablewithoption(ArrayList<String> userList,String path) throws IOException{
        String line;
        BufferedReader bReader = new BufferedReader(new FileReader(path));
        StringBuilder b = new StringBuilder("<form action='assign'>");
        if((line = bReader.readLine()) != null){
            b.append("<table><tr>");
            for(String s : line.split(",")){
                b.append("<th>"+s+"</th>");
            }
            b.append("</tr>");
        }
        int i=1;
        while ((line = bReader.readLine()) != null) {  
            b.append("<tr>");
            String[] employee = line.split(",");
            for(String s : employee){
                b.append("<td>"+s+"</td>");
            }
            b.append("<select name='"+i+"' id='"+i+"'>");
            for(String o : userList){
                b.append("<option value='"+o+"'>"+o+"</option>");
            }
            b.append("</select>");
            b.append("</tr>");
            i++;
        }
        b.append("</table><br><br><input type='submit' value='Submit'></form>");
        bReader.close();
        return b.toString();
    }
    static String createTable(String path) throws IOException{
        BufferedReader bReader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder b = new StringBuilder();
        if((line = bReader.readLine()) != null){
            b.append("<table><tr>");
            for(String s : line.split(",")){
                b.append("<th>"+s+"</th>");
            }
            b.append("</tr>");
        }
        while ((line = bReader.readLine()) != null) {  
            b.append("<tr>");
            String[] employee = line.split(",");
            for(String s : employee){
                b.append("<td>"+s+"</td>");
            }
            b.append("</tr>");
        }
        b.append("</table>");
        bReader.close();
        return b.toString();
    }   
}

