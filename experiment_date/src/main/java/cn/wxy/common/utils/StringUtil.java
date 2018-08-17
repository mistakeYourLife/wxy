package cn.wxy.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
	/**
	 * 空字符
	 */
	public static final String EMPTY = "";
	/**
	 * 默认分隔符","
	 */
	public static final String DEFAULT_SEPARATOR = ",";
	/**
	 * 竖线 "|"
	 */
	public static final String VERTICAL_BAR = "|";
	
	/**
	 *  "^"
	 */
	public static final String CARET = "^";
	/**
	 *  "~"
	 */
	public static final String  TILDE_SYMBOL = "~";
	/**
	 *  "!"
	 */
	public static final String EXCLAMATION_MARK = "!";
	/**
	 *  "@"
	 */
	public static final String AT_SIGN = "@";
	/**
	 *  "#"
	 */
	public static final String NUMBER_SIGN = "#";
	/**
	 *  "$"
	 */
	public static final String DOLLAR_SIGN = "$";
	/**
	 *  "%"
	 */
	public static final String PERCENT_SIGN = "%";
	/**
	 *  "&"
	 */
	public static final String AMPERSAND = "&";
	/**
	 *  "*"
	 */
	public static final String ASTERISK = "*";
	/**
	 *  "("
	 */
	public static final String LEFT_BRACKET = "(";
	/**
	 *  ")"
	 */
	public static final String RIGHT_BRACKET = ")";
	/**
	 *  "_"
	 */
	public static final String UNDERLINE  = "_";
	/**
	 *  "+"
	 */
	public static final String PLUS_SIGN = "+";
	/**
	 *  "-"
	 */
	public static final String MINUS_SIGN = "-";
	/**
	 *  "\\"
	 */
	public static final String ESCAPE = "\\";
	
	/**
	 * 过滤所有以<" 开头以" >结尾的标签
	 */
	private final static String regxpForHtml = "<([^>]*)>";   

	/***
	 * 字符串是否相同，如果都为<code>Null</code> 返回true
	 * 
	 * @param
	 *            obj1
	 * @param
	 *            obj2
	 * @return 相同则返回true，否则返回false
	 */
	public static boolean equals(String obj1, String obj2) {
		if (obj1 == null) {
			return obj2 == null;
		}

		return obj1.equals(obj2);
	}

	/***
	 * 字符串是否相同,不考虑大小写
	 * 
	 * @param obj1
	 *            字符串1
	 * @param obj2
	 *            字符串2
	 * @param checkNull
	 *            是否判断Null值。如果为true, 则只要其中一个对象为Null，则返回false。
	 * @return 相同则返回true，否则返回false
	 */
	public static boolean equalsIgnoreCases(String obj1, String obj2, boolean checkNull) {
		if ((obj1 == null || obj2 == null) && checkNull) {
			return false;
		} else {
			return equalsIgnoreCase(obj1, obj2);
		}

	}
	/*****
	 * 字符串是否相同,不考虑大小写
	 * @param obj1
	 * @param obj2
	 * @return
	 */	
	public static boolean equalsIgnoreCase(String obj1, String obj2) {
		if (obj1 == null) {
			return obj2 == null;
		}

		return obj1.equalsIgnoreCase(obj2);
	}
	/***
	 * 字符串是否相同
	 * 
	 * @param obj1
	 *            字符串1
	 * @param obj2
	 *            字符串2
	 * @param checkNull
	 *            是否判断Null值。如果为true, 则只要其中一个对象为Null，则返回false。
	 * @return 相同则返回true，否则返回false
	 */
	public static boolean equals(String obj1, String obj2, boolean checkNull) {
		if ((obj1 == null || obj2 == null) && checkNull) {
			return false;
		} else {
			return equals(obj1, obj2);
		}

	}

	/**
	 * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = false
	 * StringUtil.isBlank("")        = false
	 * StringUtil.isBlank(" ")       = false
	 * StringUtil.isBlank("bob")     = true
	 * StringUtil.isBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 将整数数组按照分隔符拼接成字符串
	 * 
	 * @param array
	 *            int数组
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static String join(int[] array, String separator) {
		String result = null;
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}
		result = Arrays.toString(array);
		result = result.replaceAll(",", separator);
		result = result.substring(1, result.length() - 1);
		return result;
	}

	/**
	 * 将长整数数组按照分隔符拼接成字符串
	 * 
	 * @param array
	 *            int数组
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static String join(long[] array, String separator) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = DEFAULT_SEPARATOR;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i != array.length - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否只包含unicode数字。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNumeric(null)   = false
	 * StringUtil.isNumeric("")     = true
	 * StringUtil.isNumeric("  ")   = false
	 * StringUtil.isNumeric("123")  = true
	 * StringUtil.isNumeric("12 3") = false
	 * StringUtil.isNumeric("ab2c") = false
	 * StringUtil.isNumeric("12-3") = false
	 * StringUtil.isNumeric("12.3") = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 如果字符串是<code>null</code>，则返回空字符串<code>""</code>，否则返回字符串本身。
	 * 
	 * <pre>
	 * StringUtil.defaultIfNull(null)  = ""
	 * StringUtil.defaultIfNull("")    = ""
	 * StringUtil.defaultIfNull("  ")  = "  "
	 * StringUtil.defaultIfNull("bat") = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 字符串本身或空字符串<code>""</code>
	 */
	public static String defaultIfNull(String str) {
		return (str == null) ? EMPTY : str;
	}

	/**
	 * 如果字符串是<code>null</code>，则返回指定默认字符串，否则返回字符串本身。
	 * 
	 * <pre>
	 * StringUtil.defaultIfNull(null, "default")  = "default"
	 * StringUtil.defaultIfNull("", "default")    = ""
	 * StringUtil.defaultIfNull("  ", "default")  = "  "
	 * StringUtil.defaultIfNull("bat", "default") = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param defaultStr
	 *            默认字符串
	 * 
	 * @return 字符串本身或指定的默认字符串
	 */
	public static String defaultIfNull(String str, String defaultStr) {
		return (str == null) ? defaultStr : str;
	}

	/**
	 * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回空字符串<code>""</code>
	 * ，否则返回字符串本身。
	 * 
	 * <p>
	 * 此方法实际上和<code>defaultIfNull(String)</code>等效。
	 * 
	 * <pre>
	 * StringUtil.defaultIfEmpty(null)  = ""
	 * StringUtil.defaultIfEmpty("")    = ""
	 * StringUtil.defaultIfEmpty("  ")  = "  "
	 * StringUtil.defaultIfEmpty("bat") = "bat"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 字符串本身或空字符串<code>""</code>
	 */
	public static String defaultIfEmpty(String str) {
		return (str == null) ? EMPTY : str;
	}

	/***
	 * 判断两个<link>String</link> 是否相等。 如果str1或者str2中有任意一个为<tt>null</tt>, 则返回false。
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * @return true, 如果两字符串相等 ; 否则返回false.
	 */
	public static boolean isEquals(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		} else {
			return str1.equals(str2);
		}

	}

	public static String encodeISO(String str) {
		String temp = "";
		if (isNotBlank(str)) {
			try {
				temp = URLEncoder.encode(str, "iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}

	public static String encodeUTF(String str) {
		String temp = "";
		if (isNotBlank(str)) {
			try {
				temp = URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) { 
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

		
//	public static void main(String[] args) {
//		// System.out.println(getControllerNameByURL("/metererp/menus/create"));
//		//System.out.println(getCamelCaseString("T_ID", false));
//		
//		System.out.println( StringUtil.maskString("13528415134", "*", 3, 4));
//	}

	public static String getTableColumName(String inputString) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append("_");
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(Character.toLowerCase(c));
			}

		}
		return sb.toString();
	}

	public static String getCamelCaseString(String inputString,
			boolean firstCharacterUppercase) {
		StringBuilder sb = new StringBuilder();

		boolean nextUpperCase = false;
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);

			switch (c) {
			case '_':
			case '-':
			case '@':
			case '$':
			case '#':
			case ' ':
			case '/':
			case '&':
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
				break;

			default:
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
				break;
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}
	/** 把参数 str 转换为安全字符串：如果 str = null，则把它转换为空字符串 */
	public final static String safeString(String str) {
		if (str == null)
			str = "";

		return str;
	}
	
	/**
	 * 用maskStr替换target字符，若传入的last为正数，则保留last位数字符不被替换，反之，则保留最后last位数的字符不被替换
	 * 
	 * @param target
	 * @param maskStr
	 * @return
	 */
	public static String maskMidString(String target, String maskStr, int front, int last) {
		if (target == null)
			return null;
		int length = target.length() - Math.abs(front) - Math.abs(last);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(maskStr);
		}
		sb.insert(0, StringUtils.substring(target, 0, front));
		if (length > 0) {
			sb.append(StringUtils.substring(target, target.length() - Math.abs(last),
					target.length()));
		}
		return sb.toString();
	}

	/**
	 * 用maskStr替换target字符，若传入的last为正数，则保留last位数字符不被替换，反之，则保留最后last位数的字符不被替换
	 * 
	 * @param target
	 * @param maskStr
	 * @return
	 */
	public static String maskString(String target, String maskStr, int last) {
		if (target == null)
			return null;
		int length = target.length() - Math.abs(last);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(maskStr);
		}
		if (last > 0) {
			sb.insert(0, StringUtils.substring(target, 0, last));
		} else {
			sb.append(StringUtils.substring(target, last));
		}
		return sb.toString();
	}

	/**
	 * 用maskStr替换target字符，传入的front为保留的前几位，last为保留的最后几位，front和last应该大于0，否则取其绝对值
	 * 
	 * @param target
	 * @param maskStr
	 * @param front
	 * @param last
	 * @return
	 */
	public static String maskString(String target, String maskStr, int front, int last) {
		if (target == null)
			return null;
		int length = target.length() - Math.abs(front) - Math.abs(last);
		int flength = target.length() - Math.abs(front);
		int nlength = length > 0 ? length : flength;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nlength; i++) {
			sb.append(maskStr);
		}
		sb.insert(0, StringUtils.substring(target, 0, front));
		if (length > 0) {
			sb.append(StringUtils.substring(target, target.length() - Math.abs(last),
					target.length()));
		}
		return sb.toString();
	}
	/**
	 * 掩码，手机号码
	 * @param target
	 * @return
	 */
	public static String maskMobile(Long target){
		if (target == null )
			return "";
		return maskMidString(target.toString(), StringUtil.ASTERISK, 3, 4);
	}
	
	/**
	 * 掩码，手机号码
	 * @param target
	 * @return
	 */
	public static String maskMobileCanNull(Long target){
		if (target == null )
			return "";
		return maskMidString(target.toString(), StringUtil.ASTERISK, 3, 4);
	}
	/**
	 * 掩码，手机号码
	 * @param target
	 * @return
	 */
	public static String maskMobileCanNull(String  target){
		if (target == null )
			return "";
		return maskMidString(target, StringUtil.ASTERISK, 3, 4);
	}

	/**
	 * 掩码，固话号码  例如：0571-12****12
	 * @param target
	 * @return
	 */
	public static String maskTelephoneCanNull(String  target){
		if (target == null )
			return "";
		int first =target.indexOf("-")+3;
		return maskMidString(target, StringUtil.ASTERISK, first, 2);
	}




	
	/**
	 * 处理后的身份证号 例如：330621******2212
	 * 
	 * @return
	 */
	public static String maskIdentityNumber(String identityNumber) {
		return StringUtil.maskString(identityNumber, StringUtil.ASTERISK, 6, 4,
				6);
	}
	/**
	 *  掩码 银行卡号
	 * @param bankNumber
	 * @return
	 * author: pengyong
	 * 下午12:02:35
	 */
	public static String  maskBankCodeNumber(String bankNumber){		
		return  StringUtil.maskString(bankNumber,  StringUtil.ASTERISK, 5, 4, 4);
	}
	
	/**
	 * 
	 * @Description:掩码银行卡号  ********************1234
	 * @param bankNumber
	 * @return
	 * @author: chaisen
	 * @time:2016年8月16日 下午4:59:24
	 */
	public static String  maskBankCodeNumberEnd(String bankNumber){		
		return  StringUtil.maskString(bankNumber,  StringUtil.ASTERISK, 0, 4);
	}
	
	/**
	 *  掩码 银行卡号  加入空字符
	 * @param bankNumber
	 * @return
	 * author: pengyong
	 * 下午12:02:35
	 */
	public static String  maskBankCodeNumberWithBlank(String bankNumber){	
		String maskString = StringUtil.maskStrWithBlankFront(bankNumber, StringUtil.ASTERISK,4, 4);				
		return  maskString;
	}
	
	/**
	 * 带空格的mask
	 *  demo 8912 **** **** **** **** 
	 * @param target
	 * @param maskStr
	 * @param blankPerChar
	 * @param front
	 * @return
	 */
	public static String maskStrWithBlank(String target, String maskStr, int blankPerChar, int front) {
		if (target == null)
			return null;
		String maskResult = maskString(target, maskStr, front);
		char[] chars = maskResult.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			if (i % blankPerChar == 0) {
				sb.append(" ");
			}
			sb.append(chars[i]);
		}
		return sb.toString();
	}
	/**
	 * 带空格的mask   
	 * demo **** **** **** **** 8912
	 * @param target
	 * @param maskStr
	 * @param blankPerChar
	 * @param last
	 * @return
	 * author: pengyong
	 * 下午1:34:35
	 * 
	 */
	public static String maskStrWithBlankFront(String target, String maskStr, int blankPerChar, int last) {
		if (target == null)
			return null;
		String maskResult = StringUtil.maskString(target, maskStr,0, last);
		char[] chars = maskResult.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			if (i % blankPerChar == 0) {
				sb.append(" ");
			}
			sb.append(chars[i]);
		}
		return sb.toString();
	}

	/**
	 * 用maskStr替换target字符，传入的front为保留的前几位，last为保留的最后几位，front和last应该大于0，否则取其绝对值
	 * 
	 * @param target
	 * @param maskStr
	 * @param front
	 * @param last
	 * @param count
	 *            中间被替换字符的个数
	 * @return
	 */
	public static String maskString(String target, String maskStr, int front, int last, int count) {
		if (target == null)
			return null;
		int length = target.length() - Math.abs(front) - Math.abs(last);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(maskStr);
		}
		sb.insert(0, StringUtils.substring(target, 0, front));
		if (length > 0) {
			sb.append(StringUtils.substring(target, target.length() - Math.abs(last),
					target.length()));
		}
		return sb.toString();
	}
	/**
	 *  掩码 真实姓名
	 * @param trueName
	 * @return
	 */
	public static String maskTrueName(String trueName) {		
		if (isNotBlank(trueName)) {
			char[] chars = trueName.toCharArray();
			StringBuffer s = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
				if (i == 0) {
					s.append(chars[i]);
				} else {
					s.append(ASTERISK);
				}

			}
			return s.toString();
		}
		return "";
	}

	/**
	 *  掩码 真实姓名
	 * @param trueName
	 * @return
	 */
	public static String maskFirstName(String trueName) {
		if (isNotBlank(trueName)) {
			char[] chars = trueName.toCharArray();
			StringBuffer s = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
				if (i == 0) {
					s.append(ASTERISK);
				} else {
					s.append(chars[i]);
				}

			}
			return s.toString();
		}
		return "";
	}

	/**
	 * 获得文件路径
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public static String getFilePath(String path, String type){
		if(isBlank(path)){
			return "";
		}
		if(isBlank(type) || type.equals("default")){
			return path;
		}
		StringBuffer sb = new StringBuffer(path);
		sb.insert(path.lastIndexOf("."), "_"+type);
		return sb.toString();
	}
	
	/**
	 * 获得头像路径
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public static String getAvatarPath(String path, String type){
		if(isBlank(path)){
			return "";
		}
		//最开始图片默认尺寸是70x70，故现在要对该尺寸特殊处理，为了兼容历史数据
		if(isBlank(type) || type.equals("default") || type.equals("70x70")){
			try{
				int index = path.indexOf("avatar");
				String uploadDate = path.substring(index-11,index-1);
				if(StringUtil.isNotBlank(uploadDate)){
					//图片大小新规则，为历史图片特殊处理
					Date ud = DateUtils.getDateFromString(uploadDate, "yyyy/MM/dd");
					Date d = DateUtils.getDateFromString("2015/07/25","yyyy/MM/dd");
					if(ud.getTime() >= d.getTime()){
						if(isNotBlank(type) && type.equals("70x70")){
							StringBuffer sb = new StringBuffer(path);
							sb.insert(path.lastIndexOf("."), "_70x70");
							return sb.toString();
						}
						return path;//WEB2 图片默认为210*210
					}
				}
			}catch(Exception ex){
				
			}
			return path;
		}
		StringBuffer sb = new StringBuffer(path);
		sb.insert(path.lastIndexOf("."), "_"+type);
		return sb.toString();
	}
	
	
	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}
	public static void main(String[] args) {
//		String demo = StringUtil.maskFirstName("彭我");
//		System.out.println(demo);
		String s = "wo";
		String aa=StringUtil.maskBankCodeNumberEnd("***************5000");
		try {
			//byte[] bytes = s.getBytes("UTF-8");
			System.out.println(aa);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static String maskUserNameOrMobile(String username, Long mobile) {
		if(StringUtil.isNotBlank(username)) {
			return StringUtil.maskString(username, StringUtil.ASTERISK, 1, 1, 3);
		} else if(mobile!=null){
			return StringUtil.maskString(mobile.toString(), StringUtil.ASTERISK, 3, 2, 4);
		}else{
			return "";
		}
	}
	
	/**
	 * 根据身份证获得性别
	 * 性别：(0:女，1：男,-1:保密)
	 * @param identityNumber
	 * @return
	 */
	public static int getSexByIdentityNumber(String identityNumber){
		if(StringUtil.isNotBlank(identityNumber)){
			if(identityNumber.length() == 15){
				int sex = Integer.parseInt(identityNumber.substring(14, 15));
				if(sex%2==0){
					return 0;
				}
				return 1;
			}else if(identityNumber.length() == 18){
				int sex = Integer.parseInt(identityNumber.substring(16, 17));
				if(sex%2==0){
					return 0;
				}
				return 1;
			}
		}
		return -1;
	}
	
	public static String getShortProjectName(String projectName) {
		if(projectName.contains("期")) {
			return projectName.substring(0, projectName.indexOf("期")+1);
		} else {
			return "";
		}
	}
	
	public static String getSuffixProjectName(String projectName) {
		if (projectName.contains("期")) {
			return projectName.substring(projectName.indexOf("期") + 1);
		} else {
			return projectName;
		}
	}
	
	/**
	 * 计算字符的长度
	 * @param str
	 * @return
	 */
	public static int getStrLeng(String str) {
		if(str == null){
			return 0;
		}
        int realLength = 0, len = str.length(), charCode = -1;
        for (int i = 0; i < len; i++) {
            charCode = (int)str.charAt(i);
            if (charCode >= 0 && charCode <= 128) {
                realLength += 1;
            } else {
                // 如果是中文则长度加2
                realLength += 2;
            }
        }
        return realLength;
    }
	
	/**
	 * 过滤所有以"<"开头以">"结尾的标签 
	 * @param str
	 * @return
	 */
	 public static String filterHtml(String str) {   
        Pattern pattern = Pattern.compile(regxpForHtml);   
        Matcher matcher = pattern.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    } 
	 
	 /**
	     * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
	     * <pre>
	     * StringUtil.isEmpty(null)      = false
	     * StringUtil.isEmpty("")        = false
	     * StringUtil.isEmpty(" ")       = true
	     * StringUtil.isEmpty("bob")     = true
	     * StringUtil.isEmpty("  bob  ") = true
	     * </pre>
	     *
	     * @param str 要检查的字符串
	     *
	     * @return 如果不为空, 则返回<code>true</code>
	     */
	    public static boolean isNotEmpty(String str) {
	        return ((str != null) && (str.length() > 0));
	    }
	    /**
	     * 
	     * @Description:判断数组元素是否有重复的
	     * @param str
	     * @return
	     * @author: chaisen
	     * @time:2016年7月20日 下午6:23:21
	     */
	    public static boolean isSame(String str[]){
	    	if(str.length==1){
	    		 return false;
	    	}
	        for(int i = 0;i<str.length;i++){
	        	int j=i+1;
	        	if(j==str.length){
	        		return false;
	        	}
	            if(str[i].equals(str[j])){
	                return true;
	            }
	        }
	        return false;
	    }   
	    
	    public static String  maskFormatBankCodeNumberWithBlank(String bankNumber,int last){
	    	String maskResult ="**** **** **** ";
	    	maskResult = maskResult + StringUtils.substring(bankNumber, bankNumber.length() - Math.abs(last),
	    			bankNumber.length());
	    	return maskResult;
	    }
	    
		/**
		 *  掩码 公司名称
		 * @param trueName
		 * @return
		 */
		public static String maskCompanyTrueName(String trueName) {		
			if (isNotBlank(trueName)) {
				trueName = trueName.trim();
				char[] chars = trueName.toCharArray();
				StringBuffer s = new StringBuffer();
				if(chars.length<=6){
					for (int i = 0; i < chars.length; i++) {
						if (i <= 2) {
							s.append(chars[i]);
						} else {
							s.append(ASTERISK);
						}
					}
				}else if(chars.length <= 12){
					for (int i = 0; i < chars.length; i++) {
						if ((i <= 2) || (i >= chars.length - 3)) {
							s.append(chars[i]);
						} else{
							s.append(ASTERISK);
						}
					}
				}else if(chars.length > 12){
					for (int i = 0; i < chars.length; i++) {
						if(((i >= 3) && (i <= 5)) || ((i >= chars.length - 6) && (i < chars.length - 3))){
							s.append(ASTERISK);
						}else{
							s.append(chars[i]);
						}
					}
				}
				
				return s.toString(); 
			}
			return "";
		}

		/**
		 *  掩码 真实姓名
		 * @param trueName
		 * @return
		 */
		public static String maskMiddleName(String trueName) {
			if (isNotBlank(trueName)) {
				trueName = trueName.trim();
				char[] chars = trueName.toCharArray();
				StringBuffer s = new StringBuffer();
				if(2 >= chars.length){
					for (int i = 0; i < chars.length; i++) {
						if (i == 0) {
							s.append(chars[i]);
						} else {
							s.append(ASTERISK);
						}
					}
				}else{
					for (int i = 0; i < chars.length; i++) {
						if ((i == 0) || (i == (chars.length - 1))) {
							s.append(chars[i]);
						} else {
							s.append(ASTERISK);
						}
					}
				}
				
				return s.toString();
			}
			return "";
		}


		public static String subStr(String str,int len){
			if(StringUtils.isBlank(str) || len <=0){
				return str;
			}
			if(str.length() <= len){
				return str;
			}
			return str.substring(0,len);
		}
}
