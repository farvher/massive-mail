package com.massivemail.massivemailing.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.massivemail.massivemailing.entity.Email;
import com.massivemail.massivemailing.services.EmailServices;
import com.massivemail.massivemailing.util.FileUtil;

@Controller
public class HomeController {

	private final static String VIEW_HOME = "home";

	private static final String FROM = "webbuilderssolutions@hotmail.com";

	@Autowired
	EmailServices emailServices;

	@GetMapping("/")
	public String index(Model m) {

		return VIEW_HOME;
	}

	@PostMapping("/")
	public String sendMailHtml(@RequestParam("file") MultipartFile file,
			String emails, String subject, Model m) {

		for (String to : emails.split(",|\r\n")) {

			String content = FileUtil.readFileAsString(file);
			Email email = new Email();
			email.setContent(content);
			email.setDate(new Date());
			email.setFrom(FROM);
			email.setSent(false);
			email.setSubject(subject);
			email.setTo(to);

			emailServices.saveMail(email);
			emailServices.sendMail(email);

		}
		m.addAttribute("emails", emails);
		m.addAttribute("subject", subject);
		m.addAttribute("success", "Mensajes enviados a  " + emails);

		return VIEW_HOME;
	}

}
