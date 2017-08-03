package controllers.student;

import play.mvc.Controller;
import service.student.view.ViewTestService;

/**
 * 
  * @ClassName: ViewTestController
  * @Description: 查看考试成绩
  * @author:xuwenping
  * @date: 2017年8月2日
 */
public class ViewTestController extends Controller {

	/**
	 * 
	  * @Title: go2ViewTestListPage
	  * @Description: 进入考试列表页面
	  * @author: xuwenping
	  * @date: 2017年8月2日
	 */
	public static void go2ViewTestListPage() {
		Long student_id = Long.valueOf(session.get("student_id"));
		ViewTestService viewTestService = ViewTestService.getInstance();
		renderArgs.put("viewTestPageVOs", viewTestService.go2ViewTestListPage(student_id));
		renderTemplate("/studentHome/view/testList.html");
	}
	
	/**
	 * 
	  * @Title: go2ViewTestPage
	  * @Description: 查看考试详情 -答题卡
	  * @author: xuwenping
	  * @date: 2017年8月2日
	 */
	public static void go2ViewTestPage(Long student_id, Long testRecord_id) {
		ViewTestService viewTestService = ViewTestService.getInstance();
		renderArgs.put("viewTestPageVOs", viewTestService.go2ViewTestPage(testRecord_id, student_id));
		renderTemplate("/studentHome/view/test.html");
	}
}
