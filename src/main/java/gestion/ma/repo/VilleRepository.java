package gestion.ma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gestion.ma.entities.Ville;
@RepositoryRestResource
public interface VilleRepository  extends JpaRepository<Ville,Long>{

}
