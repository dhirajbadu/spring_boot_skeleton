package com.realestate.re.service.core.token;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.TokenAssociation;
import com.realestate.re.service.common.utls.ConvertUtil;
import com.realestate.re.service.common.utls.DateParseUtil;
import com.realestate.re.service.common.utls.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenConverter tokenConverter;

    @Override
    public Token save(TokenAssociation association, String associationKey) throws Exception {
        Token entity = null;
        try {

            List<Token> previousList = tokenRepository.findAllByAssociationKeyAndAssociation(associationKey, association);

            if (previousList != null) {
                for (int i = 0; i < previousList.size(); i++) {
                    Token previous = previousList.get(i);
                    tokenRepository.delete(previous);
                }
            }

            String token = ConvertUtil.getRandomCode(10);
            while (tokenRepository.findByToken(token) != null) {
                token = ConvertUtil.getRandomCode(10);
            }
            entity = new Token();

            entity.setStatus(Status.ACTIVE);
            entity.setAssociation(association);
            entity.setAssociationKey(associationKey);
            entity.setExpireDate(DateParseUtil.addHours(DateParseUtil.getCurrentDateTime(), 1));
            entity.setSendThroughEmail(false);
            entity.setToken(token);
            entity.setUsed(false);

            entity = tokenRepository.save(entity);

        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }

        return entity;
    }

    @Override
    public TokenDto save(TokenDto dto) {
        try {
            return tokenConverter.convertToDto(tokenRepository.save(tokenConverter.convertToEntity(dto)));
        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }

    @Override
    public void updateSendMailStatus(long tokenId, boolean status) {
        Token token = tokenRepository.findById(tokenId);

        token.setSendThroughEmail(status);

        tokenRepository.save(token);
    }

    @Override
    public TokenDto update(TokenDto dto) {
        try {
            Token token = tokenRepository.findById((long) dto.getId());
            token = tokenConverter.copyConvertToEntity(dto, token);
            token = tokenRepository.save(token);
            return tokenConverter.convertToDto(token);

        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }

    @Override
    public TokenDto show(long id, Status status) {
        try {
            return tokenConverter.convertToDto(tokenRepository.findByStatusAndId(status, id));
        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }

    @Override
    public void delete(long id) {
        try {
            Token token = tokenRepository.findById(id);
            token.setStatus(Status.INACTIVE);
            tokenRepository.save(token);

        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }

    @Override
    public List<TokenDto> listOffsetBased(List<Status> statusList, Integer max, Integer offset, String direction, String property) {
        property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(Token.class));
        Pageable pageable = ConvertUtil.createOffsetPageRequest(max, offset, direction, property);
        return tokenConverter.convertToDtoList(tokenRepository.findAllByStatusIn(statusList, pageable));
    }

    @Override
    public List<TokenDto> listPageBased(List<Status> statusList, Integer page, Integer size, String property, String direction) {
        property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(Token.class));
        Pageable pageable = ConvertUtil.createPageRequest(page, size, property, direction);
        return tokenConverter.convertToDtoList(tokenRepository.findAllByStatusIn(statusList, pageable));
    }

    @Override
    public long countByStatus(List<Status> statusList) {
        return tokenRepository.countAllByStatusIn(statusList);
    }
}
