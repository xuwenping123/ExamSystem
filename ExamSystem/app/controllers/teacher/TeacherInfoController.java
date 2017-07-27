package controllers.teacher;

import models.user.Teacher;
import play.Logger;
import play.mvc.Controller;import service.login.LoginService;
import service.teacher.TeacherService;
import util.EnumUtil.loginType;

/**
 * 
  * @ClassName: TeacherInfoController
  * @Description: 老师信息修改controller
  * @author:xuwenping
  * @date: 2017年7月26日
  * @version V1.0
 */
public class TeacherInfoController extends Controller{

	private static TeacherService teacherService = TeacherService.getInstance();
	/**
	 * 
	  * @Title: go2EditTeacherPage
	  * @Description: 跳转至编辑老师信息页面
	  * @author: xuwenping
	  * @date: 2017年7月27日
	 */
	public static void go2EditTeacherPage(Long id) {
		Teacher teacher = teacherService.findTeacher(id);
		renderArgs.put("teacher", teacher);
		renderTemplate("teacherHome/editTeacher.html");
	}
	
	/**
	  * @Title: editTeacher
	  * @Description: 编辑个人信息
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param id
	  * @param name
	  * @param age
	  * @param username
	  * @param password
	 */
	public static void editTeacher(Long id, String name, Integer age, String username, String password) {
		Teacher teacher = teacherService.editTeacher(id, name, age, username, password);
		Logger.info("%s用户%s修改个人信息", loginType.teacher, teacher.getName());
		renderArgs.put("teacher", teacher);
		renderTemplate("teacherHome/home.html");
	}
}
