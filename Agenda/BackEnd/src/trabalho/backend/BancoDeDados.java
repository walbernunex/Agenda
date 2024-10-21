package trabalho.backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalho.dominio.PersistenceService;
import trabalho.dominio.Usuario;
public class BancoDeDados implements PersistenceService{

	private static Map<String, Usuario> mapa = 
			new HashMap<String, Usuario>();

	public BancoDeDados() {
		inicializa();
	}
	public static void main(String[] args) {
		inicializa();
	}
 
	
	public static void inicializa() {
		Usuario usuario1 = new 
		Usuario("José","11111111111","123","jose@email.com");
		Usuario usuario2 = new 
		Usuario("João","22222222222","123","joao@email.com");
		Usuario usuario3 = new 
		Usuario("Maria","33333333333","123", "maria@email.com");
		mapa.put(usuario1.getCpf(), usuario1);
		mapa.put(usuario2.getCpf(), usuario2);
		mapa.put(usuario3.getCpf(), usuario3);
		System.out.println(mapa);
	}
	
    public <T> List<T> listarElementos(Class<T> clazz) {
        List<T> lista = new ArrayList<>();
        for (Usuario usuario : mapa.values()) {
            if (clazz.isInstance(usuario)) {
                lista.add(clazz.cast(usuario));
            }
        }
        return lista;
    }
    
    
	@Override
	public <T> T recupera(String key) {
		return (T) mapa.get(key);
	}
	@Override
	public <T> T grava(String key, Object o) {
		return (T) mapa.put(key, null);
	}
	@Override
	public boolean apagar(String key, Object o) {
		return mapa.remove(key, o);
	}


}