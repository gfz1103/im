package com.buit.utill;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BytesUtil {

	/**
	 * 转字符集
	 * @Title: getBytesStrLength
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param validateStr
	 * @param @return    设定文件
	 * @return int    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public static int getBytesStrLength(String validateStr){
        String tempStr = "";
        try {
        	tempStr = new String(validateStr.getBytes("gb2312"),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return tempStr.length();
    }
	
	public static List<String> getListStringBytes(String str, int bytesLength) throws Exception{
		List<String> resList = new ArrayList<String>();
		while(StrUtil.isNotBlank(str)) {
			if(getBytesStrLength(str) > bytesLength) {
				String s = bytesSubstring(str, bytesLength);
				resList.add(s);
				str = str.substring(s.length(), str.length());
			}else {
				resList.add(str);
				str = "";
			}
		}
		return resList;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getBytesStrLength("[甲类][高危C]阿卡波糖胶囊/50mg/粒 100mg 口服 tid (餐时"));
	}
	
	
	
	public static String bytesSubstring(String str, int length) throws Exception{
        byte[] bytes = str.getBytes("Unicode");
        //表示当前的字节数
        int n = 0; 
        //要截取的字节数，从第3个字节开始
        int i = 2;
        for (; i < bytes.length && n < length; i++){
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
        	// 在UCS2第二个字节时n加1
            if (i % 2 == 1){
                n++; 
            }else{
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                if (bytes[i] != 0){
                    n++;
                }
            }
        }
        // 如果i为奇数时，处理成偶数
        if (i % 2 == 1){
            //该UCS2字符是汉字时，去掉这个截一半的汉字
            if (bytes[i - 1] != 0) {
                i = i - 1;
            }else {
            	//该UCS2字符是字母或数字，则保留该字符
                i = i + 1;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }
	
	

}
