package com.oopcourse.careernote.views;

import com.oopcourse.careernote.entity.Job;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import java.time.Duration;
import java.time.LocalDateTime;

public class JobForm extends FormLayout {

    private Binder<Job> binder = new Binder<>(Job.class);

    TextField jobTitle=new TextField("Job Title");
    TextField companyName= new TextField("Company Name");

    Select<String> resumeState = new Select<>();



    Select<String> interviewState = new Select<>();



    DateTimePicker scheduledTime= new DateTimePicker("Customized DeadLine");
    DatePicker resumeDDl = new DatePicker("Resume DDL");
    DatePicker interviewDDl = new DatePicker("Interview DDL");
    TextField workingTime= new TextField("Working Time");
    TextField contactInfo= new TextField("Contact Info");
    TextArea jobDescription= new TextArea("Job Description");
    TextField resumeLink= new TextField("Resume Link");
    TextArea note = new TextArea("Note");

    Button save = new Button("Save");
    Button close = new Button("Close");
    Button delete = new Button("Delete");
    public JobForm(){
        addClassName("job-form");
        binder.bindInstanceFields(this);
        createComponentLayout();
    }

    public void setJob(Job job){
        binder.setBean(job);
    }



    private void createComponentLayout(){
        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
        singleFormatI18n.setDateFormat("yyyy-MM-dd");

        resumeDDl.setI18n(singleFormatI18n);
        interviewDDl.setI18n(singleFormatI18n);

        scheduledTime.setStep(Duration.ofMinutes(30));
        scheduledTime.setValue(LocalDateTime.of(2023,6,13,10,0,0));

        resumeState.setLabel("Resume State");
        resumeState.setItems("unsend", "sended", "closed");
        resumeState.setValue("unsend");

        interviewState.setLabel("Interview State");
        interviewState.setItems("stay for notification","sheduled","closed");
        interviewState.setValue("stay for notification");

        this.add(
                jobTitle,
                companyName,
                resumeState,
                interviewState,
                scheduledTime,
                resumeDDl,
                interviewDDl,
                workingTime,
                contactInfo,
                resumeLink,
                jobDescription,
                note,
                this.createButtonLayout()
        );

        this.setResponsiveSteps(
                new ResponsiveStep("0",1),
                new ResponsiveStep("500px",2)
        );

        this.setColspan(jobTitle,2);
        this.setColspan(companyName,2);
        this.setColspan(scheduledTime,2);
        this.setColspan(workingTime,2);
        this.setColspan(resumeLink,2);
        this.setColspan(jobDescription,2);
        this.setColspan(contactInfo,2);
        this.setColspan(note,2);


    }

    private Component createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this,binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save,close,delete);
    }

    private void validateAndSave(){
        if (binder.isValid()){
            fireEvent(new SaveEvent(this,binder.getBean()));
        }
    }

    public static abstract class JobFormEvent extends ComponentEvent<JobForm>{
        private Job job;
        protected JobFormEvent(JobForm source,Job job){
            super(source,false);
            this.job=job;
        }

        public Job getJob(){
            return job;
        }
    }


    public static class SaveEvent extends JobFormEvent{
        SaveEvent(JobForm source,Job job){
            super(source,job);
        }
    }

    public static class DeleteEvent extends JobFormEvent{
        DeleteEvent(JobForm source,Job job){
            super(source,job);
        }
    }


    public static class CloseEvent extends JobFormEvent{
        CloseEvent(JobForm source){
            super(source,null);
        }
    }


    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener){
        return addListener(DeleteEvent.class,listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener){
        return addListener(SaveEvent.class,listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener){
        return addListener(CloseEvent.class,listener);
    }









}
