package com.thienday.api.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thienday.dto.NewsDTO;
import com.thienday.service.INewsService;
import com.thienday.util.ConvertStringUtils;

@CrossOrigin
@RestController(value = "NewsAPIOfAdmin")
public class NewsAPI {

	@Autowired
	private INewsService newsService;

	@Autowired
	private ConvertStringUtils convertString;

	@GetMapping("/api/news")
	public @ResponseBody NewsDTO getNews() {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setListResult(newsService.findAll());
		return newsDTO;
	}

	@PostMapping("/api/news")
	public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
		if (newsDTO != null) {
			StringBuilder result = new StringBuilder("{ " + newsDTO.getCategoryCode() + " , ");
			result.append(newsDTO.getTitle() + " , ");
			result.append(newsDTO.getShortDescription() + " , ");
			result.append(newsDTO.getContent() + " , ");
			result.append(newsDTO.getModifiedDate() + " , ");
			result.append(newsDTO.getCreatedDate() + " , ");
			result.append(newsDTO.getCreatedBy() + " , ");
			result.append(newsDTO.getModifiedBy() + " , ");
			result.append(newsDTO.getThumbnail() + " } ");
			System.out.println(result.toString());
		}
		newsService.save(newsDTO);
		return newsDTO;
	}

	@RequestMapping(value = "/api/news/upLoadImage", method = { RequestMethod.GET, RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public String saveNewsWithImage(@RequestParam("news") String news, @RequestParam("imageFile") MultipartFile file,
			HttpServletRequest request) {
		if (null != news) {
			NewsDTO newsDTO = newsService.getJson(convertString.encodeString(news), file);
			newsService.save(newsDTO);
		}
		System.out.println("Create News With Image success");
		return "create success !";
	}
//
//	@RequestMapping(value = "/api/news/upLoadImage", method = { RequestMethod.GET,RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE, 
//																								 MediaType.MULTIPART_FORM_DATA_VALUE })
//	public String updateNewsWithImage(@RequestParam("news") String news, @RequestParam("imageFile") MultipartFile file,
//			HttpServletRequest request) {
//		if (null != news) {
//			NewsDTO newsDTO = newsService.getJson(convertString.encodeString(news), file);
//			newsService.save(newsDTO);
//		}
//		System.out.println("Update News With Image success");
//		return "upload success !";
//	}

	@PutMapping("/api/news")
	public NewsDTO updateNews(@RequestBody NewsDTO newsDTO) {
		if (newsDTO != null) {
			StringBuilder result = new StringBuilder("{ " + newsDTO.getCategoryCode() + " , ");
			result.append(newsDTO.getTitle() + " , ");
			result.append(newsDTO.getShortDescription() + " , ");
			result.append(newsDTO.getContent() + " , ");
			result.append(newsDTO.getModifiedDate() + " , ");
			result.append(newsDTO.getCreatedDate() + " , ");
			result.append(newsDTO.getCreatedBy() + " , ");
			result.append(newsDTO.getModifiedBy() + " , ");
			result.append(newsDTO.getThumbnail() + " } ");
			System.out.println(result.toString());
		}
		newsService.save(newsDTO);
		return newsDTO;
	}

	@DeleteMapping("/api/news")
	public void deleteNews(@RequestBody long[] ids) {
		newsService.delete(ids);
	}
}
