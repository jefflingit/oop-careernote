package com.oopcourse.careernote.controller;

import com.oopcourse.careernote.entity.Job;
import com.oopcourse.careernote.exception.NotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JobConvertor {
    private final RestTemplate restTemplate;

    public JobConvertor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Job getJob(Job job) throws Exception {
        String url = "http://localhost:8080/jobs/" + job.getId();
        ResponseEntity<Job> response = restTemplate.getForEntity(url, Job.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Job responseBody = response.getBody();
            return responseBody;
        } else {
            throw new NotFoundException("Job not found");
        }
    }

    public List<Job> getAllJobs() throws Exception {
        String url = "http://localhost:8080/jobs/";
        ResponseEntity<List<Job>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Job>>() {
        });
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Job> responseBody = response.getBody();
            return responseBody;
        } else {
            throw new NotFoundException("Jobs not found");
        }
    }

    public List<Job> getAllJobsSortedByDaysLeft() throws Exception {
        String url = "http://localhost:8080/jobs/daysleft";
        ResponseEntity<List<Job>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Job>>() {
        });
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Job> responseBody = response.getBody();
            return responseBody;
        } else {
            throw new NotFoundException("Jobs not found");
        }
    }
    public List<Job> getJobsByQuery(String param) throws Exception{
        String url ="http://localhost:8080/jobs/"+param;
        ResponseEntity<List<Job>> response = restTemplate.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<Job>>(){});
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Job> responseBody = response.getBody();
            return responseBody;
        }else{
            throw new NotFoundException("Jobs not found");
        }
    }

    public Job addJob(Job job) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Job> request = new HttpEntity<>(job, headers);
        String url = "http://localhost:8080/jobs/";
        ResponseEntity<Job> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                Job.class
        );
        if  (response.getStatusCode().is2xxSuccessful()) {
            Job responseBody = response.getBody();
            return responseBody;
        }else{
            throw new NotFoundException("Job not added");
        }
    }

    public Job updateJob(Long id,Job job) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Job> request = new HttpEntity<>(job, headers);
        String url = "http://localhost:8080/jobs/" +id;
        ResponseEntity<Job> response = restTemplate.exchange(url,
                HttpMethod.PUT,
                request,
                Job.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Job responseBody = response.getBody();
            return responseBody;
        } else {
            throw new NotFoundException("Job not updated");
        }
    }

    public void deleteJob(Long id) throws Exception {
        String url = "http://localhost:8080/jobs/" + id;
        try{
            restTemplate.delete(url);
        } catch(HttpClientErrorException e){
            String message = e.getMessage();
            throw new NotFoundException(message + ": Job you want to delete is not found");
        }
    }




}














