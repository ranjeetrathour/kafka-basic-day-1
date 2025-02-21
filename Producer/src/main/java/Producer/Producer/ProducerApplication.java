package Producer.Producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}

@Configuration
class KafkaConfig{

	@Bean
	public NewTopic AlphaTopic(){
		return TopicBuilder.name("aplha-topic-1")
				.partitions(5)
//				.replicas(5)     this is not working need to check
				.build();
	}
}

@Service
class KafkaProducerService{

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void produceMessage(String message){
		this.kafkaTemplate.send("alpha-topic-1",message );
	}
}

@RestController
@RequestMapping("/produce")
class Producer{

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@PostMapping("/data")
	public ResponseEntity<?> produce(){

		String message = UUID.randomUUID().toString();
		kafkaProducerService.produceMessage(message);

		return ResponseEntity.ok("message created");

	}
}