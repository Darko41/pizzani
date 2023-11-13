package com.pizzani.services;

public interface EmailService {
	
	public void sendSimpleMailMessage(String name, String to, String token);
	public void sendMimeMessageWithAttachment(String name, String to, String token);
	public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
	public void sendHtmlEMail(String name, String to, String token);
	public void sendHtmlEMailWithEmbeddedFiles(String name, String to, String token);
	

}
