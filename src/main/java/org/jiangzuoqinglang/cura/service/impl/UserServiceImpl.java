package org.jiangzuoqinglang.cura.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.jiangzuoqinglang.cura.dao.UserDao;
import org.jiangzuoqinglang.cura.entity.User;
import org.jiangzuoqinglang.cura.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Value("application.secret")
    private String secret;

    /**
     * 用户注册
     *
     * @param nickname
     * @param password
     * @param phone
     * @return Boolean
     */
    @Override
    public User register(String nickname, String password, String phone) {
        String salt = BCrypt.gensalt();
        String pwdHash = BCrypt.hashpw(password, salt);

        User user = new User();
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setPassword(pwdHash);
        userDao.save(user);
        return user;
    }

    /**
     * 生成Token
     *
     * @param user
     * @return
     */
    public String generateToken(User user) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, 1);

        Date exp = now.getTime();

        return Jwts.builder()
                .claim(
                        "id", user.getCura_number()
                ).setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret)).compact();
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     */
    public User verifyToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.encode(secret))
                    .parseClaimsJws(token)
                    .getBody();

            Integer cura_number = (Integer) body.get("cura_number");

            return userDao.getOne(cura_number);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 验证登录用户
     *
     * @param cura_number
     * @param password
     * @return
     */
    public User checkUser(Integer cura_number, String password) {
        User user = userDao.getOne(cura_number);

        if (user == null) return null;

        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        } else return null;
    }
}
