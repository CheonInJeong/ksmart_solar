package com.cafe24.kangk0269.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SavePaging {
	private HttpSession session;
	/** 현재 페이지 번호 */
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	private int recordsPerPage;

	/** 화면 하단에 출력할 페이지 사이즈 */
	private int pageSize;
	public SavePaging(int pagingNumber,HttpSession session) {
		this.session = session;
		for(int i = 1; i<=pagingNumber;i++) {
			Map<String, Object> pagingSave = new HashMap<String, Object>();
			pagingSave.put("currentPageNo", null);
			pagingSave.put("recordsPerPage", null);
			pagingSave.put("pageSize", null);
			session.setAttribute(Integer.toString(i), pagingSave);
		}
	}
	/** 세션에 이전화면의 현재 페이지 번호,페이지당 출력할 데이터 개수,화면 하단에 출력할 페이지 사이즈를 해당하는 리스트 맵객체의 변수에 저장  */
	public void setPaging(int state,int currentPageNo, int recordsPerPage,int pageSize ) {
		this.currentPageNo = currentPageNo;
		this.recordsPerPage = recordsPerPage;
		this.pageSize = pageSize;
		@SuppressWarnings("unchecked")
		Map<String, Object> savePaging = (Map<String, Object>) session.getAttribute(Integer.toString(state));
		savePaging.put("currentPageNo", this.currentPageNo);
		savePaging.put("recordsPerPage", this.recordsPerPage);
		savePaging.put("pageSize", this.pageSize);
		session.setAttribute(Integer.toString(state), savePaging);
	}
	/** 세션에 이전화면의 현재 페이지 번호,페이지당 출력할 데이터 개수,화면 하단에 출력할 페이지 사이즈를 해당하는 리스트 맵객체의 변수를 불러옴  */
	public void getPaging(Criteria criteria) {
		@SuppressWarnings("unchecked")
		Map<String, Object> Paging = (Map<String, Object>) session.getAttribute(Integer.toString(criteria.getState()));
		criteria.setCurrentPageNo((int)Paging.get("currentPageNo"));
		criteria.setPageSize((int)Paging.get("pageSize"));
		criteria.setRecordsPerPage((int)Paging.get("recordsPerPage"));
	}
}
