import java.util.ArrayList;
import java.util.List;

public class PetShopRepositorio {
    
	private final ArrayList<Animal> animais = new ArrayList<>();

	/** Adiciona um animal à lista. */
	public void adicionar(Animal a) {
		animais.add(a);
	}

	/**
	 * Busca um animal pelo nome (case-insensitive).
	 * 
	 * 
	 */
	public Animal buscarPorNome(String nome) {
      for (Animal animal : animais) {
        if (animal.getNome().equalsIgnoreCase(nome)) {
            return animal;
        }
    }
		return null;
	}

	/**
	 * Remove o animal com o nome informado.
	 * 
	 * 
	 */
	public boolean remover(String nome) {
        Animal animal = buscarPorNome(nome);

    if (animal != null) {
        animais.remove(animal);
        return true;
    }
		return false;
	}

	/** Retorna a lista completa de animais cadastrados (cópia defensiva). */
	public ArrayList<Animal> listarTodos() {
		return animais;
	}

	/** Quantidade de animais cadastrados no repositório. */
	public int quantidade() {
		return animais.size();
	}
}

