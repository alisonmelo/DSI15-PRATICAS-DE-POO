public class DesafioOPC {

    public static void main(String[] args) {

        Calculadora vip = new Calculadora(new DescontoVIP());
        System.out.println("Desconto VIP: " + vip.calcular(100));

        Calculadora comum = new Calculadora(new DescontoComum());
        System.out.println("Desconto Comum: " + comum.calcular(100));
    }
}


interface Desconto {
    double calcular(double valor);
}


class DescontoVIP implements Desconto {

    @Override
    public double calcular(double valor) {
        return valor * 0.20;
    }
}


class DescontoComum implements Desconto {

    @Override
    public double calcular(double valor) {
        return valor * 0.10;
    }
}


class Calculadora {

    private Desconto desconto;

    public Calculadora(Desconto desconto) {
        this.desconto = desconto;
    }

    public double calcular(double valor) {
        return desconto.calcular(valor);
    }
}