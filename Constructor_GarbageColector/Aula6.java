import java.util.Scanner;

//criando classe (planta baixa)
class Moto{
    String modelo;
    int cilindradas;

    public Moto(String modelo, int cilindradas){
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        System.out.println("A uma nova moto " + this.modelo + " acabou de nascer na memória!" );
    }
    public void acelerar(){
        System.out.println("A "+this.modelo+ "("+this.cilindradas+ "cc) está alecerando! randan dand dannnnn");
    }
}

public class Aula6{
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);

        System.out.println("--- Fábrica de Motos --");

        System.out.print("Digite o modelo da moto (EX: Crosser):");
        String nomeDigitado = leitor.nextLine();

        System.out.print("Digite as cilindradas (EX: 150):");
        int  ccDigitado = leitor.nextInt();

        System.out.println("Iniciando a fabricação...");

        Moto minhaMoto = new Moto(nomeDigitado, ccDigitado);

        minhaMoto.acelerar();

        //gabager collector
        System.out.println("O dobi vendeu a moto e jogou o documento fora");

        minhaMoto=null;

        System.out.println("valor de moto agora e 'NULL'");
        
        minhaMoto.acelerar();

        leitor.close();


    }
}