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
	
	public enum subjectStatusType {
		/** 科目启用*/
		subjectOpen,
		/** 科目禁用 */
		subjectClose
	}
}
