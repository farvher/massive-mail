package com.massivemail.massivemailing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.massivemail.massivemailing.entity.Email;
import com.massivemail.massivemailing.repository.EmailRepository;
import com.massivemail.massivemailing.util.MailSender;

@Service
public class EmailServicesImpl implements EmailServices {

	@Autowired
	private EmailRepository emailRepo;

	@Override
	public void saveMail(Email email) {
		emailRepo.save(email);
	}

	@Override
	public void sendMail(Email email) {
		MailSender.enviarMensaje(email);
	}
	
}
