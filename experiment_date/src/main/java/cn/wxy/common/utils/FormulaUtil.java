package cn.wxy.common.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 公式计算工具类
 * 
 * @author Administrator
 *
 */
public class FormulaUtil {

	private static final BigDecimal YEAR_DAYS = new BigDecimal(36000);
	private static final BigDecimal MONTH_LEN = new BigDecimal(1200);
	private static final BigDecimal HUNDRED = new BigDecimal(100);
	private static final BigDecimal THOUSAND = new BigDecimal(1000);



	/**
	 *  按日计息
	 * @return
	 */
	public static  BigDecimal  calculateInterest(BigDecimal amount, BigDecimal annualizedRate,int date){
		BigDecimal divide = annualizedRate.divide(YEAR_DAYS,10, BigDecimal.ROUND_HALF_UP);
		BigDecimal pay = amount.multiply(divide).multiply(new BigDecimal(date)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return pay;
	}

	/**
	 *  等本等息 ，计算本金
	 * @param amount
	 * @param month
	 * @return
	 */
	public static  BigDecimal calculatePrincipalByAvg(BigDecimal amount, int month){
		BigDecimal pay = amount.divide(new BigDecimal(month),10, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
		return  pay;
	}

	/**
	 *  等本等息按月还款 ，计算利息
	 * @param amount
	 * @return
	 */
	public static  BigDecimal calculateInterestByAvg(BigDecimal amount, BigDecimal annualizedRate){
		BigDecimal pay  = amount	.multiply(annualizedRate.divide(MONTH_LEN, 10, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return  pay;
	}
	
	/**
	 *  等本等息按周还款 ，计算本金
	 * @param amount
	 * @param weeks
	 * @return
	 */
	public static  BigDecimal calculatePrincipalByAvgWeek(BigDecimal amount, int weeks){
		BigDecimal pay = amount.divide(new BigDecimal(weeks),10, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
		return pay;
	}
	
	/**
	 * 
	 * @Description 等本等息按周还款 ，计算利息
	 * @param amount
	 * @param annualizedRate
	 * @param days
	 * @return
	 * @author luwenshan
	 * @time 2016年10月31日 下午2:10:35
	 */
	public static BigDecimal calculateInterestByAvgWeek(BigDecimal amount, BigDecimal annualizedRate, int days){
		BigDecimal pay = amount.multiply(annualizedRate.divide(YEAR_DAYS, 10, BigDecimal.ROUND_HALF_UP))
				         .multiply(new BigDecimal(days))
				         .setScale(2, BigDecimal.ROUND_HALF_UP);
		return pay;
	}
	
	/**
	 * 
	 * @Description 等本等息 ，计算几个周的预期总利息
	 * @param amount
	 * @param annualizedRate
	 * @param weeks
	 * @return
	 * @author luwenshan
	 * @time 2016年10月31日 下午2:10:27
	 */
	public static BigDecimal calculateTotalInterestByWeek(BigDecimal amount, BigDecimal annualizedRate, int weeks){
		BigDecimal oneWeekInterest = amount.multiply(annualizedRate.divide(YEAR_DAYS, 10, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(7));
		BigDecimal pay = oneWeekInterest.multiply(new BigDecimal(weeks)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return pay;
	}
	
	/**
	 * 
	 * @Description 按日计息，按季付息，到期还本，计算利息
	 * @param amount
	 * @param annualizedRate
	 * @param days
	 * @return
	 * @author luwenshan
	 * @time 2016年10月31日 下午2:10:43
	 */
	public static BigDecimal calculateInterestByAvgSeason(BigDecimal amount, BigDecimal annualizedRate, int days){
		BigDecimal divide = annualizedRate.divide(YEAR_DAYS,10, BigDecimal.ROUND_HALF_UP);
		BigDecimal pay = amount.multiply(divide).multiply(new BigDecimal(days)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return pay;
	}
	
	/**
	 *  等本等息 ，计算几个月的总利息,
	 * @param amount
	 * @return
	 */
	public static  BigDecimal calculateInterestAndMonthByAvg(BigDecimal amount, BigDecimal annualizedRate,int month){
		BigDecimal pay  = amount	.multiply(annualizedRate.divide(MONTH_LEN, 10, BigDecimal.ROUND_HALF_UP))
				.multiply(new BigDecimal(month))
				.setScale(2, BigDecimal.ROUND_HALF_UP);
		return  pay;
	}




	/**
	 * 线下利息计算方法
	 * 
	 * @param investAmount
	 * @param days
	 * @param offlineRate
	 * @return
	 */
	public static BigDecimal getDayInterestSettlement(BigDecimal investAmount, int days, BigDecimal offlineRate) {
		if (offlineRate != null) {
			return investAmount.multiply(new BigDecimal(days)).multiply(
					offlineRate.divide(YEAR_DAYS, 10, BigDecimal.ROUND_HALF_UP));
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 根据分红百分比获取分红总额
	 * 
	 * @param bonusPercent
	 * @return
	 */
	public static BigDecimal getTotalBonusByPercent(BigDecimal bonusPercent, BigDecimal totalIncome) {
		BigDecimal totalUserBonus = totalIncome.multiply(
				bonusPercent.divide(new BigDecimal(100), 10, BigDecimal.ROUND_HALF_UP)).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		return totalUserBonus;
	}

	public static BigDecimal getUnitRental(BigDecimal totalRental, Integer leaseDays) {
		BigDecimal unitRental = totalRental.divide(new BigDecimal(leaseDays), 10, BigDecimal.ROUND_HALF_UP).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		return unitRental;
	}
	
	public static BigDecimal getBonusAnnualizedRate(BigDecimal bonusAmount, BigDecimal totalInterest, BigDecimal annualizedRate, BigDecimal ExtraAnnualizedRate) {
		if(ExtraAnnualizedRate!=null) {
			annualizedRate = annualizedRate.add(ExtraAnnualizedRate);
		}
		return BigDecimal.valueOf(bonusAmount.doubleValue()/totalInterest.doubleValue()*annualizedRate.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 格式化金额
	 * 
	 * @param price
	 * @return
	 */
	public static String getFormatPrice(BigDecimal price) {
		if (price == null) {
			return "";
		}
		return new DecimalFormat("###,###.##").format(price);
	}

	/**
	 * 格式化金额
	 * @Deprecated 此方法内#占位符，当匹配为0时，返回空，例如0.01格式化为.01
	 * @param price
	 * @return
	 */
	@Deprecated 
	public static String getFormatPriceDefaultZero(BigDecimal price) {
		if (price == null) {
			return "";
		}
		return new DecimalFormat("###,###.00").format(price);
	}

	/**
	 * 格式化金额，中间无分隔符“,”
	 */
	public static String getFormatPriceNoSep(BigDecimal price){
		if(price ==null){
			return "";
		}
		return new DecimalFormat("######.##").format(price);
	}
	
	/**
	 * 格式化金额，中间无分隔符“,”,结尾默认为00
	 */
	public static String getFormatPriceNoSepDefaultZero(BigDecimal price){
		if(price ==null){
			return "";
		}
		return new DecimalFormat("######.00").format(price);
	}
	
	/**
	 * 格式化金额
	 * @param price
	 * @return
	 */
	public static String getFormatPriceRound(BigDecimal price){
		if(price == null){
			return "";
		}
		String formatPrice = new DecimalFormat("###.00").format(price);
		if(formatPrice.startsWith(".")){
			return "0"+formatPrice;
		}
		return formatPrice;
	}
	
	 /**
	  * 格式化金额
	  * @param price   5000
	  * @return  ¥5000.00
	  */	
	 public static String formatCurrency(BigDecimal price) {
		 if (price == null) {
			 return "¥0.0";
		 }
		 NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CHINA);
		 String format = currency.format(price);
		 String result = "";
		 if (price.compareTo(BigDecimal.ZERO) >= 0) {
			 result = "¥" + format.substring(1);
		 } else {
			 result = "¥-" + format.substring(2);
		 }
		 return result;

	 }

	/**
	 * 格式化金额 ,不带 人民币符号
	 * 
	 * @param price
	 *            5000
	 * @return 5000.00 author: pengyong 下午4:26:42
	 */
	public static String formatCurrencyNoUnit(BigDecimal price) {
		String formatCurrency = formatCurrency(price);
		String format = formatCurrency.substring(1);
		return format;

	}

	/**
	 * 获得金额整数部分，默认为零
	 * 
	 * @param price
	 * @return
	 */
	public static String getIntegerDefaultZero(BigDecimal price) {
		String _price = getInteger(price);
		if (StringUtil.isNotBlank(_price)) {
			return _price;
		}
		return "0";
	}

	/**
	 * 获得金额小数部分，默认为零
	 * 
	 * @param price
	 * @return
	 */
	public static String getDecimalDefaultZero(BigDecimal price) {
		String _price = getDecimal(price);
		if (StringUtil.isNotBlank(_price)) {
			return _price;
		}
		return ".00";
	}

	/**
	 * 获得整数部分
	 * 
	 * @param price
	 * @return
	 */
	public static String getInteger(BigDecimal price) {
		if (price == null) {
			return "";
		}
		//String p = getFormatPriceDefaultZero(price);
		String p = formatCurrencyNoUnit(price);
		if (p.lastIndexOf(".") > 0) {
			return p.substring(0, p.lastIndexOf("."));
		}
		return "";
	}

	/**
	 * 获得小数部分
	 * 
	 * @param price
	 * @return
	 */
	public static String getDecimal(BigDecimal price) {
		if (price == null) {
			return "";
		}
		//String decimal = getFormatPriceDefaultZero(price);
		String decimal = formatCurrencyNoUnit(price);
		if (decimal.lastIndexOf(".") >= 0) {
			return decimal.substring(decimal.lastIndexOf("."), decimal.length());
		}
		return "";
	}

	/**
	 * 格式化百分比
	 * 
	 * @param percent
	 *            0.12
	 * @return 12 author: pengyong 下午4:29:09
	 */
	public static String getFormartPercentage(BigDecimal percent) {
		if (percent == null) {
			return "";
		}
		NumberFormat formart = NumberFormat.getPercentInstance();
		String format = formart.format(percent);
		return format.substring(0, format.length() - 1);
	}

	public static double doubleAdd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 格式化金额
	 * 
	 * @param price
	 * @return
	 */
	public static String getTempFormatPrice(Integer price) {
		if (price == null) {
			return "";
		}
		return new DecimalFormat("###,###.##").format(price);
	}
	


	
	/**
	 * 格式化金额,取亿
	 * 
	 * @param price
	 * @return   author: zhanghao 
	 */
	public static String getFormatPriceHundredMillion(String price) {
		String priceStrNoSeq = price.replace(",", "");
		
		if (StringUtil.isBlank(priceStrNoSeq) ) {
			return "0";
		}
		
		BigDecimal priceBig =  new BigDecimal(priceStrNoSeq);
		
		
		String priceStr = getFormatPriceNoSepDefaultZero(priceBig);
		
		if(priceStr.indexOf(".")<8){
			return "0";
		}else{
			return priceStr.substring(0, priceStr.indexOf(".")-8);
		}
		
	}
	
	/**
	 * 格式化金额,取万
	 * 
	 * @param price
	 * @return   author: zhanghao 
	 */
	public static String getFormatPriceTenThousand(String price) {
		String priceStrNoSeq = price.replace(",", "");
		
		if (StringUtil.isBlank(priceStrNoSeq) ) {
			return "0";
		}
		BigDecimal priceBig =  new BigDecimal(priceStrNoSeq);
		
		
		String priceStr = getFormatPriceNoSepDefaultZero(priceBig);
		
		if(priceStr.indexOf(".")<4){
			return "0";
		}else{
			return priceStr.substring(0, priceStr.indexOf(".")-4);
		}
		
	}

	public static void main(String[] args) {
		System.out.println(formatCurrencyNoUnit(new BigDecimal(5000)));
		System.out.println(StringUtil.isBlank(getFormatPriceHundredMillion("51095217.71")));
	}


	/**
	 * 金额相加
	 */
	public static BigDecimal addDecimal(BigDecimal... addEle){
		BigDecimal result = BigDecimal.ZERO;
		for(int i=0;i<addEle.length;i++){
			if(addEle[i]==null){
				addEle[i] = BigDecimal.ZERO;
			}
			result = result.add(addEle[i]);
		}
		return result;
	}
	
	public static BigDecimal subtractDecimal(BigDecimal firstEle,BigDecimal secondEle){
		if(firstEle==null){
			firstEle = BigDecimal.ZERO;
		}
		if(secondEle==null){
			secondEle = BigDecimal.ZERO;
		}
		return firstEle.subtract(secondEle);
		
	}

}
