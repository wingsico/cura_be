package org.jiangzuoqinglang.cura.service.impl;

import org.jiangzuoqinglang.cura.dao.GroupDao;
import org.jiangzuoqinglang.cura.dao.UserGroupDao;
import org.jiangzuoqinglang.cura.entity.Friend;
import org.jiangzuoqinglang.cura.entity.Group;
import org.jiangzuoqinglang.cura.entity.User_Group;
import org.jiangzuoqinglang.cura.service.FriendService;
import org.jiangzuoqinglang.cura.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Resource
    private UserGroupDao userGroupDao;

    @Resource
    private FriendService friendService;

    @Override
    public Group addGroup(int cura_number, String groupName){
        Group group = new Group();
        group.setGroupName(groupName);
        group.setUserCuraNumber(cura_number);
        groupDao.save(group);
        User_Group user_group = new User_Group();
        user_group.setUserID(cura_number);
        user_group.setGroupID(group.getGroupId());
        userGroupDao.save(user_group);
        return group;
    }

    @Override
    public String removeGroup(int cura_number, int group_id){
        User_Group user_group = userGroupDao.findByGroupID(group_id);
        userGroupDao.delete(user_group);
        Group group = groupDao.findByGroupId(group_id);
        Group defaultGroup = getDefaultGroup(cura_number);
        if (group_id == defaultGroup.getGroupId()){
            return null;
        }
        List<Friend> friends = friendService.findAllByGroup_id(group_id);
        for (Friend friend:friends){
            friendService.modifyGroup(friend.getUserCuraNumber(), friend.getFriendCuraNumber(), defaultGroup.getGroupId());
        }
        groupDao.delete(group);
        return "成功";
    }

    @Override
    public Group modifyGroupName(int group_id, String group_name){
        Group group = groupDao.findByGroupId(group_id);
        group.setGroupName(group_name);
        groupDao.save(group);
        return group;
    }

    @Override
    public List<Group> getUserGroups(int cura_number){
        return groupDao.findAllByUserCuraNumber(cura_number);
    }

    @Override
    public Group getDefaultGroup(int curaNumber){
        Group group = groupDao.findByUserCuraNumberAndGroupName(curaNumber, "我的好友");
        return group;
    }

    @Override
    public Group getOne(int groupId){
        return groupDao.findByGroupId(groupId);
    }


}
