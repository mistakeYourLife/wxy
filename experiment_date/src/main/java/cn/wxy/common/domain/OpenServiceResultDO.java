package cn.wxy.common.domain;


import cn.wxy.common.enums.OpenServiceResultCode;

/**
 * 
 * @desc 对外接口Output实体类
 * @author wangyanji 2016年11月4日上午10:12:59
 */
public class OpenServiceResultDO<T> extends AbstractBaseObject {

	public final static boolean DEFULT_SUCCESS = true;
	
	public final static boolean DEFULT_ERROR = false;
	
	/**
	 * 响应码
	 */
	private Integer resultCode;
	/**
	 * 响应码描述
	 */
	private String resultMsg;

	/**
	 * 返回的结果对象
	 */
	private T result;

	public OpenServiceResultDO(boolean isDefultSuccess) {
		super();
		if(isDefultSuccess)
			setResultCodeByEnum(OpenServiceResultCode.APPLY_SUCCESS);
		else
			setResultCodeByEnum(OpenServiceResultCode.SYSTEM_ERROR);	
	}

	public OpenServiceResultDO(OpenServiceResultCode resultCode) {
		super();
		setResultCodeByEnum(resultCode);
	}

	public OpenServiceResultDO(boolean isDefultSuccess, Class<T> clazz) throws Exception {
		super();
		if(isDefultSuccess)
			setResultCodeByEnum(OpenServiceResultCode.APPLY_SUCCESS);
		else
			setResultCodeByEnum(OpenServiceResultCode.SYSTEM_ERROR);	
		this.result = clazz.newInstance();
	}

	/**
	 * 自定义备注
	 */
	private String remark;

	public Integer getResultCode() {
		return resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setResultCodeByEnum(OpenServiceResultCode resultCode) {
		this.resultCode = resultCode.getResultCode();
		this.resultMsg = resultCode.getResultMsg();
	}

	public boolean isSuccess() {
		if (this.resultCode == OpenServiceResultCode.APPLY_SUCCESS.getResultCode()) {
			return true;
		}
		return false;
	}

}
