package org.jiangzuoqinglang.cura.service.impl;

import org.jiangzuoqinglang.cura.dao.FriendApplyDao;
import org.jiangzuoqinglang.cura.dao.FriendDao;
import org.jiangzuoqinglang.cura.dao.GroupFriendDao;
import org.jiangzuoqinglang.cura.entity.*;
import org.jiangzuoqinglang.cura.service.FriendService;
import org.jiangzuoqinglang.cura.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendDao friendDao;

    @Resource
    private UserService userService;

    @Resource
    private GroupFriendDao groupFriendDao;

    @Resource
    private FriendApplyDao friendApplyDao;

    @Override
    public Friend queryByCuraNumber(int friendCuraNumber, int userCuraNumber){
        return friendDao.findByFriendCuraNumberAndUserCuraNumber(friendCuraNumber, userCuraNumber);
    }

    @Override
    public List<Friend> queryByRemark(int userCuraNumber, String remark){
        return friendDao.findAllByUserCuraNumberAndRemark(userCuraNumber, remark);
    }

    @Override
    public Friend addFriendByCuraNumber(int user_cura_number, int group_id, int friend_cura_number, String remark){
        Friend friend = new Friend();
        friend.setFriendCuraNumber(friend_cura_number);
        friend.setUserCuraNumber(user_cura_number);
        friend.setGroupId(group_id);
        friend.setRemark(remark);
        friendDao.save(friend);
        Group_Friend group_friend = new Group_Friend();
        group_friend.setGroupID(group_id);
        group_friend.setFriendID(friend.getFriendId());
        groupFriendDao.save(group_friend);
        return friend;
    }

    @Override
    public List<Friend> findAllByGroup_id(int group_id){
        List<Friend> friends = new ArrayList<>();
        List<Group_Friend> group_friends = groupFriendDao.findAllByGroupID(group_id);
        for (Group_Friend group_friend:group_friends){
            friends.add(friendDao.getOne(group_friend.getFriendID()));
        }
        return friends;
    }

    @Override
    public List<Friend> findAllFriends(int userCuraNumber){
        return friendDao.findAllByUserCuraNumber(userCuraNumber);
    }

    @Override
    public void removeFriend(int userCuraNumber, int friendCuraNumber){
        Friend friend = friendDao.findByFriendCuraNumberAndUserCuraNumber(friendCuraNumber, userCuraNumber);
        Group_Friend group_friend = groupFriendDao.findByFriendID(friend.getFriendId());
        groupFriendDao.delete(group_friend);
        friendDao.delete(friend);
    }

    @Override
    public void modifyGroup(int userCuraNumber, int friendCuraNumber, int group_id){
        Friend friend = friendDao.findByFriendCuraNumberAndUserCuraNumber(friendCuraNumber, userCuraNumber);
        Group_Friend group_friend = groupFriendDao.findByFriendID(friend.getFriendId());
        group_friend.setGroupID(group_id);
        groupFriendDao.save(group_friend);
        friend.setGroupId(group_id);
        friendDao.save(friend);
    }

    @Override
    public void modifyRemark(int userCuraNumber, int friendCuraNumber, String remark){
        Friend friend = friendDao.findByFriendCuraNumberAndUserCuraNumber(friendCuraNumber, userCuraNumber);
        friend.setRemark(remark);
        friendDao.save(friend);
    }

    @Override
    public Friend findOneFriend(int userCuraNumber, int friendCuraNumber){
        return friendDao.findByFriendCuraNumberAndUserCuraNumber(friendCuraNumber, userCuraNumber);
    }

    @Override
    public FriendApply applyFriend(int sendCuraNumber, int receiveCuraNumber, int groupId, String remark){
        FriendApply friendApply = new FriendApply();
        friendApply.setSendCuraNumber(sendCuraNumber);
        friendApply.setReceiveCuraNumber(receiveCuraNumber);
        friendApply.setGroupId(groupId);
        friendApply.setRemark(remark);
        friendApplyDao.save(friendApply);
        return friendApply;
    }

    @Override
    public void deleteFriendApply(int sendCuraNumber, int receiveCuraNumber){
        FriendApply friendApply = friendApplyDao.findBySendCuraNumberAndReceiveCuraNumber(sendCuraNumber, receiveCuraNumber);
        friendApplyDao.delete(friendApply);
    }

    @Override
    public FriendApply getFriendApply(int sendCuraNumber, int receiveCuraNumber){
        return friendApplyDao.findBySendCuraNumberAndReceiveCuraNumber(sendCuraNumber, receiveCuraNumber);
    }
}
