package shop.config;



import java.io.File;
import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.fasterxml.jackson.databind.ObjectMapper;




@Configuration
@ComponentScan("shop")
@EnableWebMvc // 开启web mvc基础设施支持
@MapperScan("shop.mapper")
@PropertySource({"classpath:jdbc.properties","classpath:alipay.properties"})
public class AppConfig extends WebMvcConfigurerAdapter{
	public void configureViewResolvers(ViewResolverRegistry rigistry) {
		rigistry.jsp("/WEB-INF/jsp/",".jsp");
	}
	
	@Bean
	public DataSource dataSource(Environment env) { 
		DriverManagerDataSource ds = new DriverManagerDataSource(
				env.getProperty("jdbc.url"),
				env.getProperty("jdbc.username"),
				env.getProperty("jdbc.password"));
		ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		return ds;
	}	
	
	@Bean // 定义Mybatis的会话工厂
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean sf = new SqlSessionFactoryBean();
		sf.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sf.setDataSource(dataSource);
		return sf;
	}
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
		
	@Bean
	public AlipayClient alipayClient(Environment env) throws IOException {
	    return new DefaultAlipayClient(
	             "https://openapi.alipay.com/gateway.do",
	             env.getProperty("alipay.appId"),
	             FileUtils.readFileToString(new File(env.getProperty("alipay.appPrivateKeyFile")), "UTF-8"),
	             "json",
	             "UTF-8",
	             FileUtils.readFileToString(new File(env.getProperty("alipay.appPrivateKeyFile")), "UTF-8"),
	             env.getProperty("alipay.signType")
	             );
	    }
	

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
