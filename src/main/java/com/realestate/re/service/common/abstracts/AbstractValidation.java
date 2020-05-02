package com.realestate.re.service.common.abstracts;

import org.springframework.validation.BindingResult;

public abstract class AbstractValidation<D , E> extends GlobalValidation{
    public abstract E onSave(D dto , BindingResult result);
    public abstract E onUpdate(D dto , BindingResult result);
    public abstract E onDelete(Long Id , Integer version);
    public abstract E bindingValidation(BindingResult result);
}
