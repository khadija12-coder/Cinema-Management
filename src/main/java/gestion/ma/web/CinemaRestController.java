package gestion.ma.web;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gestion.ma.entities.Film;
import gestion.ma.entities.Ticket;
import gestion.ma.repo.FilmRepository;
import gestion.ma.repo.TicketRepository;
import lombok.Data;

@RestController
public class CinemaRestController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@GetMapping(path="/imageFilm/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id")Long id) throws Exception {
		Film f=filmRepository.findById(id).get();
		String photoname = f.getPhoto();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoname);
		Path path =Paths.get(file.toURI());
		return Files.readAllBytes(path);
		
	}
	
	
	@PostMapping("/payerTickets")
	public List<Ticket> payerTickets(@RequestBody TicketForme ticketForme) {
		List<Ticket> listTickets = new ArrayList<>();
		ticketForme.getTickets().forEach(idTicket->{
			Ticket ticket = ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForme.getNomClient());
			ticket.setReserve(true);
			ticket.setCodePayement(ticketForme.codePayement);
			ticketRepository.save(ticket);
			listTickets.add(ticket);
			
		});
		return listTickets;	
	}
	
	@Data
	class TicketForme{
		private String nomClient;
		private int codePayement;
		private List<Long> tickets = new ArrayList<>();
		
		
	}
	
	
	}
	


 

