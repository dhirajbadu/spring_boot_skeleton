package com.realestate.re.service.core.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.ConvertUtil;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.country.CountryRepository;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityConverter cityConverter;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public CityDto save(CityDto dto) {
		try {
			City city = cityConverter.convertToEntity(dto);
			city.setCountry(countryRepository.findById(dto.getCountryId()).get());
			return cityConverter.convertToDto(cityRepository.save(city));
		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public CityDto update(CityDto dto) {
		try {
			City city = cityRepository.findById((long) dto.getId());
			city = cityConverter.copyConvertToEntity(dto, city);
			city = cityRepository.save(city);
			return cityConverter.convertToDto(city);

		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public CityDto show(long id, Status status) {
		try {
			return cityConverter.convertToDto(cityRepository.findByStatusAndId(status, id));
		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public void delete(long id) {
		try {
			City city = cityRepository.findById(id);
			city.setStatus(Status.INACTIVE);
			cityRepository.save(city);

		} catch (Exception e) {
			LoggerUtil.logException(this.getClass(), e);
			throw e;
		}
	}

	@Override
	public List<CityDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction,
			String property) {
		property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(City.class));
		Pageable pageable = ConvertUtil.createOffsetPageRequest(max, offset, direction, property);
		return cityConverter.convertToDtoList(cityRepository.findAllByStatusIn(statusList, pageable));
	}

	@Override
	public List<CityDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property,
			String direction) {
		property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(City.class));
		Pageable pageable = ConvertUtil.createPageRequest(page, size, property, direction);
		return cityConverter.convertToDtoList(cityRepository.findAllByStatusIn(statusList, pageable));
	}

	@Override
	public long countByStatus(List<Status> statusList) {
		return cityRepository.countAllByStatusIn(statusList);
	}

	@Override
	public List<CityDto> findByCountryId(long countryId) {
		List<City> list = cityRepository.findByCountryId(countryId);
		return cityConverter.convertToDtoList(list);
	}
}
