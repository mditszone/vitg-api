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
	
	@Column(name = "concept")
	@Lob
	private String concept;
	
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

