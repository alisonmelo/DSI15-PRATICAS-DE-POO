abstract class Personagem {

    // Método abstrato
    public abstract void atacar();
}

class Mago extends Personagem {

    // Override
    @Override
    public void atacar() {
        System.out.println("Lançou bola de fogo!");
    }

    // Overload
    public void atacar(String magia) {
        System.out.println("Lançou " + magia + "!");
    }
}

public class Main {

    public static void main(String[] args) {

        Mago mago = new Mago();

        // Testando o método sobrescrito (Override)
        mago.atacar();

        // Testando o método sobrecarregado (Overload)
        mago.atacar("Raio de Gelo");
    }
}