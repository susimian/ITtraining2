package com.simian.it.entity;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_courseclass")
public class Courseclass {
	@Id @Column(name="courseclass_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Date start;
	private Date end;
	private Integer count;
	private String status;
	
	@ManyToMany(targetEntity=Student.class)
	@JoinTable(name="student_courseclass",
		joinColumns=@JoinColumn(name="courseclass_id",referencedColumnName="courseclass_id"),
		inverseJoinColumns=@JoinColumn(name="student_id",referencedColumnName="student_id")
	)
	private Set<Student> students = new HashSet<>();
	
	@ManyToOne(targetEntity=Teacher.class)
	@JoinColumn(name="teacher_id", referencedColumnName="teacher_id")
	private Teacher teacher;
	
	
	@ManyToOne(targetEntity=Course.class)
	@JoinColumn(name="course_id", referencedColumnName="course_id")
	private Course course;	
	
	@OneToMany(targetEntity=Comment.class, mappedBy="courseclass")
	private Set<Comment> comments = new HashSet<>();

	public Courseclass() {}

	public Courseclass(Integer id, String name, Date start, Date end, Integer count, String status,
			Set<Student> students, Teacher teacher, Course course, Set<Comment> comments) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.count = count;
		this.status = status;
		this.students = students;
		this.teacher = teacher;
		this.course = course;
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Courseclass [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + ", count=" + count
				+ ", status=" + status + ", students=" + students + ", teacher=" + teacher + ", course=" + course
				+ ", comments=" + comments + "]";
	}
	
	
}
