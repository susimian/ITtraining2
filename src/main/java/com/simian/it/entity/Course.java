package com.simian.it.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_course")
@DynamicUpdate(true)
public class Course {
	@Id @Column(name="course_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String introduce;
	
	@ManyToMany(targetEntity=Teacher.class, mappedBy="courses", fetch=FetchType.EAGER)
	/*@JoinTable(name="teacher_course",
		joinColumns=@JoinColumn(name="course_id",referencedColumnName="course_id"),
		inverseJoinColumns=@JoinColumn(name="teacher_id",referencedColumnName="teacher_id")
	)*/
	private Set<Teacher> teachers = new HashSet<>();
	
	@OneToMany(targetEntity=Courseclass.class, mappedBy="course")
	@JsonIgnore
	private Set<Courseclass> courseclasses = new HashSet<>();

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(Integer id, String name, String introduce, Set<Teacher> teachers, Set<Courseclass> courseclasses) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.teachers = teachers;
		this.courseclasses = courseclasses;
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

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Courseclass> getCourseclasses() {
		return courseclasses;
	}

	public void setCourseclasses(Set<Courseclass> courseclasses) {
		this.courseclasses = courseclasses;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", introduce=" + introduce + ", teachers=" + teachers
				+ ", courseclasses=" + courseclasses + "]";
	}
	
	
}
