package com.realestate.re.service.core.sociallogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLoginRepository extends JpaRepository<SocialLogin , Long>{

    SocialLogin findById(long id);

    SocialLogin findByAuthKey(String authKey);
}
