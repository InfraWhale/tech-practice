package me.farmingwhale.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.farmingwhale.springbootdeveloper.domain.RefreshToken;
import me.farmingwhale.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException(("unexpected token")));
    }
}
