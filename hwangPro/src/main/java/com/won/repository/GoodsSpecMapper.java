package com.won.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsSpecMapper {
	List<Map<String, Object>> selectGoodsSpecById(@Param("uniacId") String uniacId, @Param("goodsId") String goodsId);
	
	List<Map<String, Object>> selectGoodsSpecItemsById(@Param("specId") String specId);

	List<Map<String, Object>> selectGoodsOptionsById(@Param("uniacId") String uniacId, @Param("goodsId") String goodsId);
}
