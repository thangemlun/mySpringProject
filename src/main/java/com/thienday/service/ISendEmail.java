package com.thienday.service;

import com.thienday.dto.UserDTO;

public interface ISendEmail {
	public String getRandom();
	public boolean sendEmail(UserDTO userDTO);
}
