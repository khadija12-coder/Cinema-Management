package gestion.ma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gestion.ma.entities.Cinema;
@RepositoryRestResource
public interface CinemaRepository extends JpaRepository<Cinema,Long> {

}
