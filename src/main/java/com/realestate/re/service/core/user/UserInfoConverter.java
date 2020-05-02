package com.realestate.re.service.core.user;

import com.realestate.re.service.common.abstracts.ConverterBase;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.DateParseUtil;
import com.realestate.re.service.core.role.Role;
import com.realestate.re.service.core.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoConverter extends ConverterBase<UserInfo , UserInfoDto>{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserInfo convertToEntity(UserInfoDto dto) {

        if (dto == null){
            return super.convertToEntity(dto);
        }

        UserInfo entity = copyConvertToEntity(dto , new UserInfo());

        entity.setEmail(trimString(dto.getEmail()).toLowerCase());
        entity.setPassword(passwordEncoder.encode(trimString(dto.getPassword())));
        entity.setExpire(DateParseUtil.addYears(DateParseUtil.getCurrentDateTime() , 1));

        return entity;
    }

    @Override
    public UserInfoDto convertToDto(UserInfo entity) {

        if (entity == null) {
            return super.convertToDto(entity);
        }

        UserInfoDto dto = new UserInfoDto();

        dto.setVersion(entity.getVersion());
        dto.setCreated(entity.getCreated());
        dto.setLastModified(entity.getLastModified());
        dto.setUserId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setCreated(entity.getCreated());
        dto.setExpire(entity.getExpire());
        dto.setAccountNonLocked(entity.isAccountNonLocked());
        dto.setEmail(entity.getEmail());
        dto.setAccountNonExpired(entity.isAccountNonExpired());
        dto.setEnabled(entity.isEnabled());
        dto.setUserType(entity.getUserType());
        dto.setPassword(entity.getPassword());

        try {

            dto.setRoleSet(entity.getRoleSet());
            dto.setRoleIdSet(entity.getRoleSet().parallelStream().map(AbstractPersistable::getId).collect(Collectors.toSet()));
        }catch (Exception e){

        }

        return dto;
    }

    @Override
    public UserInfo copyConvertToEntity(UserInfoDto dto, UserInfo entity) {

        if (entity == null || dto == null){
            super.copyConvertToEntity(dto , entity);
        }


        entity.setAccountNonExpired(dto.isAccountNonExpired());
        entity.setAccountNonLocked(dto.isAccountNonLocked());
        entity.setEnabled(dto.isEnabled());
        entity.setUserType(dto.getUserType());
        entity.setExpire(dto.getExpire());
        entity.setRoleSet(new HashSet<Role>(roleRepository.findAllByIdIn(new ArrayList<Long>(dto.getRoleIdSet()))));

        return entity;
    }

    @Override
    public List<UserInfoDto> convertToDtoList(List<UserInfo> entities) {
        return super.convertToDtoList(entities);
    }

    @Override
    public List<UserInfo> convertToEntityList(List<UserInfoDto> dtoList) {
        return super.convertToEntityList(dtoList);
    }

}
