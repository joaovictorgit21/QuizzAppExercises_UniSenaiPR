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

        {"1) Quais as principais informações que devem ser coletadas na engenharia de requisitos?(0.5p)",
                "A) Requisitos do usuário, Requisitos do Sistema",
                "B) Requisitos de negócio, Dados do Sistema",
                "C) Requisitos de segurança, UML",
                "D) Qualidade do software, Requisitos do Sistema",
                "a"},

        {"2) O que seria a UML?(0.5p)",
                "a) Uma linguagem de programação",
                "b) Um método pra desenvolver software",
                "c) Linguagem padrão e visual pra desenvolver sistemas complexos",
                "d) Levantamento de requisitos",
                "c"},

        {"3) Quais as principais categorias do teste de software?(0.5p)",
                "a) Validação e Verificação",
                "b) Teste Funcional e Teste de Sistema",
                "c) Teste de Regressão e Validação",
                "d) Verificação e Teste de integração",
                "a"},

        {"4) Qual desses não é um tipo de teste de caixa branca?(0.5p)",
                "a) Teste comportamental",
                "b) Teste Unitario",
                "c) Teste de cobertura de loop",
                "d) Teste de cobertura de instrução",
                "a"},

        {"5) O que é um requisito?",
                "A) são descrições dos serviços que devem ser providos pelo sistema e de suas restrições operacionais.",
                "B) uma instrução ambígua, vaga, uma opinião ou uma descrição de como o sistema deve ser implementado.",
                "C) uma entrevista com o cliente pra entender o que ele quer.",
                "D) características que não podem ser testadas ou avaliadas, como \"o sistema deve ser bonito\"",
                "a"},

        {"6) O que é um teste de caixa preta?",
                "A) é uma metodologia de teste de software que avalia a funcionalidade de um sistema sem ter conhecimento de seu código interno ou estrutura",
                "B) é um método de teste de software que envolve a inspeção do código-fonte para garantir que as estruturas internas do programa funcionem como esperado",
                "C) processo sistemático para definir, analisar, documentar, verificar e validar as necessidades de um sistema de software",
                "D) linguagem padronizada para visualizar, especificar, construir e documentar sistemas de software complexos",
                "a"},

        {"7) A linguagem de modelagem de software de propósito geral conhecida como UML (Unified Modeling Language) oferece diversos diagramas que servem para documentar sistemas em seus diferentes aspectos. O diagrama que tem como objetivo representar, em alto nível, a interação entre atores e o sistema sendo modelado é chamado de",
                "A) Diagrama de Atores.",
                "B) Diagrama de Alto Nível.",
                "C) Diagrama de Casos de Uso.",
                "D) Diagrama de Fluxo de Dados.",
                "E) Diagrama de Classes.",
                "c"},

        {"8) A modelagem UML (Unified Modeling Language) pode ser usada na análise de requisitos de um sistema. Nesse contexto, assinale a opção que indica o diagrama usado para descrever a interação entre usuários (ou agentes externos) e o sistema.",
                "A) De atividades.",
                "B) De casos de uso.",
                "C) De classes.",
                "D) De máquinas de estado.",
                "E) De descrição.",
                "b"},

        {"9) Qual é a função do elemento <extends> nos diagramas de casos de uso da UML (Unified Modeling Language)?",
                "A) Denota uma relação de generalização entre casos de uso",
                "B) Representa a inclusão de um caso de uso base em outro",
                "C) Sinaliza uma dependência entre casos de uso",
                "D) Uma referência de um caso de uso que pode executar um outro caso de uso",
                "d"},

        {"10) A garantia da qualidade de software atua apenas nas fases finais do projeto, validando se os requisitos foram devidamente atendidos antes da entrega do produto.",
                "A) Certo",
                "B) Errado",
                "No render",
                "No render",
                "b"},

        // MÉDIO (5)

        {"1) Na engenharia de requisitos, os requisitos de um sistema podem ser classificados como funcionais ou não funcionais. Assinale a alternativa que apresenta um possível requisito não funcional para um sistema de controle de ponto eletrônico.(2.0p)",
                "A) A biometria do usuário poderá ser coletada por relógio eletrônico de ponto conectado ao sistema.",
                "B) O sistema irá apurar, diariamente, os registros de ponto de todos os colaboradores.",
                "C) O usuário poderá consultar os seus registros de ponto.",
                "D) Somente a equipe de recursos humanos poderá alterar ou excluir um registro de ponto.",
                "E) O sistema não poderá permanecer fora do ar por mais de 60 segundos em um único dia.",
                "e"},

        {"2) A técnica de elicitação de requisitos que é particularmente útil para descobrir requisitos implícitos e contextos complexos por meio da observação direta e não participativa dos usuários em seu ambiente real de trabalho é denominada de.(2.0p)",
                "A) etnografia.",
                "B) questionários.",
                "C) casos de uso.",
                "D) brainstorming.",
                "E) análise de protocolo.",
                "a"},

        {"3) No que diz respeito a análise de requisitos de projetos de software, julgue o item seguinte. O diagrama de casos de uso é utilizado principalmente para modelar o fluxo de dados dentro do sistema.",
                "A) Certo",
                "B) Errado",
                "No render",
                "No render",
                "b"},

        {"4) Em projetos de desenvolvimento de software há uma necessidade de estreitamento entre os profissionais de tecnologia e os stakeholder demandantes. O processo de modelagem de uma nova solução a comunicação é um dos fatores críticos de sucesso. Diante do cenário, muitas tecnologias e conceitos foram criados ao longo dos anos para auxiliar nessa demanda. A (Unified Modeling Language) – UML, tem esse objetivo. Sobre ela marque a alternativa CORRETA.(2.0p)",
                "A) Um dos fluxos possíveis na utilização da UML é: levantar os requisitos com o caso de uso, representar as estruturas das classes de negócio e interfaces com o diagrama de classes, visualizar os objetos de um determinado instante no tempo com o diagrama de objetos.",
                "B) O caso de uso é um tipo de diagrama da UML, adiciona o conceito de atores que pode ser um sistema ou uma pessoa, é usando internamente pela equipe para comunicação, a literatura desaconselha o seu uso diretamente com os usuários de negócio devido sua complexidade e dificuldade de validação dos requisitos.",
                "C) A UML divide os seus diagramas em dois grandes grupos. O primeiro grupo é chamado de comportamental, já o segundo grupo são os semiestruturais. Os diagramas comportamentais lidam com aspectos estáticos e imutáveis. Já os semiestruturais trabalham com aspectos dinâmicos dos sistemas e suas interações.",
                "D) O diagrama de classe é o mais conhecido pelas equipes que utilizam a UML, ele permite representar uma série de características de uma classe. Porém, é impossível a representação de multiplicidade, escopo, bem como atributos e operações.",
                "E) O diagrama de atividades é representado por classes, e elas são divididas em dois grupos: as abstratas e as concretas. Outra forma de dividir esse diagrama é em conceitual ou relacional.",
                "a"},

        {"5) Uma equipe de desenvolvimento está prestes a liberar uma atualização de um sistema de e-commerce. Para garantir que funcionalidades antigas, como o cálculo do frete e processamento de pagamento, continuem funcionando corretamente após as alterações recentes, eles executam um conjunto de testes automatizados previamente definidos. Nesse caso, está sendo aplicado o Teste:",
                "A) Unitário.",
                "B) de Integração.",
                "C) de Usabilidade.",
                "D) de Regressão.",
                "E) de Performance",
                "d"},

        // DIFÍCIL (5)

        {"1) Logo no início do processo de engenharia de software, ocorre uma etapa fundamental para a definição precisa do que deverá ser desenvolvido. Nessa fase, são especificadas as características operacionais do sistema, incluindo suas funções, os dados manipulados, os comportamentos esperados e a forma como o software interage com outros componentes do ambiente. Esse estágio compreende diversas atividades, como o entendimento do problema, a avaliação e síntese de informações, a modelagem dos requisitos, sua especificação formal e posterior revisão. Trata-se de uma fase crítica, pois erros ou omissões nesse momento podem levar à construção de um sistema que não atende às necessidades reais do usuário, ocasionando desperdício de tempo e recursos. A etapa descrita é:",
                "A) Desenho de software.",
                "B) Geração de código.",
                "C) Análise de requisitos.",
                "D) Arquitetura de software.",
                "E) Manutenção.",
                "c"},

        {"2) Uma empresa pretende desenvolver uma aplicação de comércio eletrônico com uma interface web amigável utilizando JavaScript (frontend) e um backend desenvolvido em Java (backend). Na fase de especificação, foram definidos requisitos funcionais e não funcionais para assegurar que o sistema seja confiável e de fácil utilização entre os usuários. Entre eles:",
                "A) I, apenas.",
                "B) II, apenas.",
                "C) I e II, apenas.",
                "D) I e III, apenas.",
                "E) II e III, apenas.",
                "a"},

        {"3) Um analista está modelando um sistema de biblioteca usando um Diagrama de Casos de Uso em UML. O analista identificou as seguintes funcionalidades:",
                "A) Uma relação de Extensão (<>) de \"Reservar Livro\" para \"Fazer Login\" e uma relação de Inclusão (≪include≫) de \"Reservar Livro\" para \"Notificar Penalidade\".",
                "B) Uma relação de Inclusão (<>) de \"Reservar Livro\" para \"Fazer Login\" e uma relação de Extensão (<>) de \"Reservar Livro\" para \"Notificar Penalidade\".",
                "C) Uma relação de Generalização (Herança) de \"Reservar Livro\" para \"Fazer Login\" e uma relação de Inclusão (≪include≫) de \"Reservar Livro\" para \"Notificar Penalidade\".",
                "D) Uma relação de Inclusão (<>) de \"Fazer Login\" para \"Reservar Livro\" e uma relação de Extensão (<>) de \"Reservar Livro\" para \"Notificar Penalidade\".",
                "E) Uma relação de Extensão (<>) de \"Fazer Login\" para \"Reservar Livro\" e uma relação de Inclusão (<>) de \"Notificar Penalidade\" para \"Reservar Livro\".",
                "b"},

        {"4) UML é uma notação gráfica para modelagem de software. A linguagem define um conjunto de diagramas para documentar e ajudar no design de sistemas de software, particularmente sistemas orientados a objetos. Os diagramas UML são classificados em dois grandes grupos: Diagramas Estáticos (ou Estruturais) modelam a estrutura e organização de um sistema e os Diagramas Dinâmicos (ou Comportamentais) modelam eventos que ocorrem durante a execução de um sistema.",
                "A) Diagrama de Classe e Diagrama de Objetos.",
                "B) Diagrama de Sequência e Diagrama de Estados.",
                "C) Diagrama de Classe e Diagrama de Atividades.",
                "D) Diagrama de Casos de Uso e Diagrama de Sequência.",
                "E) Diagrama de Classe e Diagrama de Sequência.",
                "a"},

        {"5) 5) Uma empresa de desenvolvimento de aplicativos financeiros deseja garantir que cada módulo do sistema funcione corretamente de forma isolada antes de integrar todos os módulos. Para isso, os desenvolvedores criam testes focados em funções específicas, verificando entradas e saídas esperadas de cada componente individualmente. Nesse cenário, está sendo aplicado o Teste",
                "A) Unitário.",
                "B) de Integração.",
                "C) de Sistema.",
                "D) de Aceitação.",
                "E) de Regressão.",
                "a"}
};

        private final JPanel centro;

        public QuizApp() {
                super("Quiz - Fundamentos de Software");
                setSize(1920, 1080);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLayout(new BorderLayout());

                JPanel topo = new JPanel();
                topo.add(new JLabel("Dificuldade:"));
                nivelBox = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
                topo.add(nivelBox);
                iniciarBtn = new JButton("Iniciar");
                iniciarBtn.addActionListener(e -> iniciarQuiz());
                topo.add(iniciarBtn);
                add(topo, BorderLayout.NORTH);

                pontuacaoLabel = new JLabel("Pontuação: 0");

                centro = new JPanel(new GridLayout(6, 1));
                perguntaLabel = new JLabel("Escolha o nível e clique em Iniciar.", JLabel.CENTER);
                centro.add(perguntaLabel);

                perguntaLabel.setFont(new Font("Arial", Font.BOLD, 24));
                op1 = new JRadioButton() {{ setFont(new Font("Arial", Font.PLAIN, 20)); }};
                op2 = new JRadioButton() {{ setFont(new Font("Arial", Font.PLAIN, 20)); }};
                op3 = new JRadioButton() {{ setFont(new Font("Arial", Font.PLAIN, 20)); }};
                op4 = new JRadioButton() {{ setFont(new Font("Arial", Font.PLAIN, 20)); }};

                grupo = new ButtonGroup();

                if (!perguntas[indice][1].equals("No render")) {
                        grupo.add(op1);
                        add(centro, BorderLayout.CENTER);
                }
                if (!perguntas[indice][1].equals("No render")) {
                        grupo.add(op2);
                        add(centro, BorderLayout.CENTER);
                }
                if (!perguntas[indice][1].equals("No render")) {
                        grupo.add(op3);
                        add(centro, BorderLayout.CENTER);
                }
                if (!perguntas[indice][1].equals("No render")) {
                        grupo.add(op4);
                        add(centro, BorderLayout.CENTER);
                }

                JPanel baixo = new JPanel();
                proximoBtn = new JButton("Próxima");
                proximoBtn.setPreferredSize(new Dimension(200, 70));
                proximoBtn.setFont(new Font("Arial", Font.BOLD, 16));
                proximoBtn.addActionListener(e -> actionPerformed(e));
                proximoBtn.setEnabled(false);
                baixo.add(proximoBtn);

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

                centro.removeAll();

                perguntaLabel.setText(
                        "<html><div style='width: 1450px;'>" + perguntas[indice][0] + "</div></html>"
                );
                centro.add(perguntaLabel);

                grupo.clearSelection();

                if (!perguntas[indice][1].equals("No render")) {
                        op1.setText("<html>" + perguntas[indice][1] + "</html>");
                        centro.add(op1);
                }
                if (!perguntas[indice][2].equals("No render")) {
                        op2.setText("<html>" + perguntas[indice][2] + "</html>");
                        centro.add(op2);
                }
                if (!perguntas[indice][3].equals("No render")) {
                        op3.setText("<html>" + perguntas[indice][3] + "</html>");
                        centro.add(op3);
                }
                if (!perguntas[indice][4].equals("No render")) {
                        op4.setText("<html>" + perguntas[indice][4] + "</html>");
                        centro.add(op4);
                }

                centro.revalidate();
                centro.repaint();
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