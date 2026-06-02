
public class Gato extends Animal {
	private boolean arranhaMoveis;
	Gato(String nome, Pessoa dono, int idade, boolean arranhaMoveis){
		super(nome, dono, idade);
		this.arranhaMoveis = arranhaMoveis;
	}
	@Override
	public void emitirSom() {
		System.out.println("MIAU!");	
	}
}
