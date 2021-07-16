package com.buit.utill;

/**
 * 
 * @ClassName: DigitalConversionUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2021年6月4日 上午10:07:24
 *
 */
public class DigitalConversionUtil {

	private static String nums[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

	private static String pos_units[] = {"", "十", "百", "千"};

	private static String weight_units[] = {"", "万", "亿"};

	/**
	* 数字转汉字【新】
	*
	* @param num
	* @return
	*/
	public static String numberToChinese(int num) {
		if (num == 0) {
			return "零";
		}
	
		int weigth = 0;//节权位
		String chinese = "";
		String chineseSection = "";
		boolean setZero = false;//下一小节是否需要零，第一次没有上一小节所以为false
		while (num > 0) {
			int section = num % 10000;//得到最后面的小节
			if (setZero) {//判断上一小节的千位是否为零，是就设置零
				chinese = nums[0] + chinese;
			}
			chineseSection = sectionTrans(section);
			if (section != 0) {//判断是都加节权位
				chineseSection = chineseSection + weight_units[weigth];
			}
			chinese = chineseSection + chinese;
			chineseSection = "";
		
			setZero = (section < 1000) && (section > 0);
			num = num / 10000;
			weigth++;
		}
		boolean flag = (chinese.length() == 2 || (chinese.length() == 3)) && chinese.contains("一十");
		if (flag) {
			chinese = chinese.substring(1, chinese.length());
		}
	
		if (chinese.indexOf("一十") == 0) {
			chinese = chinese.replaceFirst("一十", "十");
		}
		return chinese;
	}

	/**
	* 将每段数字转汉子
	*
	* @param section
	* @return
	*/
	public static String sectionTrans(int section) {
		StringBuilder sectionChinese = new StringBuilder();
		int pos = 0;//小节内部权位的计数器
		boolean zero = true;//小节内部的置零判断，每一个小节只能有一个零。
		while (section > 0) {
			int v = section % 10;//得到最后一个数
			if (v == 0) {
				if (!zero) {
					zero = true;//需要补零的操作，确保对连续多个零只是输出一个
					sectionChinese.insert(0, nums[0]);
				}
			} else {
				zero = false;//有非零数字就把置
				sectionChinese.insert(0, pos_units[pos]);
				sectionChinese.insert(0, nums[v]);
			}
			pos++;
			section = section / 10;
		}
		return sectionChinese.toString();
	}
	
	/**
	 * 字符串换行
	 * @Title: wrapStringLoop
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param str
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public  static String wrapStringLoop(String str) {
		StringBuffer resStr = new StringBuffer();
		if(StrUtil.isNotBlank(str)) {
			for(int i = 0; i < str.length(); i++) {
				resStr.append(str.charAt(i)).append("\n");
			}
		}
		return resStr.toString();
	}

}
