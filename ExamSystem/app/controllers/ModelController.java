package controllers;

import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;

/**
  * @ClassName: ModelController
  * @Description: 
  * ModelController类，是所有自定义Controller的父类
  * 可以实现的功能有： 1. 拦截器
  * @author:xuwenping
  * @date: 2017年7月26日
 */
public class ModelController extends Controller{

	/**
	  * @Title: loginInterceptor
	  * @Description: 登录拦截器
	  * @author: xuwenping
	  * @date: 2017年7月26日
	 */
	@Before(unless="")
	static void loginInterceptor() {
		Logger.info("ModelController.loginInterceptor is run!");
	}
}
