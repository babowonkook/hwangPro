package com.won.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AdvServiceTest {

	@Autowired
	AdvService advService;
	
	@Test
	public void test() {
		Map<String, Object> resuls = advService.getAdvsById("1");
		log.info("resuls : {}", resuls);
		assertNotNull(resuls);
	}

}
