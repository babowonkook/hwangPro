package com.won.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClassMapper {
	List<Map<String, Object>> selectClassById(@Param("uniacid") String uniacid, @Param("storeId") String storeId);

}
