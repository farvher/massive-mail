package com.massivemail.massivemailing.services;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.massivemail.massivemailing.entity.Email;
import com.massivemail.massivemailing.repository.EmailRepository;
import com.massivemail.massivemailing.util.MailSender;

@Service
public class EmailServicesImpl implements EmailServices {
	

    @Autowired()
    private EmailRepository emailRepo;

    @Override
    public void saveMail(Email email) {
        emailRepo.save(email);
    }

    @Override
    public void sendMail(Email email) {
        MailSender.enviarMensaje(email);
    }

	@Override
	public String defaulEmail() {
		String url ="https://massive-mail.herokuapp.com/wbs-email/index.html";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate.getForObject(url, String.class);
	}

   
}
