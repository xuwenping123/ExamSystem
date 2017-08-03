package controllers.student;

import play.mvc.Controller;
import service.student.take.TakeTestService;

/**
 * 
  * @ClassName: TakeTestController
  * @Description: 参加考试
  * @author:xuwenping
  * @date: 2017年8月2日
 */
public class TakeTestController extends Controller {

	/**
	 * 
	  * @Title: go2TestListPage
	  * @Description: 进入参加考试列表页面
	  * @author: xuwenping
	  * @date: 2017年8月2日
	 */
	public static void go2TakeTestListPage(Long student_id) {
		TakeTestService takeTestService = TakeTestService.getInstance();
		renderArgs.put("takeTestListPageVOs", takeTestService.go2TakeTestListPage(student_id));
		renderTemplate("/studentHome/take/testList.html");
	}
	
	/**
	 * 
	  * @Title: go2TakeTestPage
	  * @Description: 进入考试页面
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param id
	 */
	public static void go2TakeTestPage(Long id) {
		Long student_id = Long.valueOf(session.get("student_id"));
		TakeTestService takeTestService = TakeTestService.getInstance();
		renderArgs.put("takeTestPageVO", takeTestService.go2TakeTestPage(id, student_id));
		renderArgs.put("pageTitle", "参加考试");
		renderTemplate("/studentHome/take/test.html");
	}
	
	/**
	 * 
	  * @Title: submitTest
	  * @Description: 提交答题卡
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @param testRecord_id
	  * @param results
	 */
	public static void submitTest(Long student_id, Long testRecord_id, String results[]) {
		TakeTestService takeTestService = TakeTestService.getInstance();
		takeTestService.submitTest(student_id, testRecord_id, results);
		go2TakeTestListPage(student_id);
	}
}
