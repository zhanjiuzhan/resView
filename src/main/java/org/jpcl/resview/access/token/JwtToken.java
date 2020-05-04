package org.jpcl.resview.access.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author Administrator
 */
public class JwtToken {

    /**
     * 做成token
     * @param userId
     * @return
     */
    public static String generateToken(String userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + TokenConst.EXPIRE * 1000);
        String token;
        token = Jwts.builder()
                // header
                // 声明类型typ，表示这个令牌（token）的类型（type），JWT令牌统一写为JWT
                .setHeaderParam("typ", "JWT")
                // 声明加密的算法alg，通常直接使用HMACSHA256，就是HS256了，也可以使用RSA, 支持很多算法
                // (HS256、HS384、HS512、RS256、RS384、RS512、ES256、ES384、ES512、PS256、PS384)
                .setHeaderParam("alg", "hs512")

                // payload
                // jwt签发者
                .setIssuer(TokenConst.SUB_USER)
                .setSubject(userId)
                .setIssuedAt(nowDate)
                // jwt的过期时间，这个过期时间必须要大于签发时间
                .setExpiration(expireDate)

                // signature
                .signWith(SignatureAlgorithm.HS512, TokenConst.SECRET)
                .compact();

        return token;
    }

    /**
     * 从token中取得信息
     * @param token
     * @return
     */
    public static Claims getClaimByToken(String token) throws ExpiredJwtException {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        String[] header = token.split(TokenConst.BEARER);
        token = header[1];
        return Jwts.parser()
                .setSigningKey(TokenConst.SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
