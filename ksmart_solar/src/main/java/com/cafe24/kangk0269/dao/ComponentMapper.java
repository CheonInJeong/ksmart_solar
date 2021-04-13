package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.ComponentDTO;

@Mapper
public interface ComponentMapper {

	public List<ComponentDTO> getComponentListById(String SID);

	public int addComponent(ComponentDTO cp);

	public ComponentDTO getComponentListByCode(String cpCode);

	public int componentModify(ComponentDTO cp);

	public int deleteComponent(String cpCode);
	
}