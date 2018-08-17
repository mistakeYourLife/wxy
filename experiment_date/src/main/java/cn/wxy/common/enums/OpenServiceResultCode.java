package cn.wxy.common.enums;

public enum OpenServiceResultCode {
	/**
	 * 正常
	 */
	APPLY_SUCCESS(200, "提交成功"),

	SYSTEM_ERROR(300, "系统异常"),

	CHECK_SIGN_ERROR(301, "验签失败"),

	DECRYPT_ERROR(302, "数据解密失败"),

	INPUT_TRANSFER_ERROR(303, "数据解析失败"),
	
	INPUT_VALID_ERROR(304, "数据校验不通过"),

	CHECK_REPEATED_ERROR(305, "订单重复"),

	DATA_NOT_FOUND_ERROR(306, "业务数据不存在"),
	
	ENTERPRISE_ID_ISNULL(403,"企业ID不能为空"),

	MEMBER_AUTH_ERROR(400, "实名认证失败");

	
	private int resultCode;
	
	private String resultMsg;

	public int getResultCode() {
		return this.resultCode;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}

	private OpenServiceResultCode(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

}
