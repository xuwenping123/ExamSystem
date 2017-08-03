package controllers.teacher;

import java.util.List;

import models.Paper;
import play.mvc.Controller;
import service.teacher.paper.PaperManagerService;
import vo.teacher.paper.PaperListPageVO;

/**
 * 
  * @ClassName: PaperManagerController
  * @Description: 试卷控制类
  * @author:xuwenping
  * @date: 2017年7月31日
 */
public class PaperManagerController extends Controller {

	public static void go2PaperListPage() {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperListPageVOs", paperManagerService.go2PaperListPage());
		renderArgs.put("pageVO", paperManagerService.getPageVO(1));
		renderTemplate("teacherHome/paper/paperList.html");
	}
	
	public static void go2AddPaperPage() {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperPageVO", paperManagerService.go2AddPaperPage());
		renderTemplate("teacherHome/paper/paper.html");
	}
	
	public static void addPaper(String remark, Long subject_id, Long selectQuestions[],
			Integer question_scores[]) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		paperManagerService.addPaper(remark, subject_id, selectQuestions, question_scores);
		go2PaperListPage();
	}
	
	public static void go2ViewPaperPage(Long id) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperViewVO", paperManagerService.go2ViewPaperPage(id));
		renderArgs.put("pageTitle", "查看试卷");
		renderTemplate("/teacherHome/paper/viewPaper.html");
	}
	
	public static void go2EditPaperPage(Long id) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperPageVO", paperManagerService.go2EditPaperPage(id));
		renderArgs.put("pageTitle", "修改试卷");
		renderTemplate("/teacherHome/paper/paper.html");
	}
	
	public static void editPaper(Long id, String remark, Long subject_id, 
			Long selectQuestions[], Integer question_scores[]) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		paperManagerService.editPaper(id, remark, subject_id, selectQuestions, question_scores);
		go2PaperListPage();
	}
	
	public static void deletePaper(Long id) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		paperManagerService.deletePaper(id);
		go2PaperListPage();
	}
	
	/**
	 * 
	  * @Title: previousPage
	  * @Description: 分页，上一页
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param currentPage
	 */
	public static void previousPage(Integer currentPage) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperListPageVOs", paperManagerService.previousPage(currentPage));
		renderArgs.put("pageVO", paperManagerService.getPageVO(currentPage));
		renderTemplate("teacherHome/paper/paperList.html");
	}
	
	/**
	 * 
	  * @Title: nextPage
	  * @Description: 分页，下一页
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param currentPage
	 */
	public static void nextPage(Integer currentPage) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperListPageVOs", paperManagerService.nextPage(currentPage));
		renderArgs.put("pageVO", paperManagerService.getPageVO(currentPage));
		renderTemplate("teacherHome/paper/paperList.html");
	}
	
	/**
	 * 
	  * @Title: selectPage
	  * @Description: 分页，选择页
	  * @author: xuwenping
	  * @date: 2017年8月1日
	  * @param selectPage
	 */
	public static void selectPage(Integer selectPage) {
		PaperManagerService paperManagerService = PaperManagerService.getInstance();
		renderArgs.put("paperListPageVOs", paperManagerService.selectPage(selectPage));
		renderArgs.put("pageVO", paperManagerService.getPageVO(selectPage));
		renderTemplate("teacherHome/paper/paperList.html");
	}
}
