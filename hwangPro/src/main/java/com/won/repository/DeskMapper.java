package com.won.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeskMapper {
	Map<String, Object> selectDeskById(@Param("uniacid") String uniacid, @Param("storeId") String storeId, @Param("deskId") String deskId);

}
