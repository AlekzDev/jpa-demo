package net.alekz;

import net.alekz.model.Categoria;
import net.alekz.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication  implements CommandLineRunner {

	//@Autowired
	private CategoriasRepository categoriasRepository;
	
	public JpaDemoApplication(CategoriasRepository categoriasRepository){
		this.categoriasRepository = categoriasRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//guardar();
		//modificar(2);
		//System.out.println(buscarporid(2));
		//eliminar(1);
		//conteo();
		eliminarTodos();
	}

	public void guardar(){
		Categoria categoria = new Categoria();
		categoria.setNombre("Finanzas");
		categoria.setDescripcion("Trabajos relacionados con fiananzas y contabilidad");
		categoriasRepository.save(categoria);
		System.out.println(categoria);
	}

	public Categoria buscarporid(Integer id){
		Optional<Categoria> categoriaOp = categoriasRepository.findById(id);
		return categoriaOp.orElse(null);
	}

	public void modificar(Integer id){
		Categoria cat = buscarporid(id);
		cat.setNombre("Ingeniería de software");
		cat.setDescripcion("Desarrollo de sistemas");
		categoriasRepository.save(cat);
	}

	public void eliminar(Integer id){
		categoriasRepository.deleteById(id);
	}

	public void conteo(){
		System.out.println("Total de categorías: " + categoriasRepository.count());
	}

	public void eliminarTodos(){
		categoriasRepository.deleteAll();
	}
}
