package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;

public class UserDetailsImpl implements UserDetails {
	
	
	
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	
	
	public UserDetailsImpl(User user) {
		username = user.getUserId();
		password =  "{noop}"+ user.getPassword();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return password;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return username;
	}

}
