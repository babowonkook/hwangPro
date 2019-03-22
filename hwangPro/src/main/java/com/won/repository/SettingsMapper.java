package com.won.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingsMapper {
	Map<String, Object> selectSettingsByUniacid(String uniacid);

}
