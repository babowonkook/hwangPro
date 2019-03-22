package com.won.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {
	Map<String, Object> selectStoreById(@Param("uniacid") String uniacid, @Param("id") String id);

}
