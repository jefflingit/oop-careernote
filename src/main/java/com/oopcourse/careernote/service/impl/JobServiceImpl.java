package com.oopcourse.careernote.service.impl;

import com.oopcourse.careernote.entity.Job;
import com.oopcourse.careernote.exception.NotFoundException;
import com.oopcourse.careernote.parameter.JobQueryParameter;
import com.oopcourse.careernote.repository.JobRepository;
import com.oopcourse.careernote.service.JobService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // get

    @Override
    public Job getJob(Long id){
        return jobRepository.findById(id).orElseThrow(()->
                new NotFoundException("Can't find job post."));

    }

    @Override
    public List<Job> getJobsByQuery(JobQueryParameter param) {
        List<Job> jobs = null;
        String keyword = Optional.ofNullable(param.getKeyword()).orElse("");
        String attribute = Optional.ofNullable(param.getAttribute()).orElse("");
        Sort sort = genSortingStrategy(param.getOrderBy(),param.getSortRule());
        if (attribute.equals("jobTitle")) {
            jobs = jobRepository.findByJobTitleLikeIgnoreCase(keyword, sort);
        } else if (attribute.equals("companyName")) {
            jobs = jobRepository.findByCompanyNameLikeIgnoreCase(keyword, sort);
        }
        return jobs;
    }

    // get all jobs
    @Override
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getAllJobsSortedByDaysLeft() {
        List<Job> jobs = this.getAllJobs();
        for (Job job : jobs) {
            countDaysLeft(job);
        }
        sortByDaysLeft(jobs);
        return jobs;
    }

    // post
    @Override
    public Job createJob(Job job) {
        job = jobRepository.save(job);
        return job;
    }

    // put
    @Override
    public Job updateJob(Long id, Job newJob) {
        Job oldJob=getJob(id);
        newJob.setId(oldJob.getId());
        jobRepository.save(newJob);
        return newJob;
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void countDaysLeft(Job job) {
        if (job.getScheduledTime() != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate scheduledDate = job.getScheduledTime().toLocalDate();
            job.setDaysLeft(ChronoUnit.DAYS.between(currentDate, scheduledDate));
        } else {
            if (job.getResumeState()!="closed"){
                LocalDate currentDate = LocalDate.now();
                LocalDate resumeDDL = job.getResumeDDL();
                job.setDaysLeft(ChronoUnit.DAYS.between(currentDate, resumeDDL));
            }else if(job.getInterviewState()!="closed"){
                LocalDate currentDate = LocalDate.now();
                LocalDate interviewDDL = job.getInterviewDDL();
                job.setDaysLeft(ChronoUnit.DAYS.between(currentDate, interviewDDL));
            }
        }

    }




    private Sort genSortingStrategy(String orderBy,String sortRule){
        Sort sort = Sort.unsorted();
        if (Objects.nonNull(orderBy) && Objects.nonNull(sortRule)){
            Sort.Direction direction = Sort.Direction.fromString(sortRule);
            sort = Sort.by(direction,orderBy);
        }
        return sort;
    }

    public void sortByDaysLeft(List<Job> jobs){
        Collections.sort(jobs, Comparator.comparingLong(Job::getDaysLeft));
    }









}
