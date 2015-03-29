package net.atlassc.ShinChven.springwebmvc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ShinChven on 15/3/29.
 */
@Configuration
@ComponentScan("net.atlassc.ShinChven.springwebmvc.*")
@Import({DataBaseConfig.class})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * <bean id="templateResolver"
     * class="org.thymeleaf.templateresolver.ServletContextTemplateResolver">
     * <property name="prefix" value="/WEB-INF/templates/"/>
     * <property name="suffix" value=".html"/>
     * <property name="templateMode" value="HTML5"/>
     * <property name="characterEncoding" value="UTF-8"/>
     * <property name="cacheable" value="false"/>
     * </bean>
     *
     * @return
     */
    @Bean
    ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * <bean id="templateEngine"
     * class="org.thymeleaf.spring4.SpringTemplateEngine">
     * <property name="templateResolver" ref="templateResolver"/>
     * </bean>
     *
     * @return
     */
    @Bean
    SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * <bean class="org.thymeleaf.spring4.view.ThymeleafViewResolver">
     * <property name="templateEngine" ref="templateEngine"/>
     * <property name="characterEncoding" value="UTF-8"/>
     * </bean>
     *
     * @return
     */
    @Bean
    ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * 设置Converters
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // StringHttpMessageConverter
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
        converters.add(stringHttpMessageConverter);
        // JacksonMapper
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(mappingJackson2HttpMessageConverter);
        super.configureMessageConverters(converters);
    }
}
