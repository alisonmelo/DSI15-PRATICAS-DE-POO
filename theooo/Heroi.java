public class Heroi {

    String nome;
    int vida;

    public Heroi(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public static void main(String[] args) {

        Heroi heroi = new Heroi("Theo", 10000);

        System.out.println("Nome: " + heroi.nome);
        System.out.println("Vida: " + heroi.vida);

        heroi = null;
    }
}