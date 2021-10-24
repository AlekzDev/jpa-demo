package net.alekz;

import net.alekz.model.Categoria;
import net.alekz.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
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
		//eliminarTodos();
		//encontrarPorIds();
		//buscarTodas();
		//existeid(20);
		//guardarTodos();
		//buscarTodosJpa();
		//borrarTodosEnBatch();
		//buscarTodosOrdenados();
		//buscarTodosPaginacion();
		buscarTodosPaginacionOrdenamiento();
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

	public void encontrarPorIds(){
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = categoriasRepository.findAllById(ids);
		categorias.forEach(System.out::println);
	}

	public void buscarTodas(){
		Iterable<Categoria> categorias = categoriasRepository.findAll();
		categorias.forEach(System.out::println);
	}

	public void existeid(Integer id){
		if(categoriasRepository.existsById(id))
			System.out.println("El registro existe: " + buscarporid(id));
		else
			System.out.println("El registro no existe");
	}

	public void guardarTodos(){
		List<Categoria> categorias = new LinkedList<Categoria>();
		Categoria cat1 = new Categoria();
		cat1.setId(16);
		cat1.setNombre("Materia optativa 1");
		cat1.setDescripcion("Materia optativa 1");

		Categoria cat2 = new Categoria();
		cat2.setId(17);
		cat2.setNombre("Materia optativa 2");
		cat2.setDescripcion("Materia optativa 2");

		Categoria cat3 = new Categoria();
		cat3.setId(18);
		cat3.setNombre("Materia optativa 3");
		cat3.setDescripcion("Materia optativa 3");

		categorias.add(cat1);
		categorias.add(cat2);
		categorias.add(cat3);

		categoriasRepository.saveAll(categorias);
	}

	public void buscarTodosJpa(){
		List<Categoria> categorias = categoriasRepository.findAll();
		categorias.forEach(System.out::println);
	}

	public void borrarTodosEnBatch(){
		categoriasRepository.deleteAllInBatch();
	}

	public void buscarTodosOrdenados(){
		List<Categoria> categorias = categoriasRepository.findAll(Sort.by("nombre").descending());
		categorias.forEach(System.out::println);
	}

	public void buscarTodosPaginacion(){
		Page<Categoria> categorias = categoriasRepository.findAll(PageRequest.of(0,5));
		System.out.println("Total elementos: " + categorias.getTotalElements());
		System.out.println("Total páginas: " + categorias.getTotalPages());
		categorias.forEach(System.out::println);
	}

	public void buscarTodosPaginacionOrdenamiento(){
		Page<Categoria> categorias = categoriasRepository.findAll(PageRequest.of(0,5,Sort.by("nombre")));
		System.out.println("Total elementos: " + categorias.getTotalElements());
		System.out.println("Total páginas: " + categorias.getTotalPages());
		categorias.forEach(System.out::println);
	}
}
