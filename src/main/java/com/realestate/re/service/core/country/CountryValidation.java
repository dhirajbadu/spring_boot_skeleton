package com.realestate.re.service.core.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.realestate.re.service.common.abstracts.AbstractValidation;
import com.realestate.re.service.common.enums.Status;

@Service
public class CountryValidation extends AbstractValidation<CountryDto, CountryError> {

	@Autowired
	private CountryRepository countryRepository;

	private CountryError error = null;

	@Override
	public CountryError onSave(CountryDto dto, BindingResult result) {
		error = new CountryError();

		boolean valid = true;

		if (dto == null) {
			error.setValid(false);
			error.setMessage("Please fill the form");

			return error;
		}

		error = bindingValidation(result);

		if (!error.isValid()) {
			return error;
		}

		// write your code

		error.setValid(valid);

		return error;
	}

	@Override
	public CountryError onUpdate(CountryDto dto, BindingResult result) {
		error = new CountryError();

		boolean valid = true;

		if (dto == null) {
			error.setValid(false);
			error.setMessage("Please fill the form");

			return error;
		}

		error = bindingValidation(result);

		if (!error.isValid()) {
			return error;
		}

		if (dto.getVersion() == null) {
			dto.setVersion(0);
		}

		valid = valid && validationCountryId(dto.getId(), dto.getVersion());

		// write your code

		error.setValid(valid);

		return error;
	}

	@Override
	public CountryError onDelete(Long countryId, Integer version) {
		error = new CountryError();

		if (version == null) {
			version = -1;
		}

		boolean valid = validationCountryId(countryId, version);

		error.setValid(valid);

		return error;
	}

	@Override
	public CountryError bindingValidation(BindingResult result) {
		boolean valid = true;
		if (result.hasErrors()) {

			List<FieldError> errors = result.getFieldErrors();

			for (FieldError errorResult : errors) {

				// write your code

				if (errorResult.getField().equals("name")) {
					error.setMessage("Name Field Empty");
					error.setName("Name Required");
					valid = false;
				}

			}

		}
		error.setValid(valid);

		return error;
	}

	private boolean validationCountryId(Long value, Integer version) {
		error.setMessage(checkLong(value, 1, "Country Id", true));

		if (isNotNull(error.getMessage())) {
			return false;
		}

		if (version == null) {
			version = 0;
		}

		Country country = countryRepository.findByStatusAndId(Status.ACTIVE, value);

		if (country == null) {
			error.setMessage("Provided Country Not Found");
			return false;
		}

		/*
		 * if (country.getVersion() != version) {
		 * error.setMessage("Another User Already Updated !!");
		 * error.setVersionUnmatched(true); return false; }
		 */

		return true;
	}

}
