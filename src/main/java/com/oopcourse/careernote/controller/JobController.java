package com.oopcourse.careernote.controller;

import com.oopcourse.careernote.entity.Job;
import com.oopcourse.careernote.parameter.JobQueryParameter;
import com.oopcourse.careernote.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/jobs",produces= MediaType.APPLICATION_JSON_VALUE)
public class JobController {

    @Autowired
    private JobServiceImpl jobService;




    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Long id){
        Job job = jobService.getJob(id);
        return ResponseEntity.ok(job);
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping(value="/{param}")
    public ResponseEntity<List<Job>> getJobByQuery(@ModelAttribute JobQueryParameter param){
        List<Job> jobs =jobService.getJobsByQuery(param);
        jobs.sort(genSortComparator(param.getOrderBy(),param.getSortRule()));
        return ResponseEntity.ok(jobs);
    }


    @GetMapping("/daysleft")
    public ResponseEntity<List<Job>> getAllJobsSortedByDaysLeft(){
        List<Job> jobs = jobService.getAllJobsSortedByDaysLeft();
        return ResponseEntity.ok(jobs);
    }


    @PostMapping
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        job = jobService.createJob(job);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(job.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") Long id, @RequestBody Job job){
        job = jobService.updateJob(id,job);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable("id") Long id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }



    private Comparator<Job> genSortComparator(String orderBy, String sortRule){
        Comparator<Job> comparator = (j1,j2) -> 0;
        if (Objects.isNull(orderBy)|| Objects.isNull(sortRule)){
            return comparator;
        }

        if (orderBy.equalsIgnoreCase("jobTitle")){
            comparator = Comparator.comparing(Job::getJobTitle);
        } else if (orderBy.equalsIgnoreCase("companyName")){
            comparator = Comparator.comparing(Job::getCompanyName);
        } else if (orderBy.equalsIgnoreCase("daysLeft")){
            comparator = Comparator.comparing(Job::getDaysLeft);
        }

        return sortRule.equalsIgnoreCase("desc")?comparator.reversed():comparator;
    }



}
