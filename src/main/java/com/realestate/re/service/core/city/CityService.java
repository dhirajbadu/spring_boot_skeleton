package com.realestate.re.service.core.city;

import java.util.List;

import com.realestate.re.service.common.enums.Status;

public interface CityService {

	CityDto save(CityDto dto) throws Exception;

	CityDto update(CityDto dto) throws Exception;

	CityDto show(long id, Status status) throws Exception;

	void delete(long id) throws Exception;

	List<CityDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction,
			String property);

	List<CityDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property, String direction);

	long countByStatus(List<Status> statusList);

	List<CityDto> findByCountryId(long countryId);
}
