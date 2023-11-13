package com.pizzani.services.impl;


import static com.pizzani.utils.EmailUtils.getEmailMessage;
import static com.pizzani.utils.EmailUtils.getVerificationUrl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pizzani.services.EmailService;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	public static final String TEXT_HTML_ENCODING = "text/html";
	public static final String EMAIL_TEMPLATE = "emailtemplate";
	public static final String UTF_8_ENCODING = "UTF-8";
	public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
	@Value("${mail.host}")
	private String host;
	@Value("${spring.mail.username}")
	private String fromEmail;
	private final JavaMailSender emailSender;
	@Autowired
	private TemplateEngine templateEngine;

	@Override
	@Async
	public void sendSimpleMailMessage(String name, String to, String token) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			message.setFrom(fromEmail);
			message.setTo(to);
			message.setText(getEmailMessage(name, host, token));
			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	@Async
	public void sendMimeMessageWithAttachment(String name, String to, String token) {
		try {
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
			helper.setPriority(1);
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom(fromEmail);
			helper.setTo(to);;
			helper.setText(getEmailMessage(name, host, token));
			
			// Add attachments
			FileSystemResource asylumMonth1 = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/IMG_0119.PNG"));
			FileSystemResource calories = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/IMG_0120.PNG"));
			FileSystemResource insanityWelcome = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/Insanity Welcome.pdf"));
			// Should have checking if it is null
			helper.addAttachment(asylumMonth1.getFilename(), asylumMonth1);
			helper.addAttachment(calories.getFilename(), calories);
			helper.addAttachment(insanityWelcome.getFilename(), insanityWelcome);
			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	@Async
	public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {
		try {
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
			helper.setPriority(1);
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom(fromEmail);
			helper.setTo(to);;
			helper.setText(getEmailMessage(name, host, token));
			
			// Add attachments
			FileSystemResource asylumMonth1 = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/IMG_0119.PNG"));
			FileSystemResource calories = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/IMG_0120.PNG"));
			FileSystemResource insanityWelcome = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/Insanity Welcome.pdf"));
			// Should have checking if it is null
			helper.addInline(getContentId(asylumMonth1.getFilename()), asylumMonth1);
			helper.addInline(getContentId(calories.getFilename()), calories);
			helper.addInline(getContentId(insanityWelcome.getFilename()), insanityWelcome);
			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	@Async
	public void sendHtmlEMail(String name, String to, String token) {
		try {
			Context context = new Context();
			context.setVariable("name", name);
			context.setVariable("url", getVerificationUrl(host, token));
			String text = templateEngine.process(EMAIL_TEMPLATE, context);
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
			helper.setPriority(1);
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom(fromEmail);
			helper.setTo(to);;
			helper.setText(text, true);
			// Add attachments
			/*FileSystemResource asylumMonth1 = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/IMG_0119.PNG"));
			FileSystemResource calories = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/IMG_0120.PNG"));
			FileSystemResource insanityWelcome = new FileSystemResource(new File(System.getProperty("user.home") +
					"/Pictures/Insanity Welcome.pdf"));
			// Should have checking if it is null
			helper.addInline(getContentId(asylumMonth1.getFilename()), asylumMonth1);
			helper.addInline(getContentId(calories.getFilename()), calories);
			helper.addInline(getContentId(insanityWelcome.getFilename()), insanityWelcome);*/
			
			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}


	@Override
	@Async
	public void sendHtmlEMailWithEmbeddedFiles(String name, String to, String token) {
		try {
			MimeMessage message = getMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
			helper.setPriority(1);
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom(fromEmail);
			helper.setTo(to);;
//			helper.setText(text, true);
			Context context = new Context();
			context.setVariable("name", name);
			context.setVariable("url", getVerificationUrl(host, token));
			String text = templateEngine.process(EMAIL_TEMPLATE, context);
			
			// Add HTML Email body
			MimeMultipart mimeMultipart = new MimeMultipart("related");
			
			BodyPart messageBodyPart = new MimeBodyPart(); // First body part
			messageBodyPart.setContent(text, TEXT_HTML_ENCODING);
			mimeMultipart.addBodyPart(messageBodyPart);
			
			// Add images to the email body
			BodyPart imageBodyPart = new MimeBodyPart(); // Second body part
			DataSource dataSource = new FileDataSource(System.getProperty("user.home") + "/Pictures/IMG_0119.PNG");
			imageBodyPart.setDataHandler(new DataHandler(dataSource));
			imageBodyPart.setHeader("Content-ID", "image");
			mimeMultipart.addBodyPart(imageBodyPart);
			
			// Adding both parts to view
			message.setContent(mimeMultipart);
			
			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	private MimeMessage getMimeMessage() {
		return emailSender.createMimeMessage();
	}
	
	private String getContentId(String fileName) {
		return "<" + fileName + ">";
	}

	
	

	
}
