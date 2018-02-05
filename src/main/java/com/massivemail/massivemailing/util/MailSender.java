package com.massivemail.massivemailing.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.massivemail.massivemailing.entity.Email;

import javax.activation.*;

public class MailSender {
	
	private static final String USER = System.getProperty("user.name");
	private static final String HOME = System.getProperty("user.home");

    public static void enviarMensaje(Email email) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = email.getFrom();//
        final String password = "";
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(email.getFrom()));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo(), false));
            msg.setSubject(email.getSubject());
            msg.setContent(email.getContent(), "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Mensaje enviado");
        } catch (MessagingException e) {
            System.out.println("Error enviando: " + e);
        }
    }
}