package service.registered;

import models.user.Student;
import models.user.Teacher;
import play.Logger;
import play.mvc.Scope.Session;
import service.student.StudentService;
import service.teacher.TeacherService;

public class RegisteredService {

	private static RegisteredService instance = new RegisteredService();
	
	public static RegisteredService getInstance() {
		return instance;
	}
	
	private StudentService studentService = StudentService.getInstance();
	private TeacherService teacherService = TeacherService.getInstance();
	
	/**
	 * 
	  * @Title: registered
	  * @Description: 用户注册统一入口
	  * @author: xuwenping
	  * @date: 2017年7月26日
	  * @param name
	  * @param age
	  * @param username
	  * @param password
	  * @param loginType
	  * @param session
	 */
	public void registered(
			String name, 
			Integer age, 
			String username, 
			String password,
			Integer loginType,
			Session session) {
		if (loginType.equals(util.EnumUtil.loginType.teacher.ordinal())) {
			Teacher teacher = teacherService.addTeacher(name, age, username, password);
			session.put("loginType", util.EnumUtil.loginType.teacher.ordinal());
			session.put("teacher", teacher);
			session.put("teacher_id", teacher.getId());
			Logger.info("注册%s用户 %s成功！", util.EnumUtil.loginType.teacher, teacher.getName());
		}
		if (loginType.equals(util.EnumUtil.loginType.student.ordinal())) {
			Student student = studentService.addStudent(name, age, username, password);
			session.put("loginType", util.EnumUtil.loginType.student.ordinal());
			session.put("student", student);
			session.put("student_id", student.getId());
			Logger.info("注册%s用户%s成功", util.EnumUtil.loginType.student, student.getName());
		}
	}
	
}
