package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.TestRecord;
import play.mvc.Controller;
import sun.launcher.resources.launcher;

/**
 * 考试记录类	添加考试 
 * @author think
 *
 */
public class TestRecordController extends Controller{
	/**
	 * 
	@Required
	public Date beginTime;
	
	@Required
	public Date endTime;
	
	@Required
	public int status;
	
	public String remark;
	
	public long paper_id;
	 */
	
	public static void testAddTestRecord() {
		Date beginTime = new Date();
		Date endTime = new Date();
		int status = 3;
		String remark = "test add testRecord";
		long paper_id = 1;
		TestRecord testRecord = new TestRecord(beginTime, endTime, status,remark, paper_id);
		testRecord.save();
		renderArgs.put("testRecord", testRecord);
		render("testRecord/testRecord.html");
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
	public static void addTestRecord() {
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
		TestRecord testRecord = new TestRecord(beginTime, endTime, status, remark, paper_id);
		testRecord.save();
		renderArgs.put("testRecord", testRecord);
		render("testRecord/testRecord.html");
	}
	
	/**
	 * 删除考试	http.get id
	 */
	public static void deleteTestRecord(long id) {
		TestRecord testRecord = TestRecord.findById(id);
		testRecord.delete();
		renderArgs.put("testRecord", testRecord);
		render("testRecord/testRecord.html");
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
		render("testRecord/testRecord.html");
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
