package controllers;

import java.util.List;

import models.user.Student;
import models.user.Teacher;
import play.cache.Cache;
import play.data.validation.Required;
import play.mvc.Controller;

public class TeacherController extends Controller {

    public static void index() {
        render();
    }

    //阅卷人个人资料页面（个人中心）
    public static void showTeacherDetail() {
    	Teacher teacher = Cache.get("Teacher", Teacher.class);
    	renderJSON(teacher);
    }
    
    /**
     * 阅卷人列表
     */ 
    public static void getAllTeacher() {
    	List<Teacher> allTeacherList = Teacher.findAll();
    	renderJSON(allTeacherList);
    }
    
    /**
     * 阅卷人查询
     */ 
    public static void getTeacher(String name) {
    	List<Teacher> teacherList = Teacher.find("byName", name).fetch();
    	renderJSON(teacherList);
    	//返回阅卷人列表
    	getAllTeacher();
    }
    /**
     * 新增阅卷人(注册)
     */
    public static void addTeacher(@Required(message="请填写姓名") String name, 
    		@Required(message="请填写年龄") int age,
    		@Required(message="请填写用户名") String username,
    		@Required(message="请填写密码") String password) {
    	Teacher teacher = new Teacher(name, age, username, password);
    	teacher.save();
    	//调用查询接口返回阅卷人列表
    	getTeacher(null);
    }
    /**
     * 阅卷人编辑
     */
    public static void modifyTeacher(@Required(message="请填写姓名") String name, 
    		@Required(message="请填写年龄") int age,
    		@Required(message="请填写用户名") String username,
    		@Required(message="请填写密码") String password) {
    	//暂时只修改查到的第一个名字的阅卷人
    	Teacher teacher = Teacher.find("byName", name).first();
    	teacher.name = name;
    	teacher.age = age;
    	teacher.username = username;
    	teacher.password = password;
    	teacher.save();
    	//调用查询接口返回阅卷人列表
    	getTeacher(null);
    }
    /**
     * 阅卷人删除
     */
    public static void deleteTeacher(@Required(message="请填写姓名") String name) {
    	//暂时只删除查到的第一个名字的阅卷人
    	Teacher teacher = Teacher.find("byName", name).first();
    	teacher.delete();
    	//调用查询接口返回阅卷人列表
    	getTeacher(null);
    }
    /**
     * 阅卷——稍后完善
     */
    
    /**
     * 查看阅卷历史列表
     */
//    public static void readPaperHistoryList() {
//    	Teacher teacher = Cache.get("Teacher", Teacher.class);
//    	List<TeacherPaperVo> teacherPaperVo = TeacherPaperVo.find("select new TeacherPaperVo(p.title,u.name) from t_testRecord r left join t_testDetail d on r.id = d.testRecord_id where teacher_id = ?",
//    			teacher.id).fetch();
//    	TestDetail.f
//    }
    
    /**
     * 查看阅卷历史详情
     */
    public static void readPaperHistoryDetail(@Required(message="请填写姓名") String name) {
    	//暂时只删除查到的第一个名字的阅卷人
    	Teacher teacher = Teacher.find("byName", name).first();
    	teacher.delete();
    	//调用查询接口返回阅卷人列表
    	getTeacher(null);
    }
    
    /**
     * 查看备此阅卷人阅卷的考生考试历史
     */
    
}
