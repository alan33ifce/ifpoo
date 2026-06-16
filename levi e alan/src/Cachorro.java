
public class Cachorro extends Animal implements AtendivelNoEstetica {
	private String raca;
	Cachorro(String nome, Pessoa dono, int idade, String raca){
		super(nome, dono, idade);
		this.raca = raca;
	}
	   public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
	@Override
	public void emitirSom() {
		System.out.println("AUAU!");	
	}

	@Override
public void darBanho() {
    System.out.println("Dando banho no cachorro...");
}

@Override
public void cortarUnhas() {
    System.out.println("Cortando unhas do cachorro...");
}
}
