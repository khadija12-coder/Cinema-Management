package gestion.ma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gestion.ma.entities.Film;
@RepositoryRestResource
public interface FilmRepository  extends JpaRepository<Film, Long>{

}
