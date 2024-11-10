package com.elearn.app.start_learn_back.repositries;

import com.elearn.app.start_learn_back.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,String> {

    Optional<Course> findBytitle(String title);

    List<Course> findByLive(boolean live);

//    @Query("select c from Course c where c.category.id)
//    List<Course> findByCategoryId(String categoryId);

}
