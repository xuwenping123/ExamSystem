package vo.teacher.paper;

/**
 * 
  * @ClassName: PaperListPageVO
  * @Description: 用于查看试卷列表交换数据的VO
  * @author:xuwenping
  * @date: 2017年7月31日
 */
public class PaperListPageVO {

	private long paper_id;
	
	private String remark;
	
	private String subject_title;
	
	private int totalScore;

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

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	public String getSubject_title() {
		return subject_title;
	}

	public void setSubject_title(String subject_title) {
		this.subject_title = subject_title;
	}

	public PaperListPageVO(long paper_id, String remark, String subject_title, int totalScore) {
		this.paper_id = paper_id;
		this.remark = remark;
		this.subject_title = subject_title;
		this.totalScore = totalScore;
	}

}
