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

class Estagiario implements Remuneravel{
    private double base;
    public Estagiario (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base;}

    @Override
    public String getCargo(){return "Estagiario de TI";}
}

class Gestor implements Remuneravel{
    private double base;
    public Gestor (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base * 1.6;}

    @Override
    public String getCargo(){return "Gestor de TI";}
}

class Marketing implements Remuneravel{
    private double base;
    public Marketing (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base * 1.1;}

    @Override
    public String getCargo(){return "Marketing";}
}
class jungle implements Remuneravel{
    private double base;
    public jungle (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base * 0.0;}

    @Override
    public String getCargo(){return "JUNGLE(sobra nada)";}
}
class limpeza implements Remuneravel{
    private double base;
    public limpeza (double base){this.base = base;}
    @Override
    public double calcularSalario(){return base * 1.2;}

    @Override
    public String getCargo(){return "limpeza geral";}
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
        System.out.println("SISTEMA SOLID DOS FUNCIONARIOS DO TIO ALISON");
        System.out.println("\n=================================");
        System.out.println("Cargo: "+f.getCargo());
        System.out.println("Total a receber : R$ "+ total);
        System.out.println("\n=================================");
    }
}

public class SOLID_PT1 {
    public static void main (String[] args){
        Scanner leitor = new Scanner(System.in);
        Remuneravel funcionario = null;

        System.out.print("Salário base: R$");
        double base = leitor.nextDouble();

        System.out.println("\nEscolha o cargo abaixo:");
        System.out.println("1 - Desenvolvedor");
        System.out.println("2 - Designer");
        System.out.println("3 - Estagiario");
        System.out.println("4 - Gestor");
        System.out.println("5 - Marketing");
        System.out.print("Opção: ");
        int opcao = leitor.nextInt();


        switch(opcao){
            case 1: funcionario = new Desenvolvedor(base);break;
            case 2: funcionario = new Designer(base);break;
            case 3: funcionario = new Estagiario(base);break;
            case 4: funcionario = new Gestor(base);break;
            case 5: funcionario = new Marketing(base);break;
            default:
            System.out.println("Opção invalida, Definindo como estagiario");
            funcionario = new Estagiario(base);
        }
        CalculadoraFolha calc = new CalculadoraFolha();
        RelatorioService relatorio = new RelatorioService();

        double valorFinal = calc.calcular(funcionario);
        relatorio.gerarRelatorio(funcionario, valorFinal);

        leitor.close();
    }
    
}