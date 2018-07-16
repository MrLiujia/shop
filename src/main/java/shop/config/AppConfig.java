package shop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan("shop")
@EnableWebMvc // 开启web mvc基础设施支持
public class AppConfig extends WebMvcConfigurerAdapter{
	public void configureViewResolvers(ViewResolverRegistry rigistry) {
		rigistry.jsp("/WEB-INF/jsp",".jsp");
	}
		
}
