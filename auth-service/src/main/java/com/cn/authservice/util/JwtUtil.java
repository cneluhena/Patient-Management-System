//package com.cn.authservice.util;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//
//import java.security.NoSuchAlgorithmException;
//import java.security.SignatureException;
//import java.util.Base64;
//import java.util.Date;
//
//
//@Component
//public class JwtUtil {
//
//    private final String secret;
//
//    public JwtUtil() throws NoSuchAlgorithmException {
//        KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
//        SecretKey sk = key.generateKey();
//        this.secret = Base64.getEncoder().encodeToString(sk.getEncoded());
//    }
//
//    public String generateToken(String email, String role){
//            return Jwts.builder()
//                    .subject(email)
//                    .claim("role", role)
//                    .issuedAt(new Date())
//                    .expiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
//                    .signWith(getKey())
//                    .compact();
//    }
//
//    private SecretKey getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public void validateToken(String token) {
//        try {
//            Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
//        } catch (JwtException e) {
//            throw new JwtException("Invalid JWT token");
//        }
//    }
//
//}
