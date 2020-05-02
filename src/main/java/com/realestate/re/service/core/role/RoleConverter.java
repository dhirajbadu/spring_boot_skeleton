package com.realestate.re.service.core.role;

import com.realestate.re.service.common.abstracts.ConverterBase;
import com.realestate.re.service.common.enums.Permission;
import com.realestate.re.service.common.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleConverter extends ConverterBase<Role , RoleDto>{

    @Override
    public Role convertToEntity(RoleDto dto) {
        
        if (dto == null){
            return null;
        }
       Role entity = copyConvertToEntity(dto , new Role());

       entity.setStatus(Status.ACTIVE);

        return entity;
    }

    @Override
    public RoleDto convertToDto(Role entity) {
        if (entity == null){
            return null;
        }

        RoleDto dto = new RoleDto();

        dto.setCreated(entity.getCreated());
        dto.setLastModified(entity.getLastModified());
        dto.setVersion(entity.getVersion());
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setPermissionList(new ArrayList<Permission>(entity.getPermissionSet()));

        return dto;
    }

    @Override
    public Role copyConvertToEntity(RoleDto dto, Role entity) {
        
        if (entity == null || dto == null){
            return null;
        }

        entity.setTitle(trimString(dto.getTitle()));
        entity.setPermissionSet(new HashSet<Permission>(dto.getPermissionList()));

        return entity;
    }

    @Override
    public List<RoleDto> convertToDtoList(List<Role> entities) {
        return super.convertToDtoList(entities);
    }

    @Override
    public List<Role> convertToEntityList(List<RoleDto> dtoList) {
        return super.convertToEntityList(dtoList);
    }

    @Override
    public List<RoleDto> convertPageToDtoList(Page<Role> entities) {
        return super.convertPageToDtoList(entities);
    }
}
