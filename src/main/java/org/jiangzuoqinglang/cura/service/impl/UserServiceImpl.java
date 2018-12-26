package org.jiangzuoqinglang.cura.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.jiangzuoqinglang.cura.dao.UserDao;
import org.jiangzuoqinglang.cura.entity.Group;
import org.jiangzuoqinglang.cura.entity.User;
import org.jiangzuoqinglang.cura.service.FriendService;
import org.jiangzuoqinglang.cura.service.GroupService;
import org.jiangzuoqinglang.cura.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private GroupService groupService;

    @Resource
    private FriendService friendService;


    @Value("application.secret")
    private String secret;

    @Override
    public User register(String nickname, String password, String phone) {
        String salt = BCrypt.gensalt();
        String pwdHash = BCrypt.hashpw(password, salt);
        User user = new User();
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setPassword(pwdHash);
        user.setHeadUrl("http://cdn.wingsico.org/image/v2-c5a239736871ed06a8f1dd32e2820610_hd.jpg");
        user.setNativePlace("中国");
        user.setSignature("这个人很懒他什么也没有留下");
        user.setSex("男");
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        user.setBirthday(date);
        userDao.save(user);
        groupService.addGroup(user.getCuraNumber(), "我的好友");
        Group group = groupService.getDefaultGroup(user.getCuraNumber());
        friendService.addFriendByCuraNumber(user.getCuraNumber(), group.getGroupId(), user.getCuraNumber(), "我");
        return user;
    }

    @Override
    public String generateToken(User user) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, 12);
        Date exp = now.getTime();
        return Jwts.builder()
                .claim(
                        "cura_number", user.getCuraNumber()
                ).setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret)).compact();
    }

    @Override
    public User verifyToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.encode(secret))
                    .parseClaimsJws(token)
                    .getBody();
            Integer cura_number = (Integer) body.get("cura_number");
            return userDao.findByCuraNumber(cura_number);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public User checkUser(int cura_number, String password) {
        User user = userDao.findByCuraNumber(cura_number);
        if (user == null){
            return null;
        }
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        } else return null;
    }

    @Override
    public User modifyPhone(int cura_number, String phone){
        User user = userDao.findByCuraNumber(cura_number);
        user.setPhone(phone);
        userDao.save(user);
        return user;
    }

    @Override
    public User modifyNickname(int cura_number, String nickname){
        User user = userDao.findByCuraNumber(cura_number);
        user.setNickname(nickname);
        userDao.save(user);
        return user;
    }

    @Override
    public User modifyBasicInformation(int cura_number, String native_place, String signature, String sex, java.sql.Date birthday){
        User user = userDao.findByCuraNumber(cura_number);
        user.setBirthday(birthday);
        user.setSignature(signature);
        user.setSex(sex);
        user.setNativePlace(native_place);
        userDao.save(user);
        return user;
    }

    @Override
    public User modifyHead(int cura_number, String head_url){
        User user = userDao.findByCuraNumber(cura_number);
        user.setHeadUrl(head_url);
        userDao.save(user);
        return user;
    }

    @Override
    public User findOne(int cura_number){
        return userDao.findByCuraNumber(cura_number);
    }

    @Override
    public List<User> findAllUsersByNickname(String nickname){
        return userDao.findAllByNickname(nickname);
    }

    @Override
    public List<User> findAllUsersByPhone(String phone){
        return userDao.findAllByPhone(phone);
    }

    @Override
    public int count(){
        return (int)userDao.count();
    }

    @Override
    public List<User> all(){
        return userDao.findAll();
    }
}
