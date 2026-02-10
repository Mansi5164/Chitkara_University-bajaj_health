package com.mansi.springboot_api.service;

import com.mansi.springboot_api.dto.BfhlRequest;

public interface BfhlService {
    Object process(BfhlRequest request);
}