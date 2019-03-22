package com.won.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.won.repository.AddressMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
    @Autowired
    AddressMapper addressMapper;
    

    public Map<String, Object> getAddressById(String uniacId, String openId, String addressId) {
    	Map<String, Object> reulst = addressMapper.selectAddressById(uniacId, openId, addressId);
		return reulst;
    }
}