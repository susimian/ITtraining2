package com.simian.it.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_comment")
public class Comment {
	@Id @Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String author;
	private Date time;
	private String content;
	private Integer star;
	
	@ManyToOne(targetEntity=Student.class)
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	private Student student;
	
	@ManyToOne(targetEntity=Courseclass.class)
	@JoinColumn(name="courseclass_id", referencedColumnName="courseclass_id")
	private Courseclass courseclass;
	
	public Comment() {}

	public Comment(Integer id, String author, Date time, String content, Integer star, Student student,
			Courseclass courseclass) {
		super();
		this.id = id;
		this.author = author;
		this.time = time;
		this.content = content;
		this.star = star;
		this.student = student;
		this.courseclass = courseclass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Courseclass getCourseclass() {
		return courseclass;
	}

	public void setCourseclass(Courseclass courseclass) {
		this.courseclass = courseclass;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", time=" + time + ", content=" + content + ", star=" + star
				+ ", student=" + student + ", courseclass=" + courseclass + "]";
	}
		
}
