package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.Commend;

import java.util.List;

public interface CommendService {
    /**
     * 增添评论
     * @param userCuraNumber
     * @param friendCuraNumber
     * @param commend
     * @return
     */
    Commend addCommend(int userCuraNumber, int friendCuraNumber, String commend, String headUrl);

    /**
     * 删除评论
     * @param commendId
     */
    void deleteCommend(int commendId);

    /**
     * 获取该用户的所有评论
     * @param curaNumber
     * @return
     */
    List<Commend> getCommends(int curaNumber);

    /**
     * 获取所有的评论
     * @return
     */
    List<Commend> allCommends();
}
