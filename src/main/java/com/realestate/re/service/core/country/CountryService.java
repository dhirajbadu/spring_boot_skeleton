package com.realestate.re.service.core.country;

import com.realestate.re.service.common.enums.Status;

import java.util.List;

public interface CountryService {
    
    CountryDto save(CountryDto dto) throws Exception;
    
    CountryDto update(CountryDto dto) throws Exception;
    
    CountryDto show(long id , Status status) throws Exception;
    
    void delete(long id) throws Exception;

    List<CountryDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction, String property);

    List<CountryDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property, String direction);

    long countByStatus(List<Status> statusList);
}
