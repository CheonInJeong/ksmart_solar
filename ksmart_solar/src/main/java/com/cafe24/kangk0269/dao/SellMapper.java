package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.FileDTO;


@Mapper
public interface SellMapper {
	//파일 수정 메서드
	public int modifyFile(String code);
	
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(String mId);
	
	//파일 등록 메서드
	public int addFile(List<FileDTO> list);
	//해당 공고 입찰자 목록
	public List<BidListDTO> getBidderList(String code);
	
	//해당 아이디가 가지고 있는 부품 정보 
	public List<ComponentDTO> getComponent(String mId);
	
	//부품 코드로 부품 상세 정보 가져오기
	public ComponentDTO getComponentInformation(String code);
	
	//공고신청 중 발전소 선택시 선택한 발전소의 정보(잔존가치, 매입금액, 발전소시작일)을 가져오는 메서드
	public BusinessPlantDTO getPlantInformation(String code);
	
	//입찰자 수 불러오기
	public int getNumberOfBidder();
	
	//해당 발전소 공고에서 가장 높게 입찰 받은 가격 불러오기
	public int getHighestPriceByCode();
	
	//판매자의 발전소 공고 목록을 가져오는 메서드 
	public List<BidPlantDTO> getBidPlantbyId(String mId);
	//공고 코드로 공고 신청 내역 불러오는 메서드
	public List<BidPlantDTO> getBidPlantbyCode(String code);
	
	///발전소 공고 내용 조회
	public List<BidPlantDTO> getBidPlantDetail(String code);
	
	//공고 신청 삭제 메서드
	public int removePlantApply(String code);
	
	//공고신청 수정 처리 메서드
	public int modifyPlantApply(BidPlantDTO bidPlantDto);
	
	//공고신청 메서드
	public int addPlantApply(BidPlantDTO bidPlantDto);
	
	//발전소 이름 가져오는 메서드
	public List<BusinessPlantDTO> getPlantName(String mId);
}
