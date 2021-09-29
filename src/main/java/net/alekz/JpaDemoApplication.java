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
		System.out.println(buscarporid(1));
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
}
