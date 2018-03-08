package com.spring.boot.distributed.datasource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.spring.boot.distributed.config.DBConfig2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.spring.boot.distributed.test2", sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class MyBatisConfigTwo {
    //配置数据源
    @Bean(name = "test2DataSource")
    public AtomikosDataSourceBean testDataSource(DBConfig2 dbConfigTwo) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfigTwo.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfigTwo.getPassword());
        mysqlXaDataSource.setUser(dbConfigTwo.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("test2DataSource");

        xaDataSource.setMinPoolSize(dbConfigTwo.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbConfigTwo.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbConfigTwo.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbConfigTwo.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbConfigTwo.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbConfigTwo.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbConfigTwo.getMaxIdleTime());
        xaDataSource.setTestQuery(dbConfigTwo.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
