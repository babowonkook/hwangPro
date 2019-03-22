package com.won.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.bean.Result;
import com.won.service.SettingsService;
import com.won.service.SettingsServiceTest;
import com.won.service.StoreService;
import com.won.service.wxapp.WxappAccount;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by seongmin Park on 2017. 5. 3..
 */

@Slf4j
@RestController
@RequestMapping("/entry/wxapp/settings")
@ResponseBody
public class EntryWxappSettingController {
	
	@Autowired
	SettingsService settingsServide;

	@Autowired
	StoreService storeService;
	
	@RequestMapping("")
	public Result setting(String i) throws JsonProcessingException {
		
		Map<String, Object> resultMap = settingsServide.getSettingsById(i);
		
    	Result message = new Result().setSuccessMessage();
    	message.setResult(resultMap);
		return message;
	}
	
	@RequestMapping("/storeInfo")
	public Result storeInfo(String i, String store_id) throws JsonProcessingException {
		
		Map<String, Object> result = storeService.getStoreById(i, store_id);
		
		Result message = new Result().setSuccessMessage();
    	message.setResult(result);
		return message;
		
	}
	
	
}
