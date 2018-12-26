package org.jiangzuoqinglang.cura.entity;


import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class UserFriend {
    private String nickname;

    private String phone;

    private int messageBoxId;

    private MultipartFile headUrl;

    private String nativePlace;

    private String signature;

    private String sex;

    private String birthday;

    private int sendCuraNumber;

    private int receiveCuraNumber;

    private int groupId;

    private String remark;

    private int isDeal;

    private String commend;

    private int friendCuraNumber;

    private int type;

    public int getFriendCuraNumber() {
        return friendCuraNumber;
    }

    public void setFriendCuraNumber(int friendCuraNumber) {
        this.friendCuraNumber = friendCuraNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCommend() {
        return commend;
    }

    public void setCommend(String commend) {
        this.commend = commend;
    }

    public MultipartFile getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(MultipartFile headUrl) {
        this.headUrl = headUrl;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSendCuraNumber() {
        return sendCuraNumber;
    }

    public void setSendCuraNumber(int sendCuraNumber) {
        this.sendCuraNumber = sendCuraNumber;
    }

    public int getReceiveCuraNumber() {
        return receiveCuraNumber;
    }

    public void setReceiveCuraNumber(int receiveCuraNumber) {
        this.receiveCuraNumber = receiveCuraNumber;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(int isDeal) {
        this.isDeal = isDeal;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMessageBoxId() {
        return messageBoxId;
    }

    public void setMessageBoxId(int messageBoxId) {
        this.messageBoxId = messageBoxId;
    }
}
