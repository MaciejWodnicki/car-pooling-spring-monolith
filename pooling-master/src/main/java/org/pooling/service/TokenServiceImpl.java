package org.pooling.service;

import org.pooling.domain.user.AppUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private Map<String, String> tokenToEmail = new HashMap<>();
    private Map<String, AppUser> pendingUsers = new HashMap<>();

    public String generateToken(String email) {
        String token = UUID.randomUUID().toString();
        tokenToEmail.put(token, email);
        return token;
    }

    public boolean validateToken(String token) {
        return tokenToEmail.containsKey(token);
    }

    public String getEmailForToken(String token) {
        return tokenToEmail.get(token);
    }

    public void storePendingUser(String token, AppUser user) {
        pendingUsers.put(token, user);
    }

    public AppUser getPendingUser(String token) {
        return pendingUsers.get(token);
    }

    public void removeToken(String token) {
        tokenToEmail.remove(token);
        pendingUsers.remove(token);
    }
}
