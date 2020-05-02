package com.realestate.re.service.web.controller.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.country.CountryDto;
import com.realestate.re.service.core.country.CountryFilter;
import com.realestate.re.service.core.country.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryRestController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/list")
	public Map<String, Object> ajaxList(HttpServletRequest req) {
		try {

			Map<String, Object> resultMap = new HashMap<>();

			CountryFilter filter = (CountryFilter) new CountryFilter().build(req);
			List<CountryDto> countryDtoList = countryService.listOffsetBased(Status.getEnumList(Status.ACTIVE),
					filter.getMax(), filter.getOffset(), filter.getDirection(), filter.getProperty());
			List<String[]> arrStr = new ArrayList<>();

			for (CountryDto countryDto : countryDtoList) {
				String[] arr = new String[2];

				arr[0] = String.valueOf(countryDto.getId());
				arr[1] = String.valueOf(countryDto.getName());
				arrStr.add(arr);

			}

			resultMap.put("data", arrStr);
			resultMap.put("recordsTotal", countryService.countByStatus(Status.getEnumList(Status.ACTIVE)));
			resultMap.put("recordsFiltered", countryService.countByStatus(Status.getEnumList(Status.ACTIVE)));

			return resultMap;
		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}
}
