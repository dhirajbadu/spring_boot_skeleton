package com.realestate.re.service.common.abstracts;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dhiraj
 *
 * @param <E>
 * @param <D>
 */
public interface IPageConvertable<E, D> {
    List<D> convertPageToDtoList(Page<E> entities);
}
