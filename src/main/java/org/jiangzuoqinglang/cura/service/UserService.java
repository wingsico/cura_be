package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.User;

public interface UserService {
    /**
     * 进行用户注册
     *
     */
    User register(String nickname, String password, String phone);
    String generateToken(User user);
    User verifyToken(String token);
    User checkUser(Integer cura_number, String password);
}
