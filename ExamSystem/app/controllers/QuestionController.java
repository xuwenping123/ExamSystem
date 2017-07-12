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
	
	/**
	 * 测试添加试题
	 * 	
	 */
	public static void testAddQuestion() {
		String content = "2009年9月15日至18日，中国共产党第十七届中央委员会第四次全体会议在北京举行。全会审议通过了《中共中央关于加强和改进新形势下＿＿＿若干重大问题的决定》。A. 经济工作 B. 改革发展 C．党的建设 D. 科学发展";
		String answer = "A";
		int type = 0;
		long subject_id = 1;
		Question question = new Question(content, answer, type, subject_id);
		question.save();
		render("OK.html");
	}
	
	/**
	 * 添加试题	http.post content answer type subject_id	
	 */
	public static void addQuestion() {
		String content = params.get("content");
		String answer = params.get("answer");
		int type = Integer.valueOf(params.get("type"));
		long subject_id = Long.valueOf(params.get("subject_id"));
		Question question = new Question(content, answer, type, subject_id);
		question.save();
		render("OK.html");
	}
		
	/**
	 * 删除试题	单选删除	http.get 传入id
	 */
	public static void deteleQuestion() {
		long id = Long.valueOf(params.get("id"));
		Question question = Question.findById(id);
		question.delete();
		render("OK.html");
	}
	
	/**
	 * 删除试题	http post	传入 id 数组
	 */
	/*
	public static void batchDeleteQuestion(long ids[]) {
		if (ids == null || ids.length == 0) {
			render("errors/404.html");
		}
		Question question;
		for (int i = 0; i < ids.length; i++) {
			question = Question.findById(ids[i]);
			question.delete();
		}
		render("OK.html");
	}*/
	public static void batchDeleteQuestion() {
		//TODO
	}
	
	
	/**
	 * 修改试题信息	选择单个试题 	传入试题id content	answer type subject_id http.Post
	 */
	public static void modifyQuestion() {
		long id = Long.valueOf(params.get("id"));
		String content = params.get("content");
		String answer = params.get("answer");
		int type = Integer.valueOf(params.get("type"));
		long subject_id = Long.valueOf(params.get("subject_id"));
		Question question = Question.findById(id);
		question.setContent(content);
		question.setAnswer(answer);
		question.setType(type);
		question.setSubject_id(subject_id);
		question.save();
		render("OK.html");
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
