package org.pooling.service;

import org.pooling.domain.user.AppUser;

public interface TokenService {
    public String generateToken(String email);
    public boolean validateToken(String token);
    public String getEmailForToken(String token);
    public void storePendingUser(String token, AppUser user);
    public AppUser getPendingUser(String token);
    public void removeToken(String token);
}
