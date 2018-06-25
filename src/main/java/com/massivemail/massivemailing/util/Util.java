package com.massivemail.massivemailing.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.tomcat.util.codec.binary.Base64;

import com.massivemail.massivemailing.entity.Email;

import javax.activation.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Util {
	

	private static String key = "Bar12345Bar12345";
	
	private static String initVector ="RandomInitVector";


	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
		}

		return null;
	}
	

	private static final String ARG1 = decrypt("sGmux/0qvijjCoPEq7yZL9PAtRWKG43KDTghiq5DSy4rPMBPN/WTd4wYrIN2cB6R");
	private static final String ARG2 = decrypt("rX9+uhVYvgLfcIhUrUEUcg==");

	public static void enviarMensaje(Email email) {

		Properties props = System.getProperties();
		GenProperties.getSmtpGmail(props);
		final String arg1 = ARG1;
		final String arg2 = ARG2;
		try {
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(arg1, arg2);
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(email.getFrom()));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));
			msg.setSubject(email.getSubject());
			msg.setContent(email.getContent(), "text/html; charset=utf-8");
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Success");
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}