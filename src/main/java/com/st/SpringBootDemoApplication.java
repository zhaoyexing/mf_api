package com.st;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;

//@RestController
@SpringBootApplication
public class SpringBootDemoApplication {




	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		//SpringApplication app = new SpringApplication(SpringBootDemoApplication.class);
		//app.setBannerMode();
		//app.run(args)
	}

}
