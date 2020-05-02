package com.realestate.re.service.web.controller.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.city.CityDto;
import com.realestate.re.service.core.city.CityFilter;
import com.realestate.re.service.core.city.CityService;

@RestController
@RequestMapping("/api/city")
public class CityRestController {

	@Autowired
	private CityService cityService;

	@GetMapping("/list")
	public Map<String, Object> ajaxList(HttpServletRequest req) {
		try {

			Map<String, Object> resultMap = new HashMap<>();

			CityFilter filter = (CityFilter) new CityFilter().build(req);
			List<CityDto> cityDtoList = cityService.listOffsetBased(Status.getEnumList(Status.ACTIVE), filter.getMax(),
					filter.getOffset(), filter.getDirection(), filter.getProperty());
			List<String[]> arrStr = new ArrayList<>();

			for (CityDto cityDto : cityDtoList) {
				String[] arr = new String[3];

				arr[0] = String.valueOf(cityDto.getId());
				arr[1] = String.valueOf(cityDto.getName());
				arr[2] = String.valueOf(cityDto.getCountryName());
				arrStr.add(arr);

			}

			resultMap.put("data", arrStr);
			resultMap.put("recordsTotal", cityService.countByStatus(Status.getEnumList(Status.ACTIVE)));
			resultMap.put("recordsFiltered", cityService.countByStatus(Status.getEnumList(Status.ACTIVE)));

			return resultMap;
		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@GetMapping("/list/{countryId}")
	public List<CityDto> findByCountryId(@PathVariable("countryId") long countryId) {
		return cityService.findByCountryId(countryId);
	}
}
