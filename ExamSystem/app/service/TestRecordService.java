package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import controllers.util.ConstantUtil;
import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.TestRecordVO;
import play.Logger;
import play.data.validation.Required;

public class TestRecordService {
	
	private final static TestRecordService instance = new TestRecordService();
	
	public static TestRecordService getInstance() {
		return instance;
	}
	
	/**
	 * 通过一条考试记录，获取该条记录封装的 TestRecordVO
	 * @param testRecord
	 * @return
	 */
	public TestRecordVO getTestRecordVObyTestRecord(TestRecord testRecord) {
		Paper paper = Paper.findById(testRecord.getPaper_id());
		TestRecordVO testRecordVO = new TestRecordVO(testRecord.getId(), testRecord.getBeginTime(), testRecord.getEndTime(),
				testRecord.getStatus(), testRecord.getRemark(), paper);
		return testRecordVO;
	}
	
	/**
	 * 通过 testRecord id 查看 所有的TestRecordVO元素 包括 testRecord, paper teacher students
	 * @param id
	 * @return
	 */
	public TestRecordVO getTestRecordVOByTestRecordId(long id) {
		TestRecord testRecord = TestRecord.findById(id);
		Paper paper = Paper.findById(testRecord.getPaper_id());
		List<TestDetail> testDetails = TestDetail.find("testRecord_id = ?", id).fetch();
		List<Student> students = new ArrayList<>();
		Student student = null;
		for (int i = 0; i < testDetails.size(); i++) {
			student = Student.findById(testDetails.get(i).getStudent_id());
			students.add(student);
		}
		Teacher teacher = Teacher.findById(testDetails.get(0).getTeacher_id());
		TestRecordVO testRecordVO = new TestRecordVO(id, testRecord.getBeginTime(), testRecord.getEndTime(),
				testRecord.getStatus(), testRecord.getRemark(), paper, teacher, students);
		return testRecordVO;
	}
	
	/**
	 * 添加testRecord   保存元素包括 testRecord 和 testDetail
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param remark
	 * @param paper_id
	 * @param teacher_id
	 * @param studentIds
	 */
	public void addTestRecord(String beginTime, String endTime, Integer status, String remark, long paper_id, Long teacher_id, 
			Long studentIds[]) {
		TestRecord testRecord = saveTestRecord(beginTime, endTime, status, remark, paper_id);
		TestDetail testDetail = null;
		for (int i = 0; i < studentIds.length; i++) {
//			this modify !
//			testDetail = new TestDetail(testRecord.getId(), teacher_id, studentIds[i]);
			testDetail.save();
		}
	}
	
	/**
	 * 添加 testRecord
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param remark
	 * @param paper_id
	 */
	public TestRecord saveTestRecord(String beginTime, String endTime, Integer status, String remark, long paper_id) {
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantUtil.dateFormat);
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
		return testRecord;
	}
	
	/**
	 * 修改testRecord  包括 testRecord testDetail	保存testRecord前需要先手动将原有 testRecord 数据删除
	 * @param id
	 * @param remark
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param paper_id
	 * @param teacher_id
	 * @param studentIds
	 */
	public void modifyTestRecord(Long id, String remark, String beginTime, String endTime,
			Integer status, Long paper_id, Long teacher_id, Long studentIds[]) {
		//先删除原有绑定testRecord 的所有testDetail
		deleteOldTestDetail(id);
		TestRecord testRecord = modSaveTestRecord(id, remark, beginTime, endTime, status, paper_id);
		TestDetail testDetail = null;
		for (int i = 0; i < studentIds.length; i++) {
//			this modify !
//			testDetail = new TestDetail(testRecord.getId(), teacher_id, studentIds[i]);
			testDetail.save();
		}
	}
	
	/**
	 *  保存 testRecord, 保存前需要先手动将原有 testRecord 数据删除
	 * @param id
	 * @param remark
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param paper_id
	 * @return
	 */
	public TestRecord modSaveTestRecord(Long id, String remark, String beginTime, String endTime,
			Integer status, Long paper_id) {
		Date begin = null;
		Date end = null;
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantUtil.dateFormat);
		try {
			begin = sdf.parse(beginTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TestRecord testRecord = TestRecord.findById(id);
		testRecord.setBeginTime(begin);
		testRecord.setEndTime(end);
		testRecord.setStatus(status);
		testRecord.setRemark(remark);
		testRecord.setPaper_id(paper_id);
		testRecord.save();
		return testRecord;
	}
	
	/**
	 * 
	 * @param testRecord_id
	 */
	public void deleteOldTestDetail(Long testRecord_id) {
		List<TestDetail> testDetails = TestDetail.find("testRecord_id = ?", testRecord_id).fetch();
		TestDetail testDetail = null;
		for (int i = 0; i < testDetails.size(); i++) {
			testDetail = testDetails.get(i);
			testDetail.delete();
		}
	}
	
	/**
	 * 通过考试记录表获取考试试卷类
	 * testRecord -> paper
	 * @param testRecord_id
	 * @return
	 */
	public Paper getPaperByTestRecordId(Long testRecord_id) {
		TestRecord testRecord = TestRecord.findById(testRecord_id);
		return Paper.findById(testRecord.getPaper_id());
	}
	
	/**
	 * 通过试卷查找testRecord记录
	 * @param paper_id
	 * @return
	 */
	public TestRecord getTestRecordByPaperId(Long paper_id) {
		List<TestRecord> testRecords = TestRecord.find("paper_id = ?", paper_id).fetch();
		Logger.info("通过Paper_id 查找到的TestRecord记录数共有  %s/t 条", testRecords.size());
		return testRecords.get(0);
	}
}
