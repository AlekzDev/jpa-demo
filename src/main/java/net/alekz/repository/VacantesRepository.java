package net.alekz.repository;

import net.alekz.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
