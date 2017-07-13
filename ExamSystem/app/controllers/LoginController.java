package controllers;

import java.util.List;

import models.user.Student;
import models.user.Teacher;
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
	 */
	public static void login(int loginType, String username, String password) {
		if (loginType == controllers.util.EnumUtil.loginType.teacher.ordinal()) {
			List<Teacher> teachers = Teacher.find("username = ? and password = ?", username, password).fetch();
			if (teachers != null && teachers.size() != 0) {
				Teacher teacher = teachers.get(0);
				session.put("loginType", "0");
				session.put("teacher", teacher);
				render("users/teacher.html", teacher);
			}
			renderArgs.put("message", "登录失败，请重新登录");
			render("Application/index.html");
		} else if (loginType == controllers.util.EnumUtil.loginType.student.ordinal()) {
			List<Student> students = Student.find("username = ? and password = ?", username, password).fetch();
			if (students != null && students.size() != 0) {
				Student student = students.get(0);
				session.put("loginType", "1");
				session.put("student", student);
				render("users/student.html", student);
			}
			renderArgs.put("message", "登录失败，请重新登录");
			render("Application/index.html");
		}
	}
}
