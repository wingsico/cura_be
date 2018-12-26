package org.jiangzuoqinglang.cura.service;

import org.jiangzuoqinglang.cura.entity.Chat;

import java.util.List;

public interface ChatService {
    /**
     * 进行聊天
     * @param userCuraNumber
     * @param sendCuraNumber
     * @param receiveCuraNumber
     * @param message
     */
    Chat sendMessage(int userCuraNumber, int sendCuraNumber, int receiveCuraNumber, String message);

    /**
     * 进入聊天
     * @param sendCuraNumber
     * @param receiveCuraNumber
     * @return
     */
    void inChat(int sendCuraNumber, int receiveCuraNumber);

    /**
     * 退出聊天
     * @param sendCuraNumber
     * @param receiveCuraNumber
     */
    void outChat(int sendCuraNumber, int receiveCuraNumber);

    /**
     *
     * @param userCuraNumber
     * @return
     */
    List<Chat> allChatMessages(int userCuraNumber);

    /**
     * 获取与好友聊天信息
     * @param userCuraNumber
     * @return
     */
    List<Chat> chatMessages(int userCuraNumber, int friendCuraNumber);

    /**
     * 清空聊天记录
     * @param userCuraNumber
     */
    void deleteMessages(int userCuraNumber, int friendCuraNumber);

    /**
     * 获取聊天记录数
     * @return
     */
    int count();
}
