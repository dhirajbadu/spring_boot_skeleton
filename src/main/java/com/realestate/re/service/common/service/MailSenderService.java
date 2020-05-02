package com.realestate.re.service.common.service;

import com.realestate.re.service.common.utls.FileHandler;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.MailTemplateUtls;
import com.realestate.re.service.core.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class MailSenderService extends JavaMailSenderImpl {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TokenService tokenService;

    //@Async("threadPoolTaskExecutor")
    public void mailSender(String to, String subject, String messages) {
        try {

            Thread.sleep(5000);
            LoggerUtil.logInfo(MailSenderService.class, " email sending to ==>> " + to);

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("text/html; charset=utf-8", messages);

						/*FileSystemResource file
								= new FileSystemResource(new File(pathToAttachment));
						helper.addAttachment("Invoice", file);*/

            emailSender.send(message);

            LoggerUtil.logInfo(MailSenderService.class, " email send to ==>> " + to);

        } catch (Exception ex) {
            LoggerUtil.logException(MailSender.class, ex);
        }

    }

    //@Async("threadPoolTaskExecutor")
    public void userResetPasswordEmailSender(String to, String subject, String messages, long tokenId) {
        try {

            Thread.sleep(5000);
            LoggerUtil.logInfo(MailSenderService.class, " email sending to ==>> " + to);

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("text/html; charset=utf-8", messages);

						/*FileSystemResource file
								= new FileSystemResource(new File(pathToAttachment));
						helper.addAttachment("Invoice", file);*/

            emailSender.send(message);

            tokenService.updateSendMailStatus(tokenId, true);

            LoggerUtil.logInfo(MailSenderService.class, " email send to ==>> " + to);


        } catch (Exception ex) {
            LoggerUtil.logException(MailSender.class, ex);
        }

    }

    public void sendMail(String to, String subject, String message) {
        new Thread() {
            public void run() {
                mailSender(to, subject, message);
            }
        }.start();

    }

    public void sendMail(String to, String subject, String title, String header, String body, String footer) {
        String message = MailTemplateUtls.officialMailTemplate(title, header, body, footer);
        this.sendMail(to, subject, message);
    }

    //@Async("threadPoolTaskExecutor")
    public void userResetPasswordMail(String to, String link, long tokenId) {
        new Thread() {
            public void run() {
                String body = "please click the link to reset you password " + link;
                String message = MailTemplateUtls.officialMailTemplate("Forget Password", "Reset Password", body, "Forget Password");
                userResetPasswordEmailSender(to, "Reset Password", message, tokenId);
            }
        }.start();
    }

}
