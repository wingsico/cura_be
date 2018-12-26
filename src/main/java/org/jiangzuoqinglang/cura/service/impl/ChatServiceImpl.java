package org.jiangzuoqinglang.cura.service.impl;

import org.jiangzuoqinglang.cura.dao.ChatDao;
import org.jiangzuoqinglang.cura.dao.FriendDao;
import org.jiangzuoqinglang.cura.entity.Chat;
import org.jiangzuoqinglang.cura.entity.Friend;
import org.jiangzuoqinglang.cura.entity.MessageBox;
import org.jiangzuoqinglang.cura.service.ChatService;
import org.jiangzuoqinglang.cura.service.MessageBoxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private ChatDao chatDao;

    @Resource
    private FriendDao friendDao;

    @Resource
    private MessageBoxService messageBoxService;

    @Override
    public Chat sendMessage(int userCuraNumber, int sendCuraNumber, int receiveCuraNumber, String message){
        Chat chat = new Chat();
        chat.setMessage(message);
        chat.setUserCuraNumber(userCuraNumber);
        chat.setReceiveCuraNumber(receiveCuraNumber);
        chat.setSendCuraNumber(sendCuraNumber);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        chat.setTime(timestamp);
        chatDao.save(chat);
        return chat;
    }

    @Override
    public void inChat(int sendCuraNumebr, int receiveCuraNumber){
        Friend friend = friendDao.findByFriendCuraNumberAndUserCuraNumber(receiveCuraNumber, sendCuraNumebr);
        friend.setIsChat(1);
        friendDao.save(friend);
    }

    @Override
    public void outChat(int sendCuraNumebr, int receiveCuraNumber){
        Friend friend = friendDao.findByFriendCuraNumberAndUserCuraNumber(receiveCuraNumber, sendCuraNumebr);
        friend.setIsChat(0);
        friendDao.save(friend);
    }

    @Override
    public List<Chat> allChatMessages(int userCuraNumber){
        return chatDao.findAllByUserCuraNumber(userCuraNumber);
    }

    @Override
    public List<Chat> chatMessages(int userCuraNumber, int friendCuraNumber){
        List<Chat> chatsFrom = chatDao.findAllByUserCuraNumberAndReceiveCuraNumber(userCuraNumber, friendCuraNumber);
        List<Chat> chatsTo = chatDao.findAllByUserCuraNumberAndSendCuraNumber(userCuraNumber, friendCuraNumber);
        chatsFrom.addAll(chatsTo);
        return chatsFrom;
    }

    @Override
    public void deleteMessages(int userCuraNumber, int friendCuraNumber){
        List<Chat> chats = chatMessages(userCuraNumber, friendCuraNumber);
        for (Chat chat:chats){
            chatDao.delete(chat);
        }
        List<MessageBox> messageBoxes = messageBoxService.getMessagesFriends(userCuraNumber, friendCuraNumber, 0);
        for (MessageBox messageBox:messageBoxes){
            messageBoxService.deleteMessage(messageBox.getMessageBoxId());
        }
    }

    @Override
    public int count(){
        return (int)chatDao.count();
    }
}
