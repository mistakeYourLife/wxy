package cn.wxy.common.serializeFilter;

import cn.wxy.common.domain.AbstractBaseObject;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 *
 * Created by py on 2015/3/31.
 */
public class FilterClassFiled  extends AbstractBaseObject {

    private  Class<?> classzz;

  //  private Set<String> includes = Sets.newHashSet();

    private   Set<String> excludes = Sets.newHashSet();

    public Class<?> getClasszz() {
        return classzz;
    }

    public void setClasszz(Class<?> classzz) {
        this.classzz = classzz;
    }

//    public Set<String> getIncludes() {
//        return includes;
//    }
//
//    public void setIncludes(Set<String> includes) {
//        this.includes = includes;
//    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
    }
}
