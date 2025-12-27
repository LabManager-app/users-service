package com.labmanager.users.entity;

import java.util.List;

public class Codes {
    private String code1 = "67ZL3X91-5";

    private List<String> codes2 = List.of("1", "2", "3");
    private List<String> roles  = List.of("ADMINISTRATOR", "PROJECTLEADER", "EMPLOYEE");
    
    public boolean checkCode1(String c1){
        return code1.equals(c1);
    }

    public String getRole(String c2){
        for(int i = 0; i < codes2.size(); i++){
            if(c2.equals(codes2.get(i))){
                return roles.get(i);
            }
        }
        return null;
    }

}
