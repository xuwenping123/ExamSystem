package controllers.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组处理工具类
 * 
 * @author think
 *
 */
public class ArrayUtil {
	private final static ArrayUtil instance = new ArrayUtil();
	
	public static ArrayUtil getInstance() {
		return instance;
	}
	
	/**
	 * 将传入的 <i>array</i> 剔除 null， 转换成list 返回
	 * @param array
	 * @return
	 */
	public <E>List trimNull(E array[]) {
		List<E> list = new LinkedList<E>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				list.add(array[i]);
			}
		}
		return list;
	}
}

