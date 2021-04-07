package com.cafe24.kangk0269.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.common.Criteria;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.serivce.PlantService;
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class SellController {
	
	@Autowired
	private final SellService sellService;
	
	@Autowired
	private final PlantService plantService;
	
	public SellController(SellService sellService,PlantService plantService) {
		this.sellService = sellService;
		this.plantService = plantService;
		
	}
	

	@PostMapping(value="/sell/addNewPlantSell")
	public String addNewPlantBid(BidPlantDTO bidPlantDTO,MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
		sellService.addPlantRebidApply(bidPlantDTO,multipartHttpServletRequest,request);
		
		return "redirect:/sell/myHistory";
	}
	
	
	@GetMapping(value="/sell/addNewPlantSell") 
	public String addNewPlantBid(@RequestParam(value="mId") String mId
								,@RequestParam(value="groupCode") String groupCode
								,@RequestParam(value="bzPlcode") String code
								, Model model)  throws Exception {
		System.out.println(mId+"<-----재공고신청 아이디");
		System.out.println(groupCode+"<-----그룹코드");
		System.out.println(code+"<-----발전소코드");
		
		
		model.addAttribute("plant", sellService.getBidPlantAcByIdCode(mId,code));
		model.addAttribute("residual",plantService.residualValue(code));
		return "sell/addNewPlantSell";
	}
	
	
	
	@RequestMapping(value="/ajax/modifyRank", method= RequestMethod.POST)
	public @ResponseBody int modifyRank(@RequestParam(value="bRank") int rank
										,@RequestParam(value="bCode") String code) throws Exception {
		sellService.modifyRank(rank, code);
		
		return rank;
	}

	@RequestMapping(value="/ajax/rankCheck", method= RequestMethod.POST)
	public @ResponseBody boolean rankCheck(@RequestParam(value="rank") int rank,
											@RequestParam(value="announcedCode") String code) throws Exception {
		
		boolean rankCheck = false;
		System.out.println(rank+"<----------int");
		System.out.println(code);

		  List<BidListDTO> rankList = sellService.rankCheck(code); 
		  for(int i=0;i<rankList.size();i++) {
			  System.out.println(rankList.get(i).getbRank());
			  if(rank == rankList.get(i).getbRank()) { rankCheck = true; }

		  }
		  
		return rankCheck;
	}
	@GetMapping("/sell/componentBidderList")
	public String componentBidderList(@RequestParam(value="bCpCode") String code,Model model) throws Exception {
		model.addAttribute("bidder", sellService.getBidderList(code));
		model.addAttribute("component",sellService.getComponentDetail(code));
		return "sell/componentBidderList";
	}
	
	//서류 적합성 수정
	@RequestMapping(value= "/ajax/modifyDocumentCheck" , method= RequestMethod.POST)
	public @ResponseBody String modifyDocumentCheck(@RequestParam(value="bCode") String code,
													@RequestParam(value="documentYn") String check) throws Exception {
		System.out.println(code+"-----"+check);
		sellService.modifyDocumentCheck(code, check);
		return check;
	}
	//입찰자 정보 보기 (회원정보+입찰내용)
	@GetMapping("/sell/bidderDetail")
	public String bidderDetail(@RequestParam(value="bCode") String bCode,
								@RequestParam(value="bPlCode") String bPlCode,
							   Model model) throws Exception {
		model.addAttribute("member", sellService.getBuyerInfoByCode(bCode));
		model.addAttribute("file",sellService.getBidderFileList(bCode));
		model.addAttribute("status",sellService.getPlantAcStatusByCode(bPlCode));
		return "sell/bidderDetail";
	}
	
	
	
	//부품 공고 수정 화면
	@GetMapping("/sell/modifyComponentSell")
	public String modifyComponentSell(@RequestParam(value="bCpCode") String code, Model model)  throws Exception{
		model.addAttribute("component", sellService.getComponentDetail(code));
		
		return "sell/modifyComponentSell";
	}
	
	//부품 공고 수정 처리
	@PostMapping("/sell/modifyComponentSell")
	public String modifyComponentSell(BidComponentDTO bidComponentDto,
									  MultipartHttpServletRequest multipartHttpServletRequest,
									  HttpServletRequest request) throws Exception {
		try {
			sellService.modifyComponentSell(bidComponentDto,multipartHttpServletRequest,request);
		} catch (Exception e) {
		
		}
		return "redirect:/sell/myHistory";
	}
	
	
	//부품 공고 삭제 처리
	@GetMapping("/sell/removeComponentSell")
	public String removeComponentSell(@RequestParam(value="bCpCode") String code) throws Exception {
		sellService.removeComponentSell(code);
		return "redirect:/sell/myHistory";
	}
	
	//부품 판매 공고 등록
	@PostMapping("/sell/componentSell")
	public String regComponentSell(BidComponentDTO bidComponentDTO,
								  MultipartHttpServletRequest multipartHttpServletRequest ,
								  HttpServletRequest request) throws Exception {
		sellService.addComponentApply(bidComponentDTO, multipartHttpServletRequest,request);
		return "redirect:/sell/myHistory";
	}
	
	
	//부품 등록
	@RequestMapping(value="/sell/addComponent", method=RequestMethod.POST)
	public @ResponseBody String regComponentSell ( @RequestParam(value="mId") String mId,
									@RequestParam(value="cpName") String cpName,
									@RequestParam(value="cpInfo") String cpInfo,
									@RequestParam(value="cpMaker") String cpMaker,
									@RequestParam(value="cpMakedate") String cpMakedate,
									@RequestParam(value="cpUsedate") String cpUsedate) throws Exception {
		ComponentDTO componentDto = new ComponentDTO();
		componentDto.setmId(mId);

		componentDto.setCpInfo(cpInfo);
		componentDto.setCpMakedate(cpMakedate);
		componentDto.setCpMaker(cpMaker);
		componentDto.setCpName(cpName);
		componentDto.setCpUsedate(cpUsedate);
		sellService.addComponent(componentDto);
		return "성공";
	}
	//부품 공고 등록 페이지
	@GetMapping("/sell/addComponent")
	public String addComponent(@RequestParam(value="mId") String mId)  throws Exception {
		System.out.println(mId+"<--------------mId");
		return "sell/addComponent";
	}
	
	//발전소 입찰 신청자 목록 보기
	@GetMapping("/sell/plantBidderList")
	public String getBidderList(@RequestParam(value="bPlCode") String code,Model model)  throws Exception{
		model.addAttribute("bidder", sellService.getBidderList(code));
		model.addAttribute("plant", sellService.getBidPlantbyCode(code));
		
		
		return "sell/plantBidderList";
	}
	
	//부품 공고 내용 조회
	@GetMapping("/sell/getBidComponentDetail")
	public String getBidComponentDetail(@RequestParam(value="bCpCode") String code, Model model)  throws Exception{
		model.addAttribute("detail", sellService.getComponentDetail(code));
		return "sell/bidComponentDetail";
	}
	
	//발전소 공고 내용 조회
	@GetMapping("/sell/getBidPlantDetail")
	public String getBidPlantDetail(@RequestParam(value="bPlCode") String code,Model model)  throws Exception{
		
		model.addAttribute("bidPlantDetail", sellService.getBidPlantDetail(code));
		model.addAttribute("file", sellService.getsellerFileList(code));
		return "sell/bidPlantDetail";
	}
	
	//발전소 공고 삭제
	@GetMapping("/sell/removePlantSell")
	public String removePlantSell(@RequestParam (value="bPlCode") String code,
								  @RequestParam (value="groupCode") String groupCode)  throws Exception {
		System.out.println(code+"<---삭제할 공고의 코드");
		sellService.removePlantApply(code,groupCode);
		return "redirect:/sell/myHistory";
	}
	//발전소 공고 수정 처리
	@PostMapping("/sell/modifyPlantSell")
	public String modifyPlantSell(BidPlantDTO bidPlantDto,
								  MultipartHttpServletRequest multipartHttpServletRequest ,
								  HttpServletRequest request) throws Exception {
		sellService.modifyPlantApply(bidPlantDto, multipartHttpServletRequest, request);
		return "redirect:/sell/myHistory";
	}
	
	//발전소 공고 수정 화면 처리
	@GetMapping("/sell/modifyPlantSell")
	public ModelAndView modifyPlantSell(@RequestParam(value="bPlCode") String bPlCode)  throws Exception {
		System.out.println(bPlCode+"<---컨트롤러");
		ModelAndView mv = new ModelAndView("sell/modifyPlantSell");
		mv.addObject("bidPlant", sellService.getBidPlantbyCode(bPlCode));
		return mv;
	}
	
	//발전소 판매 공고 등록
	@PostMapping("/sell/plantSell")
	public String regPlantSell(BidPlantDTO bidPlantDto,
							   MultipartHttpServletRequest multipartHttpServletRequest ,
							   HttpServletRequest request) throws Exception {
	
			sellService.addPlantApply(bidPlantDto, multipartHttpServletRequest,request);

		return "redirect:/sell/myHistory";
	}
	
	//부품 선택 시 해당 부품의 정보를 가져옴
	@RequestMapping(value="/ajax/componentInformation",method = RequestMethod.POST)
	public @ResponseBody ComponentDTO componentInformation(@RequestParam(value="cpCode") String cpCode)  throws Exception{
		return sellService.getComponentInformation(cpCode);
	}
	
	//발전소 공고 등록시 선택한 발전소의 정보를 가져옴
	@RequestMapping(value="/ajax/plantInformation",method = RequestMethod.POST)
	public @ResponseBody BusinessPlantDTO plantUnformation(@RequestParam(value="plantCode") String plantCode)  throws Exception{
		BusinessPlantDTO bzPlantDto =sellService.getPlantInformation(plantCode);
		int residualValue= plantService.residualValue(bzPlantDto.getBzPlCode());
		bzPlantDto.setResidualValue(residualValue);
			
		return bzPlantDto;
	}
	
	 //발전소판매공고신청 버튼 클릭시
	@GetMapping("/sell/plantSell") 
	public ModelAndView plantSell(@RequestParam(name="mId") String mId)  throws Exception{ 
		ModelAndView mv = new ModelAndView("/sell/plantSell");
		List<BusinessPlantDTO> plantList =	sellService.getPlantName(mId); 
		mv.addObject("plantList", plantList);
	  return mv; 
	  
	}

	//부품판매공고신청 버튼 클릭시
	@GetMapping("/sell/componentSell")
	public String componentSell(Model model,HttpServletRequest request)  throws Exception{
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("SID");
		model.addAttribute("component", sellService.getComponent(sessionId));
		return "sell/componentSell";
	}

	@GetMapping("/sell/failureBid")
	public String failureBidList(Model model, HttpSession session) {
		String sessionId= (String)session.getAttribute("SID");
		System.out.println("대체 어디가 문제냐 어?????????????????????짜증나");
		model.addAttribute("plant", sellService.getBidPlantAcById(sessionId));
		return "sell/failureBid";
	}
	
	
	@GetMapping("/sell/apply")
	public String Apply(Model model,HttpSession session) {
		
		model.addAttribute("plant", sellService.getBidPlantAcById((String)session.getAttribute("SID")));
		return "sell/apply";
	}
	
	
	//내공고목록클릭시
	@GetMapping("/sell/myHistory")
	public String MyHistory(Model model,
							HttpSession session,
							String searchKey, 
							String searchValue,
							String searchKeyCp,
							String searchValueCp,
							@ModelAttribute("bidPlantDTO") BidPlantDTO bidPlantDTO,
							@ModelAttribute("bidComponentDTO") BidComponentDTO bidComponentDTO)  throws Exception{
		
		String sessionId = (String)session.getAttribute("SID");
		
		List<BidPlantDTO> bidPlantList  = sellService.getBidPlantbyId(sessionId,searchKey,searchValue,bidPlantDTO);
		List<BidComponentDTO> bidComponentList = sellService.getBidComponentById(sessionId,searchKeyCp,searchValueCp,bidComponentDTO);
		
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidComponentList", bidComponentList);
		
		return "sell/myHistory";
	}

	
	@RequestMapping(value="/ajax/getAccountInfoByNumber", method=RequestMethod.POST)
	public @ResponseBody MemberAccountDTO getAccountInfoByAccount(@RequestParam(value="bankAccount") String number)  throws Exception{
		return sellService.getAccountInfoByAccount(number);
	}
	
	@GetMapping("/sell/applyPayment")
	public String applyPayment(@RequestParam(value="trPrCode") String code, Model model,HttpSession session)  throws Exception{
		model.addAttribute("account", sellService.getMemberAccountById((String)session.getAttribute("SID")));
		model.addAttribute("applyPayment", sellService.getPaymentOutByCode(code));
		return "sell/applyPayment";
	}
	@PostMapping("/sell/applyPayment")
	public String applyPayment(TradePaymentOutDTO trPayOutDto) throws Exception {
		sellService.addApplyPayment(trPayOutDto);
		return "redirect:/sell/paymentList";
	}
	
	
	//출금 가능한 거래 내역 보기
	@GetMapping("/sell/paymentList")
	public String PaymentList(Model model, HttpSession session) throws Exception {
		String sessionId = (String)session.getAttribute("SID");
		//출금가능한목록
		model.addAttribute("available", sellService.getPaymentAvailable(sessionId));
		
		//출금신청한 목록
		model.addAttribute("apply", sellService.getPaymentApplyList(sessionId));
		//출금완료된 목록
		
		model.addAttribute("finish",sellService.getPaymentOutList(sessionId));
		return "sell/paymentList";
	}
	@GetMapping("/sell/qna")
	public String Qna() {
		return "sell/qna";
	}
}
