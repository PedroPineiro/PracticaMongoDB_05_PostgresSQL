package com.pedro.PracticaMongoDB_05_PostgresSQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PracticaMongoDb05PostgresSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaMongoDb05PostgresSqlApplication.class, args);
	}

}
