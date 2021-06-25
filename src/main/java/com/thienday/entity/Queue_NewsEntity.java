package com.thienday.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "queue_news" )
public class Queue_NewsEntity extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="queue_id")
	private QueueEntity queue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="news_id")
	private NewsEntity news;

	public QueueEntity getQueue() {
		return queue;
	}

	public void setQueue(QueueEntity queue) {
		this.queue = queue;
	}

	public NewsEntity getNews() {
		return news;
	}

	public void setNews(NewsEntity news) {
		this.news = news;
	}
	
	
	
	
}
