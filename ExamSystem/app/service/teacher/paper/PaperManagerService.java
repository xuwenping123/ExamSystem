package service.teacher.paper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import models.Paper;
import models.Question;
import models.Subject;
import models.relationship.QuestionPaperRS;
import play.Logger;
import service.teacher.question.QuestionManagerService;
import service.teacher.subject.SubjectManagerService;
import util.ArrayUtil;
import util.ConstantUtil;
import util.EnumUtil.currentPageStatus;
import util.PageUtil;
import vo.PageVO;
import vo.teacher.paper.PaperListPageVO;
import vo.teacher.paper.PaperPageVO;
import vo.teacher.paper.PaperViewVO;

/**
 * 
  * @ClassName: PaperManagerService
  * @Description: paperManagerController
  * @author:xuwenping
  * @date: 2017年7月31日
 */
public class PaperManagerService {

	private static PaperManagerService instance = new PaperManagerService();
	
	public static PaperManagerService getInstance() {
		return instance;
	}
	
	/** 标记位，用于判断currentPage值是否需要改变  */
	private currentPageStatus flag = currentPageStatus.unchanged;
	
	/**
	 * 
	  * @Title: getPageVO
	  * @Description: 内部自动将currentPage的值进行转化
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param currentPage
	  * @return
	 */
	public PageVO getPageVO(Integer currentPage) {
		Logger.info("after change : currentPage is %s", getCurrentPage(currentPage));
		return new PageVO(getTotalPage(), getCurrentPage(currentPage));
	}
	
	/**
	 * 
	  * @Title: getPaperCount
	  * @Description: 获取所有试卷数量
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @return
	 */
	public int getPaperCount() {
		return(int) Paper.count();
	}
	
	/**
	 * 
	  * @Title: getTotalPage
	  * @Description: 分页时获取总页数
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @return
	 */
	public int getTotalPage() {
		return PageUtil.getInstance().getTotalPage(getPaperCount());
	}
	
	/**
	 * 
	  * @Title: findAll
	  * @Description: 获取所有试卷
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @return
	 */
	public List<Paper> findAll() {
		return Paper.findAll();
	}
	
	/**
	 * 
	  * @Title: findPaper
	  * @Description: 获取id的试卷
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param id
	  * @return
	 */
	public Paper findPaper(Long id) {
		return Paper.findById(id);
	}
	
	/**
	 * 
	  * @Title: addPaper
	  * @Description: 添加试卷
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param remark
	  * @param subject_id
	  * @return
	 */
	public Paper addPaper(String remark, Long subject_id) {
		Paper paper = new Paper(remark, subject_id);
		paper.save();
		return paper;
	}
	
	/**
	 * 
	  * @Title: addQuestionPaperRS
	  * @Description: 添加试卷与试题关系
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @param question_id
	  * @param question_score
	  * @return
	 */
	public QuestionPaperRS addQuestionPaperRS(Long paper_id, Long question_id, Integer question_score) {
		QuestionPaperRS questionPaperRS = new QuestionPaperRS(paper_id, question_id, question_score);
		questionPaperRS.save();
		return questionPaperRS;
	}
	
	public PaperPageVO go2AddPaperPage() {
		List<Subject> subjects = SubjectManagerService.getInstance().findAll();
		List<Question> questions = QuestionManagerService.getInstance().findAll();
		return new PaperPageVO(-1, null, questions, subjects);
	}
	
	/**
	 * 
	  * @Title: addPaper
	  * @Description: 添加试卷 内部自动整理纸卷与试题的关系：添加
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param remark
	  * @param subject_id
	  * @param selectQuestions
	  * @param question_scores
	  * @return
	 */
	public Paper addPaper(String remark, Long subject_id, Long selectQuestions[],
			Integer question_scores[]) {
		List<Integer> list = ArrayUtil.getInstance().trimNull(question_scores);
		if (selectQuestions.length != list.size()) {
			throw new RuntimeException();
		}
		Paper paper = addPaper(remark, subject_id);
		for (int i = 0; i < selectQuestions.length; i++) {
			addQuestionPaperRS(paper.getId(), selectQuestions[i], list.get(i));
		}
		return paper;
	}
	
	/**
	 * 
	  * @Title: go2ViewPaperPage
	  *	@Description: 获取paperViewVO
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @return
	 */
	public PaperViewVO go2ViewPaperPage(Long paper_id) {
		Paper paper = findPaper(paper_id);
		Subject subject = SubjectManagerService.getInstance().findSubject(paper.subject_id);
		List<Question> questions = findQuestionsByPaperId(paper_id);
		Question question = null;
		for (int i = 0; i < questions.size(); i++) {
			question = questions.get(i);
			question.setQuestion_score(getQuestionScoreById(paper_id, question.getId()));
		}
		return new PaperViewVO(paper_id, paper.remark, questions, subject);
	}
	
	/**
	 * 
	  * @Title: go2EditPaperPage
	  * @Description: 跳转至编辑修改试卷页面
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @param remark
	  * @return
	 */
	public PaperPageVO go2EditPaperPage(Long paper_id) {
		Paper paper = findPaper(paper_id);
		List<Subject> subjects = SubjectManagerService.getInstance().findAll();
		List<Question> questions = QuestionManagerService.getInstance().findAll();
		return new PaperPageVO(paper_id, paper.remark, questions, subjects);
	}
	
	/**
	 * 
	  * @Title: deletePaper
	  * @Description: 删除试卷， 内部自动维护试卷与试题关系
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @return
	 */
	public Paper deletePaper(Long paper_id) {
		Paper paper = findPaper(paper_id);
		paper.delete();
		//test for delete question with paper_id = paper_id
		int deleteCount = deleteQuestionsByPaperId(paper_id);
		Logger.info("delete questionPaper count : %s", deleteCount);
		return paper;
	}
	
	/**
	 * 
	  * @Title: editPaper
	  * @Description: 重写editPaper 内部自动维护资源与试题关系 先删除 再添加
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param id
	  * @param remark
	  * @param subject_id
	  * @param selectQuestions
	  * @param question_scores
	  * @return
	 */
	public Paper editPaper(Long id, String remark, Long subject_id, 
			Long selectQuestions[], Integer question_scores[]){
		List<Integer> list = ArrayUtil.getInstance().trimNull(question_scores);
		if (selectQuestions.length != list.size()) {
			throw new RuntimeException();
		}
		Paper paper = findPaper(id);
		deleteQuestionsByPaperId(id);
		for (int i = 0; i < selectQuestions.length; i++) {
			addQuestionPaperRS(paper.getId(), selectQuestions[i], list.get(i));
		}
		return paper;
	}
	
	/**
	 * 
	  * @Title: getTotalScoreByPaperId
	  * @Description: 获取一张试卷的总分 
	  * @author: xuwenping
	  * @date: 2017年7月31日
	  * @param id
	  * @return 查询结果可能出现 null list(null) 现象
	 */
	public int getTotalScoreByPaperId(Long id) {
		String query = "select sum(question_score) from QuestionPaperRS where paper_id = ?";
		List<Long> list = QuestionPaperRS.find(query, id).fetch();
		if (list == null || list.size() == 0) {
			return 0;
		}
		if (list.get(0) == null) {
			return 0;
		}
		long totalScore = list.get(0);
		return (int) totalScore;
	}

	/**
	 * 
	  * @Title: getQuestionScoreById
	  * @Description: 查看一张试卷中的试题的满分
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @param question_id
	  * @return
	 */
	public int getQuestionScoreById(Long paper_id, Long question_id) {
		String query = "paper_id = ? and question_id = ?";
		QuestionPaperRS questionPaperRS = QuestionPaperRS.find(query, paper_id, question_id).first();
		return questionPaperRS.question_score;
	}
	
	/**
	 * 
	  * @Title: getQuestionsByPaperId
	  * @Description: 获取一张试卷的所有试题
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @return
	 */
	public List<Question> findQuestionsByPaperId(Long paper_id) {
		String query = "paper_id = ?";
		List<QuestionPaperRS> questionPaperRSs = QuestionPaperRS.find(query, paper_id).fetch();
		List<Question> questions = new ArrayList<>();
		QuestionManagerService questionManagerService = QuestionManagerService.getInstance();
		Question question = null;
		for (int i = 0; i < questionPaperRSs.size(); i++) {
			question = questionManagerService.findQuestion(questionPaperRSs.get(i).getQuestion_id());
			question.setQuestion_score(getQuestionScoreById(paper_id, question.getId()));
			questions.add(question);
		}
		return questions;
	}
	
	/**
	 * 
	  * @Title: deleteQuestionsByPaperId
	  * @Description: 删除与试卷绑定的试题记录, 注意 此处并不是删除question
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param paper_id
	  * @return
	 */
	public int deleteQuestionsByPaperId(Long paper_id) {
		String query = "from QuestionPaperRS where paper_id = ?";
		return QuestionPaperRS.delete(query, paper_id);
	}
	
	public List<PaperListPageVO> go2PaperListPage() {
		return selectPage(1);
	}
	
	public List<PaperListPageVO> selectPage(Integer selectPage) {
		List<Paper> papers = Paper.all().fetch(selectPage, ConstantUtil.perPage);
		Paper paper = null;
		List<PaperListPageVO> paperListPageVOs = new LinkedList<>();
		for (int i = 0; i < papers.size(); i++) {
			paper = papers.get(i);
			paperListPageVOs.add(new PaperListPageVO(paper.getId(), paper.remark, 
					SubjectManagerService.getInstance().findSubject(paper.subject_id).title, 
					getTotalScoreByPaperId(paper.getId())));
		}
		return paperListPageVOs;
	}
	
	public List<PaperListPageVO> previousPage(Integer currentPage) {
		if (currentPage.equals(1)) {
			return selectPage(currentPage);
		}
		flag = currentPageStatus.negative;
		return selectPage(--currentPage);
	}
	
	public List<PaperListPageVO> nextPage(Integer currentPage) {
		if (currentPage.equals(getTotalPage())) {
			return selectPage(currentPage);
		}
		flag = currentPageStatus.positive;
		return selectPage(++currentPage);
	}
	
	/**
	 * 
	  * @Title: getCurrentPage
	  * @Description: 标记位，用于判断CurrentPage的值
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param currentPage
	  * @return
	 */
	public int getCurrentPage(Integer currentPage) {
		if (flag == currentPageStatus.negative) {
			flag = currentPageStatus.unchanged;
			return --currentPage;
		} else if (flag == currentPageStatus.positive) {
			flag = currentPageStatus.unchanged;
			return ++currentPage;
		} else {
			return currentPage;
		}
	}
}