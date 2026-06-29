// 1. COMECE DIGITANDO A CLASSE MÃE
// Fale: "Pessoal, vamos criar nossa classe base. Todo veículo tem marca e modelo, certo?"
class Veiculo {
    
    // Fale: "Usamos 'protected' para que as classes filhas possam usar esses dados diretamente."
    protected String marca;
    protected String modelo;
    
    // Fale: "O chassi é 'private'. É o segredo do objeto. Ninguém mexe nele de fora."
    private String chassi; 

    // Fale: "Vamos criar um método comum a todos os veículos."
    public void ligar() {
        System.out.println("O veículo está ligado! Vrummm...");
    }

    // Fale: "Como o chassi é privado, precisamos de Getters e Setters para acessá-lo com segurança."
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    
    public String getChassi() {
        return this.chassi;
    }
}

// 2. AGORA, DIGITE A CLASSE FILHA
// Fale: "Agora vem a mágica da herança. A palavra 'extends' significa que a Moto herda tudo do Veiculo!"
class Moto extends Veiculo {
    
    // Fale: "A moto tem algo só dela, que o Veiculo genérico não tem."
    public int cilindradas;

    public void darGrau() {
        // Fale: "Olhem só, eu consigo usar a variável 'modelo' aqui dentro porque ela é 'protected' na classe mãe!"
        System.out.println("A moto " + modelo + " está empinando! Randandandan!");
    }
}

// 3. POR FIM, CRIE A CLASSE PRINCIPAL PARA TESTAR
// Fale: "Vamos instanciar nossa Moto e ver a herança funcionando na prática."
public class Main {
    public static void main(String[] args) {
        
        System.out.println("--- SISTEMA DE VEÍCULOS ---");
        
        Moto minhaMoto = new Moto();
        
        // Fale: "Eu não criei 'marca' e 'modelo' na classe Moto, mas eu posso usar porque ela HERDOU!"
        minhaMoto.marca = "Honda";
        minhaMoto.modelo = "CG 160 Titan";
        minhaMoto.cilindradas = 160;
        
        // minhaMoto.chassi = "123"; -> Fale: "Se eu fizer isso, dá erro! É privado. Usamos o Set:"
        minhaMoto.setChassi("9382103810293"); 

        // Testando os métodos
        minhaMoto.ligar();   // Fale: "Chamando o método que veio de herança."
        minhaMoto.darGrau(); // Fale: "Chamando o método próprio da moto."

        System.out.println("Chassi registrado: " + minhaMoto.getChassi());
    }
}