package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.MessageBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageBoxDao extends JpaRepository<MessageBox, Integer> {
    List<MessageBox> findAllByUserCuraNumberAndReceiveCuraNumberAndType(int userCuraNumber, int receiveCuraNumber, int type);
    List<MessageBox> findAllByUserCuraNumberAndSendCuraNumberAndType(int userCuraNumber, int sendCuraNumber, int type);
    List<MessageBox> findAllByUserCuraNumber(int userCuraNumber);
    List<MessageBox> findAllByUserCuraNumberAndType(int userCuraNumber, int type);
    MessageBox findByMessageBoxId(int messageBoxId);
}
