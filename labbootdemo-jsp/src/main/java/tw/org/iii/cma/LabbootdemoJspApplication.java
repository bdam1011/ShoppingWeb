package tw.org.iii.cma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//@Configuration
//@ComponentScan(basePackages = {"要掃描的package"})
//@EnableAutoConfiguration只能用一次，自動設定configuration
//上3被下取代(如果所有自訂類別，全部都放在專案基礎套件(package tw.org.iii.cma)下的"子package"的話)，所以下也只能用一次
@SpringBootApplication//自動掃描package tw.org.iii.cma
@ServletComponentScan
public class LabbootdemoJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabbootdemoJspApplication.class, args);
	}

}
