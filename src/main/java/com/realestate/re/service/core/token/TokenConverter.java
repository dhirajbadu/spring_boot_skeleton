package com.realestate.re.service.core.token;

import com.realestate.re.service.common.abstracts.ConverterBase;
import com.realestate.re.service.common.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TokenConverter extends ConverterBase<Token , TokenDto>{

    @Override
    public Token convertToEntity(TokenDto dto) {
        
        if (dto == null){
            return null;
        }
       Token entity = copyConvertToEntity(dto , new Token());

       entity.setStatus(Status.ACTIVE);

        return entity;
    }

    @Override
    public TokenDto convertToDto(Token entity) {
        if (entity == null){
            return null;
        }

        TokenDto dto = new TokenDto();

        dto.setCreated(entity.getCreated());
        dto.setLastModified(entity.getLastModified());
        dto.setVersion(entity.getVersion());
        dto.setId(entity.getId());
        dto.setAssociation(entity.getAssociation());
        dto.setAssociationKey(entity.getAssociationKey());
        dto.setExpireDate(entity.getExpireDate());
        dto.setSendThroughEmail(entity.isSendThroughEmail());
        dto.setStatus(entity.getStatus());
        dto.setToken(entity.getToken());
        dto.setUsed(entity.isUsed());

        //write your code
        return dto;
    }

    @Override
    public Token copyConvertToEntity(TokenDto dto, Token entity) {
        
        if (entity == null || dto == null){
            return null;
        }
        //write your code
        return entity;
    }

    @Override
    public List<TokenDto> convertToDtoList(List<Token> entities) {
        return super.convertToDtoList(entities);
    }

    @Override
    public List<Token> convertToEntityList(List<TokenDto> dtoList) {
        return super.convertToEntityList(dtoList);
    }

    @Override
    public List<TokenDto> convertPageToDtoList(Page<Token> entities) {
        return super.convertPageToDtoList(entities);
    }
}
