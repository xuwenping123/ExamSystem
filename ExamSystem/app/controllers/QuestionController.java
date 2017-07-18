package controllers;

import java.util.List;
import models.Question;
import models.Subject;
import play.mvc.Controller;

/**
 * 试题题库管理
 * @author think
 *
 */
public class QuestionController extends Controller{
	
	public static void showAllQuestions() {
		List<Question> questions = Question.findAll();
		renderArgs.put("questions", questions);
		render("question/questionList.html");
	}
	
	/**
	 * 跳转至添加试题页面
	 */
	public static void go2AddQuestionPage() {
		List<Subject> subjects = Subject.findAll();
		renderArgs.put("subjects", subjects);
		renderArgs.put("pageTitle", "试题添加");
		render("question/question.html");
	}
	
	public static void go2ModQuestionPage(Long id) {
		List<Subject> subjects = Subject.findAll();
		renderArgs.put("subjects", subjects);
		Question question = Question.findById(id);
		renderArgs.put("question", question);
		renderArgs.put("pageTitle", "试题修改");
		render("question/question.html");
	}
	
	/**
	 * 添加试题	http.post content answer type subject_id	
	 */
	public static void addQuestion(String content, String answer, Integer type, Long subject_id) {
		Question question = new Question(content, answer, type, subject_id);
		question.save();
		showAllQuestions();
	}
		
	/**
	 * 删除试题	单选删除	http.get 传入id
	 */
	public static void deteleQuestion(Long id) {
		Question question = Question.findById(id);
		question.delete();
		showAllQuestions();
	}
	
	/**
	 * 修改试题信息	选择单个试题 	传入试题id content	answer type subject_id http.Post
	 */
	public static void modifyQuestion(Long id, String content, String answer, Integer type, Long subject_id) {
		Question question = Question.findById(id);
		question.setContent(content);
		question.setAnswer(answer);
		question.setType(type);
		question.setSubject_id(subject_id);
		question.save();
		showAllQuestions();
	}
	
	/**
	 * 查询	传入试题id http.get
	 */
	public static void findById(long id) {
		Question question = Question.findById(id);
		renderArgs.put("question", question);
		render("OK.html");
	}	
	
	/**
	 * 查询 subject_id 下的所有试题	http.get subject_id
	 * @param subject_id
	 */
	public static void findBySubject_id(long subject_id) {
		List<Question> questions = Question.find("subject_id", subject_id).fetch();
		renderArgs.put("questions", questions);
		render("QuestionController/QuestionController.html");
	}
}
