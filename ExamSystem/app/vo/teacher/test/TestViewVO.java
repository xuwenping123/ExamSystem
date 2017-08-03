package vo.teacher.test;

import java.util.Date;
import java.util.List;

import models.Paper;
import models.user.Student;
import models.user.Teacher;

/**
 * 
  * @ClassName: TestViewVO
  * @Description: 查看考试所有详情
  * @author:xuwenping
  * @date: 2017年8月1日
 */
public class TestViewVO {

	private Long testRecord_id;

	private Date beginTime;
	
	private Date endTime;
	
	private int status;
	
	private String remark;
	
	private Paper paper;
	
	private Teacher teacher;
	
	private List<Student> students;
	
	private int istaked;
	
	private int isMarked;

	public TestViewVO(Long testRecord_id, Date beginTime, Date endTime, int status, String remark, Paper paper,
			Teacher teacher, List<Student> students, int istaked, int isMarked) {
		this.testRecord_id = testRecord_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.paper = paper;
		this.teacher = teacher;
		this.students = students;
		this.istaked = istaked;
		this.isMarked = isMarked;
	}

	public Long getTestRecord_id() {
		return testRecord_id;
	}

	public void setTestRecord_id(Long testRecord_id) {
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

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getIstaked() {
		return istaked;
	}

	public void setIstaked(int istaked) {
		this.istaked = istaked;
	}

	public int getIsMarked() {
		return isMarked;
	}

	public void setIsMarked(int isMarked) {
		this.isMarked = isMarked;
	}
	
}
