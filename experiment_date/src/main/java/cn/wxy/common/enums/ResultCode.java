package cn.wxy.common.enums;

public enum ResultCode {
	/**
	 * 正常
	 */
	NORMAL(0, "NORMAL", "正常", 0), SUCCESS(1, "SUCCESS", "成功", 0), ERROR(2, "ERROR", "失败", 0), REGIST_COUNT(3, "ERROR", "发送短信或者语音超过次数", 0), SUBMIT_REPEAT_DATA(
			4, "ERROR", "请勿重复提交数据", 0),CUSTOM(9,"CUSTOM","自定义返回结果",2),


	;
	private String msg;

	private int code;

	private String codeStr;

	/**
	 * 错误类型（0:一般结果码；1：系统级的错误；2：应用级的错误）
	 */
	private int type;

	public String getCode() {
		return String.valueOf(this.code);
	}

	public String getMsg() {
		return this.msg;
	}

	public String getCodeStr() {
		return this.codeStr;
	}

	public int getType() {
		return this.type;
	}

	private ResultCode(int code, String codeStr, String msg, int type) {
		this.msg = msg;
		this.code = code;
		this.codeStr = codeStr;
		this.type = type;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}

	public static ResultCode getResultCodeByCode(String code) {
		for (ResultCode result : ResultCode.values()) {
			if (result.getCode().equals(code)) {
				return result;
			}
		}
		return null;
	}

	public static ResultCode getResultCodeByMessage(String message) {
		for (ResultCode result : ResultCode.values()) {
			if (result.getMsg().equals(message)) {
				return result;
			}
		}
		return null;
	}

	public static ResultCode getResultCodeByCodeStr(String codeStr) {
		for (ResultCode result : ResultCode.values()) {
			if (result.getCodeStr().equals(codeStr)) {
				return result;
			}
		}
		return null;
	}

}
