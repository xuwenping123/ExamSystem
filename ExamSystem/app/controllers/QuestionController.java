package controllers;

import java.util.List;
import models.Question;
import net.sf.ehcache.hibernate.HibernateUtil;
import play.db.jpa.JPABase;
import play.mvc.Controller;

/**
 * 试题题库管理
 * @author think
 *
 */
public class QuestionController extends Controller{

	/**
	 * 用于展示所有试题	
	 */
	public static void showQuestions() {
		render();
	}
		
	/**
	 * 添加试题	列表选择填写信息提交  传入试题content answer type subject_id	http.Post
	 */
	public static void addQuestion() {
		String content = params.get("content");
		String answer = params.get("answer");
		int type = Integer.valueOf(params.get("type"));
		long subject_id = Long.valueOf(params.get("subject_id"));
		Question question = new Question(content, answer, type, subject_id);
		question.save();
		//TODO
	}
		
	/**
	 * 删除试题	单选删除	传入id
	 */
	public static void deteleQuestion() {
		long id = Long.valueOf(params.get("id"));
		Question question = Question.findById(id);
		question.delete();
	}
	
	/**
	 * 删除试题	批量删除	传入
	 */
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
		Question question = new Question(id, content, answer, type, subject_id);
		question.save();
		//TODO
	}
	
	/**
	 * 查询	传入试题id http.get
	 */
	public static void findById(long id) {
		Question question = Question.findById(id);
		//TODO
	}	
	
	/**
	 * 全部查询 
	 */
	public static void findAll() {
		List<Question> questions = Question.findAll();
		//TODO
	}
}
