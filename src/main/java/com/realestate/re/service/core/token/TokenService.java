package com.realestate.re.service.core.token;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.TokenAssociation;

import java.util.Date;
import java.util.List;

public interface TokenService {

    Token save(TokenAssociation association , String associationKey) throws Exception;
    
    TokenDto save(TokenDto dto) throws Exception;

    void updateSendMailStatus(long tokenId , boolean status);
    
    TokenDto update(TokenDto dto) throws Exception;
    
    TokenDto show(long id , Status status) throws Exception;
    
    void delete(long id) throws Exception;

    List<TokenDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction, String property);

    List<TokenDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property, String direction);

    long countByStatus(List<Status> statusList);
}
