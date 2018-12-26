package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao extends JpaRepository<Friend, Integer> {
    List<Friend> findAllByUserCuraNumber(int cura_number);
    Friend findByFriendCuraNumberAndUserCuraNumber(int friendCuraNumber, int userCuraNumber);
    List<Friend> findAllByUserCuraNumberAndRemark(int userCuraNumber, String remark);
}
