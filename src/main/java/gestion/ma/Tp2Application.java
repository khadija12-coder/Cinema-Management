package gestion.ma;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import gestion.ma.service.CinemaInitService;






@SpringBootApplication
public class Tp2Application implements  CommandLineRunner {

@Autowired

private CinemaInitService ic;
	

	
	public static void main(String[] args) {
		SpringApplication.run(Tp2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ic.initVilles();
		ic.initCinemas();
		ic.initSalles();
		ic.initPlaces();
		ic.initSeances();
		ic.initCategories();
		ic.initfilms();
		ic.initProjections();
		ic.initTicket();
		
	   
		
		
	}

}
