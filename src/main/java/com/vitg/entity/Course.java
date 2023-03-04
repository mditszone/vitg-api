package com.vitg.entity;
import java.io.Serializable;

import java.util.Set;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course  implements Serializable{

	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	

	@Column(name = "name")
	private String name;

	@Lob
	@Column(name = "image",length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	@Column(name = "status")
	private boolean status;

	@Column(name = "description")
	@Lob
	private String description;

	@OneToMany(mappedBy = "course")
	private Set<TrainerCourse> trainerCourse;

	public void addTrainerCourse(TrainerCourse trainerCourse) {
		this.trainerCourse.add(trainerCourse);
	}

}
