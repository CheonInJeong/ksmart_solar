package com.cafe24.kangk0269.common;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;

public class Criteria {
	private int state;
	/** 현재 페이지 번호 */
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	private int recordsPerPage;

	/** 화면 하단에 출력할 페이지 사이즈 */
	private int pageSize;

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Criteria [currentPageNo=");
		builder.append(currentPageNo);
		builder.append(", recordsPerPage=");
		builder.append(recordsPerPage);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append("]");
		return builder.toString();
	}

	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 5;
		this.pageSize = 5;
	}

	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}
	
	public String makeQueryString(int pageNo) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage", recordsPerPage)
				.queryParam("pageSize", pageSize)
				.queryParam("state", state)
				.build()
				.encode();

		return uriComponents.toUriString();
	}


}
