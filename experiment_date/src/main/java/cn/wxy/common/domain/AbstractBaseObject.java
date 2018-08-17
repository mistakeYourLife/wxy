package cn.wxy.common.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 所有model基类,已经重写toString()equals()hashCode()
 * 
 * @author py
 */
public abstract class AbstractBaseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -920004653974433365L;

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE, false);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
