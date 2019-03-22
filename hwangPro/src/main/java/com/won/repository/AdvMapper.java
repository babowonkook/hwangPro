package com.won.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdvMapper {
	List<Map<String, Object>> selectAdvsByUniacid(String uniacid);

}
