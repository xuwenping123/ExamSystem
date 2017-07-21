package service;

import java.util.ArrayList;
import java.util.List;

import models.AnswerRecord;
import models.Paper;
import models.Question;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.AnswerViewResultVo;
import models.vo.MarkingServiceListVO;

/**
 * -> MarkingController
 * @author think
 *
 */
public class MarkingService {

	private final static MarkingService instance = new MarkingService();
	
	public static MarkingService getInstance() {
		return instance;
	}
	
	/**
	 * 查看teacher_id 的 没有批改的试卷，返回给客户端交互数据
	 * @param teacher_id
	 * @return
	 */
	public List<MarkingServiceListVO> go2MarkingListPage(Long teacher_id) {
		List<TestDetail> testDetails = TestDetailService.getInstance().getNoMarkedTestById(teacher_id);
		Teacher teacher = Teacher.findById(teacher_id);
		TestDetail testDetail = null;
		List<MarkingServiceListVO> markingServiceListVOs = new ArrayList<>();
		MarkingServiceListVO markingServiceListVO = null;
		TestRecord testRecord = null;
		int totalScore = 0;
		Paper paper = null;
		Student student = null;
		for (int i = 0; i < testDetails.size(); i++) {
			testDetail = testDetails.get(i);
			student = Student.findById(testDetail.getStudent_id());
			testRecord = TestRecord.findById(testDetail.getTestRecord_id());
			paper = TestRecordService.getInstance().getPaperByTestRecordId(testDetail.getTestRecord_id());
			totalScore = QuestionPaperRsService.getInstance().getTotalScoreByPaperId(paper.getId());
			markingServiceListVO = new MarkingServiceListVO(student, paper, testRecord, totalScore, teacher);
			markingServiceListVOs.add(markingServiceListVO);
		}
		return markingServiceListVOs;
	}

	/**
	 * 跳转至答题卡页面进行试卷评分
	 * @param testRecord_id
	 * @param student_id
	 * @return
	 */
	public List<AnswerViewResultVo> go2MarkingPage(Long testRecord_id, Long student_id) {
		List<AnswerViewResultVo> answerViewResultVos = StudentTestService.getInstance().showTestResult(testRecord_id, student_id);
		return answerViewResultVos;
	}
	
	/**
	 * 进行试卷评分结果的提交   默认questions 与 scores 的序号相对应
	 * @param testRecord_id
	 * @param student_id
	 * @param scores
	 */
	public void submitMarkingResult(Long testRecord_id, Long student_id, String scores[]) {
		List<Question> questions = StudentTestService.getInstance().getQuestionsByTestRecordId(testRecord_id);
		if (questions.size() != scores.length) {
			throw new RuntimeException("答题评分数目不完整！请全部评分后再次提交！");
		}
		Paper paper = TestRecordService.getInstance().getPaperByTestRecordId(testRecord_id);
		AnswerRecordService answerRecordService = AnswerRecordService.getInstance();
		for (int i = 0; i < scores.length; i++) {
			//Long student_id, Long paper_id, Long testRecord_id, Long question_id, int score
			answerRecordService.addScore(student_id, paper.getId(), testRecord_id, questions.get(i).getId(), Integer.valueOf(scores[i]));
		}
		//设置阅卷状态值 isMarked
		TestDetailService testDetailService = TestDetailService.getInstance();
		testDetailService.setMarked(testDetailService.getTestDetailByTwoId(testRecord_id, student_id).getId());
	}
}
