package com.elearn.app.start_learn_back.repositries;

import com.elearn.app.start_learn_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
}
