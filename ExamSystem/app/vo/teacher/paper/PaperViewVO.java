package vo.teacher.paper;

import java.util.List;

import models.Question;
import models.Subject;

/**
 * 
  * @ClassName: PaperViewVO
  * @Description: 查看试题页面时的数据
  * @author:xuwenping
  * @date: 2017年8月1日
 */
public class PaperViewVO {

	private long paper_id;
	
	private String remark;
	
	private List<Question> questions;
	
	private Subject subject;

	public PaperViewVO(long paper_id, String remark, List<Question> questions, Subject subject) {
		this.paper_id = paper_id;
		this.remark = remark;
		this.questions = questions;
		this.subject = subject;
	}

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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
