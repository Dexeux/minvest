package com.example.chrisg.minvest.account;

/**
 * Created by chrisg on 11/13/16.
 */

public class InformationObject {
    private String name;
    private String value;
    public InformationObject(String name, String value){
        this.name=name;
        this.value=value;
    }

    public String getName(){
        return name;
    }
    public String getValue(){
        return value;
    }
}
