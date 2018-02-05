package com.massivemail.massivemailing.services;

import java.util.List;

import com.massivemail.massivemailing.entity.Email;

public interface EmailServices {

	void saveMail(Email email);

	void sendMail(Email email);

}
