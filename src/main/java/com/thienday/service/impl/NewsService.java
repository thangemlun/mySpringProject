package com.thienday.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.thienday.constant.SaveImageUrl;
import com.thienday.converter.NewsConverter;
import com.thienday.dto.NewsDTO;
import com.thienday.entity.CategoryEntity;
import com.thienday.entity.NewsEntity;
import com.thienday.repository.CategoryRepository;
import com.thienday.repository.NewsRepository;
import com.thienday.service.INewsService;

@Service
public class NewsService implements INewsService {
	@Autowired
	private NewsRepository newsRepository ;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewsConverter newsConverter;
	
	
	@Override
	public List<NewsDTO> findAll(Pageable pageable) {
		List<NewsDTO> models = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
		for(NewsEntity item : entities) {
			NewsDTO newsDTO = newsConverter.toDTO(item);
			models.add(newsDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int)newsRepository.count();
	}

	@Override
	public NewsDTO findById(Long id) {
		NewsEntity entity = newsRepository.findOne(id);
		return newsConverter.toDTO(entity) ;
	}

	@Override
	@Transactional //khi thêm sửa xóa dữ liệu phải thêm annotation này để giúp bảo toàn dữ liệu. 
	public NewsDTO save(NewsDTO newsDTO) {
		CategoryEntity category = categoryRepository.findOneByCode(newsDTO.getCategoryCode());
		NewsEntity newsEntity = new NewsEntity();
		if(newsDTO.getId() != null)
		{
			NewsEntity oldNews = newsRepository.findOne(newsDTO.getId());
			oldNews.setCategory(category);
			newsEntity = newsConverter.toEntity(oldNews, newsDTO);
		}else
		{
			newsEntity = newsConverter.toEntity(newsDTO);
			newsEntity.setCategory(category);
		}
//		return newsConverter.toDTO(newsRepository.save(newsEntity));
		return null ;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		for(long id: ids)
		{
			newsRepository.delete(id);
		}
	}

	@Override
	public int getTotalSearchItem(String keyWord) {
		// TODO Auto-generated method stub
		return newsRepository.getSearchTotalItem(keyWord);
	}
	
	@Override
	public List<NewsDTO> listAll(Pageable pageable,String keyword) {
		List<NewsDTO> models = new ArrayList<>();
		List<NewsEntity> entities ;
		if(keyword != null)
		{
			entities = newsRepository.findAll(pageable,keyword);
			for(NewsEntity item : entities)
			{
				NewsDTO newsDTO = newsConverter.toDTO(item);
				models.add(newsDTO);
			}
		}
		return models;
	}

	@Override
	public NewsDTO findByTitle(String title) {
		NewsEntity newsEntity = newsRepository.findOneByTitle(title);
		return newsConverter.toDTO(newsEntity);
	}

	@Override
	public NewsDTO findNewestNews() {
		NewsEntity newsEntity = newsRepository.findNewestNews();
				if(newsRepository.findNewestNews()!= null)
				{
					return newsConverter.toDTO(newsEntity);
				}
				else
					return null ;
	}
	

	@Override
	public List<NewsDTO> findAll(String name) {
		List<NewsDTO> models = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAll(name,new PageRequest(0, 6));
		for(NewsEntity item : entities) {
			NewsDTO newsDTO = newsConverter.toDTO(item);
			models.add(newsDTO);
		}
		return models;
	}

	@Override
	public int getTotalItemByCategory(String code) {
		return newsRepository.countItemByCategoryCode(code);
	}

	@Override
	public List<NewsDTO> findAllByCategoryCode(Pageable pageable,String code) {
		List<NewsDTO> models = new ArrayList<>();
		List<NewsEntity> entities ;
		if(code != null)
		{
			entities = newsRepository.findAllByCategoryCode(code, pageable);
			for(NewsEntity item : entities)
			{
				NewsDTO newsDTO = newsConverter.toDTO(item);
				models.add(newsDTO);
			}
		}
		return models;
	}

	@Override
	public List<NewsDTO> findAll() {
		List<NewsDTO> models = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAll();
		for(NewsEntity item : entities)
		{
			 NewsDTO newsDTO = newsConverter.toDTO(item);
			 models.add(newsDTO);
		}
		return models;
	}

	@Override
	public String saveImage(MultipartFile file) {
		if(file != null && !file.isEmpty())
		{
			try {
				String rootPath = System.getProperty("catalina.home") ;
				File dir = new File(rootPath+SaveImageUrl.SAVE_NEWS_URL);
				if(!dir.exists())
				{
					System.out.println("Tạo thư mục mới");
					dir.mkdirs();
				}
				byte[] bytes = file.getBytes();
				String name = String.valueOf(new Date().getTime()+".jpg");
				Path path = Paths.get(dir.getAbsolutePath() + File.separator + name);
				Files.write(path, bytes);
				return name;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Upload failed - " + e.getMessage());
			}
		}else {
			System.out.println("File not exists");
		}
		return null;
	}

	@Override
	public NewsDTO getJson(String news, MultipartFile file) {
		NewsDTO newsJson = new NewsDTO();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			newsJson = objectMapper.readValue(news,NewsDTO.class );
		}catch(IOException err) {
			System.out.println("error" + err.toString());
		}
		System.out.println(newsJson.getId()); 
		newsJson.setImageFile(file);
		return newsJson;
	}
	
}
