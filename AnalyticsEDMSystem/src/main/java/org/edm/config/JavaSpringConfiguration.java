package org.edm.config;

import org.edm.dao.DetailDAO;
import org.edm.models.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("org.edm")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class JavaSpringConfiguration implements WebMvcConfigurer{
    private final ApplicationContext applicationContext;
    @Autowired
    public JavaSpringConfiguration(ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
    @Bean
    public SessionFactory sessionFactory(){
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(MachineEDM.class)
                .addAnnotatedClass(DetailDAO.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Sensor.class)
                .addAnnotatedClass(SensorIndicator.class)
                .addAnnotatedClass(MachineIndicator.class)
                .buildSessionFactory();
        return sessionFactory;
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(
                        "/assets/css/**",
                        "/edmMachine/{d+}/assets/css/**",
                        "/edmMachine/assets/css/**",
                        "/employee/assets/css/**",
                        "/employee/{d+}/assets/css/**",
                        "/assets/img/**",
                        "/clients/{d+}/assets/css/**",
                        "/clients/assets/css/**",
                        "/assets/model/**"
                        )
                .addResourceLocations(
                        "/WEB-INF/static/assets/css/",
                        "/WEB-INF/static/assets/img/",
                        "/WEB-INF/static/assets/model/"
                        );


    }

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("loginPage");
    }
}
