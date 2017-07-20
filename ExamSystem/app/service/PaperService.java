package service;

import java.util.ArrayList;
import java.util.List;

import models.Paper;
import models.relationship.QuestionPaperRS;
import play.Logger;

public class PaperService {

	private final static PaperService instance = new PaperService();
	
	public static PaperService getInstance() {
		return instance;
	}
	
	/**
	 * 保存
	 * @param id
	 * @param remark
	 * @param subject_id
	 */
	public void modPaper(long id, String remark, long subject_id) {
		Paper paper = Paper.findById(id);
		paper.setRemark(remark);
		paper.setSubject_id(subject_id);
		paper.save();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void deletePaper(long id) {
		Paper paper = Paper.findById(id);
		paper.delete();
	}
	
	/**
	 * 通过 试卷id 获取一张试卷的满分
	 * @param paper_id
	 * @return
	 */
	public int getTotalScore(Long paper_id) {
		String query = "select sum(question_score) from QuestionPaperRS where paper_id = ?";
		long totalScore = (long)QuestionPaperRS.find(query, paper_id).fetch().get(0);
		Logger.info("试卷总分：%s\t 试卷Id: %s", totalScore, paper_id);
		return 0;
	}
}
