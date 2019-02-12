package com.elmorabit.ensak.services;

import com.elmorabit.ensak.domain.*;

public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppAuthority saveAuthority(AppAuthority role);
    public void addAuthorityToUser(Long id, String authority);
    public AppUser findUserByUsername(String username);
    public void changePassword(String currentClearTextPassword, String newPassword);
    public String refreshToken();
}
