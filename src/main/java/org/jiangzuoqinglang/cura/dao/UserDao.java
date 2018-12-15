package org.jiangzuoqinglang.cura.dao;

import org.jiangzuoqinglang.cura.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
