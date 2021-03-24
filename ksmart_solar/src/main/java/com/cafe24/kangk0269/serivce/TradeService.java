package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.TradeMapper;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;

@Service
@Transactional
public class TradeService {

	private final TradeMapper tradeMapper;

	@Autowired
	public TradeService(TradeMapper tradeMapper) {
		this.tradeMapper = tradeMapper;
	}

	public List<TradeDepositOutDTO> getDepositList() {

		return tradeMapper.getDepositList();
	}
}
