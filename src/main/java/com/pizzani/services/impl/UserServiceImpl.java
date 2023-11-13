package com.pizzani.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzani.entities.Confirmation;
import com.pizzani.entities.User;
import com.pizzani.repositories.ConfirmationRepository;
import com.pizzani.repositories.UserRepository;
import com.pizzani.services.EmailService;
import com.pizzani.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final ConfirmationRepository confirmationRepository;
	@Autowired
	private final EmailService emailService;

	@Override
	public User saveUser(User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		user.setEnabled(false);
		userRepository.save(user);
		
		Confirmation confirmation = new Confirmation(user);
		confirmationRepository.save(confirmation);
		
//		emailService.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
//		emailService.sendMimeMessageWithAttachment(user.getName(), user.getEmail(), confirmation.getToken());
//		emailService.sendMimeMessageWithAttachment(user.getName(), user.getEmail(), confirmation.getToken());
		emailService.sendHtmlEMail(user.getName(), user.getEmail(), confirmation.getToken());
//		emailService.sendHtmlEMailWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());
		
		return user;
	}

	@Override
	public Boolean verifyToken(String token) {
		Confirmation confirmation = confirmationRepository.findByToken(token);
		User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
		user.setEnabled(true);
		userRepository.save(user);
//		confirmationRepository.delete(confirmation);
		return Boolean.TRUE;
	}

}
