package cn.wxy.BeanFactory;/**
 * Created by XR on 2018/3/20.
 */

import java.io.IOException;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

public class BeanFactory extends SqlSessionFactoryBean{

    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try{
            return super.buildSqlSessionFactory();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            //  throw new NestedIOException("Failed parse Mapping resource",e.getCause());
        }finally{
            ErrorContext.instance().reset();

        }
        return null;
    }

}
