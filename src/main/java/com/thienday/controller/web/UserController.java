package com.thienday.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thienday.dto.UserDTO;
import com.thienday.service.ISendEmail;
import com.thienday.service.IUserService;
import com.thienday.util.ConvertStringUtils;
import com.thienday.util.MessageUtils;

@Controller(value = "userControllerOfWeb")
public class UserController {
	
	@Autowired 
	private IUserService userService;
	
	@Autowired
	private MessageUtils messageUtil;
	
	@Autowired
	private ConvertStringUtils convertString;
	
	@Autowired 
	private ISendEmail sendEmail;
	
	private boolean isUserExisted = false;
	
	
	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	   public ModelAndView registerPage(HttpServletRequest request) {
	      ModelAndView mav = new ModelAndView("register");
	      if(request.getParameter("message")!=null)
	      {
	    	  Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
		      mav.addObject("message",message.get("message"));
		      mav.addObject("alert",message.get("alert"));
	      }
	      return mav;
	   }
	 
	 @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
	   public ModelAndView checkRegisterPage(@RequestParam(value = "userName" ,required = false) String userName,
			   @RequestParam(value = "fullName" ,required = false) String fullName,
			   @RequestParam(value = "password",required = false) String password,
			   @RequestParam(value = "passwordConfirm" ,required = false) String passwordConfirm,
			   @RequestParam(value = "status" ,required = false) Integer status,
			   @RequestParam(value = "roleId" ,required = false) Long roleId,
			   @RequestParam(value = "email" ,required = false) String email,
			   @RequestParam(value = "phonenumber" ,required = false) String phonenumber,
			   HttpServletRequest request){
	      ModelAndView mav = new ModelAndView("register");
	      UserDTO model = new UserDTO();
	      if(userName != null)
	      {
	    	  model.setUserName(userName);
	    	  this.isUserExisted = userService.isUserExist(model.getUserName());
	    	  if(isUserExisted == true)
			  {
	    		
			   	return new ModelAndView("redirect:/dang-ky?message=register_account_exist"); 	 
			  }else if(userService.isUserEmailExist(email) == true) {
				  return new ModelAndView("redirect:/dang-ky?message=register_email_exist");
			  }else if(userService.isUserPhoneNumberExist(phonenumber)) {
				  return new ModelAndView("redirect:/dang-ky?message=register_phone_exist");
			  }
	    	  else if(isUserExisted == false && password.equals(passwordConfirm) ) {
		    	  model.setFullName(convertString.encodeString(fullName));
		    	  model.setPassword(BCrypt.hashpw(convertString.encodeString(password), BCrypt.gensalt(10)));
		    	  model.setEmail(email);
		    	  model.setPhoneNumber(phonenumber);	  
		    	  model.setStatus(status);
		    	  model.setRoleId(roleId);
		    	  model.setConfirmCode(sendEmail.getRandom());
		    	  if(model.getEmail() != null)
		    	  {
		    		  boolean test = sendEmail.sendEmail(model);
			    	  
			    	  if(test) {
			    		  HttpSession session = request.getSession();
			    		  session.setAttribute("authcode", model);
			    		  return new ModelAndView("redirect:/verify-code");
			    	  }
		    	  }
		    	  
//		    	  userService.save(model);
		    	  
		      
		      }
	      }
	      return mav;
	   }
	 
	 @RequestMapping(value = "/verify-code", method = RequestMethod.GET)
	 public ModelAndView showVerifyCodePage(HttpServletRequest request) {
		 ModelAndView mav = new ModelAndView("verifycode");
//		 HttpSession session = request.getSession();
//		 UserDTO user = (UserDTO) session.getAttribute("authcode");
//		 System.out.println(user.getConfirmCode());
		 if(request.getParameter("message")!=null)
	      {
	    	  Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
		      mav.addObject("message",message.get("message"));
		      mav.addObject("alert",message.get("alert"));
	      }
		 return mav;
	 }
	 
	 @RequestMapping(value = "/verify-code", method = RequestMethod.POST)
	 public ModelAndView verifyCode(@RequestParam(value="authcode",required = false) String authCode
			 ,HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 UserDTO user = (UserDTO) session.getAttribute("authcode");
		 if(user != null)
			 {
				 if(authCode.equals(user.getConfirmCode()))
				 {
					 userService.save(user);
					 session.removeAttribute("authcode");
					return new ModelAndView("redirect:/dang-ky?message=register_success");
				 }
				 else {
					 return new ModelAndView("redirect:/verify-code?message=verify_code_error");
				 }
			 }else {
				 return new ModelAndView("redirect:/dang-ky?message=register_error");
			 }
	 }
}
