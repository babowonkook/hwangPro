package com.won.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.service.wxapp.WxappAccount;
import com.won.utils.HttpClientResult;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by seongmin Park on 2017. 5. 3..
 */
@Slf4j
@RestController
@RequestMapping("/auth/session")
public class AuthSessionController {
	
	@Autowired
	private WxappAccount wxappAccount;
	
	@RequestMapping("/openid")
	public String openid(HttpServletRequest request,
				 		 String op,
				 		 String code) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		HttpClientResult httpClientResult = wxappAccount.getOauthInfo(code);
		int cd = httpClientResult.getCode();
		if(cd == 200) {
			String content = httpClientResult.getContent();
			JsonNode rootNode = mapper.readValue(content, JsonNode.class);
			String sessionKey = rootNode.get("session_key").toString();
			
			session.setAttribute("session_key", sessionKey);
		}else {
			log.debug("openid 획득 실페");
		}
		
		
		log.debug("sessionId" + sessionId);
		log.debug(httpClientResult.toString());
		
		Map<String, Object> data = new HashMap<>();
		data.put("sessionid", sessionId);
		
    	resultMap.put("data", data);
    	resultMap.put("status", 1);
    	
    	String result = mapper.writeValueAsString(resultMap);
		return result;
	}
	
	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request,
				 		 String op,
				 		 String code) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		log.debug("sessionId" + sessionId);
		String sessionKey = session.getAttribute("session_key").toString();
		log.debug(sessionKey + "          @@@@");
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("uid", "123123213");
		resultMap.put("data", data);
    	
    	String result = mapper.writeValueAsString(resultMap);
		return result;
	}
	
}
