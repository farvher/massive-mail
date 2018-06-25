package com.massivemail.massivemailing.util;

import java.util.Properties;

public class GenProperties {

	public static void getSmtpGmail(Properties props) {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "false");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
	}

	public static void getSmtpHotmail(Properties props) {
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.live.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

	}

	public static void getSmtpOutlook(Properties props) {
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

	}
}
