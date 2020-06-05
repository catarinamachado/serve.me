package EA_ServeMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ServeMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeMeApplication.class, args);
	}

}
