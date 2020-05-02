package com.realestate.re.service.core.city;

import com.realestate.re.service.common.abstracts.AbstractValidation;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.core.city.CityDto;
import com.realestate.re.service.core.city.CityError;
import com.realestate.re.service.core.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class CityValidation extends AbstractValidation<CityDto , CityError>{

    @Autowired
    private CityRepository cityRepository;

    private CityError error = null;

    @Override
    public CityError onSave(CityDto dto, BindingResult result) {
        error = new CityError();

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
    public CityError onUpdate(CityDto dto, BindingResult result) {
        error = new CityError();

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

        valid = validationCityId(dto.getId() , dto.getVersion());

        //write your code

        error.setValid(valid);

        return error;
    }

    @Override
    public CityError onDelete(Long cityId, Integer version) {
        error = new CityError();

        if (version == null){
            version = -1;
        }

        boolean valid = validationCityId(cityId , version);

        error.setValid(valid);

        return error;
    }

    @Override
    public CityError bindingValidation(BindingResult result) {
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

    private boolean validationCityId(Long value , Integer version){
        error.setMessage(checkLong(value , 1 , "City Id" , true));

        if (isNotNull(error.getMessage())){
            return false;
        }

        if(version == null){
            version = 0;
        }

        City city = cityRepository.findByStatusAndId(Status.ACTIVE , value );

        if (city == null){
            error.setMessage("Provided City Not Found");
            return false;
        }

        if (city.getVersion() != version){
            error.setMessage("Another User Already Updated !!");
            error.setVersionUnmatched(true);
            return false;
        }

        return true;
    }
}
