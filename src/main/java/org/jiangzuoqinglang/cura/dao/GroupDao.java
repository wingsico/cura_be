package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {
    List<Group> findAllByUserCuraNumber(int userCuraNumber);
    Group findByGroupId(int groupId);
    Group findByUserCuraNumberAndGroupName(int userCuraNumber, String groupName);
}
