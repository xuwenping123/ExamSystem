package controllers;

import java.util.ArrayList;
import java.util.List;

import controllers.util.ArrayUtil;
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
import service.PaperService;
import service.QuestionPaperRsService;
import sun.reflect.generics.tree.VoidDescriptor;

/**
 * 试卷类
 * @author think
 *
 */
public class PaperController extends Controller{

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
	 * 跳转至添加试卷页面
	 */
	public static void go2AddPaperPage() {
		List<Question> questions = Question.findAll();
		renderArgs.put("questions", questions);
		List<Subject> subjects = Subject.findAll();
		renderArgs.put("subjects", subjects);
		renderArgs.put("pageTitle", "试卷添加");
		render("paper/paper.html");
	}
	
	/**
	 * 跳转至修改试卷页面
	 * 暂时未添加所有属于同一科目的试题
	 */
	public static void go2ModPaperPage(Long id) {
		Paper paper = Paper.findById(id);
		renderArgs.put("paper", paper);
		List<Question> questions = QuestionPaperRsService.getInstance().getQuestionAndScoreByPaperId(id);
		//List<Question> questions = QuestionPaperRsService.getInstance().getQuestionsByPaperId(id);
		renderArgs.put("questions", questions);
		List<Subject> subjects = Subject.findAll();
		renderArgs.put("subjects", subjects);
		renderArgs.put("pageTitle", "试卷修改");
		render("paper/paper.html");
	}
	
	/**
	 * 添加paper http.post all 同时绑定试卷与试题
	 * 先将试卷插入数据库，再获取paper_id, 分批将paper_id, question_id, question_score插入QuestionPaperRs对应得表中
	 */
	public static void addPaper(String remark, Long subject_id, Long selectQuestions[], Integer question_score[]) {
		List<Integer> list = ArrayUtil.getInstance().trimNull(question_score);
		if (list.size() != selectQuestions.length) {
			throw new RuntimeException("请将所有试题添加上分数");
		}
		Paper paper = new Paper(remark, subject_id);
		paper.save();
		QuestionPaperRS questionPaperRS = null;
		for (int i = 0; i < selectQuestions.length; i++) {
			questionPaperRS = new QuestionPaperRS(paper.getId(), selectQuestions[i], list.get(i));
			questionPaperRS.save();
		}
		showAllpapers();
	}
	
	/**
	 * 删除paper http.get id
	 */
	public static void deletePaper(long id) {
		Paper paper = Paper.findById(id);
		paper.delete();
		showAllpapers();
	}
	
	/**
	 * 修改试卷信息	http.post all
	 * 修改处不会出现null的 分数值，所以无需去重
	 */
	public static void modifyPaper(Long id, String remark, Long subject_id, Long selectQuestions[], Integer question_score[]) {
		List<Integer> list = ArrayUtil.getInstance().trimNull(question_score);
		if (selectQuestions.length != list.size()) {
			throw new RuntimeException();
		}
		PaperService.getInstance().modPaper(id, remark, subject_id);
		//此前已存在的关系数据
		List<QuestionPaperRS> oldQuestionPaperRSs = QuestionPaperRS.find("paper_id = ?", id).fetch();
		List<QuestionPaperRS> newQuestionPaperRSs = null;
		QuestionPaperRS questionPaperRS = null;
		for (int i = 0; i < selectQuestions.length; i++) {
			newQuestionPaperRSs = QuestionPaperRS.find("paper_id = ? and question_id = ?", id, selectQuestions[i]).fetch();
			questionPaperRS = newQuestionPaperRSs.get(0);
			questionPaperRS.setQuestion_score(list.get(i));
			questionPaperRS.save();
			//从旧关系数据list中剔除已修改的关系
			for (int j = 0; j < oldQuestionPaperRSs.size(); j++) {
				if (questionPaperRS.getId() == oldQuestionPaperRSs.get(j).getId()) {
					oldQuestionPaperRSs.remove(j);
				}
			}
		}
		//将剩余的关系数据全部删除
		for( int i = 0; i < oldQuestionPaperRSs.size(); i++) {
			questionPaperRS = oldQuestionPaperRSs.get(i);
			questionPaperRS.delete();
		}
		showAllpapers();
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
	public static void showAllpapers() {
		List<Paper> papers = Paper.findAll();
		Paper paper = null;
		for(int i = 0; i < papers.size(); i++) {
			paper = papers.get(i);
			int totalScore = QuestionPaperRsService.getInstance().getTotalScoreByPaperId(paper.getId());
			paper.setTotalScore(totalScore);
		}
		renderArgs.put("papers", papers);
		render("paper/paperList.html");
	}
}
