package com.thienday.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thienday.dto.NewsDTO;
import com.thienday.entity.NewsEntity;
import com.thienday.service.INewsService;
import com.thienday.util.DateDiffUtils;

@Component
public class NewsConverter {
	
	@Autowired
	private DateDiffUtils dateDiff;
	
	@Autowired
	private INewsService newsService; 
	
	public NewsDTO toDTO(NewsEntity entity){
		NewsDTO result = new NewsDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setContent(entity.getContent());
		result.setThumbnail(entity.getThumbnail());
		result.setCategoryCode(entity.getCategory().getCode());
		result.setCategoryId(entity.getCategory().getId());
		result.setCreatedDate(getTimestamp(entity.getCreatedDate()));
		try {
			result.setTimePosted(dateDiff.getCreatedDaysDiff(entity.getCreatedDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		result.setModifiedBy(entity.getModifiedBy());
		result.setTimeNewsPosted(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(entity.getCreatedDate()));
		return result;
	}
	
	public NewsEntity toEntity(NewsDTO dto) {
		NewsEntity result = new NewsEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		if(!dto.getImageFile().isEmpty())
		{
			result.setThumbnail(newsService.saveImage(dto.getImageFile()));
		}else {
			result.setThumbnail(dto.getThumbnail());
		}
		return result;
	}
	
	public NewsEntity toEntity(NewsEntity result,NewsDTO dto) {
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		if(!dto.getImageFile().isEmpty())
		{
			result.setThumbnail(newsService.saveImage(dto.getImageFile()));
		}else {
			result.setThumbnail(dto.getThumbnail());
		}
		return result;
	}
	
	 public static Timestamp getTimestamp(Date date) {
	        return date == null ? null : new java.sql.Timestamp(date.getTime());
	    }
	
}
