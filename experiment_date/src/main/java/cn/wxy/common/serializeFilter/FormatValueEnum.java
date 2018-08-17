package cn.wxy.common.serializeFilter;

import java.math.BigDecimal;

/**
 *
 * Created by py on 2015/4/2.
 */
public enum FormatValueEnum {
    //身份证
    IDENTITYNUMBER("identityNumber", String.class),
    //真实姓名
    TRUE_NAME("trueName", String.class),
    //银行卡ID
    BANK_CARD("cardNumber", String.class),
    //手机号码
    MOBILE("mobile", Long.class),
    //银行预留手机号码
    BANK_MOBILE("bankMobile", Long.class),
    //提现金额
    WITHDRAWAMOUNT("withdrawAmount", BigDecimal.class),

    //年化最小收益率
    MINANNUALIZEDRATE("minAnnualizedRate", BigDecimal.class),
    //年化最大收益率
    MAXANNUALIZEDRATE("maxAnnualizedRate", BigDecimal.class),
    //剩余余额
    AVAILABLEBALANCE("availableBalance", BigDecimal.class),
    //预期收益
    EXECTAMOUNT("expectAmount", BigDecimal.class),
    //投资额
    INVESTAMOUNT("investAmount", BigDecimal.class),
    //收益益金额
    AMOUNT("amount", BigDecimal.class),
    //预期总收益
    TOTALINTEREST("totalInterest", BigDecimal.class),
    //应付利息
    PAYABLEINTEREST("payableInterest", BigDecimal.class),
    //应付本金
    PAYABLEPRINCIPAL("payablePrincipal", BigDecimal.class),
    //年化收益率
    ANNUALIZEDRATE("annualizedRate", BigDecimal.class),
    //项目总收益
    TOTALTRANSACTIONINTEREST("totalTransactionInterest", BigDecimal.class),
    //支出
    OUTLAY("outlay", BigDecimal.class);
    

    
    //属性名称
    private String filed;
    //属性类型
    private Class classzz;

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public Class getClasszz() {
        return classzz;
    }

    public void setClasszz(Class classzz) {
        this.classzz = classzz;
    }

    FormatValueEnum(String filed, Class classzz) {
        this.filed = filed;
        this.classzz = classzz;

    }




}
