package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.Group;

import java.util.List;

public interface GroupService {

    /**
     * 增加分组
     * @param groupName
     * @return
     */
    Group addGroup(int cura_number, String groupName);

    /**
     * 删除分组
     * @param group_id
     */
    String removeGroup(int cura_number, int group_id);

    /**
     * 改变分组的名字
     * @param group_id
     * @param group_name
     * @return
     */
    Group modifyGroupName(int group_id, String group_name);

    /**
     * 返回该用户所有的分组
     * @param cura_number
     * @return
     */
    List<Group> getUserGroups(int cura_number);

    /**
     * 获取默认分组 我的好友
     * @param curaNumber
     * @return
     */
    Group getDefaultGroup(int curaNumber);

    /**
     * 根据groupId获取分组
     * @param groupId
     * @return
     */
    Group getOne(int groupId);
}
