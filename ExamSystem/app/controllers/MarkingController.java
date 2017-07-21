package controllers;

import java.util.List;

import models.TestDetail;
import models.vo.AnswerViewResultVo;
import models.vo.MarkingServiceListVO;
import play.mvc.Controller;
import service.MarkingService;

/**
 * 教师阅卷	-> MarkingService
 * @author think
 *
 */
public class MarkingController extends Controller{

	/**
	 * 跳转至考试阅卷列表页面
	 * @param teacher_id
	 */
	public static void go2MarkingListPage() {
		Long teacher_id = Long.valueOf(session.get("teacher_id"));
		List<MarkingServiceListVO> markingServiceListVOs = MarkingService.getInstance().go2MarkingListPage(teacher_id);
		renderArgs.put("markingServiceListVOs", markingServiceListVOs);
		render("marking/markList.html");
	}
	
	/**
	 * 进入考试阅卷页面进行试卷批改
	 * @param id testRecord_id
	 * @param student_id
	 */
	public static void go2MarkingPage(Long id, Long student_id) {
		List<AnswerViewResultVo> answerViewResultVos = MarkingService.getInstance().go2MarkingPage(id, student_id);
		session.put("testRecord_id", id);
		session.put("student_id", student_id);
		renderArgs.put("answerViewResultVos", answerViewResultVos);
		render("marking/markPage.html");
	}
	
	/**
	 * 考试阅卷结束进行提交试卷
	 */
	public static void submitMarkingResult(String scores[]) {
		Long student_id = Long.valueOf(session.get("student_id"));
		Long testRecord_id = Long.valueOf(session.get("testRecord_id"));
		MarkingService.getInstance().submitMarkingResult(testRecord_id, student_id, scores);
		go2MarkingListPage();
	}
}
