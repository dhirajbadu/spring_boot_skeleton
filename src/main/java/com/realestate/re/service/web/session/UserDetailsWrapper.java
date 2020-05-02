package com.realestate.re.service.web.session;

import com.realestate.re.service.common.utls.DateParseUtil;
import com.realestate.re.service.core.user.UserInfoDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class UserDetailsWrapper implements UserDetails, Serializable, Comparable<UserDetailsWrapper> {

	private static final long serialVersionUID = 1L;
	private final Collection<GrantedAuthority> authorities;
	private final UserInfoDto user ;
	private String remoteAddress;

	public UserDetailsWrapper(UserInfoDto user, Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	public UserDetailsWrapper(UserInfoDto user, Collection<GrantedAuthority> authorities, String remoteAddress) {
		this.user = user;
		this.authorities = authorities;
		this.remoteAddress = remoteAddress;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return DateParseUtil.validateBeforeCurrentDate(user.getExpire());
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public UserInfoDto getUser() {
		return user;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserDetailsWrapper) {
			return ((UserDetailsWrapper) obj).getUser().getUserId().equals(user.getUserId());
		}
		return false;
	}

	@Override
	public int compareTo(UserDetailsWrapper o) {
		return 0;
	}

	@Override
	public String toString() {
		return "user" + user.getEmail();
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(user.getUserId() + "");
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

}
