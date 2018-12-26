package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.Group_Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupFriendDao extends JpaRepository<Group_Friend, Integer> {

    List<Group_Friend> findAllByGroupID(int group_id);

    Group_Friend findByFriendID(int friend_id);
}
