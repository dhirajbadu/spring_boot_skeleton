package com.realestate.re.service.core.role;

import com.realestate.re.service.common.abstracts.AbstractValidation;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.core.role.RoleDto;
import com.realestate.re.service.core.role.RoleError;
import com.realestate.re.service.core.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class RoleValidation extends AbstractValidation<RoleDto , RoleError>{

    @Autowired
    private RoleRepository roleRepository;

    private RoleError error = null;

    @Override
    public RoleError onSave(RoleDto dto, BindingResult result) {
        error = new RoleError();

        boolean valid = true;

        if (dto == null){
            error.setValid(false);
            error.setMessage("Please fill the form");

            return error;
        }

        error = bindingValidation(result);

        if (!error.isValid()) {
            return error;
        }

        //write your code

        error.setValid(valid);

        return error;
    }

    @Override
    public RoleError onUpdate(RoleDto dto, BindingResult result) {
        error = new RoleError();

        boolean valid = true;

        if (dto == null){
            error.setValid(false);
            error.setMessage("Please fill the form");

            return error;
        }

        error = bindingValidation(result);

        if (!error.isValid()) {
            return error;
        }

        if (dto.getVersion() == null){
            dto.setVersion(0);
        }

        valid = validationRoleId(dto.getId() , dto.getVersion());

        //write your code

        error.setValid(valid);

        return error;
    }

    @Override
    public RoleError onDelete(Long roleId, Integer version) {
        error = new RoleError();

        if (version == null){
            version = -1;
        }

        boolean valid = validationRoleId(roleId , version);

        error.setValid(valid);

        return error;
    }

    @Override
    public RoleError bindingValidation(BindingResult result) {
        boolean valid = true;
        if (result.hasErrors()) {

            List<FieldError> errors = result.getFieldErrors();

            for (FieldError errorResult : errors) {

                //write your code
                /*if (errorResult.getField().equals("name")) {
                    error.setName("Invalid name");
                    valid = false;
                }*/
            }

        }
        error.setValid(valid);

        return error;
    }

    private boolean validationRoleId(Long value , Integer version){
        error.setMessage(checkLong(value , 1 , "Role Id" , true));

        if (isNotNull(error.getMessage())){
            return false;
        }

        if(version == null){
            version = 0;
        }

        Role role = roleRepository.findByStatusAndId(Status.ACTIVE , value );

        if (role == null){
            error.setMessage("Provided Role Not Found");
            return false;
        }

        if (role.getVersion() != version){
            error.setMessage("Another User Already Updated !!");
            error.setVersionUnmatched(true);
            return false;
        }

        return true;
    }
}
