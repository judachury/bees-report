package org.beesden.report.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(Map<String, Object> config, String to, String subject, String body) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(body, "text/html");
			helper.setFrom((String) config.get("systemEmail"));
			helper.setTo(to);
			helper.setSubject(subject);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}