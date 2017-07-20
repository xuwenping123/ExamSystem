package service;

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
	
}
