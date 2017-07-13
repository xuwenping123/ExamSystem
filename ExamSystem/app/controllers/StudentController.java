package controllers;

import java.util.List;

import models.TestDetail;
import models.TestRecord;
import models.user.Student;
import play.cache.Cache;
import play.data.validation.Required;
import play.mvc.Controller;

public class StudentController extends Controller {

    public static void index() {
        render();
    }
    
    /**
     * 考生列表
     */ 
    public static void getAllStudent() {
    	List<Student> allStudentList = Student.findAll();
    	renderJSON(allStudentList);
    }
    
    /**
     * 考生查询
     */ 
    public static void getStudent(String name) {
    	List<Student> studentList = Student.find("byName", name).fetch();
    	renderJSON(studentList);
    	//返回考生列表
    	getAllStudent();
    }
    
    
    /**
     * 新增考生（注册）
     */
    public static void addStudent(@Required(message="请填写姓名") String name, 
    		@Required(message="请填写年龄") Integer age,
    		@Required(message="请填写用户名") String username,
    		@Required(message="请填写密码") String password) {
    	Student student = new Student(name, age, username, password);
    	student.save();
    	//调用个人资料查询接口返回个人中心
    	getStudentDetail();
    }
    
    /**
     * 编辑考生
     */
    public static void modifyStudent(@Required(message="请填写姓名") String name, 
    		@Required(message="请填写年龄") Integer age,
    		@Required(message="请填写用户名") String username,
    		@Required(message="请填写密码") String password) {
    	//暂时只修改查到的第一个名字的学生
    	Student student = Student.find("byName", name).first();
//    	student.name = name;
//    	student.age = age;
//    	student.username = username;
    	student.password = password;
    	student.save();
    	//调用查询接口返回考生列表
    	getStudent(null);
    }
    
    /**
     * 删除考生
     */
    public static void deleteStudent(@Required(message="请填写姓名") String name) {
    	//暂时只删除查到的第一个名字的学生
    	Student student = Student.find("byName", name).first();
    	student.delete();
    	//调用查询接口返回考生列表
    	getStudent(null);
    }
    
    /**
     * 提交答题卡——稍后完善
     */
//    public static void postAnswerSheet(@Required long studentId, 
//    		@Required long paperId,
//    		@Required long testRecordId,
//    		@Required long questionId,
//    		@Required Map<String, Object> result) {
//    	Student student = new Student(name,age);
//    	student.save();
//    	//返回考生列表
//    	showStudent();
//    }
    
    /**
     * 考生个人资料页面（个人中心）
     */
    public static void getStudentDetail() {
    	//session中获取
    	Student student = Cache.get("student", Student.class);
    	renderJSON(student);
    }
    /**
     * 学生资料编辑
     */
    public static void modifyStudentDetail(String name, Integer age) {
    	//session中获取
    	Student student = Cache.get("student", Student.class);
    	student.name = name;
    	student.age = age;
    	student.save();
    	renderJSON(student);
    	//调用个人资料查询接口返回个人中心
    	getStudentDetail();
    }
    /**
     * 学生考试历史查看
     */
    public static void getStuTestHistory() {
    	//session中获取
    	Student student = Cache.get("student", Student.class);
    	List<TestDetail> testDetailList = TestDetail.find("byStudent_id", student.id).fetch();
    	
    	renderJSON(student);
    }
}
