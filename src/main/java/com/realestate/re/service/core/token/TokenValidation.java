package com.realestate.re.service.core.token;

import com.realestate.re.service.common.abstracts.AbstractValidation;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.core.token.TokenDto;
import com.realestate.re.service.core.token.TokenError;
import com.realestate.re.service.core.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class TokenValidation extends AbstractValidation<TokenDto , TokenError>{

    @Autowired
    private TokenRepository tokenRepository;

    private TokenError error = null;

    @Override
    public TokenError onSave(TokenDto dto, BindingResult result) {
        error = new TokenError();

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
    public TokenError onUpdate(TokenDto dto, BindingResult result) {
        error = new TokenError();

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

        valid = validationTokenId(dto.getId() , dto.getVersion());

        //write your code

        error.setValid(valid);

        return error;
    }

    @Override
    public TokenError onDelete(Long tokenId, Integer version) {
        error = new TokenError();

        if (version == null){
            version = -1;
        }

        boolean valid = validationTokenId(tokenId , version);

        error.setValid(valid);

        return error;
    }

    @Override
    public TokenError bindingValidation(BindingResult result) {
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

    private boolean validationTokenId(Long value , Integer version){
        error.setMessage(checkLong(value , 1 , "Token Id" , true));

        if (isNotNull(error.getMessage())){
            return false;
        }

        if(version == null){
            version = 0;
        }

        Token token = tokenRepository.findByStatusAndId(Status.ACTIVE , value );

        if (token == null){
            error.setMessage("Provided Token Not Found");
            return false;
        }

        if (token.getVersion() != version){
            error.setMessage("Another User Already Updated !!");
            error.setVersionUnmatched(true);
            return false;
        }

        return true;
    }
}
