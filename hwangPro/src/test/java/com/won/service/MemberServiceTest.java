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
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService; 

	@Test
	public void test() throws JsonProcessingException {
		
		Map<String, Object> reulst = memberService.getAddressById("1", "123213", "addressId");
		ObjectMapper mapper = new ObjectMapper();
		String resultStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reulst);
		log.debug("result: {}",resultStr);
		assertNotNull(reulst);
	}

}
