package vo.teacher.paper;

import java.util.List;

import models.Question;
import models.Subject;

/**
 * 
  * @ClassName: PaperPageVO
  * @Description: 试卷（添加试题）页面交互数据VO
  * @author:xuwenping
  * @date: 2017年7月31日
 */
public class PaperPageVO {
	
	private long paper_id;
	
	private String remark;
	
	private List<Question> questions;

	private List<Subject> subjects;

	public long getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(long paper_id) {
		this.paper_id = paper_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public PaperPageVO(long paper_id, String remark, List<Question> questions, List<Subject> subjects) {
		this.paper_id = paper_id;
		this.remark = remark;
		this.questions = questions;
		this.subjects = subjects;
	}
	
}
