package service.student.take;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import models.AnswerRecord;
import models.Paper;
import models.Question;
import models.Subject;
import models.TestDetail;
import models.TestRecord;
import models.user.Teacher;
import service.StudentTestService;
import service.TestDetailService;
import service.TestRecordService;
import service.teacher.TeacherService;
import service.teacher.paper.PaperManagerService;
import service.teacher.subject.SubjectManagerService;
import service.teacher.test.TestManagerService;
import util.EnumUtil.istaked;
import vo.student.take.TakeTestListPageVO;
import vo.student.take.TakeTestPageVO;

/**
 * 
  * @ClassName: TakeTestService
  * @Description: takeTestService 
  * @author:xuwenping
  * @date: 2017年8月2日
 */
public class TakeTestService {

	private static TakeTestService instance = new TakeTestService();
	
	public static TakeTestService getInstance() {
		return instance;
	}
	
	/**
	 * 
	  * @Title: go2TestListPage
	  * @Description: 进入考试列表页面  
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @return 1.未参加的  2.时间大于当前时间的考试
	 */
	public List<TakeTestListPageVO> go2TakeTestListPage(Long student_id) {
		return getTakeTestListPageVOs(student_id);
	}
	
	public TakeTestPageVO go2TakeTestPage(Long testRecord_id, Long student_id) {
		TestRecord testRecord = TestManagerService.getInstance().findTestRecord(testRecord_id);
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		List<Question> questions = paperManagerService.findQuestionsByPaperId(testRecord.getPaper_id());
		Question question = null;
		for (int i = 0; i < questions.size(); i++) {
			question = questions.get(i);
			question.setQuestion_score(paperManagerService.getQuestionScoreById(testRecord.getPaper_id(), question.getId()));
		}
		return new TakeTestPageVO(student_id, testRecord_id, questions);
	}
	
	/**
	 * 
	  * @Title: submitTest
	  * @Description: 提交答题卡
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @param testRecord_id
	  * @param results
	 */
	public void submitTest(Long student_id, Long testRecord_id, String results[]) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		TestRecord testRecord = TestManagerService.getInstance().findTestRecord(testRecord_id);
		List<Question> questions = paperManagerService.findQuestionsByPaperId(testRecord.getPaper_id());
		if (questions.size() != results.length) {
			throw new RuntimeException("提交答题卡答题记录不完全，题目与答案匹配失败！");
		}
		Paper paper = paperManagerService.findPaper(testRecord.getPaper_id());
		for (int i = 0; i < results.length; i++) {
			addAnswerRecord(student_id, paper.getId(), 
					testRecord_id, questions.get(i).getId(), results[i]);
		}
		//设置考试状态值 istaked
		setIstaked(findTestDetailByStudentIdTestRecordId(student_id, testRecord_id).getId());
	}
	
	/**
	 * 
	  * @Title: getStudentViewTestListVOs
	  * @Description: 获取学生即将需要参加的考试并封装至TakeTestListPageVO对象中
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param id
	  * @return 1.未参加的  2.时间大于当前时间的考试
	 */
	public List<TakeTestListPageVO> getTakeTestListPageVOs(long id) {
		List<TestDetail> testDetails = findToDoTestDetailByStudentId(id);
		TestRecord testRecord = null;
		List<TakeTestListPageVO> takeTestListPageVOs = new ArrayList<>();
		TakeTestListPageVO takeTestListPageVO = null;
		Paper paper = null;
		Teacher teacher = null;
		int totalScore = 0;
		TestManagerService testManagerService = TestManagerService.getInstance();
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		TeacherService teacherService = TeacherService.getInstance();
		for (int i = 0; i < testDetails.size(); i++) {
			testRecord = testManagerService.findTestRecord(testDetails.get(i).testRecord_id);
			paper = paperManagerService.findPaper(testRecord.getPaper_id());
			teacher = teacherService.findTeacher(testDetails.get(i).getTeacher_id());
			totalScore = paperManagerService.getTotalScoreByPaperId(paper.getId());
			takeTestListPageVO = new TakeTestListPageVO(id, testRecord.getId(), testRecord.getBeginTime(), 
					testRecord.getEndTime(), testRecord.getRemark(), paper, totalScore, teacher);
			takeTestListPageVOs.add(takeTestListPageVO);
		}
		return takeTestListPageVOs;
	}
	
	/**
	  * @Title: findTestDetailByStudentId
	  * @Description: 获取学生的所有考试记录
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @return
	 */
	public List<TestDetail> findTestDetailByStudentId(Long student_id) {
		return TestDetail.find("student_id = ?", student_id).fetch();
	}
	
	public TestDetail findTestDetail(Long testDetail_id) {
		return TestDetail.findById(testDetail_id);
	}
	
	public TestDetail findTestDetailByStudentIdTestRecordId(Long student_id, Long testRecord_id) {
		return TestDetail.find("student_id = ? and testRecord_id = ?", student_id, testRecord_id).first();
	}
	
	/**
	 * 
	  * @Title: findToDoTestDetailByStudentId
	  * @Description: 获取即将要参加的考试
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param student_id
	  * @return
	 */
	public List<TestDetail> findToDoTestDetailByStudentId(Long student_id) {
		List<TestDetail> testDetails = findTestDetailByStudentId(student_id);
		TestRecord testRecord = null;
		TestDetail testDetail = null;
		TestManagerService testManagerService = TestManagerService.getInstance();
		for (Iterator<TestDetail> iterator = testDetails.iterator(); iterator.hasNext(); ) {
			testDetail = iterator.next();
			if (testDetail.istaked == istaked.taked.ordinal()) {
				iterator.remove();
				continue;
			}
			testRecord = testManagerService.findTestRecord(testDetail.testRecord_id);
			if (isEnd(testRecord.getEndTime(), new Date()) == true) {
				iterator.remove();
			}
		}
		return testDetails;
	}
	
	/**
	 * 
	  * @Title: isEnd
	  * @Description: 判断当前时间是否已过考试结束时间
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param endTime
	  * @param nowTime
	  * @return 若考试已经结束，返回true 否则返回false
	 */
	private boolean isEnd(Date endTime, Date nowTime) {
		if (endTime.before(nowTime)) {
			return true;
		}
		return false;
	}
	
	public AnswerRecord addAnswerRecord(long student_id, long paper_id, 
			long testRecord_id, long question_id, String result) {
		AnswerRecord answerRecord = new AnswerRecord(student_id, paper_id, testRecord_id, question_id, result);
		answerRecord.save();
		return answerRecord;
	}

	/**
	 * 
	  * @Title: setIstaked
	  * @Description: 设置该场考试学生已经参加
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param testDetail_id
	  * @return
	 */
	public boolean setIstaked(Long testDetail_id) {
		TestDetail testDetail = findTestDetail(testDetail_id);
		testDetail.setIstaked(istaked.taked.ordinal());
		testDetail.save();
		return true;
	}
}
