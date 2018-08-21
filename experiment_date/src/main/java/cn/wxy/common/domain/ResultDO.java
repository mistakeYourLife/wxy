package cn.wxy.common.domain;

import cn.wxy.common.enums.ResultCode;
import cn.wxy.common.pageable.Page;
import cn.wxy.common.utils.Collections3;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class ResultDO<T> implements Serializable {

	private static final long serialVersionUID = -4194385117011887751L;

	/**
	 * 标识请求结果的处理状态
	 */
	private boolean isSuccess = true;

	/**
	 * 存储返回的结果对象
	 */
	private T t;

	/**
	 * 存储返回的结果对象列表
	 */
	private List<T> resultList;

	/**
	 * 存储分页信息
	 */
	private Page<T> page;

	/**
	 * 程序执行结果码
	 */
	private List<ResultCode> resultCodeList = Lists.newArrayList();
	
	/**
	 * 附加其他值
	 */
//	private Map<String ,Object> map;
//
//	public Map<String, Object> getMap() {
//		return map;
//	}
//
//	public void setMap(Map<String, Object> map) {
//		this.map = map;
//	}

	public ResultDO(T t) {
		this.t = t;
	}

	public ResultDO() {
	}

	public ResultDO(Page<T> page) {
		if (page != null) {
			this.resultList = page.getData();
			this.page = page;
		}
	}
//
	public ResultDO(ResultCode resultCode) {
		this.isSuccess = false;		
		resultCodeList.add(resultCode);
	}

	public ResultDO(List<T> resultList) {
		this.resultList = resultList;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
	public  boolean isError(){
		return !isSuccess;
	}


	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getResult() {
		return t;
	}

	public ResultDO<T> setResult(T t) {
		this.t = t;
		return this;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public ResultDO<T> setResultList(List<T> resultList) {
		this.resultList = resultList;
		return this;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		if (page != null && this.resultList == null) {
			this.resultList = page.getData();
		}
		this.page = page;
	}


	public void setResultCode(ResultCode resultCode) {
		this.isSuccess = false;		
		resultCodeList.add(resultCode);
	}
	
	public ResultCode getResultCode() {
		if(Collections3.isNotEmpty(resultCodeList)) {
			return resultCodeList.get(0);
		}
		return null;
	}

	public List<ResultCode> getResultCodeList() {
		return resultCodeList;
	}

	public void setResultCodeList(List<ResultCode> resultCodeList) {
		this.isSuccess = false;
		this.resultCodeList = resultCodeList;
	}

	@SuppressWarnings("unused")
	private List<ResultCode> resultCodeEum;

	public List<ResultCodeDo>  getResultCodeEum() {
		List<ResultCodeDo> list = null;
		if (Collections3.isNotEmpty(resultCodeList) && resultCodeList.size() > 0) {
			list = Lists.newArrayList();
			for (ResultCode code : resultCodeList) {
				if(code != null){
					ResultCodeDo resuldto = new ResultCodeDo();
					resuldto.setMsg(code.getMsg());
					resuldto.setCode(code.getCode());
					resuldto.setCodeStr(code.getCodeStr());
					resuldto.setType(code.getType());
					list.add(resuldto);
				}
				
			}

		}
		return list;
	}

	public void setResultCodeEum(List<ResultCode> resultCodeEum) {
		this.resultCodeEum = resultCodeEum;
	}

	/**
     * 枚举转换类  主要是JSON 在序列化，不会转化枚举
     * @author pengyong
     *
     */
    static class ResultCodeDo {
	private String msg;
	private String code;
	private String codeStr;
	private int type;

	public String getMsg() {
	    return msg;
	}

	public void setMsg(String msg) {
	    this.msg = msg;
	}

	public String getCode() {
	    return code;
	}

	public void setCode(String code) {
	    this.code = code;
	}

	public String getCodeStr() {
	    return codeStr;
	}

	public void setCodeStr(String codeStr) {
	    this.codeStr = codeStr;
	}

	public int getType() {
	    return type;
	}

	public void setType(int type) {
	    this.type = type;
	}

    }
	
	
//	public static void main(String[] args) {
//	    ResultDO resultdo = new ResultDO();
//	    resultdo.setResultCode(ResultCode.PROJECT_SERIAL_NUMBER_IS_NOT_ADD_ERROR);
//	    System.out.println( JSONObject.toJSON(resultdo));
//	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE, false);
	}
}
