package vo.student.take;

import java.util.Date;

import models.Paper;
import models.user.Teacher;

public class TakeTestListPageVO {
	/** 学生id号 */
	private Long student_id;
	/** testRecord标号 */
	private Long testRecord_id;
	
	private Date beginTime;
	
	private Date endTime;
	
	private String remark;
	/** 考试所用试卷 */
	private Paper paper;
	/** 试卷的满分 */
	private Integer totalScore;
	/** 阅卷人 */
	private Teacher teacher;
	
	public TakeTestListPageVO(Long student_id, Long testRecord_id, Date beginTime, Date endTime, String remark,
			Paper paper, Integer totalScore, Teacher teacher) {
		this.student_id = student_id;
		this.testRecord_id = testRecord_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.remark = remark;
		this.paper = paper;
		this.totalScore = totalScore;
		this.teacher = teacher;
	}

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
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

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
