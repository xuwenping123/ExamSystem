package service.teacher.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import play.Logger;
import service.student.StudentService;
import service.teacher.TeacherService;
import service.teacher.paper.PaperManagerService;
import service.teacher.subject.SubjectManagerService;
import util.ConstantUtil;
import util.PageUtil;
import util.EnumUtil.currentPageStatus;
import vo.PageVO;
import vo.teacher.paper.PaperListPageVO;
import vo.teacher.test.TestListPageVO;
import vo.teacher.test.TestPageVO;
import vo.teacher.test.TestViewVO;

/**
 * 
  * @ClassName: TestManagerService
  * @Description: testManagerController
  * @author:xuwenping
  * @date: 2017年8月1日
 */
public class TestManagerService {

	private static TestManagerService instance = new TestManagerService();
	
	public static TestManagerService getInstance() {
		return instance;
	}
	
	/** 标记位，用于判断currentPage值是否需要改变  */
	private currentPageStatus flag = currentPageStatus.unchanged;
	
	public PageVO getPageVO(Integer currentPage) {
		Logger.info("after change : currentPage is %s", getCurrentPage(currentPage));
		return new PageVO(getTotalPage(), getCurrentPage(currentPage));
	}
	
	public List<TestListPageVO> selectPage(Integer selectPage) {
		List<TestRecord> testRecords = TestRecord.all().fetch(selectPage, ConstantUtil.perPage);
		TestRecord testRecord = null;
		TestDetail testDetail = null;
		List<TestListPageVO> testListPageVOs = new ArrayList<>();
		TestListPageVO testListPageVO = null;
		Paper paper = null;
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		Teacher teacher = null;
		TeacherService teacherService = TeacherService.getInstance();
		for (int i = 0; i < testRecords.size(); i++) {
			testRecord = testRecords.get(i);
			testDetail = findTestDetail(testRecord.getId());
			paper = paperManagerService.findPaper(testRecord.getPaper_id());
			testDetail = findTestDetail(testRecord.getId());
			teacher = teacherService.findTeacher(testDetail.getTeacher_id());
			testListPageVO = new TestListPageVO(testRecord.getId(), testRecord.getBeginTime(), 
					testRecord.getEndTime(), testRecord.getStatus(), testRecord.getRemark(), 
					paper, teacher, testDetail.getIstaked(), testDetail.getIsMarked());
			testListPageVOs.add(testListPageVO);
		}
		return testListPageVOs;
	}
	
	public List<TestListPageVO> previousPage(Integer currentPage) {
		if (currentPage.equals(1)) {
			return selectPage(currentPage);
		}
		flag = currentPageStatus.negative;
		return selectPage(--currentPage);
	}
	
	public List<TestListPageVO> nextPage(Integer currentPage) {
		if (currentPage.equals(getTotalPage())) {
			return selectPage(currentPage);
		}
		flag = currentPageStatus.positive;
		return selectPage(++currentPage);
	}
	
	public int getCurrentPage(Integer currentPage) {
		if (flag == currentPageStatus.negative) {
			flag = currentPageStatus.unchanged;
			return --currentPage;
		} else if (flag == currentPageStatus.positive) {
			flag = currentPageStatus.unchanged;
			return ++currentPage;
		} else {
			return currentPage;
		}
	}
	
	
	public int getTestCount() {
		return (int)TestRecord.count();
	}
	
	public int getTotalPage() {
		return PageUtil.getInstance().getTotalPage(getTestCount());
	}
	
	public List<TestRecord> findAll() {
		return TestRecord.findAll();
	}
	
	public TestRecord findTestRecord(Long testRecord_id) {
		return TestRecord.findById(testRecord_id);
	}
	
	public TestRecord addTestRecord(String beginTime, String endTime, int status, String remark, long paper_id) {
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantUtil.dateFormat);
		Date begin = null, end = null;
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
	
	public TestRecord editTestRecord(Long testRecord_id, String beginTime, String endTime, int status, String remark, long paper_id) {
		TestRecord testRecord = findTestRecord(testRecord_id);
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantUtil.dateFormat);
		Date begin = null, end = null;
		try {
			begin = sdf.parse(beginTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		testRecord.setBeginTime(begin);
		testRecord.setEndTime(end);
		testRecord.setStatus(status);
		testRecord.setRemark(remark);
		testRecord.setPaper_id(paper_id);
		testRecord.save();
		return testRecord;
	}
	
	public TestDetail addTestDetail(long testRecord_id, long teacher_id, long student_id) {
		TestDetail testDetail = new TestDetail(testRecord_id, teacher_id, student_id, 0, 0);
		testDetail.save();
		return testDetail;
	}
	
	/**
	 * 
	  * @Title: deleteTestDetailByTestRecord
	  * @Description: 删除考试细节
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param testRecord_id
	 */
	public int deleteTestDetailByTestRecord(Long testRecord_id) {
		String sql = "from TestDetail where testRecord_id = ?";
		return TestDetail.delete(sql, testRecord_id); 
	}
	
	/**
	 * 
	  * @Title: findTestDetail
	  * @Description: 获取与testRecord count = testDetail count 一场考试，阅卷老师仅有一人
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param testRecord_id
	  * @param teacher_id
	  * @return
	 */
	public TestDetail findTestDetail(Long testRecord_id) {
		String sql = "testRecord_id = ?";
		TestDetail testDetail = TestDetail.find(sql, testRecord_id).first();
		return testDetail;
	}
	
	/**
	 * 
	  * @Title: findAllTestDetail
	  * @Description: 获取所有的该场考试记录  student_id 不同
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param testRecord_id
	  * @return
	 */
	public List<TestDetail> findAllTestDetail(Long testRecord_id) {
		String sql = "testRecord_id = ?";
		return TestDetail.find(sql, testRecord_id).fetch();
	}
	
	/**
	 * 
	  * @Title: findStudentsByTestRecord
	  * @Description: 获取所有的student
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param testRecord_id
	  * @return
	 */
	public List<Student> findStudentsByTestRecord(Long testRecord_id) {
		List<TestDetail> testDetails = findAllTestDetail(testRecord_id);
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < testDetails.size(); i++) {
			students.add(StudentService.getInstance().findStudent(testDetails.get(i).getStudent_id()));
		}
		return students;
	}
	
	public List<TestListPageVO> go2TestListPage() {
		return selectPage(1);
	}
	
	/**
	 * 
	  * @Title: go2AddTestPage
	  * @Description: 跳转至添加test页面
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @return
	 */
	public TestPageVO go2AddTestPage() {
		List<Paper> papers = PaperManagerService.getInstance().findAll();
		List<Student> students = StudentService.getInstance().findAll();
		List<Teacher> teachers = TeacherService.getInstance().findAll();
		return new TestPageVO(papers, students, teachers);
	}
	
	/**
	 * 
	  * @Title: addTest
	  * @Description: 添加考试 
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param beginTime
	  * @param endTime
	  * @param status
	  * @param remark
	  * @param paper_id
	  * @param teacher_id
	  * @param studentIds
	  * @return
	 */
	public TestRecord addTest(String beginTime, String endTime, Integer status, String remark,
			long paper_id, Long teacher_id, Long studentIds[]) {
		TestRecord testRecord = addTestRecord(beginTime, endTime, status, remark, paper_id);
		for (int i = 0; i < studentIds.length; i++) {
			addTestDetail(testRecord.getId(), teacher_id, studentIds[i]);
		}
		return testRecord;
	}
	
	public TestViewVO go2ViewTestPage(Long testRecord_id) {
		TestRecord testRecord = findTestRecord(testRecord_id);
		Paper paper = PaperManagerService.getInstance().findPaper(testRecord.getPaper_id());
		TestDetail testDetail = findTestDetail(testRecord_id);
		Teacher teacher = TeacherService.getInstance().findTeacher(testDetail.getTeacher_id());
		List<Student> students = findStudentsByTestRecord(testRecord_id);
		return new TestViewVO(testRecord_id, testRecord.getBeginTime(), 
				testRecord.getEndTime(), testRecord.getStatus(), testRecord.getRemark(), 
				paper, teacher, students, testDetail.getIstaked(), testDetail.getIsMarked());
	}
	
	public TestPageVO go2EditTestPage(Long testRecord_id) {
		TestRecord testRecord = findTestRecord(testRecord_id);
		List<Paper> papers = PaperManagerService.getInstance().findAll();
		List<Student> students = StudentService.getInstance().findAll();
		List<Teacher> teachers = TeacherService.getInstance().findAll();
		return new TestPageVO(testRecord_id, testRecord.getBeginTime(), testRecord.getEndTime(), testRecord.getStatus(), testRecord.getRemark(), papers, students, teachers);
	}
	
	public TestRecord editTest(Long testRecord_id, String remark, String beginTime, String endTime, 
			Integer status, Long paper_id, Long teacher_id, Long studentIds[]) {
		TestRecord testRecord = editTestRecord(testRecord_id, beginTime, endTime, status, remark, paper_id);
		//delete testDetail
		int deleteCount = deleteTestDetailByTestRecord(testRecord_id);
		Logger.info("delete TestDetail count : %s", deleteCount);
		//add testDetail
		for (int i = 0; i < studentIds.length; i++) {
			addTestDetail(testRecord_id, teacher_id, studentIds[i]);
		}
		return testRecord;
	}
	
	/**
	 * 
	  * @Title: deleteTest
	  * @Description: 删除testRecord
	  * @author: xuwenping
	  * @date: 2017年8月2日
	  * @param testRecord_id
	  * @return
	 */
	public TestRecord deleteTest(Long testRecord_id) {
		TestRecord testRecord = TestRecord.findById(testRecord_id);
		testRecord.delete();
		return testRecord;
	}
}
