package net.samitkumar.allinone;

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
//@EnableWebFluxSecurity
public class AllInOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllInOneApplication.class, args);
	}

	@Bean
	CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}

	/*@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
		http.csrf(ServerHttpSecurity.CsrfSpec::disable);
		return http.build();
	}*/
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
	@GetMapping("/mnc")
	public Mono<String> empManagement() {
		return Mono.just("mnc");
	}
}
