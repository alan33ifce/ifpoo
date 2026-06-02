
public class ClinicaVeterinaria {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa("Levi", "123456");
		Pessoa p2 = new Pessoa("Alan", "654321");
		Animal[] filaAtendimento = new Animal[2];
		filaAtendimento[0] = new Cachorro("Rex", p1, 5, "Pastor Alemão");
		filaAtendimento[1] = new Gato("Mingau", p2, 2, true);
		for(int i = 0; i < filaAtendimento.length; i++) {
			filaAtendimento[i].emitirSom();
		}
	}

}
