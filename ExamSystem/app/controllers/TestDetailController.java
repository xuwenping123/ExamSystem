package controllers;

import java.util.List;
import models.TestDetail;
import play.mvc.Controller;

/**
 * 考试细节表 
 * @author think
 *
 */
public class TestDetailController extends Controller{

	/**
	 * 注意点：
	 * 1. 试卷总得分由答题卡中所有题得分组成
	 */
	
	/**
	 * 测试添加数据  http.post all
	 * @param testRecord_id
	 * @param teacher_id
	 * @param student_id
	 * @param totalScore
	 */
	public static void addTestDetail(long testRecord_id, long teacher_id, 
			long student_id) {
		// this is modify !!!
		/*TestDetail testDetail = new TestDetail(testRecord_id, teacher_id, student_id);
		testDetail.save();
		renderArgs.put("testDetail", testDetail);*/
		render("testDetail/testDetail.html");
	}
	
	/**
	 * 删除考试详情  http.get 
	 * @param id
	 */
	public static void deleteTestDetail(long id) {
		TestDetail testDetail = TestDetail.findById(id);
		testDetail.delete();
		renderArgs.put("testDetail", testDetail);
		render("testDetail/testDetail.html");
	}
	
	/**
	 * 修改考试记录	http.post
	 * @param id
	 * @param testRecord_id
	 * @param teacher_id
	 * @param student_id
	 * @param totalScore
	 */
	public static void modifyTestDetail(long id, long testRecord_id, long teacher_id,
			long student_id) {
		TestDetail testDetail = TestDetail.findById(id);
		testDetail.setTestRecord_id(testRecord_id);
		testDetail.setTeacher_id(teacher_id);
		testDetail.setStudent_id(student_id);
		testDetail.save();
		renderArgs.put("testDetail", testDetail);
		render("testDetail/testDetail.html");
	}
	
	/**
	 * 查询考试记录 	http.get
	 * @param id
	 */
	public static void findTestDetailById(long id) {
		TestDetail testDetail = TestDetail.findById(id);
		renderArgs.put("testDetail", testDetail);
		render("testDetail/testDetail.html");
	}
	
	/**
	 * 查询所有记录	http.get
	 */
	public static void finAll() {
		List<TestDetail> testDetails = TestDetail.findAll();
		renderArgs.put("testDetails", testDetails);
		render("testDetail/testDetail.html");
	}
}
