package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.UserExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExtraDao extends JpaRepository<UserExtra, Integer> {
}
