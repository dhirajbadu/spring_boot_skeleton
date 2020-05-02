package com.realestate.re.service.core.user;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.TokenAssociation;
import com.realestate.re.service.common.service.MailSenderService;
import com.realestate.re.service.common.utls.*;
import com.realestate.re.service.core.token.Token;
import com.realestate.re.service.core.token.TokenDto;
import com.realestate.re.service.core.token.TokenRepository;
import com.realestate.re.service.core.token.TokenService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoConverter userInfoConverter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto save(UserInfoDto dto) {
        return userInfoConverter.convertToDto(userInfoRepository.save(userInfoConverter.convertToEntity(dto)));
    }

    @Override
    public UserInfoDto update(UserInfoDto dto) {
        UserInfo userInfo = userInfoRepository.findById((long) dto.getUserId());
        userInfo = userInfoConverter.copyConvertToEntity(dto , userInfo);
        userInfo = userInfoRepository.save(userInfo);
        return userInfoConverter.convertToDto(userInfo);
    }

    @Override
    public UserInfoDto show(long id) {
        return userInfoConverter.convertToDto(userInfoRepository.findById(id));
    }

    @Override
    public void action(long id, String query) {
        try {

            UserInfo userInfo = userInfoRepository.findById(id);

            if ("enable".equals(query)) {
                userInfo.setEnabled(true);
            } else if ("diseble".equals(query)) {
                userInfo.setEnabled(false);
            } else if ("unExpire".equals(query)) {
                userInfo.setAccountNonExpired(true);
            } else if ("expire".equals(query)) {
                userInfo.setAccountNonExpired(false);
            } else if ("unLock".equals(query)) {
                userInfo.setAccountNonLocked(true);
            } else if ("lock".equals(query)) {
                userInfo.setAccountNonLocked(false);
            }

            userInfoRepository.save(userInfo);
        }catch (Exception e){
            LoggerUtil.logException(this.getClass() , e);
            throw e;
        }
    }

    @Override
    public void delete(long id) {
        UserInfo userInfo = userInfoRepository.findById(id);
        userInfo.setEnabled(false);
        userInfoRepository.save(userInfo);
    }

    @Override
    @Transactional
    public void resetPassword(String token, String password) {
        try {

            Token tokenEntity = tokenRepository.findByToken(token);

            UserInfo userInfo = userInfoRepository.findByEmailLeftJoinRole(tokenEntity.getAssociationKey());

            userInfo.setPassword(passwordEncoder.encode(password));
            userInfo.setExpire(DateParseUtil.addYears(DateParseUtil.getCurrentDateTime(), 1));

            userInfoRepository.save(userInfo);

            tokenEntity.setUsed(true);

            tokenRepository.save(tokenEntity);

        }catch (Exception e){
            LoggerUtil.logException(this.getClass() , e);
            throw e;
        }

    }

    @Override
    public List<UserInfoDto> listOffsetBased(Integer max, Integer offset, String direction, String property) {
        property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(UserInfo.class));
        Pageable pageable = ConvertUtil.createOffsetPageRequest(max, offset, direction, property);
        return userInfoConverter.convertToDtoList(userInfoRepository.findAll(pageable).getContent());
    }

    @Override
    public List<UserInfoDto> listPageBased(Integer page, Integer size, String property, String direction) {
        property = ConvertUtil.getValidProperty(property, ConvertUtil.getField(UserInfo.class));
        Pageable pageable = ConvertUtil.createPageRequest(page, size, property, direction);
        return userInfoConverter.convertToDtoList(userInfoRepository.findAll(pageable).getContent());
    }

    @Override
    public long count() {
        return userInfoRepository.count();
    }

    @Override
    public void resetPasswordTokenizer(String username, HttpServletRequest request) throws Exception {
        Token token = tokenService.save(TokenAssociation.FORGET_PASSWORD , username);
        String url = RequestUtils.getServerUlr(request) + "/auth/password/reset?token=" + token.getToken();
        url = UIParserUtls.simpleAnchorTagGenerator(url , "click here");

        try {
            mailSenderService.userResetPasswordMail(username , url , token.getId());
        }catch (Exception ex){
            LoggerUtil.logException(this.getClass() , ex);
        }
    }
}
