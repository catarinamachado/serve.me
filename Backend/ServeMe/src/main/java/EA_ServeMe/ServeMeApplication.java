package EA_ServeMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ServeMeApplication {

	public static void main(String[] args)
	{

		SpringApplication app = new SpringApplication(ServeMeApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);
	}

}
