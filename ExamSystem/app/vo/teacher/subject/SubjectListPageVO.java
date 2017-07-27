package vo.teacher.subject;

import java.util.List;
import models.Subject;

public class SubjectListPageVO {
	
	private List<Subject> subjects;
	
	private int totalPage;

	private int currentPage;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public SubjectListPageVO(List<Subject> subjects, int totalPage, int currentPage) {
		this.subjects = subjects;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
	}
}
