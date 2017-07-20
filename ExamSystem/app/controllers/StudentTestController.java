package controllers;

import java.util.List;

import models.Question;
import models.vo.AnswerViewResultVo;
import models.vo.StudentViewTestListVO;
import play.mvc.Controller;
import service.StudentTestService;

public class StudentTestController extends Controller {

	/**
	 * 进入可以考试的考试列表页面
	 * @param student_id
	 */
	public static void go2TestListPage(Long student_id) {
		List<StudentViewTestListVO> studentViewTestListVOs = StudentTestService.getInstance().get2DoTest(student_id);
		renderArgs.put("studentViewTestListVOs", studentViewTestListVOs);
		render("student/go2TestList.html");
	}
	
	/**
	 * 参加考试，进入答题卡页面
	 * @param testRecord_id
	 */
	public static void joinTestById(Long id) {
		List<Question> questions = StudentTestService.getInstance().getQuestionsByTestRecordId(id);
		session.put("testRecord_id", id);
		renderArgs.put("questions", questions);
		render("student/go2Test.html");
	}
	
	/**
	 * 提交答题卡页面
	 */
	public static void submitAnswerSheet(String results[]) {
		Long student_id = Long.valueOf(session.get("student_id"));
		Long testRecord_id = Long.valueOf(session.get("testRecord_id"));
		StudentTestService.getInstance().submitAnswerSheet(student_id, testRecord_id, results);
		go2TestListPage(student_id);
	}
	
	/**
	 * 查看已参加的考试列表页面
	 * @param student_id
	 */
	public static void goDoneTestPage(Long student_id) {
		List<StudentViewTestListVO> studentViewTestListVOs = StudentTestService.getInstance().getDoneTest(student_id);
		renderArgs.put("studentViewTestListVOs", studentViewTestListVOs);
		render("student/doneTestList.html");
	}
	
	/**
	 * 查看已参加考试的考试结果
	 * @param testRecord_id
	 */
	public static void showTestResult(Long id) {
		Long student_id = Long.valueOf(session.get("student_id"));
		List<AnswerViewResultVo> answerViewResultVos = StudentTestService.getInstance().showTestResult(id, student_id);
		renderArgs.put("answerViewResultVos", answerViewResultVos);
		render("student/doneTest.html");
	}
}
