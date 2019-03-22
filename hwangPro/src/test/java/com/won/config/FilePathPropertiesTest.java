package com.won.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.won.service.AdvServiceTest;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FilePathPropertiesTest {
	@Autowired
	FilePathProperties filePathProperties;
	
	@Test
	public void test() {
		String uploadUrl = filePathProperties.getUploadUrl();
		log.debug(uploadUrl);
		assertEquals(uploadUrl, "file:/home/webmaster/FILE/");
	}

}
