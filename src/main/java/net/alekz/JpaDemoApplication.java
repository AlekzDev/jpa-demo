package net.alekz;

import net.alekz.model.Categoria;
import net.alekz.model.Perfil;
import net.alekz.model.Usuario;
import net.alekz.model.Vacante;
import net.alekz.repository.CategoriasRepository;
import net.alekz.repository.PerfilesRepository;
import net.alekz.repository.UsuariosRepository;
import net.alekz.repository.VacantesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication  implements CommandLineRunner {

	//@Autowired
	private final CategoriasRepository categoriasRepository;
	private final VacantesRepository vacantesRepository;
	private final PerfilesRepository perfilesRepository;
	private final UsuariosRepository usuariosRepository;
	
	public JpaDemoApplication(CategoriasRepository categoriasRepository, VacantesRepository vacantesRepository, PerfilesRepository perfilesRepository, UsuariosRepository usuariosRepository){
		this.categoriasRepository = categoriasRepository;
		this.vacantesRepository = vacantesRepository;
		this.perfilesRepository = perfilesRepository;
		this.usuariosRepository = usuariosRepository;
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
		//buscarTodosPaginacionOrdenamiento();
		//buscarVacantes();
		//guardarVacante();
		//modificarVacante(14);
		//guardarPerfiles();
		//crearUsuarioPerfiles();
		buscarUsuario(2);
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

	/**
	 * Ejemplos sobre entidad Vacante
	 */
	public void buscarVacantes(){
		List<Vacante> vacantes = vacantesRepository.findAll(Sort.by("categoria.nombre"));
		vacantes.forEach(vacante -> System.out.println("Categoria: " + vacante.getCategoria().getNombre() +
				"/ Vacante: " + vacante.getNombre() + " (id - "+ vacante.getId()+")"));
	}

	public void guardarVacante(){
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor");
		vacante.setDescripcion("Profesor sustituto");
		vacante.setFecha(new Date());
		vacante.setSalario(18500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("sustituto.png");
		vacante.setDetalles("<<<<DETALLES>>>>");
		vacante.setCategoria(buscarporid(15)); //Se busca Categoria
		vacantesRepository.save(vacante);
	}

	public void modificarVacante(Integer id){
		Optional<Vacante> vacanteO = vacantesRepository.findById(id);
		Vacante vacante = vacanteO.orElse(null);
		if(vacante != null){
			vacante.setDetalles("<H1>Requesitos para el profesor sustituro</H1>");
			vacantesRepository.save(vacante);
		}

	}


	public List<Perfil> getPerfilesAplicacion(){
		List<Perfil> perfiles = new LinkedList<>();
		perfiles.add(new Perfil("SUPERVISOR"));
		perfiles.add(new Perfil("ADMINISTRADOR"));
		perfiles.add(new Perfil("USUARIO"));
		return perfiles;
	}

	public void guardarPerfiles(){
		perfilesRepository.saveAll(getPerfilesAplicacion());
	}

	public void crearUsuarioPerfiles(){
		Usuario usuario = new Usuario();
		usuario.setUsername("avargas");
		usuario.setNombre("Alejandro Vargas");
		usuario.setEmail("avargas@mail.com");
		usuario.setFechaRegistro(new Date());
		usuario.setPassword("12345");
		usuario.setEstatus(1);
		usuario.addPerfil(getPerfilbyId(1));
		usuario.addPerfil(getPerfilbyId(2));

		usuariosRepository.save(usuario);
	}

	public Perfil getPerfilbyId(Integer id){
		return perfilesRepository.getById(id);
	}

	public void buscarUsuario(Integer id){
		Optional<Usuario> usuarioOptional = usuariosRepository.findById(id);
		Usuario usuario = usuarioOptional.orElse(null);
		if(usuario != null){
			List<Perfil> perfiles = usuario.getPerfiles();
			System.out.println(usuario.getNombre());
			if(perfiles != null)
				for(Perfil p : perfiles)
					System.out.println(p.getPerfil());

		} else
			System.out.println("No se encontró usuario con id: " + id);

	}

}
