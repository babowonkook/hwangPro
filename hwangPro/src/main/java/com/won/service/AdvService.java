package com.won.service;

import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.won.config.FilePathProperties;
import com.won.repository.AdvMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AdvService {
    @Autowired
    AdvMapper advMapper;
	
	@Value("${server.port}")
	private String port;
	
	@Value("${filepath.upload}")
	private String upload;
	
	@Value("${filepath.rootpath}")
	private String rootPath;

    public Map<String, Object> getAdvsById(String uniacid) {
    	List<Map<String, Object>> reulsts = advMapper.selectAdvsByUniacid(uniacid);
    	int advHeight = 0;
    	int advWidth = 0;
    	if(reulsts.get(0) != null) {
    		String adv_img = upload + reulsts.get(0).get("adv_img").toString();
    		log.debug("adv_img: {}", adv_img);
    		Image img = new ImageIcon(adv_img).getImage();
    		
    		advHeight = img.getHeight(null);
    		advWidth = img.getWidth(null);
    		log.debug("img Height: {}", advHeight);
    		log.debug("img Width: {}", advWidth);
    	}
		for(Map<String, Object> adv : reulsts) {
			String advImgUrl = rootPath + ":" + port + "/static" + adv.get("adv_img").toString();
			adv.put("adv_img", advImgUrl);
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("adv", reulsts);
		resultMap.put("adv_width", advWidth);
		resultMap.put("adv_height", advHeight);
		return resultMap;
    }
}