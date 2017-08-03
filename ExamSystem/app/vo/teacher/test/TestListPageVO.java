package vo.teacher.test;

import java.util.Date;
import java.util.List;

import models.Paper;
import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import models.user.Teacher;
import play.data.validation.Required;

/**
 * 
  * @ClassName: TestListPageVO
  * @Description: 考试列表页面交互对象
  * @author:xuwenping
  * @date: 2017年8月1日
 */
public class TestListPageVO {
	private long testRecord_id;
	
	private Date beginTime;
	
	private Date endTime;
	
	private int status;
	
	private String remark;
	
	private Paper paper;
	
	private Teacher teacher;
	
	private int istaked;
	
	private int isMarked;
	
	private List<Student> students;

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

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
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

	public TestListPageVO(long testRecord_id, Date beginTime, Date endTime, int status, String remark, Paper paper,
			Teacher teacher, int istaked, int isMarked, List<Student> students) {
		super();
		this.testRecord_id = testRecord_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.paper = paper;
		this.teacher = teacher;
		this.istaked = istaked;
		this.isMarked = isMarked;
		this.students = students;
	}

	public TestListPageVO(long testRecord_id, Date beginTime, Date endTime, int status, String remark, Paper paper,
			Teacher teacher, int istaked, int isMarked) {
		this.testRecord_id = testRecord_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
		this.paper = paper;
		this.teacher = teacher;
		this.istaked = istaked;
		this.isMarked = isMarked;
	}

}
