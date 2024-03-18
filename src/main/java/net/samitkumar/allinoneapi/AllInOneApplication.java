package net.samitkumar.allinoneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class AllInOneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllInOneApiApplication.class, args);
	}

	@Bean
	CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}
}

@Controller
class ApplicationController {
	@GetMapping("/")
	public Mono<String> index() {
		return Mono.just("index");
	}
	@GetMapping("/album")
	public Mono<String> album() {
		return Mono.just("album");
	}
	@GetMapping("/emp-management")
	public Mono<String> empManagement() {
		return Mono.just("emp-management");
	}
}
