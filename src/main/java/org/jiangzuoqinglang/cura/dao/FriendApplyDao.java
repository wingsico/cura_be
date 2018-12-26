package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.FriendApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendApplyDao extends JpaRepository<FriendApply, Integer> {
    FriendApply findBySendCuraNumberAndReceiveCuraNumber(int sendCuraNumber, int receiveCuraNumber);
}
