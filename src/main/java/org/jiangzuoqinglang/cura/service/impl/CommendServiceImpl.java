package org.jiangzuoqinglang.cura.service.impl;

import org.jiangzuoqinglang.cura.dao.CommendDao;
import org.jiangzuoqinglang.cura.entity.Commend;
import org.jiangzuoqinglang.cura.service.CommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommendServiceImpl implements CommendService {

    @Resource
    private CommendDao commendDao;

    @Override
    public Commend addCommend(int userCuraNumber, int friendCuraNumber, String commend, String headUrl){
        Commend commendEntity = new Commend();
        commendEntity.setCommend(commend);
        commendEntity.setUserCuraNumber(userCuraNumber);
        commendEntity.setFriendCuraNumber(friendCuraNumber);
        commendEntity.setHeadUrl(headUrl);
        commendDao.save(commendEntity);
        return commendEntity;
    }

    @Override
    public void deleteCommend(int commendId){
        Commend commend = commendDao.findByCommendId(commendId);
        commendDao.delete(commend);
    }

    @Override
    public List<Commend> getCommends(int curaNumber){
        return commendDao.findAllByUserCuraNumber(curaNumber);
    }

    @Override
    public List<Commend> allCommends(){
        return commendDao.findAll();
    }
}
