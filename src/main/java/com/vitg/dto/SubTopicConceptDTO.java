package com.vitg.dto;



import lombok.Data;

@Data
public class SubTopicConceptDTO {
	private int id;	
	private String name;
	private String concept;
	private String trainerPpt;
	private String examples;
	private String youtubeUrl;
	private String githubUrl;
	private String otherUrls;
	private SubTopicDTO subTopic;
}
