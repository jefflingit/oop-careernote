package com.oopcourse.careernote.views;


import com.oopcourse.careernote.controller.JobController;
import com.oopcourse.careernote.entity.Job;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PermitAll
@Route(value="tabview",layout= MainLayout.class)
@PageTitle("Jobs | Career Note")
public class TabView extends VerticalLayout {
    Grid<Job> grid = new Grid<>(Job.class);
    JobController jobController;



    public TabView(JobController jobController){
        this.jobController = jobController;
        addClassName("tab-view");
        setSizeFull();
        configureGrid();
        TabSheet tabSheet = new TabSheet();
        tabSheet.add("Resume Phase",getCards());
        tabSheet.add("Interview Phase",getCards());
        tabSheet.add("History Record",getCards());
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_BORDERED);
        tabSheet.setSizeFull();
        add(tabSheet);
        updateList();
    }

    private  Component getCards(){
        List<Job> jobs = jobController.getAllJobs().getBody();
        CardComponent card1= new CardComponent(jobs.get(0).getJobTitle(),jobs.get(0).getCompanyName(),"",jobs.get(0).getResumeState(),jobs.get(0).getResumeState(),2L);
        CardComponent card2= new CardComponent(jobs.get(1).getJobTitle(),jobs.get(1).getCompanyName(),"",jobs.get(1).getResumeState(),jobs.get(1).getResumeState(),5L);
        CardComponent card3= new CardComponent(jobs.get(2).getJobTitle(),jobs.get(2).getCompanyName(),"",jobs.get(2).getResumeState(),jobs.get(2).getResumeState(),6L);
        VerticalLayout content = new VerticalLayout(card1,card2,card3);
        content.setSpacing(true);
        return content;
    }




    private void configureGrid() {
        grid.addClassName("job-grid");
        grid.setSizeFull();
        grid.setColumns("jobTitle", "resumeState", "interviewState", "daysLeft", "scheduledTime", "resumeDDL", "interviewDDL"
                , "companyName", "contactInfo", "workingTime", "jobDescription", "resumeLink"
                , "updatedTime", "createdTime", "note"
        );
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(1,grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    public void updateList(){
        grid.setItems(jobController.getAllJobs().getBody());
    }





}
