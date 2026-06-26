import java.util.Scanner;

// O arquivo se chama Servico.java, então a classe pública é a Servico
public abstract class Servico {
    protected String nomeVeiculo;
    protected double valorMaoDeObra;

    public Servico(String nomeVeiculo, double valorMaoDeObra) {
        this.nomeVeiculo = nomeVeiculo;
        this.valorMaoDeObra = valorMaoDeObra;
    }

    // Métodos de SOBRECARGA (Overload) que estavam faltando no seu código
    public double calcularTotal(double precoPecas) {
        return this.valorMaoDeObra + precoPecas;
    }

    public double calcularTotal(double precoPecas, double desconto) {
        return (this.valorMaoDeObra + precoPecas) - desconto;
    }

    // Metodo abstrato para SOBRESCRITA (Override)
    public abstract void realizarServico();

    // ==========================================================
    // SEU MÉTODO MAIN AGORA MORA AQUI DENTRO DA CLASSE SERVICO!
    // ==========================================================
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.println("===============================");
        System.out.println("       SISTEMA OFICINA");
        System.out.println("===============================");

        System.out.print("DIGITE O MODELO DO VEICULO:(ex: Crosser 150) ");
        String modelo = leitor.nextLine();

        System.out.println("\nSelecione o tipo de serviço");
        System.out.println("1 - Troca de óleo");
        System.out.println("2 - Revisão Geral");
        System.out.print("Opção desejada: ");
        int opcao = leitor.nextInt();

        Servico servicoEscolhido;

        if (opcao == 1) {
            servicoEscolhido = new TrocaOleo(modelo);
        } else {
            servicoEscolhido = new RevisaoGeral(modelo);
        }

        System.out.print("\nDigite o valor total das peças (R$): ");
        double precoPecas = leitor.nextDouble();

        System.out.print("Digite o valor do desconto (R$) ou 0: ");
        double desconto = leitor.nextDouble();

        System.out.println("\n--- Resumo do serviço ---");
        servicoEscolhido.realizarServico();
        
        double totalFinal;
        if (desconto > 0) {
            totalFinal = servicoEscolhido.calcularTotal(precoPecas, desconto);
            System.out.println("[Nota com desconto aplicado]");
        } else {
            totalFinal = servicoEscolhido.calcularTotal(precoPecas);
            System.out.println("[Nota com valor total]");
        }

        System.out.println("Veiculo: " + modelo);
        System.out.println("Mão de obra: R$ " + servicoEscolhido.valorMaoDeObra);
        System.out.println("Valor peças: R$ " + precoPecas);
        if (desconto > 0) {
            System.out.println("Desconto: R$ " + desconto);
        }
        System.out.println("-------------------------------");
        System.out.println("TOTAL FINAL: R$ " + totalFinal);
        System.out.println("===============================");
        
        leitor.close();
    }
}

// ==========================================================
// CLASSES FILHAS (Sem a palavra 'public' para não dar erro)
// ==========================================================
class TrocaOleo extends Servico {
    public TrocaOleo(String modelo) {
        super(modelo, 50.0); // Mão de obra fixa de 50 reais
    }
    @Override
    public void realizarServico() {
        System.out.println("Serviço: Realizando troca de óleo e filtro.");
    }
}

class RevisaoGeral extends Servico {
    public RevisaoGeral(String modelo) {
        super(modelo, 200.0); // Mão de obra fixa de 200 reais
    }
    @Override
    public void realizarServico() {
        System.out.println("Serviço: Revisão completa de freios, suspensão e motor.");
    }
}