package org.jiangzuoqinglang.cura.service.impl;


import org.jiangzuoqinglang.cura.dao.MessageBoxDao;
import org.jiangzuoqinglang.cura.entity.MessageBox;
import org.jiangzuoqinglang.cura.service.MessageBoxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class MessageBoxServiceImpl implements MessageBoxService{

    @Resource
    private MessageBoxDao messageBoxDao;

    @Override
    public MessageBox addMessage(int userCuraNumber, int sendCuraNumber, int receiveCuraNumebr, String content, int type, String nickname, String headUrl, String sex, java.sql.Date birthday){
        MessageBox messageBox = new MessageBox();
        messageBox.setContent(content);
        messageBox.setUserCuraNumber(userCuraNumber);
        messageBox.setNickname(nickname);
        messageBox.setHeadUrl(headUrl);
        messageBox.setBirthday(birthday);
        messageBox.setSex(sex);
        messageBox.setSendCuraNumber(sendCuraNumber);
        messageBox.setReceiveCuraNumber(receiveCuraNumebr);
        messageBox.setType(type);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        messageBox.setTime(timeStamp);
        messageBoxDao.save(messageBox);
        return messageBox;
    }

    @Override
    public List<MessageBox> getMessages(int userCuraNumber, int type){
        return messageBoxDao.findAllByUserCuraNumberAndType(userCuraNumber, type);
    }

    @Override
    public void deleteMessage(int messageBoxId){
        MessageBox messageBox = messageBoxDao.findByMessageBoxId(messageBoxId);
        messageBoxDao.delete(messageBox);
    }

    @Override
    public void readMessage(int messageBoxId){
        MessageBox messageBox = messageBoxDao.findByMessageBoxId(messageBoxId);
        messageBox.setIsRead(1);
        messageBoxDao.save(messageBox);
    }

    @Override
    public void dealMessage(int messageBoxId, int isDeal){
        MessageBox messageBox = messageBoxDao.findByMessageBoxId(messageBoxId);
        messageBox.setIsDeal(isDeal);
        messageBoxDao.save(messageBox);
    }

    @Override
    public List<MessageBox> getMessagesFriends(int userCuraNumber, int friendCuraNumber, int type){
        List<MessageBox> messageBoxesReceive = messageBoxDao.findAllByUserCuraNumberAndReceiveCuraNumberAndType(userCuraNumber,friendCuraNumber, type);
        List<MessageBox> messageBoxesSend = messageBoxDao.findAllByUserCuraNumberAndSendCuraNumberAndType(userCuraNumber,friendCuraNumber, type);
        messageBoxesReceive.addAll(messageBoxesSend);
        return messageBoxesReceive;
    }

    @Override
    public List<MessageBox> allMessages(int userCuraNumber){
        return messageBoxDao.findAllByUserCuraNumber(userCuraNumber);
    }

    @Override
    public MessageBox getMessage(int messageBoxId){
        return messageBoxDao.findByMessageBoxId(messageBoxId);
    }
}
