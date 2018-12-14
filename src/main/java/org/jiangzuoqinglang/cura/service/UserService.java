package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.UserBasic;

public interface UserService {
    /**
     * 进行用户注册
     *
     */
    UserBasic register(UserBasic userBasic);
}
