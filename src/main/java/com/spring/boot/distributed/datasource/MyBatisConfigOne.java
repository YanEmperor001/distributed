package com.spring.boot.distributed.datasource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.spring.boot.distributed.config.DBConfig1;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.spring.boot.distributed.test1", sqlSessionTemplateRef = "testSqlSessionTemplate")
public class MyBatisConfigOne {
    //配置数据源
    @Primary
    @Bean(name = "testDataSource")
    public DataSource testDataSource(DBConfig1 dbConfigOne) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfigOne.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfigOne.getPassword());
        mysqlXaDataSource.setUser(dbConfigOne.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("testDataSource");

        xaDataSource.setMinPoolSize(dbConfigOne.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbConfigOne.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbConfigOne.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbConfigOne.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbConfigOne.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbConfigOne.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbConfigOne.getMaxIdleTime());
        xaDataSource.setTestQuery(dbConfigOne.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "testSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "testSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("testSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
