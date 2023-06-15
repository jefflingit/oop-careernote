package com.oopcourse.careernote.service;

import com.oopcourse.careernote.entity.Job;
import com.oopcourse.careernote.parameter.JobQueryParameter;

import java.util.List;

public interface JobService {

    // Get

    // get job by id
    Job getJob(Long id);


    // get jobs by unified query method, put your query keyword of any attribute, order rule ,sort rule in your query
    List<Job> getJobsByQuery(JobQueryParameter param);

    // get unsorted jobs
    List<Job> getAllJobs();

    //get jobs sorted by DaysLeft
    List<Job> getAllJobsSortedByDaysLeft();

    Job createJob(Job job);

    Job updateJob(Long id,Job newJob);

    void deleteJob(Long id);

    void countDaysLeft(Job job);











}
