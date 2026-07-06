import java.util.Scanner;

//ISP - PRINCIPIO DA SECREGAÇÃO DE INTERFACE
interface DispositivoLigavel{
    void ligar();
}

interface DispositivoWifi{
    void conectarWifi();
}

//APLICANDO AS CLASSE (SENDO FIEL AO QUE CADA UM FAZ)

//lâmpada Inteligente (utiliza ambas interfaces liga e conecta no Wifi)

class LampadaSmart implements DispositivoLigavel, DispositivoWifi{
    @Override
    public void ligar(){
        System.out.println("Lâmpada Smart: luz acesa!");
    }
    @Override
    public void conectarWifi(){
        System.out.println("Lâmpa Smart: Conectado ao Wi-Fi da sala");
    }
}
//LSP - O ABUJUR QUE SO LIGA

class abajurComum implements DispositivoLigavel{
@Override
public void ligar(){
    System.out.println("Abajur: Luz ligada o interruptor Manual.");
}
}

// DIP - PRINCIPIO da INVERSÃO

class CentralAutomacao{
public void acionar(DispositivoLigavel dispotivo){
    System.out.println("[CENTRAL] -> enviando comando de energia.");
    dispotivo.ligar();
}
}

//Execução termina (scanner na pratica)
public class SOLID_PT2{
    public static void main(String[] args){
        CentralAutomacao central = new CentralAutomacao();
        Scanner leitor = new Scanner(System.in);

        System.out.println("\n--Sistema Casa--");
        System.out.println("Qual dispositivo deseja testar?");
        System.out.println("1 - LÂMPA SMART - WI-FI");
        System.out.println("2 - ABAJUR ANTIGO - SIMPLES");
        System.out.print("OPÇÃO: ");
        int opcao = leitor.nextInt();
    
        //LSP - POLIMORFIMOS (TRATA QUALQUER UM COMO UM DISPOSITIVO LIGAVEL)
        DispositivoLigavel dispositivoEscolhido;
        if(opcao == 1){
            LampadaSmart smart = new LampadaSmart();
            smart.conectarWifi();//metodo especifico dela
            dispositivoEscolhido = smart;
        }else{
            dispositivoEscolhido = new abajurComum();
        }

        central.acionar(dispositivoEscolhido);
        leitor.close();
    }
}