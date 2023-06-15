package com.oopcourse.careernote.views;

import com.oopcourse.careernote.controller.JobController;
import com.oopcourse.careernote.controller.JobConvertor;
import com.oopcourse.careernote.entity.Job;
import com.oopcourse.careernote.repository.JobRepository;
import com.oopcourse.careernote.service.impl.JobServiceImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;



import java.util.List;

@PermitAll
@Route(value="",layout= MainLayout.class)
@PageTitle("Jobs | Career Note")
public class ListView extends VerticalLayout {
    Grid<Job> grid = new Grid<>(Job.class);
    TextField filterText = new TextField();
    JobForm form;

    List<Job> jobs;

    JobController jobController;


    public ListView(JobController jobController){
        this.jobController = jobController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();


    }

    private HorizontalLayout getToolbar() {

        filterText.setPlaceholder("Search...");
        filterText.setClearButtonVisible(true);
        filterText.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        filterText.setValueChangeMode(ValueChangeMode.LAZY);


        Button addJobButton = new Button("Add Job");
        addJobButton.addClickListener(click -> addJob());

        var toolbar = new HorizontalLayout(filterText, addJobButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }

    private void configureForm() {
        form = new JobForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveJob);
        form.addDeleteListener(this::deleteJob);
        form.addCloseListener(e -> closeEditor());
    }

    private void addJob(){
        grid.asSingleSelect().clear();
        editJob(new Job());
    }
    public void editJob(Job job){
        if (job == null){
            closeEditor();
        }else{
            form.setJob(job);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void saveJob(JobForm.SaveEvent event){
        jobController.addJob(event.getJob());
        updateList();
        closeEditor();
    }

    public void deleteJob(JobForm.DeleteEvent event){
        jobController.deleteJob(event.getJob().getId());
        updateList();
        closeEditor();
    }


    public void closeEditor(){
        form.setJob(null);
        form.setVisible(false);
        removeClassName("editing");
    }



    private void configureGrid(){
        grid.addClassName("job-grid");
        grid.setSizeFull();
        grid.setColumns("jobTitle", "companyName","resumeState", "interviewState", "daysLeft", "scheduledTime", "resumeDDL", "interviewDDL"
                , "contactInfo", "workingTime", "jobDescription", "resumeLink"
                , "updatedTime", "createdTime", "note"
        );
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        Grid.Column<Job> nameColumn = grid.getColumnByKey("jobTitle");
        nameColumn.setAutoWidth(true);
        nameColumn.setFrozen(true);
        grid.asSingleSelect().addValueChangeListener(event -> editJob(event.getValue()));
        Grid.Column<Job> resumeStateColumn = grid.getColumnByKey("resumeState");
        resumeStateColumn.getStyle().set("background-color", "var(--lumo-primary-color)");



    }








    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content");
        content.setSizeFull();
        Grid.Column<Job> resumeStateColumn = grid.getColumnByKey("resumeState");
        resumeStateColumn.getStyle().set("background-color", "var(--lumo-primary-color)");
        return content;
    }


    public void updateList() {
        grid.setItems(jobController.getAllJobsSortedByDaysLeft().getBody());


    }







}
