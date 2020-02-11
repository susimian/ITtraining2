package com.simian.it.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_teacher")
public class Teacher {
	@Id @Column(name="teacher_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String introduce;
	
	@ManyToMany(targetEntity=Course.class)
	@JoinTable(name="teacher_course",
		joinColumns=@JoinColumn(name="teacher_id",referencedColumnName="teacher_id"),
		inverseJoinColumns=@JoinColumn(name="course_id",referencedColumnName="course_id")
	)
	private Set<Course> courses = new HashSet<>();
	
	@OneToMany(targetEntity=Courseclass.class, mappedBy="teacher")
	private Set<Courseclass> comments = new HashSet<>();

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Integer id, String name, String password, String email, String introduce, Set<Course> courses,
			Set<Courseclass> comments) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.introduce = introduce;
		this.courses = courses;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Courseclass> getComments() {
		return comments;
	}

	public void setComments(Set<Courseclass> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", introduce="
				+ introduce + ", courses=" + courses + ", comments=" + comments + "]";
	}
	
	
}
