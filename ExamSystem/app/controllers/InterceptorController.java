package controllers;

import play.Logger;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
/**
 * 
  * @ClassName: InterceptorController
  * @author:xuwenping
  * @date: 2017年7月24日
 */
public class InterceptorController extends Controller{

	/**
	 * 拦截器 拦截在未登录情况下的基于内部操作
	 * 除却登录注册
	 * 可以优化 session 内部使用map存储数据 map.contains(String key); 
	 */
	@Before(unless={"login", "loginAdd", "addTeacher", "addStudents"})
	static void loginInterceptor() {
		String teacher_id = session.get("teacher_id");
		String student_id = session.get("student_id");
		if (teacher_id == null && student_id == null) {
			renderArgs.put("message", "登录失败，用户名或密码错误！请重新登录");
			renderTemplate("Application/index.html");
		}
	}
	
	@Catch
	public static void exceptionMethod(Throwable throwable) {
		Logger.info("~ %s 运行 ,报错 %s", "exceptionMethod()", throwable);
	}
}
