package controllers;

import java.util.List;
import models.Subject;
import play.mvc.Controller;

/**
 * 科目管理
 * @author think
 *
 */
public class SubjectController extends Controller{

	/**
	 * 测试方法	用于添加科目
	 */
	public static void testAddSubject() {
		Subject subject = new Subject("语文", 1);
		subject.save();
		subject = new Subject("数学", 1);
		subject.save();
		subject = new Subject("英语", 1);
		subject.save();
	}
	
	/**
	 * 查看所有科目	无参数	http.get
	 */
	public static void showAllSubjects() {
		List<Subject> subjects = Subject.findAll();
		renderArgs.put("subjects", subjects);
		render();
	}
	
	/**
	 * 添加科目  http.post	
	 */
	public static void addSubject() {
		String title = params.get("title");
		String remark = params.get("remark");
		String temp = params.get("status");
		int status = Integer.valueOf(temp != null ? temp : "1");
		Subject subject = new Subject(title, remark, status);
		subject.save();
		render("OK.html");
	}
	
	/**
	 * 管理科目	http.post
	 */
	public static void modifySubject() {
		long id = Long.valueOf(params.get("id"));
		String title = params.get("title");
		String remark = params.get("remark");
		String temp = params.get("status");
		int status = Integer.valueOf(temp != null ? temp : "1");
		Subject subject = Subject.findById(id);
		subject.setTitle(title);
		subject.setRemark(remark);
		subject.setStatus(status);
		subject.save();
		render("OK.html");
	}
	
	/**
	 * 删除科目	http.get	id
	 */
	public static void deleteSubject() {
		long id = Long.valueOf(params.get("id"));
		Subject subject = Subject.findById(id);
		subject.delete();
		render("OK.html");
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
		render("OK.html");
	}
}
