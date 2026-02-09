package io.spring.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringAiCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiCamelApplication.class, args);
	}

	@EventListener(classes = {ApplicationReadyEvent.class})
	void onApplicationEvent(ApplicationReadyEvent event) {
		ApplicationContext ctx = event.getApplicationContext();
	}

}
