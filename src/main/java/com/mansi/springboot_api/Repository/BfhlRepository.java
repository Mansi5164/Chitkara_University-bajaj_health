package com.mansi.springboot_api.Repository;

import org.springframework.stereotype.Repository;

@Repository
public class BfhlRepository {

    public void logRequest(String type){
        System.out.println("Processed request: " + type);
    }
}