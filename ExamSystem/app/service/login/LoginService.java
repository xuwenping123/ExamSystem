package service.login;

import models.user.Student;
import models.user.Teacher;
import play.Logger;
import play.cache.Cache;
import play.mvc.Scope.Flash;
import play.mvc.Scope.Session;
import service.student.StudentService;
import service.teacher.TeacherService;

public class LoginService {

	private static LoginService instance = new LoginService();
	
	public static LoginService getInstance() {
		return instance;
	}

	private TeacherService teacherService = TeacherService.getInstance();
	private StudentService studentService = StudentService.getInstance();
	
	/**
	 * 
	  * @Title: login
	  * @Description: 登录业务逻辑处理，分teacher student开处理
	  * @author: xuwenping
	  * @date: 2017年7月26日
	  * @param username
	  * @param password
	  * @param loginType
	  * @param session
	  * @return
	 */
	public boolean login(String username, String password, Integer loginType, Session session) {
		if (loginType.equals(util.EnumUtil.loginType.teacher.ordinal())) {
			Teacher teacher = teacherService.findTeacher(username, password);
			if (teacher == null) {
				return false;
			} else {
				session.put("loginType", util.EnumUtil.loginType.teacher.ordinal());
				session.put("teacher", teacher);
				session.put("teacher_id", teacher.getId());
				Logger.info("%s用户%s登录成功！", util.EnumUtil.loginType.teacher, teacher.getName());
				return true;
			}
		}
		if (loginType.equals(util.EnumUtil.loginType.student.ordinal())) {
			Student student = studentService.findStudent(username, password);
			if (student == null) {
				return false;
			} else {
				session.put("loginType", util.EnumUtil.loginType.student.ordinal());
				session.put("student", student);
				session.put("student_id", student.getId());
				Logger.info("%s用户%s登录成功！", util.EnumUtil.loginType.student, student.getName());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	  * @Title: logout
	  * @Description: 清空数据
	  * @author: xuwenping
	  * @date: 2017年7月26日
	  * @param session
	  * @param flash
	  * @param cache
	 */
	public void logout(Session session, Flash flash) {
		session.clear();
		flash.clear();
		Logger.info("%s", "当前用户退出成功！");
	}
}
