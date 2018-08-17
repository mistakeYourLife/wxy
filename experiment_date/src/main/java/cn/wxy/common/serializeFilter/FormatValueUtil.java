package cn.wxy.common.serializeFilter;


import cn.wxy.common.utils.FormulaUtil;
import cn.wxy.common.utils.StringUtil;

import java.math.BigDecimal;

/**
 *
 * Created by py on 2015/4/2.
 */
public class FormatValueUtil {
    public static   String importantMaskEnum(FormatValueEnum mak, Object object) {
        String temp = null;
        if(object == null){
        	return null;
        }
        switch (mak) {
            case IDENTITYNUMBER:
                if(object.getClass().isAssignableFrom(mak.getClasszz())){
                    temp = StringUtil.maskIdentityNumber((String) object);
                }
                break;
            case TRUE_NAME:
                if(object.getClass().isAssignableFrom(mak.getClasszz())){
                    temp = StringUtil.maskFirstName((String) object);
                }
                break;
            case BANK_CARD:
                if(object.getClass().isAssignableFrom(mak.getClasszz())){
                    temp = StringUtil.maskBankCodeNumber((String) object);
                }
                break;
            case MOBILE:
            case BANK_MOBILE:
                if(object.getClass().isAssignableFrom(mak.getClasszz())){
                     temp = StringUtil.maskMobileCanNull((Long) object);
                }
                break;
            case MINANNUALIZEDRATE:
            case MAXANNUALIZEDRATE:
            case AVAILABLEBALANCE:
            case EXECTAMOUNT:
            case INVESTAMOUNT:
            case AMOUNT:
            case TOTALINTEREST:
            case PAYABLEINTEREST:
            case PAYABLEPRINCIPAL:
            case ANNUALIZEDRATE:
            case WITHDRAWAMOUNT:
            case TOTALTRANSACTIONINTEREST:
            case OUTLAY:
                if(object.getClass().isAssignableFrom(mak.getClasszz())){
                     temp = FormulaUtil.getFormatPriceRound((BigDecimal) object);
                }
                break;
            default :
                break;
        }
        return temp;
    }
}
