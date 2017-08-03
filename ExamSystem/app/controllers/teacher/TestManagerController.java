package controllers.teacher;

import play.mvc.Controller;
import service.teacher.test.TestManagerService;

/**
 * 
  * @ClassName: TestManagerController
  * @Description: 考试管理： 增加考试， 查看考试， 添加学生参加考试等
  * @author:xuwenping
  * @date: 2017年8月1日
 */
public class TestManagerController extends Controller {

	public static void go2TestListPage() {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("testListPageVOs", testManagerService.go2TestListPage());
		renderArgs.put("pageVO", testManagerService.getPageVO(1));
		renderTemplate("/teacherHome/test/testList.html");
	}
	
	public static void go2AddTestPage() {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("pageTitle", "添加考试");
		renderArgs.put("testPageVO", testManagerService.go2AddTestPage());
		renderTemplate("/teacherHome/test/test.html");
	}
	
	public static void addTest(String beginTime, String endTime, Integer status, String remark,
			long paper_id, Long teacher_id, Long studentIds[]) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		testManagerService.addTest(beginTime, endTime, status, remark, paper_id, teacher_id, studentIds);
		go2TestListPage();
	}
	
	public static void go2ViewTestPage(Long id) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("testViewVO", testManagerService.go2ViewTestPage(id));
		renderArgs.put("pageTitle", "查看考试");
		renderTemplate("/teacherHome/test/viewTest.html");
	}
	
	public static void go2EditTestPage(Long id) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("testPageVO", testManagerService.go2EditTestPage(id));
		renderArgs.put("pageTitle", "修改考试");
		renderTemplate("/teacherHome/test/test.html");
	}
	
	public static void editTest(Long id, String remark, String beginTime, String endTime, 
			Integer status, Long paper_id, Long teacher_id, Long studentIds[]) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		testManagerService.editTest(id, remark, beginTime, endTime, status, paper_id, teacher_id, studentIds);
		go2TestListPage();
	}
	
	public static void deleteTest(Long id) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		testManagerService.deleteTest(id);
		go2TestListPage();
	}
	
	public static void previousPage(Integer currentPage) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("testListPageVOs", testManagerService.previousPage(currentPage));
		renderArgs.put("pageVO", testManagerService.getPageVO(currentPage));
		renderTemplate("/teacherHome/test/testList.html");
	}

	public static void nextPage(Integer currentPage) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("testListPageVOs", testManagerService.nextPage(currentPage));
		renderArgs.put("pageVO", testManagerService.getPageVO(currentPage));
		renderTemplate("/teacherHome/test/testList.html");
	}
	
	public static void selectPage(Integer selectPage) {
		TestManagerService testManagerService = TestManagerService.getInstance();
		renderArgs.put("testListPageVOs", testManagerService.selectPage(selectPage));
		renderArgs.put("pageVO", testManagerService.getPageVO(selectPage));
		renderTemplate("/teacherHome/test/testList.html");
	}
}
