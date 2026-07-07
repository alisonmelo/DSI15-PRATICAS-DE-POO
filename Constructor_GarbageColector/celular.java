import java.util.Scanner;

// Criando a classe Smartphone
class Smartphone {
    String marca;
    int ram;

    public Smartphone(String marca, int ram) {
        this.marca = marca;
        this.ram = ram;
        System.out.println("Um novo smartphone da marca " + this.marca + " acabou de ser fabricado!");
    }

    public void ligar() {
        System.out.println("O " + this.marca + " com " + this.ram + "GB de RAM está iniciando...");
    }
}

public class Aula6 {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        System.out.println("--- Fábrica de Smartphones ---");

        // Marca fixa
        String marcaDigitada = "Samsung";

        System.out.print("Digite a quantidade de RAM (GB): ");
        int ramDigitada = leitor.nextInt();

        System.out.println("Iniciando a fabricação...");

        Smartphone meuSmartphone = new Smartphone(marcaDigitada, ramDigitada);

        meuSmartphone.ligar();

        System.out.println("O smartphone foi descartado.");

        // A variável deixa de apontar para o objeto.
        // O Garbage Collector poderá liberar a memória quando necessário.
        meuSmartphone = null;

        System.out.println("Valor do smartphone agora é 'null'.");

        leitor.close();
    }
}