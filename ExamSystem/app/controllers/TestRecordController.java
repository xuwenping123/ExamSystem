package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controllers.util.ConstantUtil;
import groovyjarjarasm.asm.tree.IntInsnNode;
import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.TestRecordVO;
import play.mvc.Controller;
import service.TestRecordService;
import sun.launcher.resources.launcher;

/**
 * 考试记录类	添加考试 
 * @author think
 *
 */
public class TestRecordController extends Controller{

	/**
	 * 查看所有考试记录安排
	 */
	public static void showAllTestRecord() {
		List<TestRecordVO> testRecordVOs = new ArrayList<>();
		TestRecordVO testRecordVO = null;
		List<TestRecord> testRecords = TestRecord.findAll();
		TestRecordService testRecordService = TestRecordService.getInstance();
		for (int i = 0; i < testRecords.size(); i++) {
			testRecordVO = testRecordService.getTestRecordVObyTestRecord(testRecords.get(i));
			testRecordVOs.add(testRecordVO);
		}
		renderArgs.put("testRecordVOs", testRecordVOs);
		render("test/testList.html");
	}
	
	/**
	 * 跳转至添加考试记录页面
	 */
	public static void go2AddTestRecord() {
		List<Paper> papers = Paper.findAll();
		renderArgs.put("papers", papers);
		List<Student> students = Student.findAll();
		renderArgs.put("students", students);
		List<Teacher> teachers = Teacher.findAll();
		renderArgs.put("teachers", teachers);
		renderArgs.put("pageTitle", "考试添加");
		render("test/test.html");
	}
	
	/**
	 * 跳转至修改考试记录页面
	 */
	public static void go2ModTestRecord(Long id) {
		TestRecord testRecord = TestRecord.findById(id);
		renderArgs.put("testRecord", testRecord);
		List<Paper> papers = Paper.findAll();
		renderArgs.put("papers", papers);
		renderArgs.put("pageTitle", "考试修改");
		List<Student> students = Student.findAll();
		renderArgs.put("students", students);
		List<Teacher> teachers = Teacher.findAll();
		renderArgs.put("teachers", teachers);
		render("test/test.html");
	}
	
	/**
	 * 跳转至查看考试记录（特别是阅卷人和考试学生信息）的页面
	 * @param id
	 */
	public static void go2ViewTestRecord(Long id) {
		TestRecordVO testRecordVO = TestRecordService.getInstance().getTestRecordVOByTestRecordId(id);
		renderArgs.put("testRecordVO", testRecordVO);
		renderArgs.put("pageTitle", "考试查看");
		render("test/testView.html");
	}
	
	/**
	 * 校验开始时间与结束时间
	 * @param begin
	 * @param end
	 * @return
	 */
	private void checkBeginEndTime(Date begin, Date end) {
		if (begin.before(end)) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 添加考试 http.post all 
	 */
	public static void addTestRecord(String beginTime, String endTime, Integer status, String remark,
			long paper_id, Long teacher_id, Long studentIds[]) {
		TestRecordService.getInstance().addTestRecord(beginTime, endTime, status, remark,
				paper_id, teacher_id, studentIds);
		showAllTestRecord();
	}
	
	/**
	 * 删除考试	http.get id
	 */
	public static void deleteTestRecord(long id) {
		TestRecord testRecord = TestRecord.findById(id);
		testRecord.delete();
		showAllTestRecord();
	}
	
	/**
	 * 修改考试信息	http.post all
	 */
	public static void modifyTestRecord(Long id, String remark, String beginTime, String endTime, 
			Integer status, Long paper_id, Long teacher_id, Long studentIds[]) {
		TestRecordService.getInstance().modifyTestRecord(id, remark, beginTime, endTime, status, 
				paper_id, teacher_id, studentIds);
		showAllTestRecord();
	}
	
	/**
	 * 查询beginTime endTime 之间的所有考试 http.post 	beginTime	endTime
	 */
	public static void findByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime = null;
		Date endTime = null;
		try {
			beginTime = sdf.parse(params.get("beginTime"));
			endTime = sdf.parse(params.get("endTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TestRecord> testRecords = TestRecord.find("beginTime >= ? and endTime <= ?", beginTime, endTime).fetch();
		renderArgs.put("testRecords", testRecords);
		render("testRecord/testRecord.html");
	}
	
	/**
	 * 查询 beginTime endTime 之间 状态为status 的所有考试  http.get beginTime endTime status
	 */
	public static void findByTimeAndStatus() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime = null;
		Date endTime = null;
		try {
			beginTime = sdf.parse(params.get("beginTime"));
			endTime = sdf.parse(params.get("endTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int status = Integer.valueOf(params.get("status"));
		List<TestRecord> testRecords = TestRecord.find("beginTime >= ? and endTime <= ? and status = ?", beginTime, endTime, status).fetch();
		renderArgs.put("testRecords", testRecords);
		render("testRecord/testRecord.html");
	}
	
	/**
	 * 查询状态为status的所有考试	http.get status
	 */
	public static void findByStatus(int status) {
		List<TestRecord> testRecords = TestRecord.find("status = ?", status).fetch();
		renderArgs.put("testRecords", testRecords);
		render("testRecord/testRecord.html");
	}
}
