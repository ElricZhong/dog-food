package com.dogfood.mybatisspring;

import com.dogfood.mybatissample.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongfupeng
 */
//@Component
public class SingleMapperFactoryBean implements FactoryBean {

    private SqlSession sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Override
    public Object getObject() throws Exception {
        //Object proxyInstance = Proxy.newProxyInstance(
        //    MybatisSampleFactoryBean.class.getClassLoader(),
        //    new Class[] {UserMapper.class},
        //    new InvocationHandler() {
        //        @Override
        //        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //            System.out.println(method);
        //            return null;
        //        }
        //    });
        //
        //return proxyInstance;

        // TODO 要是多个mapper咋办呢？手动定义多个FactoryBean吗？
        return sqlSession.getMapper(UserMapper.class);

        // TODO 使用时相当于以下代码，区别在于我的Mapper在使用完毕后没有关闭connection
        //try (SqlSession session = sqlSessionFactory.openSession()) {
        //    BlogMapper mapper = session.getMapper(BlogMapper.class);
        //    Blog blog = mapper.selectBlog(101);
        //}
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }
}
