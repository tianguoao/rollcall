package com.tga.rollcall.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年6月17日 下午5:13:47
 * Class: DataBaseConfig.java
 */
@Configuration
@MapperScan(basePackages = {"com.tga.rollcall.dao"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataBaseConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "rollcall.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /*
     * Mybatis - 通用分页拦截器 PageHelper 使用示例
     * 
     * 加在Dao代码执行前
     * PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
     * List<TrainingArticle> articles = trainingArticleMapper.selectByExampleWithBLOBs(example);
     * PageInfo<TrainingArticle> pageInfo = new PageInfo<>(articles);
     * pageInfo.getTotal() //总条数
     * */
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean
                .setMapperLocations(resolver.getResources("classpath*:/mybatis/*.xml"));
        // 配置mybatis的分页插件pageHelper
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true"); 
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "pageNum=pageNumKey;pageSize=pageSizeKey;");
        interceptor.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[] {interceptor});
        return sqlSessionFactoryBean.getObject();
    } 

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
