package controllers.login;

import play.cache.Cache;
import play.mvc.Controller;
import service.login.LoginService;
import service.student.StudentService;
import service.teacher.TeacherService;
import util.MessageUtil;
import util.EnumUtil.loginType;

/**
 * 
  * @ClassName: LoginController
  * @Description: 登录Controller 教师学生登录退出统一入口
  * @author:xuwenping
  * @date: 2017年7月26日
 */
public class LoginController extends Controller{

	private static LoginService loginService = LoginService.getInstance();
	
	/**
	 * 
	  * @Title: index
	  * @Description: 跳转至登录页面
	  * @author: xuwenping
	  * @date: 2017年7月26日
	 */
	public static void go2IndexPage() {
		renderTemplate("index.html");
	}

	/**
	 * 
	  * @Title: login
	  * @Description: 登录Controller
	  * @author: xuwenping
	  * @date: 2017年7月26日
	  * @param username
	  * @param password
	  * @param loginType
	 */
	public static void login(String username, String password, Integer loginType) {
		boolean flag = loginService.login(username, password, loginType, session);
		if (flag == true) {
			if (loginType.equals(util.EnumUtil.loginType.teacher.ordinal())) {
				renderArgs.put("teacher", TeacherService.getInstance().findTeacher(Long.valueOf(session.get("teacher_id"))));
				renderTemplate("teacherHome/home.html");
			}
			if (loginType.equals(util.EnumUtil.loginType.student.ordinal())) {
				renderArgs.put("student", StudentService.getInstance().findStudent(Long.valueOf(session.get("student_id"))));
				renderTemplate("studentHome/home.html");
			}
		}
		renderArgs.put("message", MessageUtil.loginFail + MessageUtil.loginAgain);
		renderTemplate("index.html");
	}
	
	/**
	 * 
	  * @Title: logOut
	  * @Description: student teacher 退出登录统一入口
	  * @author: xuwenping
	  * @date: 2017年7月26日
	 */
	public static void logout() {
		loginService.logout(session, flash);
		renderArgs.put("message", MessageUtil.logoutSucc);
		render("index.html");
	}
}
