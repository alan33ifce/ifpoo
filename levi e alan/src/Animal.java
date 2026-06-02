
public class Animal {
	protected String nome;
	protected int idade;
	protected boolean faminto;
	protected Pessoa dono;
	static protected int totalAnimais;
	
	Animal(String nome, Pessoa dono, int idade){
		Animal.totalAnimais = Animal.totalAnimais +1;
		this.nome = nome;
		this.dono = dono;
		this.idade = idade;
	}

	public void emitirSom() {
		System.out.println("O animal emite um som");
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean isFaminto() {
		return faminto;
	}

	public void setFaminto(boolean faminto) {
		this.faminto = faminto;
	}

	public static int getTotalAnimais() {
		return totalAnimais;
	}

	public Pessoa getDono() {
		return dono;
	}

	public void setDono(Pessoa dono) {
		this.dono = dono;
	}
	
	
}
