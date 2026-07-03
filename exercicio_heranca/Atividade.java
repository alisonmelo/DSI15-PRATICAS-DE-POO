import java.util.Scanner;

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

class Aluno extends Pessoa {
    private String matricula;

    public Aluno(String nome, int idade, String matricula) {

        super(nome, idade);
        this.matricula = matricula;
    }
}

public class Atividade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do aluno: ");
        int idade = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = new Aluno(nome, idade, matricula);

        System.out.println("Aluno " + aluno.getNome() + " cadastrado com sucesso!");

        scanner.close();
    }
}
