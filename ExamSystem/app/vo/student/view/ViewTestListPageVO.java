package vo.student.view;

import java.util.Date;

import models.Paper;
import models.user.Teacher;

public class ViewTestListPageVO {
	/** 学生id号 */
	private long student_id;
	
	/** testRecord标号 */
	private long testRecord_id;
	
	private Date beginTime;
	
	private Date endTime;
	
	private String remark;
	/** 考试所用试卷 */
	private Paper paper;
	/** 试卷的满分 */
	private int totalScore;
	/** 阅卷人 */
	private Teacher teacher;
	/** 是否参加了考试 */
	private int istaked;
	/** 是否批改了试卷*/
	private int isMarked;
	/** 试卷所得总分 */
	private int ownScore;
	
	public ViewTestListPageVO(long student_id, long testRecord_id, Date beginTime, Date endTime, 
			String remark, Paper paper,
			int totalScore, Teacher teacher, int istaked, int isMarked, int ownScore) {
		this.student_id = student_id;
		this.testRecord_id = testRecord_id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.remark = remark;
		this.paper = paper;
		this.totalScore = totalScore;
		this.teacher = teacher;
		this.istaked = istaked;
		this.isMarked = isMarked;
		this.ownScore = ownScore;
	}
	
	public long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(long student_id) {
		this.student_id = student_id;
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
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	public int getOwnScore() {
		return ownScore;
	}

	public void setOwnScore(int ownScore) {
		this.ownScore = ownScore;
	}
	
}
