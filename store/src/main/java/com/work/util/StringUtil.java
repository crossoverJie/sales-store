package com.work.util;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import sun.misc.BASE64Decoder;

public class StringUtil {	
	private final static String[] hexDigits = { 
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 
	 * @Description: 返回该流程状态
	 * @param @param state
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author crossoverJie
	 * @date 2016年4月18日  下午1:41:12
	 */
	public static String getState(String state){
		String status ="" ;
		if("0".equals(state)){
			status ="管理员处理中";
		}else if("1".equals(state)){
			status = "供应商报价中";
		}else if("2".equals(state)){
			status ="会员处理中";
		}else if("3".equals(state)){
			status ="供应商上架中";
		}else if("4".equals(state)){
			status ="会员拒绝报价";
		}else if("5".equals(state)){
			status ="已生成订单";
		}
		return status ;
	}
	
	
	/**
	 * 随机产生指定长度的字符串
	 */
	public static String getRandomString(int length)
	{
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i)
		{
			int number = random.nextInt(62);

			sb.append(str.charAt(number));
		}

		return sb.toString();
	}
	
	/**
	 * 判断输入的字符串参数是否为空。
	 * @param args 输入的字串
	 * @return true/false
	 */
	public static boolean isNullOrEmpty(String args) {
		if (args == null || args.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 空数组
	 * @param length
	 * @return
	 */
	public static String[] getEmtpyArray(int length){
		String[] temp = new String[length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = "";
		}
		return temp;
	}
	/**
	 * 判断输入的字符串参数是否为空。
	 * @param args 输入的字串
	 * @return true/false
	 */
	public static boolean isNullOrEmpty(Object args) {
		if (args == null || args.toString().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断输入的字符串参数是否为空或者是"null"字符,如果是,就返回target参数,如果不是,就返回source参数。
	 */
	public static String chanageNull(String source, String target) {
		if (source == null || source.length() == 0 || source.equalsIgnoreCase("null")) {
			return target;
		} else {
			return source;
		}
	}

	/**
	 * 过滤<, >,\n 字符的方法。
	 * @param input 需要过滤的字符
	 * @return 完成过滤以后的字符串
	
	public static String filterHtml(String input) {
		if (input == null) {
			return null;
		}
		if (input.length() == 0) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;");
		input = input.replaceAll("\"", "&quot;");
		return input.replaceAll("\n", "<br>");
	}
	 */
	
	/**
	 * 对企业标签逗号的大写替换成小写
	 */
	public static String filterTag(String input) {
		if (null == input) {
			return null;
		}
		if (input.length() == 0) {
			return input;
		}
		
		if(input.indexOf("，")>=0){
			return input.replaceAll("，", ",");
		}else{
			return input;
		}
	}
	
	// 字符编码处理
	public static String codeToString(String str)
	{  //处理中文字符串的函数

		String s=str;
		try{
			byte tempB[]=s.getBytes("ISO-8859-1");
			s=new String(tempB);
			return s;
		}catch(Exception e){
			return s;
		}
	}
	
	/**
	 * 将null的字符串对象转换成空
	 * @param value 转换字对象
	 * @return 转换后字符串
	 */
	public static String null2Empty(Object value){
		if (value == null) {
			return "";
		} else {
			return value.toString();
		}
	}
	
	/**
	 * 将逗号分隔的字符串转换成整型数组
	 * @param valus 字符串
	 * @return 整型数组
	 */
	public static Integer[] toIntList(String valus){
		Integer[] intList = null;
		
		try {
			String[] strList = valus.replace(" ", "").split(",");
			
			intList = new Integer[strList.length];

			for (int i = 0; i < strList.length; i++) {
				intList[i] = Integer.parseInt(strList[i]);
			}
		} catch (Exception e) {
		}
		
		return intList;
	}
	
	/**
	 * 将逗号分隔的字符串转换成长整型数组
	 * @param valus
	 * @return
	 */
	public static Long[] toLongList(String valus){
		Long[] longList = null;
		
		try {
			String[] strList = valus.replace(" ", "").split(",");
			
			longList = new Long[strList.length];

			for (int i = 0; i < strList.length; i++) {
				longList[i] = Long.parseLong(strList[i]);
			}
		} catch (Exception e) {
		}
		
		return longList;
	}
		
	/**
	 * md5加密
	 * @param origin 待加密字符串
	 * @return 加密后字符串
	 */
	public static String md5(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}

		return resultString;
	}
	
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;

		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 获取URL参数
	 * @param url URL
	 * @return URL的参数Map
	 */
	public static Map<String, String> getQueryMap(String url) {
		if (url.indexOf("?") < 0) {
			return null;
		}
		
		String queryString = url.substring(url.indexOf("?") + 1);
		String[] queryList = queryString.split("&");
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		for (int i = 0; i < queryList.length; i++) {
			String[] paramList = queryList[i].split("=");
			
			if (paramList.length > 1) {
				paramMap.put(paramList[0], paramList[1]);
			} else {
				paramMap.put(paramList[0], "");
			}
		}
		
		return paramMap;
	}
	
	/**
	 * 根据文件名取扩展名
	 * @param fileName 文件名
	 * @return 扩展名(.xxx)
	 */
	public static String getExtendName(String fileName){
		if(StringUtil.isNullOrEmpty(fileName) || fileName.lastIndexOf(".") == -1){
			return "";
		}
		
		int lastIndex = fileName.lastIndexOf(".");		
		String ext = fileName.substring(lastIndex);
		
		return ext;
	}
	
	/**
	 * 从文件路径中取文件名
	 * @param fullName 带路径的文件名
	 * @return 文件名
	 */
	public static String getFileName(String fullName){
		if(StringUtil.isNullOrEmpty(fullName) || fullName.lastIndexOf("/") == -1){
			return fullName;
		}
		
		int lastIndex = fullName.lastIndexOf("/");	
		String fileName = fullName.substring(lastIndex + 1);
		
		return fileName;
	}
	
	/**
	 * 截断字符（页面显示用）
	 * @param value 截断前字符
	 * @return 截断后字符
	 */
	public static String getShowTitle(String value) {
		return getShowTitle(value, 14);
	}
	
	/**
	 * 截断字符（页面显示用）
	 * @param value 截断前字符
	 * @param length 保留字符个数（汉字/双字节字符个数）
	 * @return 截断后字符
	 */
	public static String getShowTitle(String value, int length) {
		if (value.getBytes().length < length * 2) {
			return value;
		}

		int count = 0;
		String subString;
		StringBuffer newString = new StringBuffer();
		
		for (int i = 0; i < value.length(); i++) {
			subString = value.substring(i, i + 1);
			
			if (subString.getBytes().length < 2) {
				count++;
			} else {
				count += 2;
			}

			if (count > length * 2) {
				break;
			}
			
			newString.append(subString);
		}
		
		return newString.toString() + "...";
	}
	
	/**
	 * 字节长度转成兆
	 * @param bLength 字节长度
	 * @return 兆
	 */
	public static String bToMB(int bLength){
		return bLength/(1024*1024) + "M";
	}
	
	/**
	 * 字符串替换
	 * @param source 元字符串
	 * @param oldValue 旧字符串
	 * @param newValue 新字符串
	 * @return 替换后字符串
	 */
    public static String replaceAll(String source, String oldValue, String newValue) {
		StringBuffer buffer = new StringBuffer(source);

		int start = source.indexOf(oldValue);
		int end = start + oldValue.length();

		buffer.replace(start, end, newValue);

		return buffer.toString();
	}
    /**
     * 对字符串型的数值进行排序并去掉重复
     * @param str
     * @return
     */
    public static String[] sortCutRepeat(String[] str) {
    	List<String> strs = new ArrayList<String>();
    	for (int i = 0; i < str.length; i++) {
			strs.add(str[i]);
		}
    	Comparator<String> com = new Comparator<String>() {
			public int compare(String o1, String o2) {
				// 前面3个IF主要是判空的
				if (o1 == o2) {
					return 0;
				}
				if (o1 == null) {
					return 1;
				}
				if (o2 == null) {
					return -1;
				}
				int num1 = Integer.parseInt(o1);
				int num2 = Integer.parseInt(o2);
				return num1 - num2;
			}
		};
		Collections.sort(strs, com);
		List<String> temp = new ArrayList<String>();
		temp.add(strs.get(0));
		for (int i = 1; i < strs.size(); i++) {
			if (!strs.get(i).equals(strs.get(i-1))) {
				temp.add(strs.get(i));
			}
		}
		String[] tempStr = new String[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			tempStr[i] = temp.get(i);
		}
		return tempStr;
	}
    //List值为数字类的字符串数组时按照倒数第十位进行升序排序
    public static List<String[]> sortListStingArray(List<String[]> list) {
    	Comparator<String[]> com = new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				double num1 = Double.valueOf(o1[o1.length-10]==null?"0":o1[o1.length-10]);
				double num2 = Double.valueOf(o2[o2.length-10]==null?"0":o2[o2.length-10]);
				if (num2 > num1) {
					return 1;
				}else if (num2 < num1) {
					return -1;
				}else{
					return 0;
				}
			}
		};
		Collections.sort(list, com);
		return list;
    }
    //List值为数字类的字符串数组时按照倒数最后一位进行降序排序
    public static List<String[]> sortListStingArrayForSRF(List<String[]> list) {
    	Comparator<String[]> com = new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				double num1 = Double.valueOf(o1[o1.length-1]==null?"0":o1[o1.length-1]);
				double num2 = Double.valueOf(o2[o2.length-1]==null?"0":o2[o2.length-1]);
				if (num2 > num1) {
					return 1;
				}else if (num2 < num1) {
					return -1;
				}else{
					return 0;
				}
			}
		};
		Collections.sort(list, com);
		return list;
    }
    public static List<String[]> sortListStingArrayByASC(List<String[]> list) {
    	Comparator<String[]> com = new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				double num1 = Double.valueOf(o1[o1.length-1]==null?"0":o1[o1.length-1]);
				double num2 = Double.valueOf(o2[o2.length-1]==null?"0":o2[o2.length-1]);
				if (num2 < num1) {
					return 1;
				}else if (num2 > num1) {
					return -1;
				}else{
					return 0;
				}
			}
		};
		Collections.sort(list, com);
		return list;
    }
    public static List<String[]> sortListStingArrayByFirstDes(List<String[]> list) {
    	Comparator<String[]> com = new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				double num1 = Double.valueOf(o1[0]==null?"0":o1[0]);
				double num2 = Double.valueOf(o2[0]==null?"0":o2[0]);
				if (num2 < num1) {
					return 1;
				}else if (num2 > num1) {
					return -1;
				}else{
					return 0;
				}
			}
		};
		Collections.sort(list, com);
		return list;
    }
    
    /**
     * 将字符串数字转换成double数组
     * @param strs
     * @return
     */
    public static double[] strToDou(String [] strs){
    	double [] data=new double[strs.length];
    	if (strs!=null||strs.length>0) {
			for (int i = 0; i < strs.length; i++) {
				data[i]=Double.parseDouble(strs[i]);
			}
		}
    	
    	return data;
    }
    
    /**
	 * 字符串转换方法（把传入的字符串，转换成指定的字符串并返回）
	 * @param str
	 * @return  指定字符串(AA BB(aa bb))
	 */
	public static String changeStr(String str){
		String returnStr="";
		
		String [] ch_va=new String[2]; //存放中文
		String [] en_va=new String[2]; //存放英文
		if (!"".equals(str)||str!=null) {
			String [] changeOne=str.split("★");
			if (changeOne.length>1) {
				String [] changeTwo1=changeOne[0].replace(".", "☆").split("☆");
				String [] changeTwo2=changeOne[1].replace(".", "☆").split("☆");
				if (changeTwo1.length>1&&changeTwo2.length>1) {
					ch_va[0]=changeTwo1[0];
					ch_va[1]=changeTwo2[0];
					
					en_va[0]=changeTwo1[1];
					en_va[1]=changeTwo2[1];
				}else if (changeTwo1.length>1&&changeTwo2.length<=1) {
					ch_va[0]=changeTwo1[0];
					ch_va[1]=changeTwo2[0];
					
					en_va[0]=changeTwo1[1];
				}else if (changeTwo1.length<=1&&changeTwo2.length>1) {
					ch_va[0]=changeTwo1[0];
					ch_va[1]=changeTwo2[0];
					
					en_va[1]=changeTwo2[1];
				}else {
					ch_va[0]=changeTwo1[0];
					ch_va[1]=changeTwo2[0];
				}
				
				//构造字符串
				StringBuffer ch_sb=new StringBuffer();
				for (int i = 0; i < ch_va.length; i++) {
					ch_sb.append(ch_va[i]);
					if (i<ch_va.length-1) {
						ch_sb.append(" ");
					}
					
				}
				StringBuffer en_sb=new StringBuffer();
				en_sb.append("(");
				for (int i = 0; i < en_va.length; i++) {
					if (en_va[i]==null) {
						continue;
					}
					en_sb.append(en_va[i]);
					if (i<en_va.length-1) {
						en_sb.append(" ");
					}
				}
				en_sb.append(")");
				if (en_sb.toString().trim().length()==2) {
					returnStr=ch_sb.toString().trim();
				}else {
					returnStr=ch_sb.toString().trim()+en_sb.toString().trim();
				}
				
			}else {
				returnStr=str;
			}
		}
		return returnStr;
	}
	// 将 s 进行 BASE64 编码
	public static String getBASE64(String s) {
	if (s == null) return null;
	return (new sun.misc.BASE64Encoder()).encode(s.trim().getBytes() );
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
	if (s == null) return null;
	BASE64Decoder decoder = new BASE64Decoder();
	try {
	byte[] b = decoder.decodeBuffer(s);
	return new String(b);
	} catch (Exception e) {
	return null;
	}
	}
	public static String arrayChangeToStr(String str){
		String returnStr="";
		if (!StringUtil.isNullOrEmpty(str)) {
			String[] tempStr =str.split(",");
			for (int i = 0; i < tempStr.length; i++) {
				returnStr += StringUtil.changeStr(tempStr[i]);
				if (i != tempStr.length-1) {
					returnStr += ",";
				}
			}
		}
		return returnStr;
	}
	/*
	 * 整理数组，将为空的项去掉
	 */
	public static String[] arraySortOut(String[] old_strings){
		if(old_strings == null)
			return null;
		
		List<String> list = new ArrayList<String>();
		for (String string : old_strings) {
			if(StringUtils.isNotBlank(StringUtils.trimToEmpty(string))){
				list.add(string);
			}
		}		
		return list.toArray(new String[list.size()]);
	}
	
	 public static List<String[]> sortListStingArray(List<String[]> list,int compare) {
		 	Comparator<String[]> com = new Mycomparator(compare);
			Collections.sort(list, com);
			return list;
	 }
	
	
}

class Mycomparator implements Comparator<String[]>{
	 private int compare;
	 public Mycomparator(int compare){
		 this.compare = compare;
	 }
	public int compare(String[] o1, String[] o2) {
		double num1 = 0.0d;
		try {
			num1 = Double.valueOf(o1[compare]);
		} catch (Exception e) {			
		}
				
		double num2 = 0.0d;
		try {
			num2 = Double.valueOf(o2[compare]);
		} catch (Exception e) {			
		}
				
		if (num2 > num1) {
			return 1;
		}else if (num2 < num1) {
			return -1;
		}else{
			return 0;
		}
	}
	public int getCompare() {
		return compare;
	}
	public void setCompare(int compare) {
		this.compare = compare;
	}
	
	
}

