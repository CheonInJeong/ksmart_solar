package com.cafe24.kangk0269.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SavePaging {
	private HttpSession session;
	private int currentPageNo;
	private int recordsPerPage;
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
	public void getPaging(Criteria criteria) {
		@SuppressWarnings("unchecked")
		Map<String, Object> Paging = (Map<String, Object>) session.getAttribute(Integer.toString(criteria.getState()));
		criteria.setCurrentPageNo((int)Paging.get("currentPageNo"));
		criteria.setPageSize((int)Paging.get("pageSize"));
		criteria.setRecordsPerPage((int)Paging.get("recordsPerPage"));
	}
}
