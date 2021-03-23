package com.cafe24.kangk0269.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(value = "com.cafe24.kangk0269.dao",sqlSessionFactoryRef = "mybatisSqlSessionFactory")
@EnableTransactionManagement
public class MybatisConfig {
   
   //1.datasource
  /* @Bean(name="hikariDataSource")
   public DataSource datasource(@Qualifier("jasyptStringEncryptor") StringEncryptor stringEncrytor) {
	   
	   String jdbcUrl = stringEncrytor.decrypt("BQ8Ol3gKvOcAsm0mqXkWvdXuQZiYxjUGBwfEw9Z/D66v2Ymy1+H1c3Fy0glWEeeFweMNR7kjeqTi5TtM9HPLjDqBdI6wn49Na4y4xZ7M++YfNQeU87Iddt1gGN+g0ri6Oy4YsOh080Q=");
	   String userName = stringEncrytor.decrypt("jkKpqW+AWou2+rwtPJWUl5U2LqK2ab6r");
	   String password = stringEncrytor.decrypt("XeHX0x3og3EUIix80I4Xh8/bqiVqwbWP");
		 
	   
      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
      hikariConfig.setJdbcUrl(jdbcUrl);
      hikariConfig.setUsername(userName);
      hikariConfig.setPassword(password);
      hikariConfig.setMaximumPoolSize(15);
      hikariConfig.setMaxLifetime(1000*60*30);
      
      return new HikariDataSource(hikariConfig);
   }*/
   
   //2.Mybatis 연동을 위한 mybatisSqlSessionFactory 설정
   @Bean(name = "mybatisSqlSessionFactory")
   public SqlSessionFactory mybatisSqlSessionFactory(DataSource dataSource,
                                         ApplicationContext context) throws Exception {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(dataSource);
      sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:mapper/**/*.xml"));
      sqlSessionFactoryBean.setTypeAliasesPackage("com.cafe24.kangk0269.dto");  
      //sqlSessionFactoryBean.setConfiguration(mybatisConfig());
      return sqlSessionFactoryBean.getObject();
   }
   //3.Mybatis SqlSessionTemplate 설정
   @Bean(name = "mybatisSqlSessionTemplate")
   public SqlSessionTemplate mybatisSqlSessionTemplate(
         @Qualifier("mybatisSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {      
      return new SqlSessionTemplate(sqlSessionFactory);
   }
   //4.Mybatis TransactionManager 설정
   @Bean(name = "mybatisTransactionManager")
   public PlatformTransactionManager transactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
   }

}
