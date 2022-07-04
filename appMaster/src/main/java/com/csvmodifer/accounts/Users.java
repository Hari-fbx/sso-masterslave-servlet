package com.csvmodifer.accounts;

import java.util.HashMap;
import java.util.HashSet;

public class Users {
    public static HashMap<String,String> user;
    public static HashSet<String> activeUsers;
    static{
        activeUsers = new HashSet<String>();
        user =new HashMap<String,String>();
        user.put("managerA","managerA123");
        user.put("userA","userA"); 
        user.put("userB","userB"); 
    }
}
