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

@Entity
@Table(name="t_student")
public class Student {
	@Id @Column(name="student_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String avatar;
	private String qq;
	private String phone;
	
	@OneToMany(targetEntity=Comment.class,mappedBy="student")
	private Set<Comment> comments = new HashSet<>();
	
	@OneToMany(targetEntity=Score.class,mappedBy="student")
	private Set<Score> scores = new HashSet<>();
	
	@ManyToMany(targetEntity=Courseclass.class, fetch=FetchType.EAGER)
	@JoinTable(name="student_courseclass",
		joinColumns=@JoinColumn(name="student_id",referencedColumnName="student_id"),
		inverseJoinColumns=@JoinColumn(name="courseclass_id",referencedColumnName="courseclass_id")
	)	
	private Set<Courseclass> courseclasses = new HashSet<>();                                                       
	public Student() {}
	public Student(Integer id, String name, String password, String email, String avatar, String qq, String phone,
			Set<Comment> comments, Set<Score> scores, Set<Courseclass> courseclasses) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.avatar = avatar;
		this.qq = qq;
		this.phone = phone;
		this.comments = comments;
		this.scores = scores;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Score> getScores() {
		return scores;
	}
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	public Set<Courseclass> getCourseclasses() {
		return courseclasses;
	}
	public void setCourseclasses(Set<Courseclass> courseclasses) {
		this.courseclasses = courseclasses;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", avatar="
				+ avatar + ", qq=" + qq + ", phone=" + phone + ", comments=" + comments + ", scores=" + scores
				+ ", courseclasses=" + courseclasses + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;

		if (other.getName().equals(this.getName()) && other.getPassword().equals(this.getPassword())) {
			return true;
		}else {
			return false;
		}
	}	
}
