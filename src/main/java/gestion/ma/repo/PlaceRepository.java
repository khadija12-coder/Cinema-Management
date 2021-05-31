package gestion.ma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gestion.ma.entities.Place;
@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Long>{

}
