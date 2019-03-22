package com.won.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper	
public interface AddressMapper {
	Map<String, Object> selectAddressById(@Param("uniacId") String uniacId, @Param("openId") String openId, @Param("addressId") String addressId);

}
