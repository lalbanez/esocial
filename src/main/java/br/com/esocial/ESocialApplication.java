package br.com.esocial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@EnableScheduling
public class ESocialApplication implements CommandLineRunner {
	
//	@Autowired
//	private QuartzApp quartz;
	
	/// For now, you can toggle the "suspend execution on uncaught exceptions"
	/// checkbox in Java -> Debug preferences to prevent it from happening.
	public static void main(String[] args) {
		SpringApplication.run(ESocialApplication.class, args);
	}

//    @Autowired
//    PlayerService soccerService;

	@Override
	public void run(String... arg0) throws Exception {

//		quartz.startSchedulers();
//        soccerService.addBarcelonaPlayer("nishino", "Midfielder", 30);

//        List<String> players = soccerService.getAllTeamPlayers(1);
//        for(String player : players)
//        {
//            System.out.println("dwwd fefe s => " + player);
//        }
	}
}
