package org.jiangzuoqinglang.cura.service;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.jiangzuoqinglang.cura.entity.Friend;
import org.jiangzuoqinglang.cura.entity.FriendApply;
import org.jiangzuoqinglang.cura.entity.Group_Friend;

import java.util.List;

public interface FriendService {

    /**
     * 根据账号查找好友
     * @param curaNumber
     * @return
     */
    Friend queryByCuraNumber(int friendCuraNumber, int userCuraNumber);

    /**
     * 根据备注查找好友
     * @param userCuraNumber
     * @param remark
     * @return
     */
    List<Friend> queryByRemark(int userCuraNumber, String remark);

    /**
     * 根据cura账号添加好友
     * @param group_id
     * @param cura_number
     * @param remark
     * @return
     */
    Friend addFriendByCuraNumber(int user_cura_number, int group_id, int friend_cura_number, String remark);

    /**
     * 查询此分组的所有好友
     * @param group_id
     * @return
     */
    List<Friend> findAllByGroup_id(int group_id);

    /**
     * 获取该用户的所有好友
     * @param cura_number
     * @return
     */
    List<Friend> findAllFriends(int userCuraNumber);

    /**
     * 删除好友
     * @param friend_id
     */
    void removeFriend(int userCuraNumber, int friendCuraNumber);

    /**
     * 移动好友的分组
     * @param friend_id
     * @param group_id
     */
    void modifyGroup(int userCuraNumber, int friendCuraNumber, int group_id);

    /**
     * 修改好友的备注
     * @param friend_id
     * @param remark
     */
    void modifyRemark(int userCuraNumber, int friendCuraNumber, String remark);

    /**
     * 获取单个好友
     * @param friend_id
     * @return
     */
    Friend findOneFriend(int userCuraNumber, int friendCuraNumber);

    /**
     * 申请添加好友
     * @param sendCuraNumber
     * @param receiveCuraNumber
     * @param groupId
     * @param remark
     * @return
     */
    FriendApply applyFriend(int sendCuraNumber, int receiveCuraNumber, int groupId, String remark);

    /**
     * 删除添加好友信息
     * @param sendCuraNumber
     * @param receiveCuraNumber
     * @return
     */
    void deleteFriendApply(int sendCuraNumber, int receiveCuraNumber);

    /**
     * 获取好友添加信息
     * @param sendCuraNumber
     * @param receiveCuraNumber
     * @return
     */
    FriendApply getFriendApply(int sendCuraNumber, int receiveCuraNumber);
}
