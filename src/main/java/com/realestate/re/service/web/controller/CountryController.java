package com.realestate.re.service.web.controller;

import javax.validation.Valid;

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
import com.realestate.re.service.core.country.CountryDto;
import com.realestate.re.service.core.country.CountryError;
import com.realestate.re.service.core.country.CountryService;
import com.realestate.re.service.core.country.CountryValidation;

@Controller
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryValidation countryValidation;

	@GetMapping("/")
	public String index() {
		return "redirect:/country/list";
	}

	@GetMapping("/list")
	public String list() {
		return "/country/list";
	}

	@GetMapping("/add")
	public String add() {
		return "/country/add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("country") CountryDto countryDto, BindingResult bindingResult, ModelMap map,
			RedirectAttributes redirectAttributes) throws Exception {

		try {

			synchronized (this.getClass()) {

				if (countryDto == null) {
					redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
					return "redirect:/country/add";
				}

				CountryError countryError = countryValidation.onSave(countryDto, bindingResult);
				if (!countryError.isValid()) {
					map.put(StringConstants.COUNTRY, countryDto);
					map.put(StringConstants.COUNTRY_ERROR, countryError);
					return "/country/add";
				}

				countryService.save(countryDto);
				redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "country saved successfully");
			}
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}

		return "redirect:/country/list";
	}

	@GetMapping("/edit/{countryId}")
	public String edit(@PathVariable("countryId") Long countryId, ModelMap map, RedirectAttributes redirectAttributes)
			throws Exception {
		try {

			if (countryId == null) {
				redirectAttributes.addFlashAttribute(StringConstants.ERROR, "bad request");
				return "redirect:/country/list";
			}

			if (countryId < 1) {
				redirectAttributes.addFlashAttribute(StringConstants.ERROR, "bad request");
				return "redirect:/country/list";
			}

			CountryDto countryDto = countryService.show(countryId, Status.ACTIVE);

			if (countryDto == null) {
				redirectAttributes.addFlashAttribute(StringConstants.ERROR, "requested record not found");
				return "redirect:/country/list";
			}
			map.put(StringConstants.COUNTRY, countryDto);
			map.put(StringConstants.STATUS_LIST, EnumGetter.getStatusForEdit());
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}
		return "/country/edit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("country") CountryDto countryDto, BindingResult bindingResult, ModelMap map,
			RedirectAttributes redirectAttributes) throws Exception {

		try {
			synchronized (this.getClass()) {

				if (countryDto == null) {
					redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
					return "redirect:/country/list";
				}

				CountryError countryError = countryValidation.onUpdate(countryDto, bindingResult);
				if (!countryError.isValid()) {
					if (countryError.isVersionUnmatched()) {
						redirectAttributes.addFlashAttribute(StringConstants.ERROR, countryError.getMessage());
						return "redirect:/country/edit/" + countryDto.getId();
					}

					map.put(StringConstants.COUNTRY, countryDto);
					map.put(StringConstants.COUNTRY_ERROR, countryError);
					return "/country/edit";
				}

				countryService.update(countryDto);
				redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "country updated successfully");
			}
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}

		return "redirect:/country/list";
	}

	@GetMapping("/delete/{countryId}")
	public String delete(@PathVariable("countryId") Long countryId, @RequestParam("version") Integer version,
			RedirectAttributes redirectAttributes) throws Exception {

		try {

			synchronized (this.getClass()) {
				CountryError countryError = countryValidation.onDelete(countryId, version);
				if (!countryError.isValid()) {
					redirectAttributes.addFlashAttribute(StringConstants.ERROR, countryError.getMessage());
					return "redirect:/country/list";
				}

				countryService.delete(countryId);
				redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "country deleted successfully");
			}
		} catch (Exception ex) {
			LoggerUtil.logException(this.getClass(), ex);
			throw ex;
		}

		return "redirect:/country/list";
	}
}
