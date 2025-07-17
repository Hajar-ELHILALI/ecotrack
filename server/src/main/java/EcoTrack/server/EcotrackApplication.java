package EcoTrack.server;

import EcoTrack.server.records.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		RsaKeyProperties.class,
})
@SpringBootApplication
public class EcotrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcotrackApplication.class, args);
	}

}
