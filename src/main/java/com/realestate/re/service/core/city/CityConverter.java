package com.realestate.re.service.core.city;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.realestate.re.service.common.abstracts.ConverterBase;
import com.realestate.re.service.common.enums.Status;

@Service
public class CityConverter extends ConverterBase<City, CityDto> {

	@Override
	public City convertToEntity(CityDto dto) {

		if (dto == null) {
			return null;
		}
		City entity = copyConvertToEntity(dto, new City());

		entity.setStatus(Status.ACTIVE);

		return entity;
	}

	@Override
	public CityDto convertToDto(City entity) {
		if (entity == null) {
			return null;
		}

		CityDto dto = new CityDto();

		dto.setCreated(entity.getCreated());
		dto.setLastModified(entity.getLastModified());
		dto.setVersion(entity.getVersion());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		
		dto.setCountryId(entity.getCountry().getId());
		dto.setCountryName(entity.getCountry().getName());
		// write your code
		return dto;
	}

	@Override
	public City copyConvertToEntity(CityDto dto, City entity) {

		if (entity == null || dto == null) {
			return null;
		}
		// write your code
		entity.setName(trimString(dto.getName()));
		entity.setStatus(dto.getStatus());
		return entity;
	}

	@Override
	public List<CityDto> convertToDtoList(List<City> entities) {
		return super.convertToDtoList(entities);
	}

	@Override
	public List<City> convertToEntityList(List<CityDto> dtoList) {
		return super.convertToEntityList(dtoList);
	}

	@Override
	public List<CityDto> convertPageToDtoList(Page<City> entities) {
		return super.convertPageToDtoList(entities);
	}
}
