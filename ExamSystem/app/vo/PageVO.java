package vo;

import java.util.List;

public class  PageVO {

	private int totalPage;
	
	private int currentPage;
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public PageVO(int totalPage, int currentPage) {
		this.totalPage = totalPage;
		this.currentPage = currentPage;
	}

}
