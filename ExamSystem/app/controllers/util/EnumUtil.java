package controllers.util;

public class EnumUtil {

	/**
	 * 试题类型
	 * @author think
	 *
	 */
	public enum questionType {
		/** 单选题 */
		singleQuestion,
		/** 多选题 */
		multiQuestion,
		/** 填空题 */
		blankFillQuestion,
		/** 问答题 */
		wenDaQuestion,
	}
	
	/**
	 * 科目状态
	 * @author think
	 *
	 */
	public enum subjectStatusType {
		/** 科目启用 0*/
		subjectOpen,
		/** 科目禁用  1*/
		subjectClose
	}
	
	/**
	 * 考试记录表状态
	 * @author think
	 *
	 */
	public enum testRecordType {
		/** 考试启用 0 */
		testRecordOpen,
		/** 考试禁用  1*/
		testRecordClose
	}
}
