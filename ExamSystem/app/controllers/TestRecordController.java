package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Paper;
import models.TestRecord;
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
		renderArgs.put("pageTitle", "考试添加");
		render("test/test.html");
	}
	
	/**
	 * 跳转至修改考试记录页面
	 */
	public static void go2ModTestRecord() {
		renderArgs.put("pageTitle", "考试修改");
		render("test/test.html");
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
	public static void addTestRecord(String beginTime, String endTime, Integer status, String remark, long paper_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(beginTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TestRecord testRecord = new TestRecord(begin, end, status, remark, paper_id);
		testRecord.save();
		renderArgs.put("testRecord", testRecord);
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
	public static void modifyTestRecord() {
		long id = Long.valueOf(params.get("id"));
		Date beginTime = null;
		Date endTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			beginTime = sdf.parse(params.get("beginTime"));
			endTime = sdf.parse(params.get("endTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int status = Integer.valueOf(params.get("status"));
		String remark = params.get("remark");
		long paper_id = Long.valueOf(params.get("paper_id"));
		TestRecord testRecord = TestRecord.findById(id);
		testRecord.setBeginTime(beginTime);
		testRecord.setEndTime(endTime);
		testRecord.setStatus(status);
		testRecord.setRemark(remark);
		testRecord.setPaper_id(paper_id);
		testRecord.save();
		renderArgs.put("testRecord", testRecord);
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
