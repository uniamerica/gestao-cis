package com.cis.config.jwtConfig;

import com.cis.exceptions.BadRequestException;
import com.cis.model.User;
import com.cis.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret}") // application.properties
    private String SECRET_KEY;

    @Autowired
    private UserRepository userRepository;

    // Acessar o username do Payload
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Acessar o expiration time do Payload
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Acessar todo o Payload do Token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Verificar se o Token é válido
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Verificar a validade do Token
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Gerar um novo Token
    public String generateToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();

        GrantedAuthority grantedAuthority = user.getAuthorities().stream().findFirst().get();

        claims.put("role", grantedAuthority.getAuthority());
        claims.put("email", user.getUsername());

        return createToken(claims, user.getUsername());
    }

    // Criar um novo Token
    private String createToken(Map<String, Object> claims, String username) {

        return Jwts //
                .builder() //
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 12 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) //
                .compact();
    }

    // Verificar Validade do Token
    public Boolean validateToken(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}
