package service;

import java.util.List;

import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import models.vo.TestRecordVO;

public class TestRecordService {
	
	/**
	 * 
	private TestRecord testRecord;
	private TestDetail testDetail;
	private Paper paper;
	private Teacher teacher;
	private List<Student> students;
	 */
	
	/**
	 * 
	 * @param testRecord
	 * @return
	 */
	public TestRecordVO getTestRecordVObyTestRecord(TestRecord testRecord) {
		Paper paper = Paper.findById(testRecord.getPaper_id());
		List<TestDetail> testDetails = TestDetail.find("testRecord_id = ?", testRecord.getId()).fetch();
		TestDetail testDetail = testDetails.get(0);
		Teacher teacher = Teacher.findById(testDetail.getTeacher_id());
		return null;
	}
}
