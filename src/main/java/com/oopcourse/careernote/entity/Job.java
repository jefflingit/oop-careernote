package com.oopcourse.careernote.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "job")
public class Job extends AbstractEntity{

    @NotEmpty
    @Column(name="job_title")
    private String jobTitle;

    @NotEmpty
    @Column(name="company_name")
    private String companyName;

    @Column(name="job_description")
    private String jobDescription;

    @Column(name="working_time")
    private String workingTime;

    @Column(name="contact_info")
    private String contactInfo;

    @Column(name="resume_ddl")
    private LocalDate resumeDDL;

    @Column(name="interview_ddl")
    private LocalDate interviewDDL;

    @Column(name="resume_link")
    private String resumeLink;

    @Column(name="resume_state")
    private String resumeState;

    @Column(name="interview_state")
    private String interviewState;

    @CreationTimestamp
    @Column(name="created_time")
    private LocalDateTime createdTime;

    @Column(name="updated_time")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name="scheduled_time")
    private LocalDateTime scheduledTime;

    @Column(name="days_left")
    private Long daysLeft;

    @Column(name="note")
    private String note;

    public Job(){

    }












}
