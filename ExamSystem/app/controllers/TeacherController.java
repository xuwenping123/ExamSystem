package controllers;

import java.util.List;
import models.user.Teacher;
import play.mvc.Controller;

public class TeacherController extends Controller{

	/**
	 * 进入编辑老师信息页面
	 * @param id
	 */
	public static void editTeacher(long id) {
		Teacher teacher = Teacher.findById(id);
		render("users/editTeacher.html", teacher);
	}
	
	/**
	 * 添加老师	http.post all
	 */
	public static void addTeacher(String name, int age, String username, String password) {
		Teacher teacher = new Teacher(name, age, username, password);
		teacher.save();
		renderArgs.put("teacher", teacher);
		render("teacher/teacher.html");
	}
	
	/**
	 * 删除teahcer http.get id
	 * @param id
	 */
	public static void deleteTeacher(long id) {
		Teacher teacher = Teacher.findById(id);
		teacher.delete();
		renderArgs.put("teacher", teacher);
		render("teacher/teacher.html");
	}
	
	/**
	 * 修改老师信息	http.post all
	 * @param id
	 * @param name
	 * @param age
	 * @param username
	 * @param password
	 */
	public static void modifyTeacher(long id, String name, int age, String username, String password) {
		Teacher teacher = Teacher.findById(id);
		teacher.setName(name);
		teacher.setAge(age);
		teacher.setUsername(username);
		teacher.setPassword(password);
		teacher.save();
		renderArgs.put("teacher", teacher);
		render("users/teacher.html");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 */
	public static void findTeacherById(long id) {
		Teacher teacher = Teacher.findById(id);
		renderArgs.put("teacher", teacher);
		render("teacher/teacher.html");
	}
	
}
