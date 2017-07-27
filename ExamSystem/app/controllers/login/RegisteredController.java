package controllers.login;

import controllers.ModelController;
import play.mvc.Controller;
import service.registered.RegisteredService;
import util.MessageUtil;
/**
 * 
  * @ClassName: RegisteredController
  * @Description: 老师学生注册统一入口
  * @author:xuwenping
  * @date: 2017年7月26日
 */
public class RegisteredController extends Controller{
	
	private static RegisteredService registeredService = RegisteredService.getInstance();
	
	/**
	 * 
	  * @Title: go2RegisteredPage
	  * @Description: 跳转至注册页面
	  * @author: xuwenping
	  * @date: 2017年7月26日
	 */
	public static void go2RegisteredPage() {
		renderTemplate("/registered/registered.html");
	}
	
	/**
	 * 
	  * @Title: registered
	  * @Description: 注册，依据注册类型进行业务处理
	  * @author: xuwenping
	  * @date: 2017年7月26日
	  * @param name
	  * @param age
	  * @param username
	  * @param password
	  * @param loginType	注册类型
	 */
	public static void registered(String name, Integer age, String username,
			String password, Integer loginType) {
		registeredService.registered(name, age, username, password, loginType, session);
		renderArgs.put("message", MessageUtil.registeredSucc + MessageUtil.loginAgain);
		renderTemplate("index.html");
	}
}
