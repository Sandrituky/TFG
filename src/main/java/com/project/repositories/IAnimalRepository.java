package com.project.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.model.Animal;
import com.project.model.Estado;
import com.project.model.Sexo;
import com.project.model.Tipo;
import com.project.model.Usuario;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
	
	boolean existsById(int id);
	
	Optional <Animal> findOneAnimalById(int id);
	
	Animal findAnimalById(int id);
	
	Animal findAnimalByFoto(String foto);
	
	List <Animal> findAllAnimalesByEstado(Estado estado);
	
	
	
	//Filtra animales por (tipo && estado), usado para mostrar las fichas de los animales
	List <Animal> findAllAnimalesByTipoAndEstado(Tipo tipo, Estado estado);
	
	//Filtra animales por (usuario && estado), usado para mostrar las fichas de los animales
	List <Animal> findAllAnimalesByOwnerAndEstado(Usuario owner, Estado estado);
	
	
	
	//_____________________________(usado para filtrar animales en Vista Modificar)

	//Filtra animales por (tipo && sexo && estado(en adopcion)))
	List <Animal> findAllAnimalesByEstadoAndTipoAndSexo(Estado estado, Tipo tipo, Sexo sexo);
	
	//Filtra animales por (tipo || sexo)
	List <Animal> findAllAnimalesByEstadoAndTipoOrEstadoAndSexo(Estado estado, Tipo tipo, Estado estado2, Sexo sexo);
//_______________________
	
	
	//Funcion para reservar, adoptar y poner en adopcion
	@Modifying
	@Query(value="UPDATE Animal SET ESTADO = ?1, USUARIO_ID = ?2 WHERE ID=?3", nativeQuery=true)
	int setEstadoAndOwnerForId(Estado estado, int owner, int id);
	
	
	@Query(value="SELECT enum_range(NULL::myenum)", nativeQuery=true)
	List <Estado> findAllEstados();
	
	
	//saca el nº de animales reservados por un usuario (tambien serviria para sacar nº de adoptados).
	int countByOwnerAndEstado(Usuario usuario, Estado estado);
	
	
	
	
	List <Animal> findByEstadoOrderByFechaAltaDesc(Estado estado);
	
	
	


	
	
	
	
	
}