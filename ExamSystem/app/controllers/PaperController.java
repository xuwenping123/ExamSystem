package controllers;

import java.util.List;

import models.Paper;
import play.mvc.Controller;

/**
 * 试卷类
 * @author think
 *
 */
public class PaperController extends Controller{

	/**
	public String remark;
	
	@Required
	public long subject_id;
	*/
	
	public static void testAddPaper() {
		Paper paper = new Paper("test add paper", 1);
		paper.save();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 添加paper http.post all
	 */
	public static void addPaper() {
		String remark = params.get("remark");
		long subject_id = Long.valueOf(params.get("subject_id"));
		Paper paper = new Paper(remark, subject_id);
		paper.save();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 删除paper http.get id
	 */
	public static void deletePaper(long id) {
		Paper paper = Paper.findById(id);
		paper.delete();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 修改试卷信息	http.post all
	 */
	public static void modifyPaper() {
		long id = Long.valueOf(params.get("id"));
		String remark = params.get("remark");
		long subject_id = Long.valueOf(params.get("subject_id"));
		Paper paper = Paper.findById(id);
		paper.setRemark(remark);
		paper.setSubject_id(subject_id);
		paper.save();
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 查询paper http.get id
	 * @param id
	 */
	public static void findPaperById(long id) {
		Paper paper = Paper.findById(id);
		renderArgs.put("paper", paper);
		render("paper/paper.html");
	}
	
	/**
	 * 获取全部	
	 */
	public static void findAll() {
		List<Paper> papers = Paper.findAll();
		renderArgs.put("papers", papers);
		render("paper/paper.html");
	}
}
