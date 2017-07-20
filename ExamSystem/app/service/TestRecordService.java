package service;

import java.util.List;

import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.TestRecordVO;

public class TestRecordService {
	
	private final static TestRecordService instance = new TestRecordService();
	
	public static TestRecordService getInstance() {
		return instance;
	}
	
	/**
	 * 
	private TestRecord testRecord;
	private TestDetail testDetail;
	private Paper paper;
	private Teacher teacher;
	private List<Student> students;
	 */
	
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
}
