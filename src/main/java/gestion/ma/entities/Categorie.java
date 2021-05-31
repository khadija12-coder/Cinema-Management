package gestion.ma.entities;



import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categorie{
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@Column(length=75)
private String name;
@OneToMany(mappedBy="categorie")
@JsonProperty(access=Access.WRITE_ONLY) // meme chose qu'on je consulte la categorie d'un film cherché c'est pas la peine de me donner les films de cette categorie
private Collection<Film> films;
}
