package com.xxxx.config.security.component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    //设置载荷主体  {"sub":""}
    private static final String CLAIM_KEY_USERNAME = "sub";
    //创建日期  {"created":""}
    private static final String CLAIM_KEY_CREATED = "created";
    //加解密使用的密钥
    @Value("${jwt.secret}")
    private String secret;
    //失效时间
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     *
     * @return true 有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && isNotExpiredToken(token);
    }

    /**
     * 判断token是否可以被刷新
     *
     * @return true 可以被刷新
     */
    public boolean canRefresh(String token) {
        return isNotExpiredToken(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date()); //更新时间
        return generateToken(claims);
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据过期时间判断token是否失效
     *
     * @return true 时间未过期，token有效
     */
    private boolean isNotExpiredToken(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.after(new Date());
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 生成token过期时间
     */
    private Date generateExpirationDate() {
        //配置的时间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 根据负载生成JWT Token
     *
     * @param claims 参数
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


}
