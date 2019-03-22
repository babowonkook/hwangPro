package com.won.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.service.wxapp.WxappAccount;
import com.won.utils.HttpClientResult;

/**
 * Created by seongmin Park on 2017. 5. 3..
 */

@RestController
@RequestMapping("/entry/wxapp/deampost")
public class EntryWxappDeampostController {
	
	
	private static final Logger log = LoggerFactory.getLogger(EntryWxappDeampostController.class);
	
	@RequestMapping("/submitorder")
	public String submitorder(String op) throws JsonProcessingException {
		Map<String, Object> settings = new HashMap<>();
		
		settings.put("timeStamp", "201903181219");
		settings.put("nonceStr", "172367126378216378216378216738261783");
		settings.put("package", "1232132131232");
		settings.put("paySign", "sfasdkjfklsadnmflkj1k2j3klnmkl23h1j2k3nl");
		settings.put("order_id", "201903181219_ord_id");
		
		
		Map<String, Object> resultMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
    	resultMap.put("result", settings);
    	resultMap.put("status", 1);
    	String result = mapper.writeValueAsString(resultMap);
		return result;
	}
	
}
