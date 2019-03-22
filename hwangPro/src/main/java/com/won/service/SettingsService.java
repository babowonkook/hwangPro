package com.won.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.won.repository.SettingsMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SettingsService {
    @Autowired
    SettingsMapper settingsMapper;

    public Map<String, Object> getSettingsById(String uniacid) {
    	Map<String, Object> reulst = settingsMapper.selectSettingsByUniacid(uniacid);
    	
        log.debug(reulst.toString());
    	if(reulst.get("tencent_map_apikey") == null || StringUtils.isEmpty(reulst.get("tencent_map_apikey").toString())) {
    		reulst.put("tencent_map_apikey", "DOKBZ-4HHRX-JVR4Q-ZROIE-INAP3-7UFG3");
    	}
    	if(reulst.get("bg_color") == null || StringUtils.isEmpty(reulst.get("bg_color").toString())) {
    		reulst.put("bg_color", "#ff9c37");
    	}
    	if(reulst.get("fg_color") == null || StringUtils.isEmpty(reulst.get("fg_color").toString())) {
    		reulst.put("fg_color", "#ffffff");
    	}
    	

		
		
		Map<String, Object> settings = new HashMap<>();

		
//		type storeType(2:location 기타:store_id로 조회)
		settings.put("type", "type");
		
//		매장번호
		settings.put("single_storeid", "");
//		copyright 위에있는 이미지
		settings.put("store_blogo", "http://img1.3lian.com/2015/w7/85/d/101.jpg");
		settings.put("copyright", "copyright");
		
//		스캔(1:보여지기, 0:감추기)
		settings.put("wxapp_scan", 1);
//		배달
		settings.put("wxapp_takeout", 1);
//		셀프 주문
		settings.put("wxapp_getself", 1);
		
//		직원호출 사용여부(1:사용)
//		settings.put("call_waiter", 1);
		
//		index.wxml에서 직접 사용
		settings.put("wxapp_scan_logo", "");
		settings.put("wxapp_takeout_logo", "");
		settings.put("wxapp_getself_logo", "");
		
		settings.put("tencent_map_apikey", "");
		
		
		return reulst;
    }
}