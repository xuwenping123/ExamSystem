package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import groovyjarjarasm.asm.tree.IntInsnNode;
import models.AnswerRecord;
import models.Paper;
import models.Question;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.AnswerViewResultVo;
import models.vo.StudentViewTestListVO;

/**
 * @see StudentTestController
 * 
 * 封装关于student 参加考试时查看可以参加的考试的考试列表
 * 
 * @author think
 *
 */
public class StudentTestService {

	private final static StudentTestService instance = new StudentTestService();
	
	public static StudentTestService getInstance() {
		return instance;
	}
	
	/**
	 * 通过学生id获取学生参加过的以及没有参加的考试，并使用StudentViewTestListVO类进行封装
	 * 使用时需要依据beginTime endTime 和 status 进行封装
	 * @param id
	 * @return
	 */
	public List<StudentViewTestListVO> getStudentViewTestListVOs(long id) {
		List<TestDetail> testDetails = TestDetail.find("student_id = ? ", id).fetch();
		TestRecord testRecord = null;
		List<StudentViewTestListVO> studentViewTestListVOs = new ArrayList<>();
		StudentViewTestListVO studentViewTestListVO = null;
		Paper paper = null;
		Teacher teacher = null;
		int totalScore = 0;
		for (int i = 0; i < testDetails.size(); i++) {
			testRecord = TestRecord.findById(testDetails.get(i).getTestRecord_id());
			paper = Paper.findById(testRecord.getPaper_id());
			teacher = Teacher.findById(testDetails.get(i).getTeacher_id());
			totalScore = QuestionPaperRsService.getInstance().getTotalScoreByPaperId(paper.getId());
			studentViewTestListVO = new StudentViewTestListVO(id, testRecord.getId(), testRecord.getBeginTime(), 
					testRecord.getEndTime(), testRecord.getRemark(), paper, totalScore, teacher, testDetails.get(i).getIstaked());
			studentViewTestListVOs.add(studentViewTestListVO);
		}
		return studentViewTestListVOs;
	}
	
	/**
	 * 通过学生id获取学生当前时间可以参加的考试	  
	 * 有两个条件：
	 * 1. nowDate before endTime
	 * 2. istaked = 0
	 * @param id
	 * @return
	 */
	public List<StudentViewTestListVO> get2DoTest(long id) {
		List<StudentViewTestListVO> studentViewTestListVOs = getStudentViewTestListVOs(id);
		Date nowDate = new Date();
		for (int i = 0; i < studentViewTestListVOs.size(); i++) {
			if (nowDate.after(studentViewTestListVOs.get(i).getEndTime()) || studentViewTestListVOs.get(i).getIstaked() == 1) {
				studentViewTestListVOs.remove(i);
			}
		}
		return studentViewTestListVOs;
	}
	
	/**
	 * 通过学生id获取学生当前时间已经过期的考试	  
	 * 1. nowDate before endTime
	 * 2. istaked == 1
	 * @param id
	 * @return
	 */
	public List<StudentViewTestListVO> getDoneTest(long id) {
		List<StudentViewTestListVO> studentViewTestListVOs = getStudentViewTestListVOs(id);
		Date nowDate = new Date();
		for (int i = 0; i < studentViewTestListVOs.size(); i++) {
			if (nowDate.before(studentViewTestListVOs.get(i).getEndTime()) || studentViewTestListVOs.get(i).getIstaked() == 0) {
				studentViewTestListVOs.remove(i);
			}
		}
		return studentViewTestListVOs;
	}

	/**
	 * testRecord -> questions 获取对应得考试试题，包括所有分数
	 * @param testRecord_id
	 * @return
	 */
	public List<Question> getQuestionsByTestRecordId(Long testRecord_id) {
		Paper paper = TestRecordService.getInstance().getPaperByTestRecordId(testRecord_id);
		return QuestionPaperRsService.getInstance().getQuestionAndScoreByPaperId(paper.getId());
	}
	
	/**
	 * 通过该三个参数进行一次答题卡记录的提交与保存，并设置考试状态值istaked
	 * @param student_id
	 * @param testRecord_id
	 * @param results
	 */
	public void submitAnswerSheet(Long student_id, Long testRecord_id, String results[]) {
		List<Question> questions = StudentTestService.getInstance().getQuestionsByTestRecordId(testRecord_id);
		if (questions.size() != results.length) {
			throw new RuntimeException("提交答题卡答题记录不完全，题目与答案匹配失败！");
		}
		AnswerRecord answerRecord = null;
		Paper paper = TestRecordService.getInstance().getPaperByTestRecordId(testRecord_id);
		for (int i = 0; i < results.length; i++) {
			answerRecord = new AnswerRecord(student_id, paper.getId(), testRecord_id, questions.get(i).getId(), results[i]);
			answerRecord.save();
		}
		//设置考试状态值 istaked
		TestDetailService testDetailService = TestDetailService.getInstance();
		testDetailService.setIstaked(testDetailService.getTestDetailByTwoId(testRecord_id, student_id).getId());
	}
	
	/**
	 * 通过 考试记录表和学生的id号 查看学生在该场考试中的所有结果
	 * 结果用AnswerViewResultVo 进行封装
	 * @param testRecord_id
	 * @param student_id
	 * @return
	 */
	public List<AnswerViewResultVo> showTestResult(Long testRecord_id, Long student_id) {
		Paper paper = TestRecordService.getInstance().getPaperByTestRecordId(testRecord_id);
		Student student = Student.findById(student_id);
		List<Question> questions = QuestionPaperRsService.getInstance().getQuestionAndScoreByPaperId(paper.getId());
		List<AnswerViewResultVo> answerViewResultVos = new ArrayList<>();
		AnswerViewResultVo answerViewResultVo = null;
		List<AnswerRecord> answerRecords = null;
		String result = null;
		int score = 0;
		for (int i = 0; i < questions.size(); i++) {
			answerRecords = AnswerRecord.find("student_id = ? and paper_id = ? and testRecord_id = ? and question_id = ?", 
					student_id, paper.getId(), testRecord_id, questions.get(i).getId()).fetch();
			result = answerRecords.get(0).getResult();
			score = answerRecords.get(0).getScore();
			answerViewResultVo = new AnswerViewResultVo(student, paper, questions.get(i), result, score);
			answerViewResultVos.add(answerViewResultVo);
		}
		return answerViewResultVos;
	}
}
