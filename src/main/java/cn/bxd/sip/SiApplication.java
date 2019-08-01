package cn.bxd.sip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn")
public class SiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiApplication.class, args);
	}

}