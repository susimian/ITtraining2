package com.simian.it.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simian.it.entity.Courseclass;
import com.simian.it.entity.Student;
import com.simian.it.entity.Teacher;
import com.simian.it.service.AdminService;
import com.simian.it.service.CommentService;
import com.simian.it.service.CourseService;
import com.simian.it.service.CourseclassService;
import com.simian.it.service.NewsService;
import com.simian.it.service.ScoreService;
import com.simian.it.service.StudentService;
import com.simian.it.service.TeacherService;
import com.simian.it.tool.Result;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
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
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(Teacher teacher) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isRight = teacherService.login(teacher);
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
	
	@RequestMapping("/save")
	public String save() {
		Teacher teacher = new Teacher();
		teacher.setName("苏老师");
		teacherService.save(teacher);
		return "success";
	}
	
	// score
	@RequestMapping("/score/save")
	public void saveScore() {
		scoreService.save(1, 1, 20, 10);
	}
}
