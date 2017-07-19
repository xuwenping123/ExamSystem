package service;

import models.Paper;

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
}
