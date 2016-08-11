package ch.sbb.cloud.autoscaler.usecase;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class FrontendserviceApplication {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rest = new RestTemplate();

		//need to create a new message converter to handle hal+json
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(mapper);

		//add the new converters to the restTemplate
		//but make sure it is BEFORE the exististing converters
		List<HttpMessageConverter<?>> converters = rest.getMessageConverters();
		converters.add(0,converter);
		rest.setMessageConverters(converters);

		return rest;
	}

	public static void main(String[] args) {
		SpringApplication.run(FrontendserviceApplication.class, args);
	}
}
