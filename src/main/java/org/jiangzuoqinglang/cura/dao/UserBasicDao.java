package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.UserBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicDao extends JpaRepository<UserBasic, Integer> {
}
