package com.realestate.re.service.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.enums.EnumGetter;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.city.CityDto;
import com.realestate.re.service.core.city.CityError;
import com.realestate.re.service.core.city.CityService;
import com.realestate.re.service.core.city.CityValidation;
import com.realestate.re.service.core.country.CountryService;

@Controller
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;

	@Autowired
	private CityValidation cityValidation;

	@Autowired
	private CountryService countryService;

	@GetMapping("/")
	public String index() {
		return "redirect:/city/list";
	}

	@GetMapping("/list")
	public String list() {
		return "/city/list";
	}

	@GetMapping("/add")
	public String add(ModelMap map) {
		map.put(StringConstants.COUNTRY_LIST,
				countryService.listOffsetBased(Status.getEnumList(Status.ACTIVE), 5000, 0, "asc", "0"));
		return "/city/add";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("city") CityDto cityDto, BindingResult bindingResult, ModelMap map,
			RedirectAttributes redirectAttributes) throws Exception {

		try {

			synchronized (this.getClass()) {

				if (cityDto == null) {
					redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
					return "redirect:/city/add";
				}

				CityError cityError = cityValidation.onSave(cityDto, bindingResult);
				if (!cityError.isValid()) {
					map.put(StringConstants.CITY, cityDto);
					map.put(StringConstants.CITY_ERROR, cityError);
					return "/city/add";
				}

				cityService.save(cityDto);
				redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "city saved successfully");
			}
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}

		return "redirect:/city/list";
	}

	@GetMapping("/edit/{cityId}")
	public String edit(@PathVariable("cityId") Long cityId, ModelMap map, RedirectAttributes redirectAttributes)
			throws Exception {
		try {

			if (cityId == null) {
				redirectAttributes.addFlashAttribute(StringConstants.ERROR, "bad request");
				return "redirect:/city/list";
			}

			if (cityId < 1) {
				redirectAttributes.addFlashAttribute(StringConstants.ERROR, "bad request");
				return "redirect:/city/list";
			}

			CityDto cityDto = cityService.show(cityId, Status.ACTIVE);

			if (cityDto == null) {
				redirectAttributes.addFlashAttribute(StringConstants.ERROR, "requested record not found");
				return "redirect:/city/list";
			}
			map.put(StringConstants.CITY, cityDto);
			map.put(StringConstants.STATUS_LIST, EnumGetter.getStatusForEdit());
			map.put(StringConstants.COUNTRY_LIST,
					countryService.listOffsetBased(Status.getEnumList(Status.ACTIVE), 5000, 0, "asc", "0"));
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}
		return "/city/edit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("city") CityDto cityDto, BindingResult bindingResult, ModelMap map,
			RedirectAttributes redirectAttributes) throws Exception {

		try {
			synchronized (this.getClass()) {

				if (cityDto == null) {
					redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
					return "redirect:/city/list";
				}

				CityError cityError = cityValidation.onUpdate(cityDto, bindingResult);
				if (!cityError.isValid()) {
					if (cityError.isVersionUnmatched()) {
						redirectAttributes.addFlashAttribute(StringConstants.ERROR, cityError.getMessage());
						return "redirect:/city/edit/" + cityDto.getId();
					}

					map.put(StringConstants.CITY, cityDto);
					map.put(StringConstants.CITY_ERROR, cityError);
					return "/city/edit";
				}

				cityService.update(cityDto);
				redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "city updated successfully");
			}
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}

		return "redirect:/city/list";
	}

	@GetMapping("/delete/{cityId}")
	public String delete(@PathVariable("cityId") Long cityId, @RequestParam("version") Integer version,
			RedirectAttributes redirectAttributes) throws Exception {

		try {

			synchronized (this.getClass()) {
				CityError cityError = cityValidation.onDelete(cityId, version);
				if (!cityError.isValid()) {
					redirectAttributes.addFlashAttribute(StringConstants.ERROR, cityError.getMessage());
					return "redirect:/city/list";
				}

				cityService.delete(cityId);
				redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "city deleted successfully");
			}
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}

		return "redirect:/city/list";
	}
}
