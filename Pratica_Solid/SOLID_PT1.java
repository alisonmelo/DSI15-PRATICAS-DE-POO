import java.util.Scanner;

//OCP - ABERTO PARA EXTENSÃO
interface Remuneravel{
    double calcularSalario();
    String getCargo();
}

//SRP - RESPONSABILIDADE ÚNICA CADA CARGO CUIDA DO SEU CÁLCULO

class Desenvolvedor implements Remuneravel{
    private double base;
    public Desenvolvedor (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base * 1.2;}

    @Override
    public String getCargo(){return "Desenvolvedor Java";}
}

class Designer implements Remuneravel{
    private double base;
    public Designer (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base * 1.1;}

    @Override
    public String getCargo(){return "Designer UI/UX";}
}

//SRP - APENAS PROCESSAR O CALCULO

class CalculadoraFolha{
    public double calcular(Remuneravel funcionario){
        return funcionario.calcularSalario();
    }
}

//SRP - Apenas gera o relatoria de saida de dados

class RelatorioService {
    public void gerarRelatorio(Remuneravel f, double total){
        System.out.println("\n=================================");
        System.out.println("SISTEMA SOLID DOS MENINO DO TIO ALISON");
        System.out.println("\n=================================");
        System.out.println("Cargo: "+f.getCargo());
        System.out.println("Total a receber : R$ "+ total);
        System.out.println("\n=================================");
    }
    
}
public class SOLID_PT1 {
    public static void main (String[] args){
        Scanner leitor = new Scanner(System.in);

        System.out.print("Salário base: R$ ");
        double base = leitor.nextDouble();

        System.out.print("Tipo de cargo: \n1-Dev | 2-Designer");
        int opcao = leitor.nextInt();

        Remuneravel funcionario = (opcao == 1) ? new Desenvolvedor(base) : new Designer(base);

        CalculadoraFolha calc = new CalculadoraFolha();
        RelatorioService relatorio = new RelatorioService();

        relatorio.gerarRelatorio(funcionario, calc.calcular(funcionario));

        leitor.close();
    }
    
}