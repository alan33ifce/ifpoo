import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPetShop extends JFrame {

	private final PetShopRepositorio repositorio = new PetShopRepositorio();

	// ── Campos do formulário ───────────────────────────────
	private final JTextField campNome = new JTextField(10);
	private final JTextField campRaca = new JTextField(10);
	private final JTextField campIdade = new JTextField(5);
	private final JTextField campTutor = new JTextField(10);
	private final JTextField campTelefone = new JTextField(10);
	// ── Área de resultado ──────────────────────────────────
	private final JTextArea areaResultado = new JTextArea(12, 50);

	// ── Botões ─────────────────────────────────────────────
	private final JButton btnCadastrar = new JButton("Cadastrar");
	private final JButton btnBuscar = new JButton("Buscar");
	private final JButton btnAtualizar = new JButton("Atualizar");
	private final JButton btnRemover = new JButton("Remover");
	private final JButton btnListarTodos = new JButton("Listar Todos");
	// ── Construtor ─────────────────────────────────────────
	public TelaPetShop() {
		super("Pet Shop — Gerenciador de Animais");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// O JFrame usa BorderLayout por padrão
		setLayout(new BorderLayout(8, 8));

		add(criarPainelFormulario(), BorderLayout.NORTH);
		add(criarAreaResultado(), BorderLayout.CENTER);
		add(criarPainelBotoes(), BorderLayout.SOUTH);

		configurarListeners();

		setSize(900, 600);
		pack();
		setLocationRelativeTo(null); // centraliza na tela

		setVisible(true);
	}

	// ── Painel Norte: formulário ───────────────────────────
	private JPanel criarPainelFormulario() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
		painel.setBorder(BorderFactory.createTitledBorder("Dados do Pet e Tutor"));

		painel.add(new JLabel("Nome:"));
		painel.add(campNome);
		painel.add(new JLabel("Raça:"));
		painel.add(campRaca);
		painel.add(new JLabel("Idade:"));
		painel.add(campIdade);
		painel.add(new JLabel("Tutor:"));
		painel.add(campTutor);
		painel.add(new JLabel("Telefone:"));
		painel.add(campTelefone);
		

		return painel;
	}

	// ── Centro: área de texto com scroll ──────────────────
	private JScrollPane criarAreaResultado() {
		areaResultado.setEditable(false);
		areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
		areaResultado.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		exibirTexto("Bem-vindo ao sistema do Pet Shop!\n"
				+ "Preencha os campos acima e use os botões para gerenciar os pets.\n");
		return new JScrollPane(areaResultado);
	}

	// ── Painel Sul: botões ─────────────────────────────────
	private JPanel criarPainelBotoes() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
		painel.add(btnCadastrar);
	painel.add(btnBuscar);
	painel.add(btnAtualizar);
	painel.add(btnRemover);
	painel.add(btnListarTodos);
		return painel;
	}

	// ── ActionListeners ────────────────────────────────────
	private void configurarListeners() {

		// ---- CADASTRAR ----
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = campNome.getText().trim();
				String raca = campRaca.getText().trim();
				String textoIdade = campIdade.getText().trim();
				String nomeTutor = campTutor.getText().trim();
				String telefone = campTelefone.getText().trim();

				Pessoa dono = new Pessoa(nomeTutor, telefone);

				int idade;
				try {
   				 idade = Integer.parseInt(textoIdade);
				}
				 catch (NumberFormatException ex) {
 				 exibirTexto("ERRO: Idade inválida.");
  				 return;
				}

				if (nome.isEmpty()) {
					exibirTexto("ERRO: O campo Nome é obrigatório.");
					return;
				}

				if (contemNumero(nome)) {
  					exibirTexto("Nome não pode conter números.");
   					return;
				}

				if (raca.isEmpty())
					raca = "Indefinida";

				if (contemNumero(nomeTutor)) {
  				  exibirTexto("Nome do tutor não pode conter números.");
   					 return;
				}
				if (!telefone.isEmpty() && !telefone.matches("\\d+")) {
  					exibirTexto("Telefone deve conter apenas números.");
   					return;
				}
				Cachorro novo = new Cachorro(nome, idade, raca); 
				novo.setDono(dono);
				repositorio.adicionar(novo);
				exibirTexto("Pet cadastrado com sucesso!\n\n" + novo.exibirDados());
				limparCampos();
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        String nome = campNome.getText().trim();

        if (nome.isEmpty()) {
            exibirTexto("Digite o nome para buscar.");
            return;
        }

        Animal a = repositorio.buscarPorNome(nome);

        if (a != null) {
            exibirTexto("Encontrado:\n\n" + a.exibirDados());
        } else {
            exibirTexto("Animal não encontrado.");
        }
    }
});

btnRemover.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        String nome = campNome.getText().trim();

        if (nome.isEmpty()) {
            exibirTexto("Digite o nome para remover.");
            return;
        }

        boolean ok = repositorio.remover(nome);

        if (ok) {
            exibirTexto("Removido com sucesso.");
            limparCampos();
        } else {
            exibirTexto("Animal não encontrado.");
        }
    }
});

btnListarTodos.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        if (repositorio.quantidade() == 0) {
            exibirTexto("Nenhum animal cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (Animal a : repositorio.listarTodos()) {
            sb.append(a.exibirDados()).append("\n\n");
        }

        exibirTexto(sb.toString());
    }
});

btnAtualizar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        String nome = campNome.getText().trim();

        Animal a = repositorio.buscarPorNome(nome);

        if (a == null) {
            exibirTexto("Animal não encontrado.");
            return;
        }

        String raca = campRaca.getText().trim();
        String textoIdade = campIdade.getText().trim();
        String tutor = campTutor.getText().trim();

        if (!raca.isEmpty()) {
            if (a instanceof Cachorro c) {
                c.setRaca(raca);
            }
        }

        if (!tutor.isEmpty()) {
    	Pessoa p = new Pessoa(tutor, campTelefone.getText().trim());
    	a.setDono(p);
}

        try {
            if (!textoIdade.isEmpty()) {
                a.setIdade(Integer.parseInt(textoIdade));
            }
        } catch (NumberFormatException ex) {
            exibirTexto("Idade inválida.");
            return;
        }

        exibirTexto("Atualizado com sucesso!\n\n" + a.exibirDados());
        limparCampos();
    }
});


	}

	// ── Métodos auxiliares ─────────────────────────────────

	/** Exibe texto na área de resultado, substituindo o conteúdo anterior. */
	private void exibirTexto(String texto) {
		areaResultado.setText(texto);
	}

	private boolean contemNumero(String texto) {
    return texto.matches(".*\\d.*");
}

	/** Limpa todos os campos do formulário. */
	private void limparCampos() {
    	campNome.setText("");
    	campRaca.setText("");
  		campIdade.setText("");
  		campTutor.setText("");
  		campTelefone.setText("");
   		campNome.requestFocus();
	}

}