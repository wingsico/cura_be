package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDao extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByUserCuraNumber(int userCuraNumber);
    List<Chat> findAllByUserCuraNumberAndReceiveCuraNumber(int userCuraNumber, int receiveCuraNumber);
    List<Chat> findAllByUserCuraNumberAndSendCuraNumber(int userCuraNumber, int sendCuraNumber);
}
