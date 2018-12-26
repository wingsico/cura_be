package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.MessageBox;

import java.util.List;

public interface MessageBoxService {
    /**
     * 消息盒子中增加消息
     * @param receiveCuraNumebr
     * @param content
     * @param type
     * @return
     */
    MessageBox addMessage(int userCuraNumber, int sendCuraNumber, int receiveCuraNumebr, String content, int type, String nickname, String headUrl, String sex, java.sql.Date birthday);

    /**
     * 获取消息盒子中某类所有的消息
     * @param userCuraNumber
     * @return
     */
    List<MessageBox> getMessages(int userCuraNumber, int type);

    /**
     * 删除消息盒子中的消息
     * @param messageBoxId
     */
    void deleteMessage(int messageBoxId);

    /**
     * 读取消息
     * @param messageBoxId
     */
    void readMessage(int messageBoxId);

    /**
     * 处理消息盒子中的消息
     * @param messageBoxId
     */
    void dealMessage(int messageBoxId, int isDeal);

    /**
     * 获取该用户与该好友的某种类型的所有消息
     * @param userCuraNumber
     * @param friendCuraNumber
     * @param type
     * @return
     */
    List<MessageBox> getMessagesFriends(int userCuraNumber, int friendCuraNumber, int type);

    /**
     * 获取自己所有的消息
     * @param userCuraNumber
     * @return
     */
    List<MessageBox> allMessages(int userCuraNumber);

    /**
     * 获取消息盒子
     * @param messageBoxId
     * @return
     */
    MessageBox getMessage(int messageBoxId);
}
