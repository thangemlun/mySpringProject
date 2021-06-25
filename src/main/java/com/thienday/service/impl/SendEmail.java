package com.thienday.service.impl;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.thienday.constant.MailPropertiesConstants;
import com.thienday.dto.UserDTO;
import com.thienday.service.ISendEmail;

@Service
public class SendEmail implements ISendEmail {
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	@Override
	public boolean sendEmail(UserDTO userDTO) {
		boolean  test = false ;
		
		if(userDTO != null)
		{
			String toEmail = userDTO.getEmail();
			String subject = "Xác nhận Email người dùng";
			String content = "Bạn đã đăng ký thành công. \n Vui lòng xác minh tài khoản của bạn với mã này: " + userDTO.getConfirmCode();
			Mail(MailPropertiesConstants.FROM_USER,toEmail,subject,content);
			test = true ;
		}			
//			Message mess = new MimeMessage(session);
//			mess.setFrom(new InternetAddress(MailPropertiesConstants.FROM_USER));
//			mess.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
//			mess.setSubject("Xác nhận Email người dùng");
//			mess.setText("Bạn đã đăng ký thành công.Vui lòng xác minh tài khoản của bạn với mã này: " + userDTO.getConfirmCode());
			
//			Transport.send();
				
		return test;
	}

 	public void Mail(String from , String to , String subject,String content)
 	{
 		SimpleMailMessage mailMessage = new SimpleMailMessage();
 		mailMessage.setFrom(from);
 		mailMessage.setTo(to);
 		mailMessage.setSubject(subject);
 		mailMessage.setText(content);
 		
 		mailSender.send(mailMessage);
 	}
	
}
