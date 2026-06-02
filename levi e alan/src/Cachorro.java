
public class Cachorro extends Animal { 
	private String raca;
	Cachorro(String nome, Pessoa dono, int idade, String raca){
		super(nome, dono, idade);
		this.raca = raca;
	}
	@Override
	public void emitirSom() {
		System.out.println("AUAU!");	
	}
}
