package com.won.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.bean.Result;
import com.won.config.FilePathProperties;
import com.won.service.AdvService;
import com.won.service.MemberService;
import com.won.service.StoreService;
import com.won.service.wxapp.WxappAccount;

/**
 * Created by seongmin Park on 2017. 5. 3..
 */

@RestController
@RequestMapping("/entry/wxapp/data")
public class EntryWxappDataController {
	
	private static final Logger log = LoggerFactory.getLogger(EntryWxappDataController.class);
	
	@Autowired
	AdvService advService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	MemberService memberService; 

	
	@RequestMapping("/index")
	public Result index(String i) throws JsonProcessingException {
		
		Map<String, Object> advs = advService.getAdvsById(i);
		

		
    	Result message = new Result().setSuccessMessage();
    	message.setResult(advs);
		return message;
	}
	
	@RequestMapping("/storeinfo")
	public Result storeinfo(String i, String storeid, String deskid, String storetype) throws JsonProcessingException {
		Map<String, Object> result = storeService.getStoreById(i, storeid, deskid);
		
    	Result message = new Result().setSuccessMessage();
    	message.setResult(result);
		return message;
	}
	
	@RequestMapping("/goodsinfo")
	public Result goodsinfo(String i, String storeid, String deskid, String goodsid) throws JsonProcessingException {
		
		Map<String, Object> result = storeService.getGoodsInfosById(i, storeid, deskid, goodsid);
		
    	Result message = new Result().setSuccessMessage();
    	message.setResult(result);
		return message;
	}
	
	@RequestMapping("/check_addr")
	public Result checkAddr(String i, String address_id, String member_id) throws JsonProcessingException {
		
		Map<String, Object> result = memberService.getAddressById(i, "sdf", "address_id");
		
		Result message = new Result();
		
		if(result == null) {
			message.setErrorMessage("주소정보 조회하지 못함");
		}else {
			message.setSuccessMessage();
		}
		
//		message.setResult(result);
		return message;
	}
	
	@RequestMapping("/getfood_time")
	public Result getFoodTime(String i, String store_id, String member_id) throws JsonProcessingException {
		
		Map<String, Object> result = memberService.getAddressById(i, "sdf", "address_id");
		
		Result message = new Result();
		
		
//		message.setResult(result);
		return message;
	}
	
	@RequestMapping("/orderinfo")
	public String orderinfo(String op) throws JsonProcessingException {
		Map<String, Object> settings = new HashMap<>();
		settings.put("goods_list", "[null,{\"count\":1,\"options\":[null,null,null,null,null,null,null,null,null,null,null,null,null,{\"count\":1,\"id\":\"13\",\"name\":\"大+微辣\",\"marketprice\":\"12\",\"price\":12}],\"hasoption\":\"1\",\"goodsid\":\"1\",\"name\":\"鱿鱼\",\"is_pbox\":\"\",\"pbox_price\":0},{\"count\":1,\"options\":[],\"hasoption\":\"0\",\"goodsid\":\"2\",\"name\":\"鸡爪\",\"is_pbox\":\"\",\"pbox_price\":0,\"totalprice\":20,\"marketprice\":\"20.00\"},{\"count\":1,\"options\":[],\"hasoption\":\"0\",\"goodsid\":\"3\",\"name\":\"锡纸酸菜\",\"is_pbox\":\"\",\"pbox_price\":0,\"totalprice\":40,\"marketprice\":\"40.00\"},{\"count\":1,\"options\":[],\"hasoption\":\"0\",\"goodsid\":\"4\",\"name\":\"锡纸金针菇\",\"is_pbox\":\"\",\"pbox_price\":0,\"totalprice\":30,\"marketprice\":\"30.00\"}]");
		settings.put("order_number", "201903181219_ord_id");
		settings.put("ticket_remark", "请留意服务员叫号");
		settings.put("pay_price", 120);
		settings.put("use_coupon", "f");
		settings.put("ordersn", "201903181219_ord_id");
		settings.put("paytime", "201903181219");
		
		Map<String, Object> resultMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
    	resultMap.put("result", settings);
    	resultMap.put("status", 1);
    	String result = mapper.writeValueAsString(resultMap);
		return result;

	}
	

    
}
