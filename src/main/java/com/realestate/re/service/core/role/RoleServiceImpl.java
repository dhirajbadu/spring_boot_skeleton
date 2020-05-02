package com.realestate.re.service.core.role;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public RoleDto save(RoleDto dto) {
        return roleConverter.convertToDto(roleRepository.save(roleConverter.convertToEntity(dto)));
    }

    @Override
    public RoleDto update(RoleDto dto) {
        Role role = roleRepository.findById((long) dto.getId());
        role = roleConverter.copyConvertToEntity(dto , role);
        role = roleRepository.save(role);
        return roleConverter.convertToDto(role);
    }

    @Override
    public RoleDto show(long id , Status status) {
        return roleConverter.convertToDto(roleRepository.findByStatusAndId(status , id));
    }

    @Override
    public void delete(long id) {
        Role role = roleRepository.findById(id);
        role.setStatus(Status.INACTIVE);
        roleRepository.save(role);
    }

    @Override
    public List<RoleDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction, String property) {
        property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(Role.class));
        Pageable pageable = ConvertUtil.createOffsetPageRequest(max, offset, direction, property);
        return roleConverter.convertToDtoList(roleRepository.findAllByStatusIn(statusList, pageable));
    }

    @Override
    public List<RoleDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property, String direction) {
        property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(Role.class));
        Pageable pageable = ConvertUtil.createPageRequest(page, size, property, direction);
        return roleConverter.convertToDtoList(roleRepository.findAllByStatusIn(statusList, pageable));
    }

    @Override
    public long countByStatus(List<Status> statusList) {
        return roleRepository.countAllByStatusIn(statusList);
    }
}
