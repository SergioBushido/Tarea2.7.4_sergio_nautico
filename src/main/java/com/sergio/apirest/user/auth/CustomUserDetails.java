package com.sergio.apirest.user.auth;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {

  Collection<? extends GrantedAuthority> getAuthorities();

  String getPassword();

  String getEmail();

  String getUsername();

  boolean isAccountNonExpired();

  boolean isAccountNonLocked();

  boolean isCredentialsNonExpired();

  boolean isEnabled();
}
