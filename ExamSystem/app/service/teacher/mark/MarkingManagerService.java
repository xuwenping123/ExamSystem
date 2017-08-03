package service.teacher.mark;

import java.util.ArrayList;
import java.util.List;
import models.AnswerRecord;
import models.Paper;
import models.Question;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.MarkingServiceListVO;
import service.AnswerRecordService;
import service.StudentTestService;
import service.TestDetailService;
import service.TestRecordService;
import service.student.view.ViewTestService;
import service.teacher.TeacherService;
import service.teacher.paper.PaperManagerService;
import service.teacher.test.TestManagerService;
import util.EnumUtil.isMarked;
import vo.student.view.ViewTestPageVO;
import vo.teacher.mark.MarkListPageVO;

/**
 * 
  * @ClassName: MarkingManagerService
  * @Description: MarkingManagerController
  * @author:xuwenping
  * @date: 2017年8月3日
 */
public class MarkingManagerService {

	private static MarkingManagerService instance = new MarkingManagerService();
	
	public static MarkingManagerService getInstance() {
		return instance;
	}
	
	public List<MarkListPageVO> go2MarkListPage(Long teacher_id) {
		TeacherService teacherService = TeacherService.getInstance();
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		List<TestDetail> testDetails = findNoMarkTestDetail(teacher_id);
		Teacher teacher = teacherService.findTeacher(teacher_id);
		TestDetail testDetail = null;
		List<MarkListPageVO> markListVOs = new ArrayList<>();
		MarkListPageVO markListVO = null;
		TestRecord testRecord = null;
		int totalScore = 0;
		Paper paper = null;
		Student student = null;
		for (int i = 0; i < testDetails.size(); i++) {
			testDetail = testDetails.get(i);
			student = Student.findById(testDetail.getStudent_id());
			testRecord = TestRecord.findById(testDetail.getTestRecord_id());
			paper = paperManagerService.findPaper(testRecord.getPaper_id());
			totalScore = paperManagerService.getTotalScoreByPaperId(paper.getId());
			markListVO = new MarkListPageVO(student, paper, testRecord, totalScore, teacher);
			markListVOs.add(markListVO);
		}
		return markListVOs;
	}

	public List<ViewTestPageVO> go2MarkingPage(Long testRecord_id, Long student_id) {
		ViewTestService viewTestService = ViewTestService.getInstance();
		return viewTestService.go2ViewTestPage(testRecord_id, student_id);
	}
	
	public void submitMarkingPage(Long testRecord_id, Long student_id, String scores[]) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		Paper paper = paperManagerService.findPaper(TestManagerService.getInstance().findTestRecord(testRecord_id).getPaper_id());
		List<Question> questions = paperManagerService.findQuestionsByPaperId(paper.getId());
		if (questions.size() != scores.length) {
			throw new RuntimeException("答题评分数目不完整！请全部评分后再次提交！");
		}
		for (int i = 0; i < scores.length; i++) {
			addAnswerRecord(student_id, paper.getId(), testRecord_id, questions.get(i).getId(), Integer.valueOf(scores[i]));
		}
		//设置阅卷状态值 isMarked
		setMarked(testRecord_id, student_id);
	}
	
	public List<TestDetail> findNoMarkTestDetail(Long teacher_id) {
		return TestDetail.find("isMarked = ? and teacher_id = ?", 0, teacher_id).fetch();
	}
	
	public AnswerRecord addAnswerRecord(long student_id, long paper_id, long testRecord_id, long question_id, int score) {
		AnswerRecord answerRecord = AnswerRecord.find("student_id = ? and paper_id = ? and testRecord_id = ? and question_id = ?",
				student_id, paper_id, testRecord_id, question_id).first();
		answerRecord.setScore(score);
		answerRecord.save();
		return answerRecord;
	}
	
	public TestDetail setMarked(Long testRecord_id, Long student_id) {
		TestDetail testDetail = TestDetail.find("testRecord_id = ? and student_id = ?", testRecord_id, student_id).first();
		testDetail.setIsMarked(isMarked.marked.ordinal());
		testDetail.save();
		return testDetail;
	}
}
