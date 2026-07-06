import java.util.Scanner;

// ISP - Princípio da Segregação de Interface
interface DispositivoLigavel {
    void ligar();
}

interface DispositivoWifi {
    void conectarWifi();
}

// Lâmpada inteligente (utiliza ambas interfaces)
class LampadaSmart implements DispositivoLigavel, DispositivoWifi {

    @Override
    public void ligar() {
        System.out.println("Lâmpada Smart: Luz acesa!");
    }

    @Override
    public void conectarWifi() {
        System.out.println("Lâmpada Smart: Conectado ao Wi-Fi da sala.");
    }
}

// LSP - O abajur só liga
class AbajurComum implements DispositivoLigavel {

    @Override
    public void ligar() {
        System.out.println("Abajur: Luz ligada no interruptor manual.");
    }
}

// DIP - Princípio da Inversão de Dependência
class CentralAutomacao {

    public void acionar(DispositivoLigavel dispositivo) {
        System.out.println("[CENTRAL] -> Enviando comando de energia...");
        dispositivo.ligar();
    }
}

// Classe principal
public class SOLID_PT2 {

    public static void main(String[] args) {

        CentralAutomacao central = new CentralAutomacao();
        Scanner leitor = new Scanner(System.in);

        System.out.println("\n===== Sistema Casa Inteligente =====");
        System.out.println("Qual dispositivo deseja testar?");
        System.out.println("1 - Lâmpada Smart Wi-Fi");
        System.out.println("2 - Abajur Antigo");
        System.out.print("Opção: ");

        int opcao = leitor.nextInt();

        // LSP - Polimorfismo
        DispositivoLigavel dispositivoEscolhido;

        if (opcao == 1) {
            LampadaSmart smart = new LampadaSmart();
            smart.conectarWifi(); // método específico da lâmpada
            dispositivoEscolhido = smart;
        } else {
            dispositivoEscolhido = new AbajurComum();
        }

        central.acionar(dispositivoEscolhido);

        leitor.close();
    }
}