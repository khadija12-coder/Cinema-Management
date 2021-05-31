package gestion.ma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import gestion.ma.entities.Salle;
@RepositoryRestResource
public interface SalleRepository  extends JpaRepository<Salle, Long>{

}
