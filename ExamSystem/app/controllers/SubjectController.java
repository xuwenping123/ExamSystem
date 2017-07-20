package controllers;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import models.Subject;
import play.mvc.Controller;

/**
 * 科目管理
 * @author think
 *
 */
public class SubjectController extends Controller{

	/**
	 * 查看所有科目	无参数	http.get
	 */
	public static void showAllSubjects() {
		List<Subject> subjects = Subject.findAll();
		renderArgs.put("subjects", subjects);
		render("subject/subjectList.html");
	}
	
	/**
	 * 用于进入科目添加页面的跳转
	 */
	public static void go2AddSubjectPage() {
		renderArgs.put("pageTitle", "科目添加");
		render("subject/subject.html");
	}
	
	/**
	 * 添加科目  http.post	
	 */
	public static void addSubject(String title, String remark, Integer status) {
		Subject subject = new Subject(title, remark, status);
		subject.save();
		showAllSubjects();
	}
	
	/**
	 * 跳转至修改科目页面
	 * @param id
	 */
	public static void go2ModSubjectPage(Long id) {
		Subject subject = Subject.findById(id);
		renderArgs.put("pageTitle", "科目修改");
		renderArgs.put("subject", subject);
		render("subject/subject.html");
	}
	
	/**
	 * 管理科目	http.post
	 */
	public static void modifySubject(Long id, String title, String remark, Integer status) {
		Subject subject = Subject.findById(id);
		subject.setTitle(title);
		subject.setRemark(remark);
		subject.setStatus(status);
		subject.save();
		showAllSubjects();
	}
	
	/**
	 * 删除科目	http.get	id
	 */
	public static void deleteSubject(Long id) {
		Subject subject = Subject.findById(id);
		subject.delete();
		showAllSubjects();
	}
	
	/**
	 * 设置科目属性 启用 禁用	http.get id status
	 */
	public static void setSubjectStatus() {
		long id = Long.valueOf(params.get("id"));
		Subject subject = Subject.findById(id);
		String temp = params.get("status");
		if (temp == null) {
			render("errors/404.html");
		}
		int status = Integer.valueOf(temp);
		subject.setStatus(status);
		subject.save();
	}
}
