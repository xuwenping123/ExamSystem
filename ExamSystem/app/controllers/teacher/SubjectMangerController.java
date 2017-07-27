package controllers.teacher;

import java.util.List;
import models.Subject;
import play.mvc.Controller;
import service.teacher.subject.SubjectManagerService;
import vo.teacher.subject.SubjectListPageVO;

/**
 * 
  * @ClassName: SubjectMangerController
  * @Description: 教师主页下的科目管理
  * @author:xuwenping
  * @date: 2017年7月27日
 */
public class SubjectMangerController extends Controller {
	
	/**
	 * 
	  * @Title: go2SubjectListPage
	  * @Description: 进入科目列表页面
	  * @author: xuwenping
	  * @date: 2017年7月27日
	 */
	public static void go2SubjectListPage() {
		SubjectListPageVO subjectListPageVO = SubjectManagerService.getInstance().go2SubjectListPage();
		renderArgs.put("subjectListPageVO", subjectListPageVO);
		renderTemplate("teacherHome/subject/subjectList.html");
	}
	
	/**
	 * 
	  * @Title: go2AddSubjectPage
	  * @Description: 跳转至添加科目页面
	  * @author: xuwenping
	  * @date: 2017年7月27日
	 */
	public static void go2AddSubjectPage() {
		renderArgs.put("pageTitle", "科目添加");
		renderTemplate("teacherHome/subject/subject.html");
	}
	
	/**
	 * 
	  * @Title: addSubject
	  * @Description: 进行科目添加
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param title
	  * @param remark
	  * @param status
	 */
	public static void addSubject(String title, String remark, Integer status) {
		SubjectManagerService.getInstance().addSubject(title, remark, status);
		go2SubjectListPage();
	}
	
	/**
	  * @Title: go2EditSubjectPage
	  * @Description: 进入编辑科目页面
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param id
	 */
	public static void go2EditSubjectPage(Long id) {
		renderArgs.put("subject", SubjectManagerService.getInstance().findSubject(id));
		renderTemplate("teacherHome/subject/subject.html");
	}
	
	/**
	 * 
	  * @Title: editSubject
	  * @Description: 修改科目进行提交
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param id
	  * @param title
	  * @param remark
	  * @param status
	 */
	public static void editSubject(Long id, String title, String remark, Integer status) {
		SubjectManagerService.getInstance().editSubject(id, title, remark, status);
		go2SubjectListPage();
	}
	
	/**
	 * 
	  * @Title: deleteSubject
	  * @Description: 删除科目
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param id
	 */
	public static void deleteSubject(Long id) {
		SubjectManagerService.getInstance().deleteSubject(id);
		go2SubjectListPage();
	}
	
	/**
	 * 
	  * @Title: previousPage
	  * @Description: 分页 上一页
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param currentPage
	 */
	public static void previousPage(Integer currentPage) {
		SubjectListPageVO subjectListPageVO = SubjectManagerService.getInstance().previousPage(currentPage);
		renderArgs.put("subjectListPageVO", subjectListPageVO);
		renderTemplate("teacherHome/subject/subjectList.html");
	}
	
	/**
	 * 
	  * @Title: nextPage
	  * @Description: 分页 下一页
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param currentPage
	 */
	public static void nextPage(Integer currentPage) {
		SubjectListPageVO subjectListPageVO = SubjectManagerService.getInstance().nextPage(currentPage);
		renderArgs.put("subjectListPageVO", subjectListPageVO);
		renderTemplate("teacherHome/subject/subjectList.html");
	}
	
	/**
	 * 
	  * @Title: selectPage
	  * @Description: 分页，选择页数
	  * @author: xuwenping
	  * @date: 2017年7月27日
	  * @param selectPage
	 */
	public static void selectPage(Integer selectPage)	{
		SubjectListPageVO subjectListPageVO = SubjectManagerService.getInstance().selectPage(selectPage);
		renderArgs.put("subjectListPageVO", subjectListPageVO);
		renderTemplate("teacherHome/subject/subjectList.html");
	}
}
