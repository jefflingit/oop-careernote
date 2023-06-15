package com.oopcourse.careernote.repository;

import com.oopcourse.careernote.entity.Job;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Job findById(long id);
    List<Job> findByDaysLeftLessThanOrderByDaysLeftAsc(long daysLeft);


    List<Job> findByJobTitleLikeIgnoreCase(String keyword, Sort sort);

    List<Job> findByCompanyNameLikeIgnoreCase(String keyword, Sort sort);
}