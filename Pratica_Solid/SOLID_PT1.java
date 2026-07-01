import java.util.Scanner;

// OCP - Aberto para extensão
interface Remuneravel {
    double calcularSalario();
    String getCargo();
}

// SRP - Cada cargo cuida do seu próprio cálculo
class Desenvolvedor implements Remuneravel {
    private double base;

    public Desenvolvedor(double base) {
        this.base = base;
    }

    @Override
    public double calcularSalario() {
        return base * 1.2;
    }

    @Override
    public String getCargo() {
        return "Desenvolvedor Java";
    }
}

class Designer implements Remuneravel {
    private double base;

    public Designer(double base) {
        this.base = base;
    }

    @Override
    public double calcularSalario() {
        return base * 1.1;
    }

    @Override
    public String getCargo() {
        return "Designer UI/UX";
    }
}

// SRP - Apenas processa o cálculo
class CalculadoraFolha {

    public double calcular(Remuneravel funcionario) {
        return funcionario.calcularSalario();
    }
}

// SRP - Apenas gera o relatório
class RelatorioService {

    public void gerarRelatorio(Remuneravel funcionario, double total) {
        System.out.println("\n==============================");
        System.out.println("SISTEMA SOLID DOS MENINO DO TIO ALISON");
        System.out.println("==============================");
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.printf("Total a receber: R$ %.2f%n", total);
        System.out.println("==============================");
    }
}

public class SOLID_PT1 {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        System.out.print("Salário base: R$ ");
        double base = leitor.nextDouble();

        System.out.println("\nTipo de cargo:");
        System.out.println("1 - Desenvolvedor");
        System.out.println("2 - Designer");
        System.out.print("Escolha: ");
        int opcao = leitor.nextInt();

        Remuneravel funcionario;

        if (opcao == 1) {
            funcionario = new Desenvolvedor(base);
        } else {
            funcionario = new Designer(base);
        }

        CalculadoraFolha calc = new CalculadoraFolha();
        RelatorioService relatorio = new RelatorioService();

        double total = calc.calcular(funcionario);
        relatorio.gerarRelatorio(funcionario, total);

        leitor.close();
    }
}