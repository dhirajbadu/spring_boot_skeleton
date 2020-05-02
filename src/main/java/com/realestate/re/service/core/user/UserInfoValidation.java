package com.realestate.re.service.core.user;

import com.realestate.re.service.common.abstracts.AbstractValidation;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.core.user.UserInfoDto;
import com.realestate.re.service.core.user.UserInfoError;
import com.realestate.re.service.core.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class UserInfoValidation extends AbstractValidation<UserInfoDto , UserInfoError>{

    @Autowired
    private UserInfoRepository userInfoRepository;

    private UserInfoError error = null;

    @Override
    public UserInfoError onSave(UserInfoDto dto, BindingResult result) {
        error = new UserInfoError();

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
    public UserInfoError onUpdate(UserInfoDto dto, BindingResult result) {
        error = new UserInfoError();

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

        valid = validationUserInfoId(dto.getUserId() , dto.getVersion());

        //write your code

        error.setValid(valid);

        return error;
    }

    @Override
    public UserInfoError onDelete(Long userInfoId, Integer version) {
        error = new UserInfoError();

        if (version == null){
            version = -1;
        }

        boolean valid = validationUserInfoId(userInfoId , version);

        error.setValid(valid);

        return error;
    }

    @Override
    public UserInfoError bindingValidation(BindingResult result) {
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

    private boolean validationUserInfoId(Long value , Integer version){
        error.setMessage(checkLong(value , 1 , "UserInfo Id" , true));

        if (isNotNull(error.getMessage())){
            return false;
        }

        if(version == null){
            version = 0;
        }

        UserInfo userInfo = userInfoRepository.findById(value).orElse(null);

        if (userInfo == null){
            error.setMessage("Provided UserInfo Not Found");
            return false;
        }

        if (userInfo.getVersion() != version){
            error.setMessage("Another User Already Updated !!");
            error.setVersionUnmatched(true);
            return false;
        }

        return true;
    }
}
