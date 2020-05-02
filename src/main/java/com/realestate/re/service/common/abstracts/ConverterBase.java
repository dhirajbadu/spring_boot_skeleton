package com.realestate.re.service.common.abstracts;

import com.realestate.re.service.common.utls.ParseUtls;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterBase<E, D> implements IListConvertable<E , D>, IConvertable<E , D> , IPageConvertable<E , D>{

    @Override
    public E convertToEntity(D dto) {
        return null;
    }

    @Override
    public D convertToDto(E entity) {
        return null;
    }

    @Override
    public E copyConvertToEntity(D dto, E entity) {
        return null;
    }

    @Override
    public List<D> convertToDtoList(List<E> entities) {

        if (entities == null){
            return null;
        }

        return entities.parallelStream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<E> convertToEntityList(List<D> dtoList) {

        if (dtoList == null){
            return null;
        }

        if (dtoList.isEmpty()){
            return null;
        }

        return dtoList.parallelStream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public List<D> convertPageToDtoList(Page<E> entities) {

        if (entities == null){
            return null;
        }

        List<D> dtoList = new ArrayList<>();

        for (E e: entities) {
            dtoList.add(convertToDto(e));
        }

        return dtoList;
    }

    protected String trimString(String value){
        return ParseUtls.trimString(value);
    }

    protected double doubleFormatter(Double value){
        return ParseUtls.doubleFormatter(value);
    }
}
