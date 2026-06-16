
public class ClinicaVeterinaria {
	public static void main(String[] args) {

		Pessoa p1 = new Pessoa("Levi", "123456");
		Pessoa p2 = new Pessoa("Alan", "654321");

		AtendivelNoEstetica[] filaEstetica = new AtendivelNoEstetica[1];
		filaEstetica[0] =  new Cachorro("Rex", p1, 5, "Pastor Alemão");

		for (AtendivelNoEstetica animal : filaEstetica) {
			animal.darBanho();
			animal.cortarUnhas();
		}
		// dá erro isso aqui -> Animal a = new Animal("Invalido", 1);
	}
}


