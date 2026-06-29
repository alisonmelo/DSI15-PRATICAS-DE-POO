import java.util.Scanner;

// Classe Mãe (Sem o modificador public para permitir ficar no mesmo arquivo)
class Pessoa {
    protected String nome;
    protected int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }
}

// Classe Filha (Sem o modificador public)
class Aluno extends Pessoa {
    private String matricula;

    public Aluno(String nome, int idade, String matricula) {
        // O super() repassa os dados para o construtor da classe mãe
        super(nome, idade);
        this.matricula = matricula;
    }
}

// Classe Principal - O nome da classe pública deve ser exatamente o nome do arquivo (Atividade.java)
public class Atividade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do aluno: ");
        int idade = scanner.nextInt();

        // Limpeza do buffer do Scanner após ler um inteiro
        scanner.nextLine();

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        // Criação do objeto Aluno com os dados digitados
        Aluno aluno = new Aluno(nome, idade, matricula);

        // Exibição da mensagem de sucesso
        System.out.println("Aluno " + aluno.getNome() + " cadastrado com sucesso!");

        scanner.close();
    }
}