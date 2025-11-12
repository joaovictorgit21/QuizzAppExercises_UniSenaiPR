import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QuizApp extends JFrame implements ActionListener {

    JLabel perguntaLabel, pontuacaoLabel;
    JRadioButton op1, op2, op3, op4;
    JButton proximoBtn, iniciarBtn;
    ButtonGroup grupo;
    JComboBox<String> nivelBox;

    // controle
    int indice = 0, acertos = 0;
    double pontuacao = 0, valorPergunta = 0;
    String nivel = "";

    // perguntas
    String[][] perguntas = {
            // FÁCIL (10)
            {"Quais informações devem ser coletadas na engenharia de requisitos?",
                    "A) Requisitos do usuário e do sistema", "B) Dados do sistema", "C) UML", "D) Qualidade do software", "a"},
            {"O que seria a UML?", "A) Linguagem de programação", "B) Método", "C) Linguagem padrão e visual", "D) Levantamento de requisitos", "c"},
            {"Quais as principais categorias do teste de software?",
                    "A) Validação e Verificação", "B) Funcional e Sistema", "C) Regressão e Validação", "D) Integração", "a"},
            {"Qual desses não é um teste de caixa branca?",
                    "A) Comportamental", "B) Unitário", "C) Cobertura de loop", "D) Cobertura de instrução", "a"},
            {"O que é um requisito?",
                    "A) Descrição dos serviços e restrições do sistema", "B) Instrução vaga", "C) Entrevista com cliente", "D) Característica estética", "a"},
            {"O que é um teste de caixa preta?",
                    "A) Testa sem conhecer o código interno", "B) Inspeciona o código-fonte", "C) Valida necessidades", "D) Linguagem UML", "a"},
            {"Qual diagrama representa interação entre atores e o sistema?",
                    "A) Atores", "B) Alto Nível", "C) Casos de Uso", "D) Fluxo de Dados", "c"},
            {"Qual diagrama descreve interação entre usuários e sistema?",
                    "A) Atividades", "B) Casos de uso", "C) Classes", "D) Máquinas de estado", "b"},
            {"Função do elemento <extends> nos casos de uso UML?",
                    "A) Generalização", "B) Inclusão", "C) Dependência", "D) Executar outro caso de uso", "d"},
            {"A garantia da qualidade atua só no fim do projeto?",
                    "A) Certo", "B) Errado", "", "", "b"},

            // MÉDIO (5)
            {"Requisito não funcional do ponto eletrônico:",
                    "A) Biometria por relógio eletrônico", "B) Apurar registros", "C) Consultar registros", "D) Não ficar fora do ar > 60s", "d"},
            {"Técnica de elicitação por observação direta:",
                    "A) Etnografia", "B) Questionários", "C) Casos de uso", "D) Brainstorming", "a"},
            {"Diagrama de casos de uso modela fluxo de dados?",
                    "A) Certo", "B) Errado", "", "", "b"},
            {"Sobre UML, marque a correta:",
                    "A) Levantar requisitos com casos de uso e classes", "B) Caso de uso é difícil de validar", "C) Diagramas comportamentais são estáticos", "D) Classes não têm multiplicidade", "a"},
            {"Teste que garante que funções antigas funcionam após atualização:",
                    "A) Unitário", "B) Integração", "C) Usabilidade", "D) Regressão", "d"},

            // DIFÍCIL (5)
            {"Etapa que define funções, dados e comportamento do sistema:",
                    "A) Desenho de software", "B) Código", "C) Análise de requisitos", "D) Arquitetura", "c"},
            {"Requisitos funcionais de e-commerce:",
                    "A) I apenas", "B) II apenas", "C) I e II", "D) I e III", "a"},
            {"Correlação correta entre Login, Reservar e Notificar:",
                    "A) Extensão + Inclusão", "B) Inclusão + Extensão", "C) Generalização", "D) Inclusão de Login + Extensão", "b"},
            {"Diagramas estruturais da UML:",
                    "A) Classe e Objetos", "B) Sequência e Estados", "C) Classe e Atividades", "D) Casos de Uso e Sequência", "a"},
            {"Teste que verifica módulos isoladamente:",
                    "A) Unitário", "B) Integração", "C) Sistema", "D) Aceitação", "a"}
    };

    public QuizApp() {
        super("Quiz - Fundamentos de Software");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior
        JPanel topo = new JPanel();
        topo.add(new JLabel("Dificuldade:"));
        nivelBox = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        topo.add(nivelBox);
        iniciarBtn = new JButton("Iniciar");
        iniciarBtn.addActionListener(e -> iniciarQuiz());
        topo.add(iniciarBtn);
        add(topo, BorderLayout.NORTH);

        // Pergunta + opções
        JPanel centro = new JPanel(new GridLayout(6, 1));
        perguntaLabel = new JLabel("Escolha o nível e clique em Iniciar.", JLabel.CENTER);
        centro.add(perguntaLabel);

        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();
        grupo = new ButtonGroup();
        grupo.add(op1); grupo.add(op2); grupo.add(op3); grupo.add(op4);
        centro.add(op1); centro.add(op2); centro.add(op3); centro.add(op4);
        add(centro, BorderLayout.CENTER);

        // Painel inferior
        JPanel baixo = new JPanel();
        proximoBtn = new JButton("Próxima");
        proximoBtn.addActionListener(e -> actionPerformed(e));
        proximoBtn.setEnabled(false);
        baixo.add(proximoBtn);

        pontuacaoLabel = new JLabel("Pontuação: 0");
        baixo.add(pontuacaoLabel);
        add(baixo, BorderLayout.SOUTH);
    }

    private void iniciarQuiz() {
        nivel = (String) nivelBox.getSelectedItem();
        indice = nivel.equals("Fácil") ? 0 : nivel.equals("Médio") ? 10 : 15;
        acertos = 0;
        pontuacao = 0;
        valorPergunta = nivel.equals("Fácil") ? 0.5 : 2.0;
        proximoBtn.setEnabled(true);
        mostrarPergunta();
    }

    private void mostrarPergunta() {
        perguntaLabel.setText(perguntas[indice][0]);
        op1.setText(perguntas[indice][1]);
        op2.setText(perguntas[indice][2]);
        op3.setText(perguntas[indice][3]);
        op4.setText(perguntas[indice][4]);
        grupo.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String resp = "";
        if (op1.isSelected()) resp = "a";
        else if (op2.isSelected()) resp = "b";
        else if (op3.isSelected()) resp = "c";
        else if (op4.isSelected()) resp = "d";

        if (resp.equals(perguntas[indice][5])) {
            acertos++;
            pontuacao += valorPergunta;
        }

        indice++;
        boolean acabou = (nivel.equals("Fácil") && indice == 10)
                || (nivel.equals("Médio") && indice == 15)
                || (nivel.equals("Difícil") && indice == 20);

        if (acabou) finalizarQuiz();
        else mostrarPergunta();

        pontuacaoLabel.setText("Pontuação: " + pontuacao);
    }

    private void finalizarQuiz() {
        proximoBtn.setEnabled(false);
        int totalPerguntas = 0;

        switch (nivel) {
            case "Fácil" -> totalPerguntas = 10;
            case "Médio" -> totalPerguntas = 5;
            case "Difícil" -> totalPerguntas = 5;
            default -> {
            }
        }
        JOptionPane.showMessageDialog(this,
                "Fim do Quiz!\nVocê acertou " + acertos + " de " + totalPerguntas + " questões .\nPontuação final: " + pontuacao + " pontos.",
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().setVisible(true));
    }
}import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QuizApp extends JFrame implements ActionListener {

    JLabel perguntaLabel, pontuacaoLabel;
    JRadioButton op1, op2, op3, op4;
    JButton proximoBtn, iniciarBtn;
    ButtonGroup grupo;
    JComboBox<String> nivelBox;

    // controle
    int indice = 0, acertos = 0;
    double pontuacao = 0, valorPergunta = 0;
    String nivel = "";

    // perguntas
    String[][] perguntas = {
            // FÁCIL (10)
            {"Quais informações devem ser coletadas na engenharia de requisitos?",
                    "A) Requisitos do usuário e do sistema", "B) Dados do sistema", "C) UML", "D) Qualidade do software", "a"},
            {"O que seria a UML?", "A) Linguagem de programação", "B) Método", "C) Linguagem padrão e visual", "D) Levantamento de requisitos", "c"},
            {"Quais as principais categorias do teste de software?",
                    "A) Validação e Verificação", "B) Funcional e Sistema", "C) Regressão e Validação", "D) Integração", "a"},
            {"Qual desses não é um teste de caixa branca?",
                    "A) Comportamental", "B) Unitário", "C) Cobertura de loop", "D) Cobertura de instrução", "a"},
            {"O que é um requisito?",
                    "A) Descrição dos serviços e restrições do sistema", "B) Instrução vaga", "C) Entrevista com cliente", "D) Característica estética", "a"},
            {"O que é um teste de caixa preta?",
                    "A) Testa sem conhecer o código interno", "B) Inspeciona o código-fonte", "C) Valida necessidades", "D) Linguagem UML", "a"},
            {"Qual diagrama representa interação entre atores e o sistema?",
                    "A) Atores", "B) Alto Nível", "C) Casos de Uso", "D) Fluxo de Dados", "c"},
            {"Qual diagrama descreve interação entre usuários e sistema?",
                    "A) Atividades", "B) Casos de uso", "C) Classes", "D) Máquinas de estado", "b"},
            {"Função do elemento <extends> nos casos de uso UML?",
                    "A) Generalização", "B) Inclusão", "C) Dependência", "D) Executar outro caso de uso", "d"},
            {"A garantia da qualidade atua só no fim do projeto?",
                    "A) Certo", "B) Errado", "", "", "b"},

            // MÉDIO (5)
            {"Requisito não funcional do ponto eletrônico:",
                    "A) Biometria por relógio eletrônico", "B) Apurar registros", "C) Consultar registros", "D) Não ficar fora do ar > 60s", "d"},
            {"Técnica de elicitação por observação direta:",
                    "A) Etnografia", "B) Questionários", "C) Casos de uso", "D) Brainstorming", "a"},
            {"Diagrama de casos de uso modela fluxo de dados?",
                    "A) Certo", "B) Errado", "", "", "b"},
            {"Sobre UML, marque a correta:",
                    "A) Levantar requisitos com casos de uso e classes", "B) Caso de uso é difícil de validar", "C) Diagramas comportamentais são estáticos", "D) Classes não têm multiplicidade", "a"},
            {"Teste que garante que funções antigas funcionam após atualização:",
                    "A) Unitário", "B) Integração", "C) Usabilidade", "D) Regressão", "d"},

            // DIFÍCIL (5)
            {"Etapa que define funções, dados e comportamento do sistema:",
                    "A) Desenho de software", "B) Código", "C) Análise de requisitos", "D) Arquitetura", "c"},
            {"Requisitos funcionais de e-commerce:",
                    "A) I apenas", "B) II apenas", "C) I e II", "D) I e III", "a"},
            {"Correlação correta entre Login, Reservar e Notificar:",
                    "A) Extensão + Inclusão", "B) Inclusão + Extensão", "C) Generalização", "D) Inclusão de Login + Extensão", "b"},
            {"Diagramas estruturais da UML:",
                    "A) Classe e Objetos", "B) Sequência e Estados", "C) Classe e Atividades", "D) Casos de Uso e Sequência", "a"},
            {"Teste que verifica módulos isoladamente:",
                    "A) Unitário", "B) Integração", "C) Sistema", "D) Aceitação", "a"}
    };

    public QuizApp() {
        super("Quiz - Fundamentos de Software");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior
        JPanel topo = new JPanel();
        topo.add(new JLabel("Dificuldade:"));
        nivelBox = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        topo.add(nivelBox);
        iniciarBtn = new JButton("Iniciar");
        iniciarBtn.addActionListener(e -> iniciarQuiz());
        topo.add(iniciarBtn);
        add(topo, BorderLayout.NORTH);

        // Pergunta + opções
        JPanel centro = new JPanel(new GridLayout(6, 1));
        perguntaLabel = new JLabel("Escolha o nível e clique em Iniciar.", JLabel.CENTER);
        centro.add(perguntaLabel);

        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();
        grupo = new ButtonGroup();
        grupo.add(op1); grupo.add(op2); grupo.add(op3); grupo.add(op4);
        centro.add(op1); centro.add(op2); centro.add(op3); centro.add(op4);
        add(centro, BorderLayout.CENTER);

        // Painel inferior
        JPanel baixo = new JPanel();
        proximoBtn = new JButton("Próxima");
        proximoBtn.addActionListener(e -> actionPerformed(e));
        proximoBtn.setEnabled(false);
        baixo.add(proximoBtn);

        pontuacaoLabel = new JLabel("Pontuação: 0");
        baixo.add(pontuacaoLabel);
        add(baixo, BorderLayout.SOUTH);
    }

    private void iniciarQuiz() {
        nivel = (String) nivelBox.getSelectedItem();
        indice = nivel.equals("Fácil") ? 0 : nivel.equals("Médio") ? 10 : 15;
        acertos = 0;
        pontuacao = 0;
        valorPergunta = nivel.equals("Fácil") ? 0.5 : 2.0;
        proximoBtn.setEnabled(true);
        mostrarPergunta();
    }

    private void mostrarPergunta() {
        perguntaLabel.setText(perguntas[indice][0]);
        op1.setText(perguntas[indice][1]);
        op2.setText(perguntas[indice][2]);
        op3.setText(perguntas[indice][3]);
        op4.setText(perguntas[indice][4]);
        grupo.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String resp = "";
        if (op1.isSelected()) resp = "a";
        else if (op2.isSelected()) resp = "b";
        else if (op3.isSelected()) resp = "c";
        else if (op4.isSelected()) resp = "d";

        if (resp.equals(perguntas[indice][5])) {
            acertos++;
            pontuacao += valorPergunta;
        }

        indice++;
        boolean acabou = (nivel.equals("Fácil") && indice == 10)
                || (nivel.equals("Médio") && indice == 15)
                || (nivel.equals("Difícil") && indice == 20);

        if (acabou) finalizarQuiz();
        else mostrarPergunta();

        pontuacaoLabel.setText("Pontuação: " + pontuacao);
    }

    private void finalizarQuiz() {
        proximoBtn.setEnabled(false);
        int totalPerguntas = 0;

        switch (nivel) {
            case "Fácil" -> totalPerguntas = 10;
            case "Médio" -> totalPerguntas = 5;
            case "Difícil" -> totalPerguntas = 5;
            default -> {
            }
        }
        JOptionPane.showMessageDialog(this,
                "Fim do Quiz!\nVocê acertou " + acertos + " de " + totalPerguntas + " questões .\nPontuação final: " + pontuacao + " pontos.",
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().setVisible(true));
    }
}