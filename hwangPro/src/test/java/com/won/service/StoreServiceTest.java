package com.won.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class StoreServiceTest {
	
	@Autowired
	StoreService storeService;

	@Test
	public void getStoreByIdTest() {
		Map<String, Object> reulst = storeService.getStoreById("1", "1","1");
		log.debug("result: {}",reulst);
		assertNotNull(reulst);
	}
	
	@Test
	public void getStoreById2Test() throws JsonProcessingException {
		Map<String, Object> reulst = storeService.getStoreById("1", "1");
		ObjectMapper mapper = new ObjectMapper();
		String resultStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reulst);
		log.debug("result: {}",resultStr);
		assertNotNull(reulst);
	}

	
	@Test
	public void getGoodsInfosByIdTest() throws JsonProcessingException {
		Map<String, Object> reulst = storeService.getGoodsInfosById("1", "1","1", "2");
		ObjectMapper mapper = new ObjectMapper();
		String resultStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reulst);
		log.debug("result: {}",resultStr);
		assertNotNull(reulst);
	}
}
