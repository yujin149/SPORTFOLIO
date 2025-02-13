package com.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 자동으로 현재 시간 설정을 위한 Entity에서 @CreatedDate사용
public class SportfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportfolioApplication.class, args);
	}

}
