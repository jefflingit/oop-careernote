package com.oopcourse.careernote.component;

import com.oopcourse.careernote.entity.Job;
import com.oopcourse.careernote.service.impl.EmailServiceImpl;
import com.oopcourse.careernote.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {
    private final EmailServiceImpl emailService;
    private final JobServiceImpl jobService;

    @Autowired
    public NotificationScheduler(EmailServiceImpl emailService , JobServiceImpl jobService) {
        this.jobService = jobService;
        this.emailService = emailService;
    }

    @Scheduled(cron="0 33 09 * * *")
    public void checkAndSendNotifications() {
        Integer notificationCount = 0;
        List<Job> jobs = jobService.getAllJobs();
        for (Job job : jobs) {
            try {
                Long daysLeft = job.getDaysLeft();
                if (daysLeft < 7L) {
                    notificationCount++;
                    emailService.sendEmail("usedasaproxy38@gmail.com", "Notification", String.format("your %d job",notificationCount));
                }
            }catch (Exception e){
                continue;
            }

        }


    }

    public void sendNotificationEmail(String recipientEmail) {
        SimpleMailMessage message= emailService.setReceiver("usedasaproxy38@gmail.com");
        message.setSubject("TestNotification");
        message.setText("This is a test notification");
        emailService.getJavaMailSender().send(message);

    }
}
