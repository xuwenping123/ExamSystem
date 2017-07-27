package util;

import java.util.List;
import play.db.jpa.Model;

/**
 * 
  * @ClassName: PageUtil
  * @Description: 分页工具类
  * 页数从1 开始
  * @author:xuwenping
  * @date: 2017年7月27日
 */
public class PageUtil {

	private static PageUtil instance = new PageUtil();
	
	public static PageUtil getInstance() {
		return instance;
	}
	
	public int getTotalPage(Integer totalCount) {
		int temp = totalCount / ConstantUtil.perPage;
		if (totalCount % ConstantUtil.perPage == 0) {
			return temp;
		}
		return ++temp;
	}
	
	public List<? extends Model> previousPage(Integer currentPage, Class<? extends Model> clazz) {
		if (currentPage.equals(1)) {
			return selectPage(currentPage, clazz);
		}
		return selectPage(--currentPage, clazz);
	}
	
	public List<? extends Model> nextPage(Integer currentPage, Class<? extends Model> clazz, Integer totalCount) {
		if (currentPage.equals(getTotalPage(totalCount))) {
			return selectPage(currentPage, clazz);
		}
		return selectPage(++currentPage, clazz);
	}
	
	public List<? extends Model> selectPage(Integer page, Class<? extends Model> clazz) {
		Model model = null;
		List<? extends Model> list = null; 
		try {
			model = clazz.newInstance();
			list = model.all().fetch(page, ConstantUtil.perPage);
			return list;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
}
