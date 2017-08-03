package controllers.teacher;

import play.mvc.Controller;
import service.teacher.mark.MarkingManagerService;

/**
 * 
  * @ClassName: MarkingManagerController
  * @Description: 阅卷管理
  * @author:xuwenping
  * @date: 2017年8月3日
 */
public class MarkingManagerController extends Controller {

	/**
	 * 
	  * @Title: go2MarkListPage
	  * @Description: 进入阅卷列表页面
	  * @author: xuwenping
	  * @date: 2017年8月3日
	 */
	public static void go2MarkListPage() {
		MarkingManagerService markingManagerService = MarkingManagerService.getInstance();
		Long teacher_id = Long.valueOf(session.get("teacher_id"));
		renderArgs.put("markListVOs", markingManagerService.go2MarkListPage(teacher_id));
		renderTemplate("/teacherHome/mark/markList.html");
	}
	
	/**
	 * 
	  * @Title: go2MarkingPage
	  * @Description: 进入阅卷页面
	  * @author: xuwenping
	  * @date: 2017年8月3日
	  * @param testRecord_id
	  * @param student_id
	 */
	public static void go2MarkingPage(Long testRecord_id, Long student_id) {
		MarkingManagerService markingManagerService = MarkingManagerService.getInstance();
		renderArgs.put("viewTestPageVOs", markingManagerService.go2MarkingPage(testRecord_id, student_id));
		session.put("testRecord_id", testRecord_id);
		session.put("student_id", student_id);
		renderTemplate("/teacherHome/mark/mark.html");
	}
	
	/**
	 * 
	  * @Title: submitMarkingPage
	  * @Description: 提交阅卷结果
	  * @author: xuwenping
	  * @date: 2017年8月3日
	  * @param scores
	 */
	public static void submitMarkingPage(String scores[]) {
		Long student_id = Long.valueOf(session.get("student_id"));
		Long testRecord_id = Long.valueOf(session.get("testRecord_id"));
		MarkingManagerService markingManagerService = MarkingManagerService.getInstance();
		markingManagerService.submitMarkingPage(testRecord_id, student_id, scores);
		go2MarkListPage();
	}
}
