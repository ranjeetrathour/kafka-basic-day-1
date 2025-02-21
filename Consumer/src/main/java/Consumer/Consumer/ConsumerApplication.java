package Consumer.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}

//@Configuration
//class KafkaConfig{
//
//}


@Service
class KafkaConsumer{

	@KafkaListener(topics = "alpha-topic-1", groupId = "group-1")
	public void consume(String message){
		System.out.println("message conusme "+message);
	}

}
