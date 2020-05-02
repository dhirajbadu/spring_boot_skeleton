package com.realestate.re.service.common.abstracts;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.ParseUtls;

import java.util.List;

public class AbstractFilterDto extends FilterMapper {

	private List<Status> statusList;

	private Integer max;

	private Integer offset;

	private String direction;

	private String property;

	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void build(String query, Integer max, Integer offset, String direction, String property ,List<Status> statusList){

		this.max = max == null ? 10 : max;
		this.offset = offset == null ? 0 : offset;
		this.direction = ParseUtls.trimString(direction);
		this.property = ParseUtls.trimString(property);
		this.query = ParseUtls.trimString(query);
		this.statusList = statusList;
	}
}
