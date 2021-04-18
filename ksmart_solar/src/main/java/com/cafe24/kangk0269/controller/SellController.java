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
import com.cafe24.kangk0269.common.SavePaging;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardSellerDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.CommentDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;
import com.cafe24.kangk0269.serivce.BidListService;
import com.cafe24.kangk0269.serivce.BoardSellerService;
import com.cafe24.kangk0269.serivce.PlantService;
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class SellController {
	
	@Autowired
	private final SellService sellService;
	
	@Autowired
	private final PlantService plantService;
	
	@Autowired
	private final BoardSellerService boardSellerService;
	@Autowired
	private final BidListService bidListService;
	
	SavePaging savePaging = null;
	
	public SellController(SellService sellService,PlantService plantService,BoardSellerService boardSellerService,BidListService bidListService) {
		this.sellService = sellService;
		this.plantService = plantService;
		this.boardSellerService = boardSellerService;
		this.bidListService = bidListService;
		
	}
	
	//댓글수 가져오기
	@RequestMapping(value="/ajax/getCmtCount",method=RequestMethod.POST)
	public @ResponseBody int getCmtCount(@RequestParam(value="bIdx") int bIdx) {
		return boardSellerService.getCmtCount(bIdx);
	}
	
	//출금신청 목록 은행 수정
	@RequestMapping(value="/ajax/updateBankInfo",method=RequestMethod.POST)
	public @ResponseBody String updateBankInfo(@RequestParam(value="accountNumber") String accountNumber
											  ,@RequestParam(value="trPayoutCode") String trPayoutCode
											  ,@RequestParam(value="accountBank") String accountBank) {
		sellService.updateBankInfo(accountBank, accountNumber, trPayoutCode);
		
		return "성공";
	}
	
	//출금신청 목록 수정하기 위한 은행 정보 불러오기
	@RequestMapping(value="/ajax/getBankInfo",method = RequestMethod.POST)
	public @ResponseBody List<MemberAccountDTO> getBankInfo(HttpSession session) throws Exception {
		return sellService.getMemberAccountById((String)session.getAttribute("SID"));
	}
	
	//댓글 수정
	@RequestMapping(value="/ajax/modifyCmt" , method = RequestMethod.POST)
	public @ResponseBody String modifyCmt(@RequestParam(value="cmtIdx") int cmtIdx,
										  @RequestParam(value="comment") String comment) {
		boardSellerService.modifyCmt(cmtIdx, comment);
		return comment ;
	}
	
	//대댓글등록
	
	@RequestMapping(value="/ajax/addReCmt", method = RequestMethod.POST)
	public @ResponseBody String addReCmt(@RequestParam(value="boardIdx") int bIdx,
										 @RequestParam(value="comment") String comment,
										 @RequestParam(value="boardId") String boardId,
										 @RequestParam(value="cmtIdx") int cmtIdx,
										 @RequestParam(value="targetId") String targetId,
										 HttpSession session) {
		boardSellerService.addReCmt(bIdx, comment, targetId, (String)session.getAttribute("SID"), cmtIdx);
		return "성공";
	}
	
	
	//댓글등록
	@RequestMapping(value="/ajax/addCmt", method= RequestMethod.POST)
	public @ResponseBody String addCmt(@RequestParam(value="boardIdx") int bIdx,
										@RequestParam(value="comment") String comment,
										@RequestParam(value="boardId") String boardId,
										HttpSession session){
		System.out.println((String)session.getAttribute("SID"));
		boardSellerService.addCmt(bIdx, comment, boardId, (String)session.getAttribute("SID"));
		
		return "성공";
	}
	
	
	
	//댓글삭제
	@GetMapping(value="/sell/removeCmt")
	public String removeCmt(@RequestParam(value="idx") int idx,
							@RequestParam(value="bIdx") int bIdx) {
		boardSellerService.removeCmt(idx);
		return "redirect:/sell/qnaDetail?idx="+bIdx;
	}
	
	//게시글 상세 보기
	@GetMapping(value="/sell/qnaDetail")
	public String qnaDetail(@RequestParam(value="idx") int idx,
							Model model,
							HttpSession session,
							@RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
							@RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
							@RequestParam(value = "pageSize", required = false) 			String pageSize,
							@RequestParam(value = "state", required = false) String state) {
		
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setState(1);
		if(savePaging==null||state==null) {
			savePaging = new SavePaging(1,session);
			savePaging.setPaging(1,1,10,5);
		}
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage), Integer.parseInt(pageSize));
		}
		savePaging.getPaging(commentDTO);
		
		model.addAttribute("qna", boardSellerService.getQnaDetailForSeller(idx));
		model.addAttribute("comments",boardSellerService.getCommentList(idx,commentDTO));
		model.addAttribute("commentDTO",commentDTO);
		
		
		
		return "sell/qnaDetail";
	}
	
	//발전소재공고 처리
	@PostMapping(value="/sell/addNewPlantSell")
	public String addNewPlantBid(BidPlantDTO bidPlantDTO,MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
		sellService.addPlantRebidApply(bidPlantDTO,multipartHttpServletRequest,request);
		
		return "redirect:/sell/myHistory";
	}
	
	//부품 재공고 처리
	@PostMapping(value="/sell/addNewComponentSell")
	public String addNewComponentSell(BidComponentDTO bidComponentDTO,MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request ) throws Exception{
		sellService.addComponentRebidApply(bidComponentDTO, multipartHttpServletRequest, request);
		return "redirect:/sell/myHistory";
	}
	
	//발전소재공고화면
	@GetMapping(value="/sell/addNewPlantSell") 
	public String addNewPlantBid(@RequestParam(value="mId") String mId
								,@RequestParam(value="bzPlcode") String code
								, Model model)  throws Exception {
		System.out.println(mId+"<-----재공고신청 아이디");
		System.out.println(code+"<-----발전소코드");
		
		
		model.addAttribute("plant", sellService.getBidPlantAcByIdCode(mId,code));
		model.addAttribute("residual",plantService.residualValue(code));
		return "sell/addNewPlantSell";
	}
	
	//부품 재공고 화면
	@GetMapping(value="/sell/addNewComponentSell")
	public String addNewComponentSell(@RequestParam(value="mId") String id,
									  @RequestParam(value="cpCode") String code,
									  Model model) {
		
		model.addAttribute("component", sellService.getBidComponentAcByIdCode(id, code));
		
		return "sell/addNewComponentSell";
	}
	
	
	//순위 수정
	@RequestMapping(value="/ajax/modifyRank", method= RequestMethod.POST)
	public @ResponseBody int modifyRank(@RequestParam(value="bRank") int rank
										,@RequestParam(value="bCode") String code) throws Exception {
		sellService.modifyRank(rank, code);
		
		return rank;
	}
	//순위 중복체크
	@RequestMapping(value="/ajax/rankCheck", method= RequestMethod.POST)
	public @ResponseBody boolean rankCheck(@RequestParam(value="rank") int rank,
											@RequestParam(value="announcedCode") String code) throws Exception {
		
		boolean rankCheck = false;

		  List<BidListDTO> rankList = sellService.rankCheck(code); 
		  for(int i=0;i<rankList.size();i++) {
			 
			  if(rankList.get(i).getbRank()==rank) {
				  rankCheck = true; 
			  }
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
		
		System.out.println(bidComponentDTO.getCpCode()+"<---잘들어와야지");
		sellService.addComponentApply(bidComponentDTO, multipartHttpServletRequest,request);
		return "redirect:/sell/myHistory";
	}
	
	
	//부품 등록
	@RequestMapping(value="/ajax/sell/addComponent", method=RequestMethod.POST)
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
		model.addAttribute("file", sellService.getsellerFileList(code));
		model.addAttribute("trade",sellService.getTradeInfo(code));
		model.addAttribute("qna",boardSellerService.getQnaListForSeller(code));
		return "sell/bidComponentDetail";
	}
	
	//발전소 공고 내용 조회
	@GetMapping("/sell/bidPlantDetail")
	public String getBidPlantDetail(@RequestParam(value="bPlCode") String code,Model model)  throws Exception{
		
		model.addAttribute("bidPlantDetail", sellService.getBidPlantDetail(code));
		model.addAttribute("file", sellService.getsellerFileList(code));
		model.addAttribute("trade",sellService.getTradeInfo(code));
		model.addAttribute("qna",boardSellerService.getQnaListForSeller(code));
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
		int residualValue= plantService.residualValue(plantCode);
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
		model.addAttribute("plant", sellService.getBidPlantAcById(sessionId));
		model.addAttribute("component", sellService.getBidComponentAcById(sessionId));
		return "sell/failureBid";
	}
	
	
	@GetMapping("/sell/apply")
	public String Apply(Model model,HttpSession session) {
		String sessionId = (String)session.getAttribute("SID");
		model.addAttribute("plant", sellService.getBidPlantAcById(sessionId));
		model.addAttribute("component", sellService.getBidComponentAcById(sessionId));
		return "sell/apply";
	}
	
	
	//내공고목록클릭시
	@GetMapping("/sell/myHistory")
	public String MyHistory(Model model,
							HttpSession session,
							@RequestParam(value="searchKey", required = false) String searchKey, 
							@RequestParam(value="searchValue", required = false) String searchValue,
							@RequestParam(value="searchKeyCp", required = false) String searchKeyCp,
							@RequestParam(value="searchValueCp", required = false) String searchValueCp,
							@RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
							@RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
							@RequestParam(value = "pageSize", required = false) 			String pageSize,
							@RequestParam(value = "state", required = false) 				String state
																											)  throws Exception{
		
		BidPlantDTO bidPlantDTO = new BidPlantDTO();
		BidComponentDTO bidComponentDTO = new BidComponentDTO();
		bidPlantDTO.setState(1);
		bidComponentDTO.setState(2);
		if(savePaging==null || state==null) {
			//화면의 제일 처름 페이지 설정
			System.out.println("세이브페이징 만들어짐");
			savePaging = new SavePaging(2,session);
			savePaging.setPaging(1, 1, 5, 5);//첫번째 리스트, 맨처음페이징번호,한번에 몇개 보여줄지, 버튼몇개
			savePaging.setPaging(2, 1, 5, 5);
		}
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			//페이지가 넘어갈때 넘어간 페이지 저장
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage),Integer.parseInt(pageSize));
		}
		//페이지 저장 가져오기
		savePaging.getPaging(bidComponentDTO);
		savePaging.getPaging(bidPlantDTO);
		
		if(searchKey != null && searchKey.equals("null")) {
			searchKey = null;
		}
		if(searchValue != null && searchValue.equals("null")) {
			searchValue = null;
		}
		if(searchKeyCp != null && searchKeyCp.equals("null")) {
			searchKeyCp = null;
		}
		if(searchValueCp != null && searchValueCp.equals("null")) {
			searchValueCp = null;
		}
		
		List<BidPlantDTO> bidPlantList =null;
		List<BidComponentDTO> bidComponentList =null;
		String sessionId = (String)session.getAttribute("SID");
		
		if(sessionId!=null) {
		 bidPlantList  = sellService.getBidPlantbyId(sessionId,searchKey,searchValue,bidPlantDTO);
		 bidComponentList = sellService.getBidComponentById(sessionId,searchKeyCp,searchValueCp,bidComponentDTO);
		}
		
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidComponentList", bidComponentList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("searchKeyCp", searchKeyCp);
		model.addAttribute("searchValueCp", searchValueCp);
		model.addAttribute("bidComponentDTO", bidComponentDTO);
		model.addAttribute("bidPlantDTO", bidPlantDTO);
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
	public String PaymentList(Model model, 
							  HttpSession session,
							  @RequestParam(value="searchKey", required = false) 			String searchKey,
							  @RequestParam(value="searchValue", required = false) 			String searchValue,
							  @RequestParam(value="searchKeyApply", required = false) 		String searchKeyApply,
							  @RequestParam(value="searchValueApply", required = false) 	String searchValueApply,
							  @RequestParam(value="searchKeyFinish", required = false) 		String searchKeyFinish,
							  @RequestParam(value="searchValueFinish", required = false)	String searchValueFinish,
							  @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
							  @RequestParam(value = "recordsPerPage", required = false) 	String recordsPerPage,
							  @RequestParam(value = "pageSize", required = false) 			String pageSize,
							  @RequestParam(value = "state", required = false) 				String state
							  ) throws Exception {
		
		TradePriorityDTO search= new TradePriorityDTO();
		TradePriorityDTO searchApply= new TradePriorityDTO();
		TradePriorityDTO searchFinish= new TradePriorityDTO();
		
		search.setState(1);
		searchApply.setState(2);
		searchFinish.setState(3);

		if(savePaging==null || state==null) {
			savePaging = new SavePaging(3,session);
			savePaging.setPaging(1, 1, 5, 5);//첫번째 리스트, 맨처음페이징번호,한번에 몇개 보여줄지, 버튼몇개
			savePaging.setPaging(2, 1, 5, 5);
			savePaging.setPaging(3, 1, 5, 5);
		}
		
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
		savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage),Integer.parseInt(pageSize));
		}
		
		
		//페이지 저장 가져오기
		savePaging.getPaging(search);
		savePaging.getPaging(searchApply);
		savePaging.getPaging(searchFinish);

		if(searchKey != null && searchKey.equals("null")) {
		searchKey = null;
		}
		if(searchValue != null && searchValue.equals("null")) {
		searchValue = null;
		}
		if(searchKeyApply != null && searchKeyApply.equals("null")) {
			searchKeyApply = null;
		}
		if(searchValueApply != null && searchValueApply.equals("null")) {
			searchValueApply = null;
		}
		if(searchKeyFinish != null && searchKeyFinish.equals("null")) {
			searchKeyFinish = null;
		}
		if(searchValueFinish != null && searchValueFinish.equals("null")) {
			searchValueFinish = null;
		}

			
		
		String sessionId = (String)session.getAttribute("SID");
		//출금가능한목록
		model.addAttribute("available", sellService.getPaymentAvailable(sessionId, searchKey, searchValue, search));
		//출금신청한 목록
		model.addAttribute("apply", sellService.getPaymentApplyList(sessionId, searchKeyApply, searchValueApply, searchApply));
		//출금완료된 목록
		model.addAttribute("finish",sellService.getPaymentOutList(sessionId, searchKeyFinish, searchValueFinish, searchFinish));
		model.addAttribute("search",search);
		model.addAttribute("searchApply",searchApply);
		model.addAttribute("searchFinish",searchFinish);
		return "sell/paymentList";
	}
	@GetMapping("/sell/qna")
	public String Qna(Model model, 
					  HttpSession session,
					  @RequestParam(value="searchValue", required=false) String searchValue,
					  @RequestParam(value="searchKey", required=false) String searchKey,
					  @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
					  @RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
					  @RequestParam(value = "pageSize", required = false) 			String pageSize,
					  @RequestParam(value = "state", required = false) String state) {
		
		BoardSellerDTO boardSellerDTO = new BoardSellerDTO(); 
		boardSellerDTO.setState(1);
		if(savePaging==null||state==null) {
			savePaging = new SavePaging(1,session);
			savePaging.setPaging(1,1,10,5);
		}
		
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage), Integer.parseInt(pageSize));
		}
		
		savePaging.getPaging(boardSellerDTO);
		
		if(searchKey!=null && searchKey.equals("null")) {
			searchKey =null;
		}
		if(searchValue != null && searchValue.equals("null")) {
			searchValue = null;
		}
		
		
		model.addAttribute("qnaList",boardSellerService.getQnaListById("sell",(String)session.getAttribute("SID"), searchKey, searchValue, boardSellerDTO));
		model.addAttribute("boardSellerDTO",boardSellerDTO);
		model.addAttribute("searchKey",searchKey);
		model.addAttribute("searchValue",searchValue);
		
		return "sell/qna";
	}
	@RequestMapping(value="/ajax/modifyQna",method = RequestMethod.POST)
	public @ResponseBody int modifyQna(HttpSession session,BoardSellerDTO boardSellerDTO) throws Exception {
		return bidListService.modifyQna((String)session.getAttribute("SID"), boardSellerDTO.getbSubject(), boardSellerDTO.getbContents(), boardSellerDTO.getbIdx());
	}
	@PostMapping("/sell/removeQna")
	public String removeQna(int bIdx, String url) {
		bidListService.removeQna(bIdx);
		return "redirect:"+url;
	}
	
}
