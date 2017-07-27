package service.teacher.subject;

import java.util.List;
import models.Subject;
import util.ConstantUtil;
import util.PageUtil;
import vo.teacher.subject.SubjectListPageVO;

/**
 * 
  * @ClassName: SubjectManagerService
  * @Description: 科目管理service
  * @author:xuwenping
  * @date: 2017年7月27日
 */
public class SubjectManagerService {

	private static SubjectManagerService instance = new SubjectManagerService();
	
	public static SubjectManagerService getInstance() {
		return instance;
	}
	
	public int getSubjectCount() {
		return (int)Subject.count();
	}
	
	public List<Subject> findAll() {
		return Subject.findAll();
	}
	
	public Subject findSubject(Long id) {
		return Subject.findById(id);
	}
	
	public SubjectListPageVO go2SubjectListPage() {
		List<Subject> subjects = Subject.all().fetch(1, ConstantUtil.perPage);
		int totalPage = PageUtil.getInstance().getTotalPage(getSubjectCount());
		return new SubjectListPageVO(subjects, totalPage, 1);
	}
	
	public Subject addSubject(String title, String remark, Integer status) {
		Subject subject = new Subject(title, remark, status);
		subject.save();
		return subject;
	}
	
	public Subject editSubject(Long id, String title, String remark, Integer status) {
		Subject subject = Subject.findById(id);
		subject.setTitle(title);
		subject.setRemark(remark);
		subject.setStatus(status);
		subject.save();
		return subject;
	}
	
	public Subject deleteSubject(Long id) {
		Subject subject = Subject.findById(id);
		subject.delete();
		return subject;
	}
	
	public int getTotalPage(Integer subejctCount) {
		return PageUtil.getInstance().getTotalPage(subejctCount);
	}
	
	public SubjectListPageVO selectPage(Integer selectPage)	{
		List<Subject> list = Subject.all().fetch(selectPage, ConstantUtil.perPage);
		int totalPage = getTotalPage(getSubjectCount());
		return new SubjectListPageVO(list, totalPage, selectPage);
	}
	
	public SubjectListPageVO previousPage(Integer currentPage) {
		if (currentPage.equals(1)) {
			return selectPage(currentPage);
		}
		return selectPage(--currentPage);
	}
	
	public SubjectListPageVO nextPage(Integer currentPage) {
		if (currentPage.equals(getTotalPage(getSubjectCount()))) {
			return selectPage(currentPage);
		}
		return selectPage(++currentPage);
	}
}
