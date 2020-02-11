package com.simian.it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="t_score")
public class Score {
	@Id @Column(name="score_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer theory;
	private Integer practice;
	@Transient
	private Double total;
	
	@ManyToOne(targetEntity=Student.class)
	@JoinColumn(name="student_id", referencedColumnName="student_id")
	private Student student;
	
	@ManyToOne(targetEntity=Courseclass.class)
	@JoinColumn(name="courseclass_id", referencedColumnName="courseclass_id")
	private Courseclass courseclass;

	public Score() {}

	public Score(Integer id, Integer theory, Integer practice, Double total, Student student, Courseclass courseclass) {
		super();
		this.id = id;
		this.theory = theory;
		this.practice = practice;
		this.total = total;
		this.student = student;
		this.courseclass = courseclass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTheory() {
		return theory;
	}

	public void setTheory(Integer theory) {
		this.theory = theory;
	}

	public Integer getPractice() {
		return practice;
	}

	public void setPractice(Integer practice) {
		this.practice = practice;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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
		return "Score [id=" + id + ", theory=" + theory + ", practice=" + practice + ", total=" + total + ", student="
				+ student + ", courseclass=" + courseclass + "]";
	}
	
	
}
