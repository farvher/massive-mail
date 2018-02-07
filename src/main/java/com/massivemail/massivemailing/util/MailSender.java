package com.massivemail.massivemailing.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.massivemail.massivemailing.entity.Email;

import javax.activation.*;

public class MailSender {

	private static final String USER = "ghostwalking135@gmail.com";
	private static final String PASS = "THEMEANINGOFPAIN";

	public static void enviarMensaje(Email email) {

		Properties props = System.getProperties();
		SmtpProperties.getSmtpGmail(props);
		final String username = USER;
		final String password = PASS;
		try {
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(email.getFrom()));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
			msg.setSubject(email.getSubject());
			msg.setContent(email.getContent(), "text/html; charset=utf-8");
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Mensaje enviado");
		} catch (Exception e) {
			System.out.println("Error enviando: " + e);
		}
	}
}