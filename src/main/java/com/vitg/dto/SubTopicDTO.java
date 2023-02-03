package com.vitg.dto;

import lombok.Data;

@Data
public class SubTopicDTO {
	private int id;	
	private String name;
	private TopicDTO topic;
}
