package vo.teacher.test;

import java.util.Date;
import java.util.List;
import models.Paper;
import models.user.Student;
import models.user.Teacher;

/**
 * 
  * @ClassName: TestPageVO
  * @Description: 考试模块 add edit 页面交互VO
  * @author:xuwenping
  * @date: 2017年8月1日
 */
public class TestPageVO {

	private long testRecord_id;

	private Date beginTime;
	
	private Date endTime;
	
	private int status;
	
	private String remark;
	
	private List<Paper> papers;
	
	private List<Student> students;
	
	private List<Teacher> teachers;

	public TestPageVO(long testRecord_id, Date beginTime, Date endTime, 
			int status, String remark, List<Paper> papers,
			List<Student> students, List<Teacher> teachers) {
		this.testRecord_id = testRecord_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.papers = papers;
		this.students = students;
		this.teachers = teachers;
	}
	
	public TestPageVO(List<Paper> papers, List<Student> students, List<Teacher> teachers) {
		this.papers = papers;
		this.students = students;
		this.teachers = teachers;
	}

	public long getTestRecord_id() {
		return testRecord_id;
	}

	public void setTestRecord_id(long testRecord_id) {
		this.testRecord_id = testRecord_id;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
}
