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

	public int addComponent(ComponentDTO cp) {
		int result = componentMapper.addComponent(cp);
		System.out.println(result + " <<< addComponent 'result'");
		return result;
	}

	public void getComponentListByCode(Model model, String cpCode) {
		ComponentDTO cp = componentMapper.getComponentListByCode(cpCode);
		model.addAttribute("cpCode", cp.getCpCode());
		model.addAttribute("mId", cp.getmId());
		model.addAttribute("cpName", cp.getCpName());
		model.addAttribute("cpPhoto", cp.getCpPhoto());
		model.addAttribute("cpInfo", cp.getCpInfo());
		model.addAttribute("cpMaker", cp.getCpMaker());
		model.addAttribute("cpMakedate", cp.getCpMakedate());
		model.addAttribute("cpUsedate", cp.getCpUsedate());
	}

	public int componentModify(ComponentDTO cp) {
		return componentMapper.componentModify(cp);
	}

	public int deleteComponent(String cpCode) {
		return componentMapper.deleteComponent(cpCode);
	}

	
	
	
}