package controllers.teacher;

import play.mvc.Controller;
import service.teacher.question.QuestionManagerService;
import service.teacher.subject.SubjectManagerService;
import vo.teacher.question.QuestionListPageVO;

/**
 * 
  * @ClassName: QuestionManagerController
  * @Description: 试题管理
  * @author:xuwenping
  * @date: 2017年7月27日
 */
public class QuestionManagerController extends Controller {
	
	/**
	  * @Title: go2QuestionListPage
	  * @Description: 跳转至试题列表页面
	  * @author: xuwenping
	  * @date: 2017年7月31日
	 */
	public static void go2QuestionListPage() {
		QuestionListPageVO questionListPageVO = QuestionManagerService.getInstance().go2QuestionListPage();
		renderArgs.put("subjects", SubjectManagerService.getInstance().findAll());
		renderArgs.put("questionListPageVO", questionListPageVO);
		renderTemplate("teacherHome/question/questionList.html");
	}
	
	/**
	 * 
	  * @Title: go2AddQuestionPage
	  * @Description: 跳转至添加试题页面 需要和页面交互 试题种类数据
	  * @author: xuwenping
	  * @date: 2017年7月31日
	 */
	public static void go2AddQuestionPage() {
		renderArgs.put("pageTitle", "试题添加");
		renderArgs.put("subjects", SubjectManagerService.getInstance().findAll());
		renderTemplate("teacherHome/question/question.html");
	}
	
	/**
	 * 
	  * @Title: addQuestion
	  * @Description: 添加试题
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param content
	  * @param answer
	  * @param type
	  * @param subject_id
	 */
	public static void addQuestion(String content, String answer, Integer type, Long subject_id) {
		QuestionManagerService.getInstance().addQuestion(content, answer, type, subject_id);
		go2QuestionListPage();
	}
	
	/**
	 * 
	  * @Title: go2EditQuestionPage
	  * @Description: 跳转至试题修改页面
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param id
	 */
	public static void go2EditQuestionPage(Long id) {
		renderArgs.put("question", QuestionManagerService.getInstance().findQuestion(id));
		renderArgs.put("subjects", SubjectManagerService.getInstance().findAll());
		renderArgs.put("pageTitle", "试题修改");
		renderTemplate("teacherHome/question/question.html");
	}
	
	/**
	 * 
	  * @Title: editQuestion
	  * @Description: 修改试题
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param id
	  * @param content
	  * @param answer
	  * @param type
	  * @param subject_id
	 */
	public static void editQuestion(Long id, String content, String answer, Integer type, Long subject_id) {
		QuestionManagerService.getInstance().editQuestion(id, content, answer, type, subject_id);
		go2QuestionListPage();
	}
	
	/**
	 * 
	  * @Title: deleteQuestion
	  * @Description: 
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param id
	 */
	public static void deleteQuestion(Long id) {
		QuestionManagerService.getInstance().deleteQuestion(id);
		go2QuestionListPage();
	}
	
	/**
	 * 
	  * @Title: previousPage
	  * @Description: 分页，上一页
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param currentPage
	 */
	public static void previousPage(Integer currentPage) {
		QuestionListPageVO questionListPageVO = QuestionManagerService.getInstance().previousPage(currentPage);
		renderArgs.put("questionListPageVO", questionListPageVO);
		renderArgs.put("subjects", SubjectManagerService.getInstance().findAll());
		renderTemplate("teacherHome/question/questionList.html");
	}
	
	/**
	 * 
	  * @Title: nextPage
	  * @Description: 分页，下一页
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param currentPage
	 */
	public static void nextPage(Integer currentPage) {
		QuestionListPageVO questionListPageVO = QuestionManagerService.getInstance().nextPage(currentPage);
		renderArgs.put("questionListPageVO", questionListPageVO);
		renderArgs.put("subjects", SubjectManagerService.getInstance().findAll());
		renderTemplate("teacherHome/question/questionList.html");
	}
	
	/**
	 * 
	  * @Title: selectPage
	  * @Description: 分页，选择页面
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param selectPage
	 */
	public static void selectPage(Integer selectPage) {
		QuestionListPageVO questionListPageVO = QuestionManagerService.getInstance().selectPage(selectPage);
		renderArgs.put("subjects", SubjectManagerService.getInstance().findAll());
		renderArgs.put("questionListPageVO", questionListPageVO);
		renderTemplate("teacherHome/question/questionList.html");
	}
}
