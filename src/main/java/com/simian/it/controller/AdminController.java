package com.simian.it.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simian.it.entity.Admin;
import com.simian.it.entity.Course;
import com.simian.it.entity.Courseclass;
import com.simian.it.entity.News;
import com.simian.it.entity.Student;
import com.simian.it.entity.Teacher;
import com.simian.it.service.AdminService;
import com.simian.it.service.CourseService;
import com.simian.it.service.CourseclassService;
import com.simian.it.service.NewsService;
import com.simian.it.service.StudentService;
import com.simian.it.service.TeacherService;
import com.simian.it.tool.Result;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin")
public class AdminController {
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
	
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public void save() {
		Admin admin = new Admin();
		admin.setName("hhh");
		System.out.println("controller");
		adminService.save(admin);
	}
	@RequestMapping("/update")
	public String Update() {
		Admin admin = new Admin(1, "222", "222");
		adminService.save(admin);
		return "success";
	}
	@RequestMapping("/login")
	public String login(Admin admin) {
		Boolean isRight = adminService.login(admin);
		if (isRight) {
			return "success";
		}
		return "error";
	}
	
	// news 新闻
	// 增
	@RequestMapping("/news/save")
	@ResponseBody
	public Map<String, Object> saveNews(@RequestBody News news) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isSave = newsService.save(news);
		if (isSave) {
			result.setCode("1");
			result.setText("添加成功！");
		}else {
			result.setCode("0");
			result.setText("添加失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	@RequestMapping("/news/add")
	public void addnn(String content) {
		System.out.println(content);
	}
	// 删
	@RequestMapping("/news/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteNews(@PathVariable("id") Integer id) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isDelete = newsService.deleteOne(id);
		if (isDelete) {
			result.setCode("1");
			result.setText("删除成功！");
		}else {
			result.setCode("0");
			result.setText("删除失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	@RequestMapping("/news/delete")
	@ResponseBody
	public Map<String, Object> deleteNews(@RequestParam(value = "ids") Integer[] ids) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isDelete = newsService.deleteSelected(ids);
		if (isDelete) {
			result.setCode("1");
			result.setText("删除成功！");
		}else {
			result.setCode("0");
			result.setText("删除失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	// 改
	@RequestMapping("/news/update")
	public String updateNews(News news) {
		Boolean isUpdate = newsService.update(news);
		if(isUpdate) {
			return "success";
		}
		return "error";
	}
	// 查
	@RequestMapping("/news/get/{id}")
	@ResponseBody
	public Map<String, Object> getNewsById(@PathVariable("id") Integer id) {
		News news = newsService.getById(id);
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		if (news == null) {
			result.setCode("0");
			result.setText("找不到对应编号的资讯");
			resultMap.put("result", result);
		}else {
			result.setCode("1");
			result.setText("成功");
			resultMap.put("result", result);
			resultMap.put("data", news);
		}
		return resultMap;
	}
	/*@RequestMapping("/news/get/page/{index}/{size}")
	@ResponseBody
	public Page<News> getNewsByPageIndex(@PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<News> news = newsService.getByPageIndex(index, size);
		return news;
	}*/
	@RequestMapping("/news/get/page/{keyword}/{index}/{size}")
	@ResponseBody
	public Page<News> getNewsByKeyword(@PathVariable("index") Integer index, @PathVariable("size") Integer size, @PathVariable("keyword") String keyword) {
		Page<News> news = newsService.getByKeyword(keyword, index, size);
		return news;
	}
	@RequestMapping("/news/get/page/{index}/{size}")
	@ResponseBody
	public Page<News> getNewsByPageIndex(String keyword, @PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<News> news;
		if(keyword.equals("")) {
			news = newsService.getByPageIndex(index, size);
		}else {
			news = newsService.getByKeyword(keyword, index, size);
		}
		return news;
	} 
	
	// course 课程
	@RequestMapping("/course/save")
	public String saveCourse() {
		Course course = new Course();
		course.setName("JAVA基础");
		courseService.save(course);
		return "success";
	}
	@RequestMapping("/course/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteCourse(@PathVariable("id") Integer id) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isDelete = courseService.deleteOne(id);
		if (isDelete) {
			result.setCode("1");
			result.setText("删除成功！");
		}else {
			result.setCode("0");
			result.setText("删除失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	@RequestMapping("/course/update")
	public String updateCourse(Course course) {		
		Boolean isUpdate = courseService.update(course);
		if(isUpdate) {
			return "success";
		}
		return "error";
		
	}
	// 查
	@RequestMapping(value="/course/get/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCourseById(@PathVariable("id") Integer id) {
		Course course = courseService.getById(id);
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		if (course == null) {
			result.setCode("0");
			result.setText("找不到对应编号的课程");
			resultMap.put("result", result);
		} else {
			result.setCode("1");
			result.setText("成功");
			resultMap.put("result", result);
			resultMap.put("data", course);
		}
		return resultMap;
	}
	/*@RequestMapping("/course/get/page/{index}/{size}")
	@ResponseBody
	public Page<Course> getCourseByPageIndex(@PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<Course> course = courseService.getByPageIndex(index, size);
		return course;
	}*/
	@RequestMapping(value="/course/get/page/{index}/{size}", method=RequestMethod.GET)
	@ResponseBody
	public Page<Course> getCourse(String keyword, @PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<Course> course;
		if(keyword.equals("")) {
			course = courseService.getByPageIndex(index, size);
		}else {
			course = courseService.getByKeyword(keyword, index, size);
		}
		return course;
	} 	
	
	// courseclass 课程班
	@RequestMapping("/courseclass/save")
	public String saveCourseclass() {
		Courseclass courseclass = new Courseclass();
		courseclass.setName("JAVA基础1");
		return "success";
	}
	@RequestMapping("/courseclass/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteCourseclass(@PathVariable("id") Integer id) {
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		Boolean isDelete = courseclassService.deleteOne(id);
		if (isDelete) {
			result.setCode("1");
			result.setText("删除成功！");
		}else {
			result.setCode("0");
			result.setText("删除失败！");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	@RequestMapping("/courseclass/update")
	public String updateCourseclass(Courseclass courseclass) {		
		Boolean isUpdate = courseclassService.update(courseclass);
		if(isUpdate) {
			return "success";
		}
		return "error";
		
	}
	// 查
	@RequestMapping("/courseclass/get/{id}")
	@ResponseBody
	public Map<String, Object> getCourseclassById(@PathVariable("id") Integer id) {
		Courseclass courseclass = courseclassService.getById(id);
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		if (courseclass == null) {
			result.setCode("0");
			result.setText("找不到对应编号的课程");
			resultMap.put("result", result);
		} else {
			result.setCode("1");
			result.setText("成功");
			resultMap.put("result", result);
			resultMap.put("data", courseclass);
		}
		return resultMap;
	}
	@RequestMapping("/courseclass/get/page/{index}/{size}")
	@ResponseBody
	public Page<Courseclass> getCourseclassByPageIndex(@PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<Courseclass> courseclass = courseclassService.getByPageIndex(index, size);
		return courseclass;
	}
	@RequestMapping("/courseclass/get/page/{index}/{size}/{keyword}")
	@ResponseBody
	public Page<Courseclass> getCourseclassByKeyword(@PathVariable("index") Integer index, @PathVariable("size") Integer size,
			@PathVariable("keyword") String keyword) {
		Page<Courseclass> courseclass = courseclassService.getByKeyword(keyword, index, size);
		return courseclass;
	}	
	
	// 学生
	@RequestMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable("id") Integer id) {
		Boolean isDelete = studentService.deleteOne(id);
		if (isDelete) {
			return "success";
		}
		return "error";
	}
	@RequestMapping("/student/update")
	public String updateStudent(Student student) {		
		Boolean isUpdate = studentService.update(student);
		if(isUpdate) {
			return "success";
		}
		return "error";
		
	}
	// 查
	@RequestMapping("/student/get/{id}")
	@ResponseBody
	public Map<String, Object> getStudentById(@PathVariable("id") Integer id) {
		Student student = studentService.getById(id);
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		if (student == null) {
			result.setCode("0");
			result.setText("找不到对应编号的学生");
			resultMap.put("result", result);
		} else {
			result.setCode("1");
			result.setText("成功");
			resultMap.put("result", result);
			resultMap.put("data", student);
		}
		return resultMap;
	}
	@RequestMapping("/student/get/page/{index}/{size}")
	@ResponseBody
	public Page<Student> getStudentByPageIndex(@PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<Student> student = studentService.getByPageIndex(index, size);
		return student;
	}
	@RequestMapping("/student/get/page/{index}/{size}/{keyword}")
	@ResponseBody
	public Page<Student> getStudentByKeyword(@PathVariable("index") Integer index, @PathVariable("size") Integer size,
			@PathVariable("keyword") String keyword) {
		Page<Student> student = studentService.getByKeyword(keyword, index, size);
		return student;
	}	
	
	// teacher 老师
	@RequestMapping("/teacher/save")
	public String saveTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("JAVA基础1");
		return "success";
	}
	@RequestMapping("/teacher/delete/{id}")
	public String deleteTeacher(@PathVariable("id") Integer id) {
		Boolean isDelete = teacherService.deleteOne(id);
		if (isDelete) {
			return "success";
		}
		return "error";
	}
	@RequestMapping("/teacher/update")
	public String updateTeacher(Teacher teacher) {		
		Boolean isUpdate = teacherService.update(teacher);
		if(isUpdate) {
			return "success";
		}
		return "error";
		
	}
	@RequestMapping("/teacher/get/{id}")
	@ResponseBody
	public Map<String, Object> getTeacherById(@PathVariable("id") Integer id) {
		Teacher teacher = teacherService.getById(id);
		Result result = new Result();
		Map<String, Object> resultMap = new HashMap<>();
		if (teacher == null) {
			result.setCode("0");
			result.setText("找不到对应编号的老师");
			resultMap.put("result", result);
		} else {
			result.setCode("1");
			result.setText("成功");
			resultMap.put("result", result);
			resultMap.put("data", teacher);
		}
		return resultMap;
	}
	@RequestMapping("/teacher/get/page/{index}/{size}")
	@ResponseBody
	public Page<Teacher> getTeacherByPageIndex(@PathVariable("index") Integer index, @PathVariable("size") Integer size) {
		Page<Teacher> teacher = teacherService.getByPageIndex(index, size);
		return teacher;
	}
	@RequestMapping("/teacher/get/page/{index}/{size}/{keyword}")
	@ResponseBody
	public Page<Teacher> getTeacherByKeyword(@PathVariable("index") Integer index, @PathVariable("size") Integer size,
			@PathVariable("keyword") String keyword) {
		Page<Teacher> teacher = teacherService.getByKeyword(keyword, index, size);
		return teacher;
	}	
}
