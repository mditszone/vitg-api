package com.vitg.entity;
import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = " subTopicConcept ")
public class  SubTopicConcept   implements Serializable{
	private static final long serialVersionUID = 4130758031076098234L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "concept")
	private String concept;
	
	@Column(name = "trainer_ppt")
	private String trainerPpt;
	
	@Column(name = "examples")
	private String examples;
	
	@Column(name = "youtube_url")
	private String youtubeUrl;
	
	@Column(name = "github_url")
	private String githubUrl;
	
	@Column(name = "other_urls")
	private String otherUrls;
	
	@OneToOne( targetEntity = SubTopic.class)
	@JoinColumn(name = "subTopic_id", referencedColumnName = "id")
	private SubTopic subTopic;
}

