package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.ComponentDTO;

@Mapper
public interface ComponentMapper {

	public List<ComponentDTO> getComponentListById(String SID);
	
	public List<ComponentDTO> addComponent(String SID);
	
	
}