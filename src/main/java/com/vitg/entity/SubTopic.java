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
@Table(name = " subTopic ")
public class  SubTopic   implements Serializable{
	private static final long serialVersionUID = 4130758031076098234L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	
	
	@Column(name = "name")
	private String name;
	
//	@Column(name = "topic_id")
//	private boolean TopicId;
//	
	@ManyToOne( targetEntity = Topic.class)
	@JoinColumn(name = "topic_id", referencedColumnName = "id")
	private Topic topic;
}

