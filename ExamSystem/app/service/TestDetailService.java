package service;

import java.util.List;

import models.TestDetail;
import play.Logger;

/**
 *  1. TestDetailController  
 *  2. 处理关于TestDetail的事务
 * @author think
 *
 */
public class TestDetailService {

	private final static TestDetailService instance = new TestDetailService();
	
	public static TestDetailService getInstance() {
		return instance;
	}
	
	/**
	 * 通过三个Id号获取唯一的testDetal记录
	 * @param testRecord_id
	 * @param teacher_id
	 * @param student_id
	 * @return
	 */
	public TestDetail getTestDetailBythreeId(Long testRecord_id, Long teacher_id, Long student_id) {
		List<TestDetail> testDetails = TestDetail.find("testRecord_id = ? and teacher_id = ? and student_id = ?", testRecord_id, teacher_id, student_id).fetch();
		return testDetails.get(0);
	}
	
	/**
	 * 通过两个Id号获取唯一的testDetail记录
	 * @param testRecord_id
	 * @param student_id
	 * @return
	 */
	public TestDetail getTestDetailByTwoId(Long testRecord_id, Long student_id) {
		List<TestDetail> testDetails = TestDetail.find("testRecord_id = ? and student_id = ?", testRecord_id, student_id).fetch();
		Logger.info("获取到的考试详情记录数有 :%s\t 条", testDetails.size());
		return testDetails.get(0);
	}
	
	/**
	 * 参加考试后，保证状态值为1， 且不可调整
	 * @param testDetail_id
	 */
	public void setIstaked(Long testDetail_id) {
		TestDetail testDetail = TestDetail.findById(testDetail_id);
		testDetail.setIstaked(1);
		testDetail.save();
	}
	
	/**
	 * 查看老师的未进行批改的试卷
	 * @param teacher_id
	 * @return
	 */
	public List<TestDetail> getNoMarkedTestById(Long teacher_id) {
		List<TestDetail> testDetails = TestDetail.find("isMarked = ? and teacher_id = ?", 0, teacher_id).fetch();
		return testDetails;
	}
	
	/** 
	 * 老师阅卷后，修改状态值为1， 且不可调整
	 * @param testDetail_id
	 */
	public void setMarked(Long testDetail_id) {
		TestDetail testDetail = TestDetail.findById(testDetail_id);
		testDetail.setIsMarked(1);
		testDetail.save();
	}
	
	/** 
	 * 判断该条考试记录是否已被老师阅卷
	 * 若被，返回true
	 * 否则，返回false
	 * @param testDetail_id
	 * @return
	 */
	public boolean isMarkedBy(Long testDetail_id) {
		TestDetail testDetail = TestDetail.findById(testDetail_id);
		return testDetail.getIsMarked() == 1;
	}
}
