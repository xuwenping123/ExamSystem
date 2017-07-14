package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Paper;
import models.Question;
import models.Subject;
import models.TestDetail;
import models.TestRecord;
import models.relationship.QuestionPaperRS;
import models.user.Student;
import models.user.Teacher;
import models.vo.PaperInfo;
import models.vo.QuestionAddScore;
import models.vo.TestInfo;
import play.Logger;
import play.mvc.Controller;

/**
 * 试卷类
 * @author think
 *
 */
public class PaperController extends Controller{

	/**
	 * private Paper paper;
	
	private Teacher teacher;
	
	private Student student;
	
	private Subject subject;
	
	private TestRecord testRecord;
	
	private TestDetail testDetail;
	 */
	
	/**
	 * 参加考试
	 * @param testInfo
	 */
	public static void joinPaper(long paper_id, long teacher_id, long student_id, long subject_id, long testRecord_id, long testDetail_id) {
		Paper paper = Paper.findById(paper_id);
		Student student = Student.findById(student_id);
		TestRecord testRecord = TestRecord.findById(testRecord_id);
		List<QuestionPaperRS> questionPaperRSs = QuestionPaperRS.find("paper_id = ?", paper_id).fetch();
		List<QuestionAddScore> questionAddScores = new ArrayList<>();
		Question question;
		QuestionAddScore questionAddScore;
		for (int i = 0; i < questionPaperRSs.size(); i++) {
			question = Question.findById(questionPaperRSs.get(i).question_id);
			questionAddScore = new QuestionAddScore(question.id, question.content, question.answer, question.type, question.subject_id, questionPaperRSs.get(i).question_score);
			questionAddScores.add(questionAddScore);
		}
		PaperInfo paperInfo = new PaperInfo( paper, student);
		render("paper/paper.html", paperInfo, questionAddScores, testRecord, questionPaperRSs);
	}
	
	/**
	 * 添加paper http.post all
	 */
	public static void addPaper() {
		String remark = params.get("remark");
		long subject_id = Long.valueOf(params.get("subject_id"));
		Paper paper = new Paper(remark, subject_id);
		paper.save();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 删除paper http.get id
	 */
	public static void deletePaper(long id) {
		Paper paper = Paper.findById(id);
		paper.delete();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 修改试卷信息	http.post all
	 */
	public static void modifyPaper() {
		long id = Long.valueOf(params.get("id"));
		String remark = params.get("remark");
		long subject_id = Long.valueOf(params.get("subject_id"));
		Paper paper = Paper.findById(id);
		paper.setRemark(remark);
		paper.setSubject_id(subject_id);
		paper.save();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 查询paper http.get id
	 * @param id
	 */
	public static void findPaperById(long id) {
		Paper paper = Paper.findById(id);
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 获取全部	
	 */
	public static void findAll() {
		List<Paper> papers = Paper.findAll();
		renderArgs.put("papers", papers);
		render("paper/paper.html");
	}
}
