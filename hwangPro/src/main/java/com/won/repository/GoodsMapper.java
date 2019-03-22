package com.won.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper {
	List<Map<String, Object>> selectGoodsById(@Param("uniacId") String uniacId, @Param("storeId") String storeId, @Param("classId") String classId, @Param("goodsId") String goodsId);

}
