package com.cafe24.kangk0269.dto;

import com.cafe24.kangk0269.common.Criteria;
import com.cafe24.kangk0269.common.Pagination;

public class CommonDTO extends Criteria{
	
	private Pagination pagination;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonDTO [pagination=");
		builder.append(pagination);
		builder.append("]");
		return builder.toString();
	}
	

	
}
