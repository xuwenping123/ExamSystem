package service;

import java.util.List;

import models.AnswerRecord;

/**
 * 答题卡提交 service -> AnswerRecordController
 * @author think
 *
 */
public class AnswerRecordService {

	private final static AnswerRecordService instance = new AnswerRecordService();
	
	public static AnswerRecordService getInstance() {
		return instance;
	}
	
	/**
	 * 进行答题记录的添加,包括答题结果的提交
	 * @param student_id
	 * @param paper_id
	 * @param testRecord_id
	 * @param question_id
	 * @param result
	 */
	public void addAnswerRecord(Long student_id, Long paper_id, Long testRecord_id, 
			Long question_id, String result) {
		AnswerRecord answerRecord = new AnswerRecord(student_id, paper_id, testRecord_id, question_id, result);
		answerRecord.save();
	}
	
	/**
	 * 老师阅卷时添加分数
	 * 先找到该答题记录，再进行分数设定
	 * @param student_id
	 * @param paper_id
	 * @param testRecord_id
	 * @param question_id
	 * @param score
	 */
	public void addScore(Long student_id, Long paper_id, Long testRecord_id, Long question_id, int score) {
		AnswerRecord answerRecord = findAnswerRecordByfourIds(student_id, paper_id, testRecord_id, question_id);
		answerRecord.setScore(score);
		answerRecord.save();
	}
	
	/**
	 * 通过四个id参数查询到答题记录
	 * @param student_id
	 * @param paper_id
	 * @param testRecord_id
	 * @param question_id
	 * @return
	 */
	public AnswerRecord findAnswerRecordByfourIds(Long student_id, Long paper_id, Long testRecord_id, Long question_id) {
		List<AnswerRecord> answerRecords = AnswerRecord.find("student_id = ? and paper_id = ? and testRecord_id = ? and question_id = ?",
				student_id, paper_id, testRecord_id, question_id).fetch();
		return answerRecords.get(0); 
	}
}
