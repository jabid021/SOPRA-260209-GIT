package demoSpring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.config.AppConfig;

public class SpringApplication {

	public static void main(String[] args) {
		//On ne peut avoir qu'une seule config principale
		//Config principale en XML
	//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	//Config principale en JAVA
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ctx.getBeanFactory().createBean(Test.class).run();
		ctx.close();

	}

}
