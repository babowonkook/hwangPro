package com.won.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.won.config.FilePathProperties;
import com.won.repository.ClassMapper;
import com.won.repository.DeskMapper;
import com.won.repository.GoodsMapper;
import com.won.repository.GoodsSpecMapper;
import com.won.repository.StoreMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StoreService {
    @Autowired
    StoreMapper storeMapper;
    
    @Autowired
    DeskMapper deskMapper;
    
    @Autowired
    ClassMapper classMapper;
    
    @Autowired
    GoodsMapper goodsMapper;
    
    @Autowired
    GoodsSpecMapper goodsSpecMapper;

	@Autowired
	FilePathProperties filePathProperties;
	
    public Map<String, Object> getStoreById(String uniacId, String storeId, String deskId) {
    	Map<String, Object> storeInfo = storeMapper.selectStoreById(uniacId, storeId);
    	
    	Map<String, Object> deskInfo = deskMapper.selectDeskById(uniacId, storeId, deskId);
    	
    	List<Map<String, Object>> classList = classMapper.selectClassById(uniacId, storeId);
    	
    	List<List<Map<String, Object>>> goodsList = new ArrayList<>();
    	for(Map<String, Object> classMap : classList) {
    		List<Map<String, Object>> goodsByClass = goodsMapper.selectGoodsById(uniacId, storeId, classMap.get("id").toString(),null);
    		for(Map<String, Object> good : goodsByClass) {
    			if(good.get("img") != null && StringUtils.isNotEmpty(good.get("img").toString())) {
    				String img = "http://" + filePathProperties.getRootpath() + "/static" + good.get("img").toString();
    				good.put("img", img);
    			}
    		}
    		goodsList.add(goodsByClass);
    	}
    	
    	Map<String, Object> reulst = new HashMap<>();
    	
    	reulst.put("storeinfo", storeInfo);
    	reulst.put("deskinfo", deskInfo);
    	reulst.put("classlist", classList);
    	reulst.put("goodslist", goodsList);
		return reulst;
    }
    
    public Map<String, Object> getStoreById(String uniacId, String storeId) {
    	Map<String, Object> storeInfo = storeMapper.selectStoreById(uniacId, storeId);
    	
    	Map<String, Object> reulst = new HashMap<>();
    	
    	reulst.put("storeinfo", storeInfo);
    	return reulst;
    }
    
    public Map<String, Object> getGoodsInfosById(String uniacId, String storeId, String deskId, String goodsId) {
    	List<Map<String, Object>> goodsById = goodsMapper.selectGoodsById(uniacId, storeId, null, goodsId);
    	Map<String, Object> reulst = new HashMap<>();
    	if(goodsById != null && goodsById.size() > 0) {
    		List<Map<String, Object>> goodsSpecList = goodsSpecMapper.selectGoodsSpecById(uniacId, goodsId);
    		for(Map<String, Object> goodsSpec : goodsSpecList) {
    			List<Map<String, Object>> specItemsList = goodsSpecMapper.selectGoodsSpecItemsById(goodsSpec.get("id").toString());
    			goodsSpec.put("items", specItemsList);
    		}
    		List<Map<String, Object>> goodsOptionsList = goodsSpecMapper.selectGoodsOptionsById(uniacId, goodsId);
    		
    		
    		reulst.put("goods", goodsById.get(0));
    		reulst.put("specs", goodsSpecList);
    		reulst.put("options", goodsOptionsList);
    	}
    	
    	
    	return reulst;
    	
    }
    
    public Map<String, Object> getFoodTime(String uniacId, String storeId) {
    	
    	return null;
    }
    
}