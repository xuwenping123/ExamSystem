package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Paper;
import models.Subject;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.TestInfo;
import play.mvc.Controller;
/**
 * 控制登录模块
 * @author think
 *
 */
public class LoginController extends Controller {

	/**
	 * 登录校验
	 * @param loginType
	 * @param username
	 * @param password
	 * @param submit 关键字，用于判断是登录还是注册
	 */
	public static void login(int loginType, String username, String password, String submit) {
		if (submit.equals("0")) {
			//注册，跳转至注册页面
			render("Application/loginAdd.html");
		}
		if (loginType == controllers.util.EnumUtil.loginType.teacher.ordinal()) {
			List<Teacher> teachers = Teacher.find("username = ? and password = ?", username, password).fetch();
			if (teachers != null && teachers.size() != 0) {
				Teacher teacher = teachers.get(0);
				session.put("loginType", "0");
				session.put("teacher", teacher);
				session.put("teacher_id", teacher.getId());
				render("users/teacher.html", teacher);
			}
			renderArgs.put("message", "登录失败，用户名或密码错误！请重新登录");
			render("Application/index.html");
		} else if (loginType == controllers.util.EnumUtil.loginType.student.ordinal()) {
			List<Student> students = Student.find("username = ? and password = ?", username, password).fetch();
			if (students != null && students.size() != 0) {
				Student student = students.get(0);
				session.put("loginType", "1");
				session.put("student", student);
				session.put("student_id", student.getId());
				List<TestInfo> testInfos = getTestInfo2do(student.id);
				List<TestInfo> testInfosDones = getTestInfodone(student.id);
				render("users/student.html", student, testInfos, testInfosDones);
			}
			renderArgs.put("message", "登录失败，用户名或密码错误！请重新登录");
			renderTemplate("Application/index.html");
		}
	}
	
	/**
	 * 获取Student_id即将要参加的考试
	 * @param student_id
	 * @return	
	 */
	public static List<TestInfo> getTestInfo2do(long student_id) {
		List<TestDetail> testDetails = TestDetail.find("student_id = ?", student_id).fetch();
		List<TestInfo> testInfos = new ArrayList<TestInfo>();
		Date now = new Date();
		TestDetail testDetail;
		TestRecord testRecord;
		for (int i = 0; i < testDetails.size(); i++) {
			testDetail = testDetails.get(i);
			testRecord = TestRecord.findById(testDetail.testRecord_id);
			if (now.before(testRecord.beginTime) == true) {
				Paper paper = Paper.findById(testRecord.paper_id);
				Teacher teacher = Teacher.findById(testDetail.teacher_id);
				Student student = Student.findById(testDetail.student_id);
				Subject subject = Subject.findById(paper.subject_id);
				testInfos.add(new TestInfo(paper, teacher, student, subject, testRecord, testDetail));
			} 
		}
		return testInfos;
	}
	
	/**
	 * 获取student_id 已经参加的考试
	 * @param student_id
	 * @return
	 */
	public static List<TestInfo> getTestInfodone(long student_id) {
		List<TestDetail> testDetails = TestDetail.find("student_id = ?", student_id).fetch();
		List<TestInfo> testInfos = new ArrayList<TestInfo>();
		Date now = new Date();
		TestDetail testDetail;
		TestRecord testRecord;
		for (int i = 0; i < testDetails.size(); i++) {
			testDetail = testDetails.get(i);
			testRecord = TestRecord.findById(testDetail.testRecord_id);
			if (now.after(testRecord.endTime) == true) {
				Paper paper = Paper.findById(testRecord.paper_id);
				Teacher teacher = Teacher.findById(testDetail.teacher_id);
				Student student = Student.findById(testDetail.student_id);
				Subject subject = Subject.findById(paper.subject_id);
				testInfos.add(new TestInfo(paper, teacher, student, subject, testRecord, testDetail));
			} 
		}
		return testInfos;
	}
	
	/**
	 * 注册信息，学生老师同一入口
	 * @param name
	 * @param age
	 * @param username
	 * @param password
	 * @param loginType
	 */
	public static void loginAdd(String name, Integer age, String username, String password, Integer loginType) {
		if (loginType == 0) {
			TeacherController.addTeacher(name, age, username, password);
		}
		if (loginType == 1) {
			StudentController.addStudent(name, age, username, password);
		}
	}
	
	/**
	 * 退出按钮
	 */
	public static void logOut() {
		session.clear();
		flash.clear();
		renderArgs.put("message", "您已经成功退出！");
		render("Application/index.html");
	}
}
