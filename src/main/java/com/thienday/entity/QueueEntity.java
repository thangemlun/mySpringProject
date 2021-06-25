package com.thienday.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "queue")
public class QueueEntity extends BaseEntity{
	
	@Column(name= "queue_name")
	private String queueName;

	@OneToMany(mappedBy = "queue")
	private List<Queue_NewsEntity> queueNews = new ArrayList<>();
	

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	
	
}
