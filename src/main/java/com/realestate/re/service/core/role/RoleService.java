package com.realestate.re.service.core.role;

import com.realestate.re.service.common.enums.Status;

import java.util.List;

public interface RoleService {
    
    RoleDto save(RoleDto dto);
    
    RoleDto update(RoleDto dto);
    
    RoleDto show(long id , Status status);
    
    void delete(long id);

    List<RoleDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction, String property);

    List<RoleDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property, String direction);

    long countByStatus(List<Status> statusList);
}
