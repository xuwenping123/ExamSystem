package service.student.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.AnswerRecord;
import models.Paper;
import models.Question;
import models.TestDetail;
import models.TestRecord;
import models.relationship.QuestionPaperRS;
import models.user.Student;
import models.user.Teacher;
import models.vo.AnswerViewResultVo;
import service.QuestionPaperRsService;
import service.TestRecordService;
import service.student.StudentService;
import service.student.take.TakeTestService;
import service.teacher.TeacherService;
import service.teacher.paper.PaperManagerService;
import service.teacher.test.TestManagerService;
import util.EnumUtil.istaked;
import vo.student.take.TakeTestListPageVO;
import vo.student.view.ViewTestListPageVO;
import vo.student.view.ViewTestPageVO;

/**
 * 
  * @ClassName: ViewTestCService
  * @Description: 查看考试成绩
  * @author:xuwenping
  * @date: 2017年8月2日
 */
public class ViewTestService {

	private static ViewTestService instance = new ViewTestService();
	
	public static ViewTestService getInstance() {
		return instance;
	}
	
	/**
	 * 
	  * @Title: go2ViewTestListPage
	  * @Description: go2ViewTestListPage
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @return
	 */
	public List<ViewTestListPageVO> go2ViewTestListPage(Long student_id) {
		return getViewTestPageVOs(student_id);
	}
	
	
	public List<ViewTestPageVO> go2ViewTestPage(Long testRecord_id, Long student_id) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		TestManagerService testManagerService = TestManagerService.getInstance();
		StudentService studentService = StudentService.getInstance();
		Paper paper = paperManagerService.findPaper(testManagerService.findTestRecord(testRecord_id).getPaper_id());
		Student student = studentService.findStudent(student_id);
		List<Question> questions = paperManagerService.findQuestionsByPaperId(paper.getId());
		List<ViewTestPageVO> viewTestPageVOs = new ArrayList<>();
		ViewTestPageVO viewTestPageVO = null;
		List<AnswerRecord> answerRecords = null;
		String result = null;
		int score = 0;
		for (int i = 0; i < questions.size(); i++) {
			answerRecords = AnswerRecord.find("student_id = ? and paper_id = ? and testRecord_id = ? and question_id = ?", 
					student_id, paper.getId(), testRecord_id, questions.get(i).getId()).fetch();
			result = answerRecords.get(0).getResult();
			score = answerRecords.get(0).getScore();
			viewTestPageVO = new ViewTestPageVO(student, paper, questions.get(i), result, score);
			viewTestPageVOs.add(viewTestPageVO);
		}
		return viewTestPageVOs;
	}
	
	/**
	 * 
	  * @Title: getViewTestPageVOs
	  * @Description: 获取考试可以查看的列表
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @return
	 */
	public List<ViewTestListPageVO> getViewTestPageVOs(Long student_id) {
		List<TestDetail> testDetails = findDoneTestDetailByStudentId(student_id);
		TestRecord testRecord = null;
		List<ViewTestListPageVO> viewTestPageVOs = new ArrayList<>();
		ViewTestListPageVO viewTestPageVO = null;
		Paper paper = null;
		Teacher teacher = null;
		int totalScore = 0;
		TestManagerService testManagerService = TestManagerService.getInstance();
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		TeacherService teacherService = TeacherService.getInstance();
		TestDetail testDetail = null;
		for (int i = 0; i < testDetails.size(); i++) {
			testDetail = testDetails.get(i);
			testRecord = testManagerService.findTestRecord(testDetail.testRecord_id);
			paper = paperManagerService.findPaper(testRecord.getPaper_id());
			teacher = teacherService.findTeacher(testDetail.getTeacher_id());
			totalScore = paperManagerService.getTotalScoreByPaperId(paper.getId());
			viewTestPageVO = new ViewTestListPageVO(student_id, testRecord.getId(), 
					testRecord.getBeginTime(), testRecord.getEndTime(), testRecord.getRemark(), 
					paper, totalScore, teacher, testDetail.getIstaked(), 
					testDetail.getIsMarked(), getAnswerTotalScore(student_id, testRecord.getId()));
			viewTestPageVOs.add(viewTestPageVO);
		}
		return viewTestPageVOs;
	}
	
	/**
	 * 
	  * @Title: findDoneTestDetailByStudentId
	  * @Description: 获取当前已经参加过的考试或者没有参加过的考试但是已经过了结束时间的考试
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @return 1. istake == 1
	  * 		2. endTime <= nowTime
	 */
	public List<TestDetail> findDoneTestDetailByStudentId(Long student_id) {
		List<TestDetail> testDetails = TakeTestService.getInstance().findTestDetailByStudentId(student_id);
		TestRecord testRecord = null;
		TestDetail testDetail = null;
		TestManagerService testManagerService = TestManagerService.getInstance();
		for (Iterator<TestDetail> iterator = testDetails.iterator(); iterator.hasNext(); ) {
			testDetail = iterator.next();
			if (testDetail.istaked == istaked.notaked.ordinal()) {
				testRecord = testManagerService.findTestRecord(testDetail.testRecord_id);
				if (isBefore(testRecord.getBeginTime(), new Date()) == true) {
					iterator.remove();
				}
			}
		}
		return testDetails;
	}
	
	/**
	 * 
	  * @Title: isBefore
	  * @Description: 开始时间比当前时间晚
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param beginTime
	  * @param nowTime
	  * @return
	 */
	private boolean isBefore(Date beginTime, Date nowTime) {
		if (beginTime.after(nowTime)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	  * @Title: getAnswerTotalScore
	  * @Description: 获取考生该试卷所得总分分
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @return
	 */
	public int getAnswerTotalScore(Long student_id, Long testRecord_id) {
		String query = "select sum(score) from AnswerRecord where student_id = ? and testRecord_id = ?";
		List<Long> list = QuestionPaperRS.find(query, student_id, testRecord_id).fetch();
		if (list == null || list.size() == 0) {
			return 0;
		}
		if (list.get(0) == null) {
			return 0;
		}
		long totalScore = list.get(0);
		return (int) totalScore;
	}
}
