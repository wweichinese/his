package com.example.his.util;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author 王伟
 */
@Component
public class JwtUtil {

    //有效期为1小时
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    //设置秘钥明文
    public static final String JWT_KEY = "his2024";

    /**
     * 生成jtw: 生成加密后的秘钥 secretKey
     *
     * @param subject token中要存放的数据（json格式）
     */
    public String createJwt(String uuid, String subject) {
        JwtBuilder builder = getJwtBuilder(uuid, subject);
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     */
    public String createJwt(String subject) {
        JwtBuilder builder = getJwtBuilder(null, subject);
        return builder.compact();
    }

     private JwtBuilder getJwtBuilder(String uuid, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + JwtUtil.JWT_TTL;
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
    public Claims parseJwt(String jwt) {
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

    public static void main(String[] args) {
        //先创建map，里面放用户名，密码，再通过fastjson转成json格式
        Map<String, String> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123456");
        String subject = JSON.toJSONString(map);
        String uuid = UUID.randomUUID().toString();

        /*String jwt = createJwt(uuid, subject);
        System.out.println(jwt);

        Claims claims = parseJwt(jwt);
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());*/
    }
}
