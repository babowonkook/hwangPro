package com.won.service.wxapp;

import org.springframework.stereotype.Service;

import com.won.utils.HttpClientResult;
import com.won.utils.HttpClientUtils;


@Service
public class WxappAccount {
	
	private String appid = "wx87c9fcf6e4dde89f";
	private String secret = "26227173a00175fb330478a809a71159";
	
	public HttpClientResult getOauthInfo(String code) throws Exception {
		
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
		HttpClientResult result = HttpClientUtils.doGet(url);
		return result;
	}

}
