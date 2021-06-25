package com.thienday.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thienday.constant.SaveImageUrl;

/**
 * Servlet implementation class LoadImage
 */
@WebServlet(urlPatterns = "/image/*")
public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private String imagePath ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void init() throws ServletException {
		imagePath = System.getProperty("catalina.home") + File.separator + SaveImageUrl.SAVE_NEWS_URL ;
		
		super.init();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET REQUESTED IMAGE BY PATH INFO
		String requestedImage = request.getPathInfo();
		if(null == requestedImage) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return ;
		}
		
		File image = new File(imagePath, URLDecoder.decode(requestedImage,"UTF-8"));
		
		//Check if file actually exists in file system
		if(!image.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return ;
		}
		
		String contentType = getServletContext().getMimeType(image.getName());
		
		//Check if file is an image (forbid hacker download our files )
		if(contentType == null || contentType.startsWith("image")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return ;
		}
		
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-length", String.valueOf(image.length()));
		
		Files.copy(image.toPath(), response.getOutputStream());
		
	}

}
