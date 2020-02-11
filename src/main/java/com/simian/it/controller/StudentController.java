package com.simian.it.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simian.it.entity.Comment;
import com.simian.it.entity.Courseclass;
import com.simian.it.entity.Student;
import com.simian.it.service.AdminService;
import com.simian.it.service.CommentService;
import com.simian.it.service.CourseService;
import com.simian.it.service.CourseclassService;
import com.simian.it.service.NewsService;
import com.simian.it.service.StudentService;
import com.simian.it.service.TeacherService;
import com.simian.it.tool.Result;
import com.simian.it.tool.VerifyCode;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseclassService courseclassService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(Student student) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isRight = studentService.login(student);
		if (isRight) {
			/*result.setCode("1");
			result.setText("登陆成功");*/
			result = new Result("1", "登陆成功");
		}else {
			result.setCode("0");
			result.setText("账号信息有误！");
		}
		resultMap.put("result", result);
		return resultMap;
	}	
	@RequestMapping("/register/check")
	@ResponseBody
	public Map<String, Object> isNameExisted(String name) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isExisted = studentService.isNameExisted(name);
		if (isExisted) {
			result.setCode("0");
			result.setText("该名字已被占用，请更换！");
		}else {
			result.setCode("1");
			result.setText("该名字未被占用，可使用！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, Object> saveStudent(Student student) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isRegister = studentService.save(student);
		if (isRegister) {
			result.setCode("1");
			result.setText("恭喜你，注册成功！");
		}else {
			result.setCode("0");
			result.setText("很遗憾，注册失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	// 改
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(Student student) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isUpdate = studentService.update(student);
		if (isUpdate) {
			result.setCode("1");
			result.setText("恭喜你，修改成功！");
		}else {
			result.setCode("0");
			result.setText("很遗憾，修改失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	// 评论
	@RequestMapping("/comment/save")
	public String saveComment() {
		Comment comment = new Comment();
		comment.setContent("hao");
		Student student = studentService.getById(1);
		comment.setStudent(student);
		commentService.save(comment);
		return "success";
	}
	
	// 课程班
	@RequestMapping("/courseclass/save")
	public String saveCourseclass() {
		Student student = studentService.getById(1);
		Courseclass courseclass = courseclassService.getById(1);
		
		student.getCourseclasses().add(courseclass);
		studentService.save(student);
		return "success";
	}
	
	// test
	@RequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		request.getSession().setAttribute("vcode", vc.getText());
		
		VerifyCode.output(image, response.getOutputStream());
	}
}
