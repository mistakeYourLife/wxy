package cn.wxy.common.serializeFilter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.ValueFilter;
import cn.wxy.common.utils.StringUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * json 序列化， 自定义过滤字段
 * Created by py on 2015/3/31.
 */
public class SimplePropertyPreFilter implements PropertyPreFilter, ValueFilter {

    public SimplePropertyPreFilter() {
        super();
    }

    private Set<FilterClassFiled> filterClassFileds;

    private FormatValueEnum formatValueEnum[];

    public FormatValueEnum[] getFormatValueEnum() {
        return formatValueEnum;
    }

    public void setFormatValueEnum(FormatValueEnum[] formatValueEnum) {
        this.formatValueEnum = formatValueEnum;
    }

    public Set<FilterClassFiled> getFilterClassFileds() {
        return filterClassFileds;
    }

    public void setFilterClassFileds(Set<FilterClassFiled> filterClassFileds) {
        this.filterClassFileds = filterClassFileds;
    }

    private FilterClassFiled getFileClass(Class classzz) {
        Iterator<FilterClassFiled> iterator = filterClassFileds.iterator();
        while (iterator.hasNext()) {
            FilterClassFiled filterClassFiled = iterator.next();
            if (filterClassFiled.getClasszz().isAssignableFrom(classzz)) {
                return filterClassFiled;
            }
        }
        return null;
    }


    /**
     * 序列化时，判断特定类的属性，不需要序列化
     *
     * @param serializer
     * @param source
     * @param name
     * @return
     */
    public boolean apply(JSONSerializer serializer, Object source, String name) {
        if (source == null) {
            return true;
        }
        Class classzz = source.getClass();
        FilterClassFiled fileClass = getFileClass(classzz);
        if (fileClass != null) {
            return !fileClass.getExcludes().contains(name);
        } else {
            return true;
        }
    }

    /**
     * 序列化，根据注解
     *
     * @param object
     * @param name
     * @param value
     * @return
     */
    @Override
    public Object process(Object object, String name, Object value) {
        if (value == null){
            return value;
        }
        FormatValueEnum formatValueEnum = getFormatValueEnum(name, value.getClass());
        if (formatValueEnum != null) {
            String maskEnum = FormatValueUtil.importantMaskEnum(formatValueEnum, value);
            return maskEnum == null ? value : maskEnum;
        }
        return value;
    }

    private FormatValueEnum getFormatValueEnum(String filed, Class classzz) {
        if (formatValueEnum == null) {
            return null;
        }
        for (FormatValueEnum temp : formatValueEnum) {
            if (StringUtil.equals(temp.getFiled(), filed) && temp.getClasszz().isAssignableFrom(classzz)) {
                return temp;
            }
        }
        return null;
    }

}
