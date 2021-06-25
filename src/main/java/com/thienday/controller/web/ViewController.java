package com.thienday.controller.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thienday.constant.SaveImageUrl;

@Controller
public class ViewController {

	
	@RequestMapping(value = "/image-response/{thumbnail}" ,method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
	public  @ResponseBody byte[] getImage(@PathVariable String thumbnail) throws IOException{
		String rootPath = System.getProperty("catalina.home");	
		Path path = Paths.get(rootPath+File.separator+SaveImageUrl.SAVE_NEWS_URL+ thumbnail+".jpg");
		File file = new File(path.toString());
		if(file.exists())
		{
			InputStream inputStream = new FileInputStream(file);
			//InputStream in = servletContext.getResourceAsStream(rootPath+File.separator+SaveImageUrl.SAVE_NEWS_URL+ thumbnail);
			return IOUtils.toByteArray(inputStream);
		}else {
			return null; 
		}
	}	
}
