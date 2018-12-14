package org.jiangzuoqinglang.cura.service.impl;

import org.jiangzuoqinglang.cura.dao.UserBasicDao;
import org.jiangzuoqinglang.cura.entity.UserBasic;
import org.jiangzuoqinglang.cura.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserBasicDao userBasicDao;

    @Override
    public UserBasic register(UserBasic userBasic){
        userBasicDao.save(userBasic);
        return userBasic;
    }

}
