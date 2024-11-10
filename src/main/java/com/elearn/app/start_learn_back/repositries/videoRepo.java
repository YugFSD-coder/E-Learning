package com.elearn.app.start_learn_back.repositries;

import com.elearn.app.start_learn_back.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface videoRepo extends JpaRepository<Video,String> {
}
