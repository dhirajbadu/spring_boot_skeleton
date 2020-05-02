package com.realestate.re.service.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	UserInfo findById(long id);

	@Query("select u from UserInfo u left join fetch u.roleSet where u.id = ?1")
	UserInfo findByIdJoinRole(long id);

	UserInfo findByEmail(String email);

	@Query("select u from UserInfo u left join fetch u.roleSet where u.email = ?1")
	UserInfo findByEmailLeftJoinRole(String email);

}
