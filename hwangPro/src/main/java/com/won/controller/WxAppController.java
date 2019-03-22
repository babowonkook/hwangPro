package com.won.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.bean.Result;
import com.won.service.wxapp.WxappAccount;
import com.won.utils.HttpClientResult;

/**
 * Created by seongmin Park on 2017. 5. 3..
 */

@RestController
public class WxAppController {
	
	@Autowired
	private WxappAccount wxappAccount;
	
	private static final Logger log = LoggerFactory.getLogger(WxAppController.class);
	
	
	// 用户登录方法
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public Result login(HttpServletRequest request)throws Exception{
        //.....业务逻辑代码

        // 1. 返回成功，附带数据
        Result message = new Result(). setSuccessMessage("登录成功");
        message.getResult().put("loginAccount","sdfsdfsdf");//可添加一些需要显示的数据
        message.getResult().put("role",1);//登录人权限
        message.getResult().put("loginTime",new Date());//登录时间
        //return rest;//Controller方法返回

        // 2. 返回失败，不附带数据
        //return new Message().setErrorMessage("请求失败，xxx")
        return message;
    }

    @RequestMapping("/wxapp")
    public String findByWriter(HttpServletRequest request, String c, String a, 
    		                   @RequestParam("do")String action, 
    		                   String op,
    		                   String code,
    		                   String encryptedData,
    		                   String signature,
    		                   String rawData,
    		                   String iv,
    		                   String cart
    		                   ) throws Exception{
    	log.debug("########################request 변수 시작########################");

    	log.debug("c="+c);
    	log.debug("a="+a);
    	log.debug("action="+action);
    	log.debug("op="+op);
    	log.debug("---------------");
    	

    	log.debug("code="+code);
    	log.debug("encryptedData="+encryptedData);
    	log.debug("signature="+signature);
    	log.debug("rawData="+rawData);
    	log.debug("iv="+iv);
    	log.debug("cart="+cart);
    	log.debug("########################request 변수 끝   ########################");
    	Map<String, Object> resultMap = new HashMap<>();
    	ObjectMapper mapper = new ObjectMapper();
    	if("entry".equals(c)) {
    		Map<String, Object> settings = new HashMap<>();
        	if("settings".equals(action)) {
        		settings.put("fg_color", "#000000");
        		settings.put("bg_color", "Yellow");
        		settings.put("name", "点餐系统");
        		
        		settings.put("share_title", "share_title");
//        		type storeType(2:location 기타:store_id로 조회)
        		settings.put("type", "type");
        		
//        		매장번호
        		settings.put("single_storeid", "");
//        		copyright 위에있는 이미지
        		settings.put("store_blogo", "http://img1.3lian.com/2015/w7/85/d/101.jpg");
        		settings.put("copyright", "copyright");
        		
//        		스캔(1:보여지기, 0:감추기)
        		settings.put("wxapp_scan", 1);
//        		배달
        		settings.put("wxapp_takeout", 1);
//        		셀프 주문
        		settings.put("wxapp_getself", 1);
        		
//    			직원호출 사용여부(1:사용)
//        		settings.put("call_waiter", 1);
        		
//        		index.wxml에서 직접 사용
        		settings.put("wxapp_scan_logo", "");
        		settings.put("wxapp_takeout_logo", "");
        		settings.put("wxapp_getself_logo", "");
        		
        		settings.put("tencent_map_apikey", "");
        		
        		if("storeInfo".equals(op)) {
    				Map<String, Object> storeinfo = new HashMap<>();
//    				单笔订单满enoughmoney减enoughdeduct
    				storeinfo.put("enoughmoney", "50000");
    				storeinfo.put("enoughdeduct", "3000");
    				settings.put("storeinfo", storeinfo);
        		}
        		
        		
        	}else if("data".equals(action)) {
        		switch (op) {
    			case "index":
    				Map<String, Object> adv1 = new HashMap<>();
    				adv1.put("id", "1");
    				adv1.put("adv_title", "adv_title1");
    				adv1.put("adv_img", "http://img1.3lian.com/2015/w7/85/d/101.jpg");
    				adv1.put("adv_url", "adv_url");
    				Map<String, Object> adv2 = new HashMap<>();
    				adv2.put("id", "2");
    				adv2.put("adv_title", "adv_title2");
    				adv2.put("adv_img", "http://img1.3lian.com/2015/w7/85/d/101.jpg");
    				adv2.put("adv_url", "adv_url");
    				List<Map<String, Object>> advs = new ArrayList<>();
    				
    				advs.add(adv1);
    				advs.add(adv2);

    	    		settings.put("adv", advs);
    	    		settings.put("adv_width", "80");
    	    		settings.put("adv_height", "30");
    				break;
    			case "storelist":
    				
    				break;
    			case "storeinfo":
    				Map<String, Object> storeinfo = new HashMap<>();
    				storeinfo.put("fg_color", "#000000");
    				storeinfo.put("bg_color", "Yellow");
    				storeinfo.put("name", "小祖宗烤串");
    				storeinfo.put("remark_text", "remark_text");
    				storeinfo.put("starttime", "05:00");
    				storeinfo.put("endtime", "24:00");
    				storeinfo.put("statusText", "正常营业中");
//    				单笔订单满enoughmoney减enoughdeduct
    				storeinfo.put("enoughmoney", "50000");
    				storeinfo.put("enoughdeduct", "3000");
//    				영업상태(1:영업중, 2:휴식중)
    				storeinfo.put("storeStatus", "1");
    				
    				List<Map<String, Object>> classlist = new ArrayList<>();
    				Map<String, Object> class1 = new HashMap<>();
    				class1.put("id", "0");
    				class1.put("classname", "烧烤类");
    				Map<String, Object> class2 = new HashMap<>();
    				class2.put("id", "1");
    				class2.put("classname", "锡纸类");
    				classlist.add(class1);
    				classlist.add(class2);
    				
    				Map<String, Object> goodslist = new HashMap<>();
    				Map<String, Object> goods1 = new HashMap<>();
    				goods1.put("id", "1");
    				goods1.put("name", "鱿鱼");
    				goods1.put("intro", "好吃，贼好吃");
    				goods1.put("price", "10");
    				goods1.put("img", "http://img.qiyezhanlan.com/mingxiangy/2018/0904/20180904ifl1536027972.jpg");
//    				메뉴 옵션 유무(필수 입력)
    				goods1.put("hasoption", "1");
    				goods1.put("unit", "一条");
    				goods1.put("is_pbox", "");
    				goods1.put("pbox_price", "");
    				Map<String, Object> goods2 = new HashMap<>();
    				goods2.put("id", "2");
    				goods2.put("name", "鸡爪");
    				goods2.put("intro", "");
    				goods2.put("price", "20");
    				goods2.put("img", "http://5b0988e595225.cdn.sohucs.com/images/20171224/9ef876f4c53a40ae953f45ba3e35d57c.jpeg");
    				goods2.put("hasoption", "0");
    				goods2.put("unit", "一盘");
    				goods2.put("is_pbox", "");
    				goods2.put("pbox_price", "");
    				Map<String, Object> goods3 = new HashMap<>();
    				goods3.put("id", "3");
    				goods3.put("name", "锡纸酸菜");
    				goods3.put("intro", "炖酸菜");
    				goods3.put("price", "40");
    				goods3.put("img", "https://i2.kknews.cc/SIG=n4fifb/5r7n0000p90r7p392r2r.jpg");
    				goods3.put("hasoption", "0");
    				goods3.put("unit", "一份");
    				goods3.put("is_pbox", "");
    				goods3.put("pbox_price", "");
    				Map<String, Object> goods4 = new HashMap<>();
    				goods4.put("id", "4");
    				goods4.put("name", "锡纸金针菇");
    				goods4.put("intro", "");
    				goods4.put("price", "30");
    				goods4.put("img", "http://img.mp.sohu.com/upload/20170523/e1bcc02fe7db4c008359f846fac743a9_th.png");
    				goods4.put("hasoption", "0");
    				goods4.put("unit", "一份");
    				goods4.put("is_pbox", "");
    				goods4.put("pbox_price", "");
//    				goodslist.put("1", goods1);
//    				goodslist.put("2", goods2);
    				List<Map<String, Object>> class1Goods = new ArrayList<>();
    				class1Goods.add(goods1);
    				class1Goods.add(goods2);
    				List<Map<String, Object>> class2Goods = new ArrayList<>();
    				class2Goods.add(goods3);
    				class2Goods.add(goods4);
    				goodslist.put("0", class1Goods);
    				goodslist.put("1", class2Goods);
    				
    				Map<String, Object> deskinfo = new HashMap<>();
    				deskinfo.put("id", "1");
    				deskinfo.put("name", "当前位置:1号桌");
    				
    				
    				settings.put("storeinfo", storeinfo);
    				settings.put("classlist", classlist);
    				settings.put("goodslist", goodslist);
    				settings.put("deskinfo", deskinfo);
    				break;
    			case "goodsinfo":
    				Map<String, Object> goods11 = new HashMap<>();
    				goods11.put("id", "1");
    				goods11.put("name", "鱿鱼");
    				goods11.put("intro", "好吃，贼好吃");
    				goods11.put("price", "10");
    				goods11.put("img", "http://img.qiyezhanlan.com/mingxiangy/2018/0904/20180904ifl1536027972.jpg");
//    				메뉴 옵션 유무(필수 입력)
    				goods11.put("hasoption", "1");
    				goods11.put("unit", "一条");
    				goods11.put("is_pbox", "");
    				goods11.put("pbox_price", "");
    				
    				
    				
    				List<Map<String, Object>> specs = new ArrayList<>();
    				Map<String, Object> spec1 = new HashMap<>();
    				Map<String, Object> spec2 = new HashMap<>();
//    				大小
//    				여러 스팩안에있는 아이템 아이디는 중복될수 없음
    				List<Map<String, Object>> spec1Items = new ArrayList<>();
    				Map<String, Object> spec1Item1 = new HashMap<>();
    				Map<String, Object> spec1Item2 = new HashMap<>();
    				Map<String, Object> spec1Item3 = new HashMap<>();
    				spec1Item1.put("id", "0");
    				spec1Item1.put("title", "大");
    				
    				spec1Item2.put("id", "1");
    				spec1Item2.put("title", "中");
    				
    				spec1Item3.put("id", "2");
    				spec1Item3.put("title", "小");
    				
    				spec1Items.add(spec1Item1);
    				spec1Items.add(spec1Item2);
    				spec1Items.add(spec1Item3);
    				
    				spec1.put("title", "大小");
    				spec1.put("items", spec1Items);
    				
    				List<Map<String, Object>> spec2Items = new ArrayList<>();
    				Map<String, Object> spec2Item1 = new HashMap<>();
    				Map<String, Object> spec2Item2 = new HashMap<>();
    				Map<String, Object> spec2Item3 = new HashMap<>();
    				spec2Item1.put("id", "3");
    				spec2Item1.put("title", "特辣");
    				
    				spec2Item2.put("id", "4");
    				spec2Item2.put("title", "中辣");
    				
    				spec2Item3.put("id", "5");
    				spec2Item3.put("title", "微辣");
    				
    				spec2Items.add(spec2Item1);
    				spec2Items.add(spec2Item2);
    				spec2Items.add(spec2Item3);
    				
//    				辣度
    				spec2.put("title", "辣度");
    				spec2.put("items", spec2Items);
    				
    				
    				specs.add(spec1);
    				specs.add(spec2);
    				
    				
    				List<Map<String, Object>> options = new ArrayList<>();
    				Map<String, Object> option1 = new HashMap<>();
//    				大，特辣
    				option1.put("specs", "0_3");
    				option1.put("marketprice", "14");
    				option1.put("id", "11");
    				
    				Map<String, Object> option2 = new HashMap<>();
//    				大，中辣
    				option2.put("specs", "0_4");
    				option2.put("marketprice", "13");
    				option2.put("id", "12");
    				
//    				大，小辣
    				Map<String, Object> option3 = new HashMap<>();
    				option3.put("specs", "0_5");
    				option3.put("marketprice", "12");
    				option3.put("id", "13");
    				
    				
    				Map<String, Object> option4 = new HashMap<>();
//    				中，特辣
    				option4.put("specs", "1_3");
    				option4.put("marketprice", "11");
    				option4.put("id", "14");
    				
    				Map<String, Object> option5 = new HashMap<>();
//    				中，中辣
    				option5.put("specs", "1_4");
    				option5.put("marketprice", "10");
    				option5.put("id", "15");
    				
//    				中，小辣
    				Map<String, Object> option6 = new HashMap<>();
    				option6.put("specs", "1_5");
    				option6.put("marketprice", "9");
    				option6.put("id", "16");
    				
    				
    				Map<String, Object> option7 = new HashMap<>();
//    				小，特辣
    				option7.put("specs", "2_3");
    				option7.put("marketprice", "8");
    				option7.put("id", "17");
    				
    				Map<String, Object> option8 = new HashMap<>();
//    				小，中辣
    				option8.put("specs", "2_4");
    				option8.put("marketprice", "7");
    				option8.put("id", "18");
    				
//    				小，小辣
    				Map<String, Object> option9 = new HashMap<>();
    				option9.put("specs", "2_5");
    				option9.put("marketprice", "6");
    				option9.put("id", "19");
    				
    				
    				options.add(option1);
    				options.add(option2);
    				options.add(option3);
    				options.add(option4);
    				options.add(option5);
    				options.add(option6);
    				options.add(option7);
    				options.add(option8);
    				options.add(option9);
    				
    				settings.put("goods", goods11);
    				settings.put("specs", specs);
    				settings.put("options", options);
    				break;
    			case "orderlist":
    				
    				break;
    			case "orderinfo":
    				settings.put("goods_list", "[null,{\"count\":1,\"options\":[null,null,null,null,null,null,null,null,null,null,null,null,null,{\"count\":1,\"id\":\"13\",\"name\":\"大+微辣\",\"marketprice\":\"12\",\"price\":12}],\"hasoption\":\"1\",\"goodsid\":\"1\",\"name\":\"鱿鱼\",\"is_pbox\":\"\",\"pbox_price\":0},{\"count\":1,\"options\":[],\"hasoption\":\"0\",\"goodsid\":\"2\",\"name\":\"鸡爪\",\"is_pbox\":\"\",\"pbox_price\":0,\"totalprice\":20,\"marketprice\":\"20.00\"},{\"count\":1,\"options\":[],\"hasoption\":\"0\",\"goodsid\":\"3\",\"name\":\"锡纸酸菜\",\"is_pbox\":\"\",\"pbox_price\":0,\"totalprice\":40,\"marketprice\":\"40.00\"},{\"count\":1,\"options\":[],\"hasoption\":\"0\",\"goodsid\":\"4\",\"name\":\"锡纸金针菇\",\"is_pbox\":\"\",\"pbox_price\":0,\"totalprice\":30,\"marketprice\":\"30.00\"}]");
    				settings.put("order_number", "201903181219_ord_id");
    				settings.put("ticket_remark", "请留意服务员叫号");
    				settings.put("pay_price", 120);
    				settings.put("use_coupon", "f");
    				settings.put("ordersn", "201903181219_ord_id");
    				settings.put("paytime", "201903181219");

    				break;
    			case "address":
    				
    				break;
    			case "addr_detail":
    				
    				break;
    			case "couponlist":
    				
    				break;
    			case "first_addr":
    				
    				break;
    			case "select_addr":
    				
    				break;
    			case "check_addr":
    				
    				break;
    			case "adv_info":
    				
    				break;
    			case "auth_setting":
    				
    				break;
    			case "getfood_time":
    				
    				break;
    			default:
    				
    				break;
    			}
        	}else if("deampost".equals(action)) {
        		switch (op) {
        			case "submitorder" : 
        				settings.put("timeStamp", "201903181219");
        				settings.put("nonceStr", "172367126378216378216378216738261783");
        				settings.put("package", "1232132131232");
        				settings.put("paySign", "sfasdkjfklsadnmflkj1k2j3klnmkl23h1j2k3nl");
        				settings.put("order_id", "201903181219_ord_id");
        				break;
    				default:
    					break;
        		}
        		
        	}
        	resultMap.put("result", settings);
        	resultMap.put("status", 1);
    	}else if("auth".equals(c)) {
    		
    		if("openid".equals(action)) {
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
        		
        	}else if("userinfo".equals(action)) {
    			HttpSession session = request.getSession();
    			String sessionId = session.getId();
    			log.debug("sessionId" + sessionId);
    			String sessionKey = session.getAttribute("session_key").toString();
    			log.debug(sessionKey + "          @@@@");
    			
    			
    			Map<String, Object> data = new HashMap<>();
        		data.put("uid", "123123213");
    			resultMap.put("data", data);
        		
        	}
    		
    	}
    	
    	String result = mapper.writeValueAsString(resultMap);
    	String printjson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
    	log.debug("printjson : " + printjson);
        return result;
    	
    }
}
