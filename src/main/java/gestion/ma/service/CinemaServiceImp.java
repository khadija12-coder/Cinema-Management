package gestion.ma.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import gestion.ma.entities.Categorie;
import gestion.ma.entities.Cinema;
import gestion.ma.entities.Film;
import gestion.ma.entities.Place;
import gestion.ma.entities.Projection;
import gestion.ma.entities.Salle;
import gestion.ma.entities.Seance;
import gestion.ma.entities.Ticket;
import gestion.ma.entities.Ville;
import gestion.ma.repo.CategorieRepository;
import gestion.ma.repo.CinemaRepository;
import gestion.ma.repo.FilmRepository;
import gestion.ma.repo.PlaceRepository;
import gestion.ma.repo.ProjectionRepository;
import gestion.ma.repo.SalleRepository;
import gestion.ma.repo.SeanceRepository;
import gestion.ma.repo.TicketRepository;
import gestion.ma.repo.VilleRepository;
@Service
@Transactional
public class CinemaServiceImp implements CinemaInitService {

	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Override
	public void initVilles() {
		Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
			Ville ville=new Ville();
			ville.setName(nameVille);
		  villeRepository.save(ville);
		});
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("Megarama","IMAX","FOUNOUN","CHAHRAZAD","DAOULIZ").forEach(nameCinema->{
				
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
			
			
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
			Salle salle = new Salle();
			salle.setNombrePlace(15+(int)(Math.random()*20));
			salle.setCinema(cinema);
			salle.setName("Salle"+(i+1));
			salleRepository.save(salle);
			}
		});
		
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
			
		});
	}

	@Override
	public void initSeances() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","17:00","21:00").forEach(s->{
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (java.text.ParseException e) {
				
				e.printStackTrace();
			}
			
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("Histoire","Actions","Science-Fiction","Horreur").forEach(c->{
			Categorie categorie = new Categorie();
			categorie.setName(c);
			categorieRepository.save(categorie);
			
		});
		
	}

	@Override
	public void initfilms() {
		double[] durees = new double[] {1,1.5,2,2.5,5,3};
		List <Categorie>  c = categorieRepository.findAll();
		Stream.of("Game of Thrones","Seigneur des anneaux","Gladiator","Viking","GreenBook","LeParrain").forEach(namefilm->{
			Film film = new Film();
			film.setTitre(namefilm);
			film.setDuree(durees [new Random().nextInt(durees.length)]);
			film.setPhoto(namefilm.replaceAll("", "")+".jpg");
			film.setCategorie(c.get(new Random().nextInt(c.size())));
			filmRepository.save(film);
		});
		
	}

	@Override
	public void initProjections() {
		double[] prices = new double[] {30,50,60,70,90,100};
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmRepository.findAll().forEach(film->{
						seanceRepository.findAll().forEach(seance->{
							Projection projection = new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setSalle(salle);
							projection.setSalle(salle);
							projection.setPrix(prices [new Random().nextInt(prices.length)]);
							projectionRepository.save(projection);
						});
					});
					
				});
			});
		});
		
	}

	@Override
	public void initTicket() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReserve(false);
				ticketRepository.save(ticket);
				
				
			});;
		});
		
	}

}
