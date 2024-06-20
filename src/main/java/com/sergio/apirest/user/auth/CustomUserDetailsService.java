package com.sergio.apirest.user.auth;

import com.sergio.apirest.repositories.UserRepository;
import com.sergio.apirest.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CustomUserDetailsImpl loadUserByUsername(final String username) throws UsernameNotFoundException {
    final User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new CustomUserDetailsImpl(user);
  }

  public CustomUserDetailsImpl loadUserByEmail(final String email) throws UsernameNotFoundException {
    final User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new CustomUserDetailsImpl(user);
  }
}
