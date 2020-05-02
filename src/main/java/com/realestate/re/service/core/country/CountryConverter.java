package com.realestate.re.service.core.country;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.realestate.re.service.common.abstracts.ConverterBase;
import com.realestate.re.service.common.enums.Status;

@Service
public class CountryConverter extends ConverterBase<Country, CountryDto> {

	@Override
	public Country convertToEntity(CountryDto dto) {

		if (dto == null) {
			return null;
		}
		Country entity = copyConvertToEntity(dto, new Country());

		entity.setStatus(Status.ACTIVE);

		return entity;
	}

	@Override
	public CountryDto convertToDto(Country entity) {
		if (entity == null) {
			return null;
		}

		CountryDto dto = new CountryDto();

		dto.setCreated(entity.getCreated());
		dto.setLastModified(entity.getLastModified());
		dto.setVersion(entity.getVersion());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		// write your code
		return dto;
	}

	@Override
	public Country copyConvertToEntity(CountryDto dto, Country entity) {

		if (entity == null || dto == null) {
			return null;
		}
		// write your code
		entity.setName(trimString(dto.getName()));
		entity.setStatus(dto.getStatus());
		return entity;
	}

	@Override
	public List<CountryDto> convertToDtoList(List<Country> entities) {
		return super.convertToDtoList(entities);
	}

	@Override
	public List<Country> convertToEntityList(List<CountryDto> dtoList) {
		return super.convertToEntityList(dtoList);
	}

	@Override
	public List<CountryDto> convertPageToDtoList(Page<Country> entities) {
		return super.convertPageToDtoList(entities);
	}
}
