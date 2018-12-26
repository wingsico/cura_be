package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.Friend;
import org.jiangzuoqinglang.cura.entity.Group;
import org.jiangzuoqinglang.cura.entity.User;

import java.sql.Date;
import java.util.List;

public interface UserService {

    /**
     * 用户注册
     * @param nickname
     * @param password
     * @param phone
     * @return
     */
    User register(String nickname, String password, String phone);

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    String generateToken(User user);

    /**
     * 进行token验证
     * @param token
     * @return
     */
    User verifyToken(String token);

    /**
     * 进行密码验证
     *
     * @param cura_number
     * @param password
     * @return
     */
    User checkUser(int cura_number, String password);

    /**
     * 修改电话号码
     * @param cura_number
     * @param phone
     * @return
     */
    User modifyPhone(int cura_number, String phone);

    /**
     * 修改基础信息
     * @param cura_number
     * @param nickname
     * @param native_place
     * @param resume
     * @param signature
     * @param sex
     * @param birthday
     * @param commend
     * @return
     */
    User modifyBasicInformation(int cura_number, String native_place, String signature, String sex, Date birthday);

    /**
     * 修改头像
     * @param cura_number
     * @param head_url
     * @return
     */
    User modifyHead(int cura_number, String head_url);

    /**
     * 修改昵称
     * @param cura_number
     * @param nickname
     * @return
     */
    User modifyNickname(int cura_number, String nickname);

    /**
     * 根据账号查询某个用户
     * @param cura_number
     * @return
     */
    User findOne(int cura_number);

    /**
     * 通过昵称查找所有用户
     * @param nickname
     * @return
     */
    List<User> findAllUsersByNickname(String nickname);

    /**
     * 通过电话号查找所有用户
     * @param nickname
     * @return
     */
    List<User> findAllUsersByPhone(String phone);

    /**
     * 获取用户数
     * @return
     */
    int count();

    /**
     * 返回所有用户
     * @return
     */
    List<User> all();
}
