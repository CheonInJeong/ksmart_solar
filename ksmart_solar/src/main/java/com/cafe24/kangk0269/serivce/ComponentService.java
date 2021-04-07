package com.cafe24.kangk0269.serivce;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cafe24.kangk0269.dao.ComponentMapper;
import com.cafe24.kangk0269.dto.ComponentDTO;

@Service
@Transactional
public class ComponentService {

	private final ComponentMapper componentMapper;

	@Autowired
	public ComponentService(ComponentMapper componentMapper) {
		this.componentMapper = componentMapper;
	}
	
	public void getComponentListById(Model model, HttpSession session) {
		
		String SID = (String) session.getAttribute("SID");
		List<ComponentDTO> cd = componentMapper.getComponentListById(SID);
		model.addAttribute("componentListById", cd);
	}

	public void addComponent(Model model, HttpSession session) {
		String SID = (String) session.getAttribute("SID");
		
		componentMapper.addComponent(SID);
		//부품등록 작성
		
	}

	
	
	
}