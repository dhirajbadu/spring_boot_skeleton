package com.realestate.re.service.core.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.ConvertUtil;
import com.realestate.re.service.common.utls.LoggerUtil;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CountryConverter countryConverter;

	@Override
	public CountryDto save(CountryDto dto) {
		try {
			return countryConverter.convertToDto(countryRepository.save(countryConverter.convertToEntity(dto)));
		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public CountryDto update(CountryDto dto) {
		try {
			Country country = countryRepository.findById((long) dto.getId());
			country = countryConverter.copyConvertToEntity(dto, country);
			country = countryRepository.save(country);
			return countryConverter.convertToDto(country);

		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public CountryDto show(long id, Status status) {
		try {
			return countryConverter.convertToDto(countryRepository.findByStatusAndId(status, id));
		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public void delete(long id) {
		try {
			Country country = countryRepository.findById(id);
			country.setStatus(Status.INACTIVE);
			countryRepository.save(country);

		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public List<CountryDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction,
			String property) {
		property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(Country.class));
		Pageable pageable = ConvertUtil.createOffsetPageRequest(max, offset, direction, property);
		return countryConverter.convertToDtoList(countryRepository.findAllByStatusIn(statusList, pageable));
	}

	@Override
	public List<CountryDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property,
			String direction) {
		property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(Country.class));
		Pageable pageable = ConvertUtil.createPageRequest(page, size, property, direction);
		return countryConverter.convertToDtoList(countryRepository.findAllByStatusIn(statusList, pageable));
	}

	@Override
	public long countByStatus(List<Status> statusList) {
		return countryRepository.countAllByStatusIn(statusList);
	}
}
