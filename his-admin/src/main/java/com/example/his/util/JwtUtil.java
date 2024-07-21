package com.example.his.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    //设置秘钥明文
    public static final String JWT_KEY = "his2024";

    /**
     * 生成jtw: 生成加密后的秘钥 secretKey
     *
     * @param subject token中要存放的数据（json格式）
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUuid());
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUuid());
        return builder.compact();
    }

    /**
     * 生成jtw
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)           //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("his")      // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 解析
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 生成加密后的秘钥 secretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = JwtUtil.JWT_KEY.getBytes(StandardCharsets.UTF_8);
        // 补全密钥至32字节，如果不足32字节，则重复拼接直至达到32字节
        if (encodedKey.length < 32) {
            byte[] fullKey = new byte[32];
            for (int i = 0; i < 32; i++) {
                fullKey[i] = encodedKey[i % encodedKey.length];
            }
            encodedKey = fullKey;
        }

        return new SecretKeySpec(encodedKey, "HmacSHA256");
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        String jwt = createJWT(JWT_KEY);
        System.out.println(jwt);

        Claims claims = parseJWT(jwt);
        String subject = claims.getSubject();
        System.out.println(subject);
    }
}
