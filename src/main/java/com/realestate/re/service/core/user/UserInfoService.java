package com.realestate.re.service.core.user;

import com.realestate.re.service.common.enums.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserInfoService {
    
    UserInfoDto save(UserInfoDto dto);
    
    UserInfoDto update(UserInfoDto dto);
    
    UserInfoDto show(long id);

    void action(long id , String query) throws Exception;

    void delete(long id);

    void resetPassword(String token , String password);

    List<UserInfoDto> listOffsetBased(Integer max, Integer offset, String direction, String property);

    List<UserInfoDto> listPageBased(Integer page, Integer size, String property, String direction);

    long count();

    void resetPasswordTokenizer(String username, HttpServletRequest request) throws Exception;
}
