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
@Table(name = "subCourse")
public class SubCourse  implements Serializable{
	
	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image", length = 1000)
	private byte[] image;
	
	@Column(name = "duration_days")
	private int durationDays;
	
	@Column(name = "duration_hours")
	private int durationHours;
	
	@Column(name = "youtube_url")
	private String youtubeUrl;
	
	@Column(name = "fee")
	private int fee;
	
	@Column(name = "overview")
	@Lob
	private String overview;
	
	@Column(name = "curriculum")
	@Lob
	private String curriculum;
	
	@Column(name = "exam_certification")
	private String examCertification;
	
	@Column(name = "training_mode")
	private String trainingMode;	

	@ManyToOne( targetEntity = Course.class)
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;

}
