package net.alekz.repository;

import net.alekz.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
    List<Vacante> findByEstatusEquals(String estatus);
    List<Vacante> findByEstatusInOrderByEstatusDesc(String[] estatus);
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
    List<Vacante> findBySalarioBetweenOrderBySalario(Double salarioIni, Double salarioFin);
}
