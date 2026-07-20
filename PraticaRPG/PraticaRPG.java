import java.util.Scanner;
import java.util.Random;

class Personagem {
    private String nome;
    private int hp;
    private int hpMax; // limita o maximo de HP que o personagem pode ter
    private int forca;

    public Personagem(String nome, int hpMax, int forca) {
        this.nome = nome;
        this.hpMax = hpMax;
        this.hp = hpMax; // inicia com o valor máximo de HP
        this.forca = forca;
    }

    // cofigurar getters e setters para os atributos privados
    // getter
    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getForca() {
        return forca;
    }

    // setter
    // regra limita vida maximo e não permit
    public void setHp(int novoHp) {
        if (novoHp > hpMax) {
            this.hp = hpMax;
        } else if (novoHp < 0) {
            this.hp = 0;
        } else {
            this.hp = novoHp;
        }
    }

    public void atacar(Personagem alvo) {
        // personagem ataca outro personagem alvo
        System.out.println("\n⚔️​" + this.nome + " ataca " + alvo.getNome() + " com força de " + this.forca + "!");

        int dano = this.forca;
        // Reduzir hp do alvo
        alvo.setHp(alvo.getHp() - dano);

        System.out.println(
                "🩸 " + alvo.getNome() + " Perdeu " + this.forca + " de HP. (HP Restante: " + alvo.getHp() + ")");
    }

    public void usarPocao() {
        System.out.println("\n" + this.getNome() + "usou porção de cura!");
        Random rand = new Random();
        int cura = rand.nextInt(21) + 10; // cura aleatória entre 10 e 30
        this.setHp(this.hp + cura);
        System.out.println(this.nome + " recuperou " + cura +
                " de HP.(HP Atual: " + this.getHp() + ")");
    }
}

class Mago extends Personagem {
    private int mana;

    public Mago(String nome, int hpMax, int forca, int mana) {
        super(nome, hpMax, forca);
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (this.mana >= 5) {
            System.out.println("\n" + this.getNome() +
                    " jogou bola de mana em "
                    + alvo.getNome() + "! Mana restante: " + this.mana);
            this.mana -= 5;
            int dano = this.getForca() + 5;
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("🩸 " + alvo.getNome() + " Perdeu "
                    + dano + " de HP. (HP Restante: "
                    + alvo.getHp() + ")");
        } else {
            System.out.println("\n" + this.getNome() +
                    " Não possui mana suficiente, deu uma cajadada no "
                    + alvo.getNome() + "!");
            int dano = this.getForca() - 5;
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("🩸 " + alvo.getNome() + " Perdeu "
                    + dano + " de HP. (HP Restante: "
                    + alvo.getHp() + ")");
        }
    }

    public void usarMagiaFogo(Personagem alvo) {
        if (this.mana >= 10) {
            System.out.println("\n" + this.getNome() + " lançou magia de fogo em " + alvo.getNome() + "!");
            this.mana -= 10;
            double danoMagico = this.getForca() * 1.5; // dano mágico é 1.5 vezes a força
            alvo.setHp(alvo.getHp() - (int) danoMagico);
            System.out.println("Dano Magico: " + (int) danoMagico +
                    " Mana restante: " + this.mana);
        } else {
            System.out.println("\n" + this.getNome() +
                    "tentou lançar magia, mas não tem mana suficiente!");
        }
    }

    public void usarMagiaRaio(Personagem alvo) {
        if (this.mana >= 35) {
            System.out.println("\n" + this.getNome() + " lançou magia de raio em " + alvo.getNome() + "!");
            this.mana -= 35;
            double danoMagico = this.getForca() * 2; // dano mágico é 1.5 vezes a força
            alvo.setHp(alvo.getHp() - (int) danoMagico);
            System.out.println("Dano Magico: " + (int) danoMagico +
                    " Mana restante: " + this.mana);
        } else {
            System.out.println("\n" + this.getNome() +
                    "tentou lançar magia, mas não tem mana suficiente!");
        }
    }

    public void usarMagiaGelo(Personagem alvo) {
        if (this.mana >= 20) {
            System.out.println("\n" + this.getNome() + " lançou magia de gelo em " + alvo.getNome() + "!");
            this.mana -= 20;
            double danoMagico = this.getForca() * 1.8; // dano mágico é 1.5 vezes a força
            alvo.setHp(alvo.getHp() - (int) danoMagico);
            System.out.println("Dano Magico: " + (int) danoMagico +
                    " Mana restante: " + this.mana);
        } else {
            System.out.println("\n" + this.getNome() +
                    "tentou lançar magia, mas não tem mana suficiente!");
        }
    }
}

class Arqueiro extends Personagem {
    private int flechas;
    private int forcaFlecha;

    public Arqueiro(String nome, int hpMax, int forca, int flechas, int forcaFlecha) {
        super(nome, hpMax, forca);
        this.flechas = flechas;
        this.forcaFlecha = forcaFlecha;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (this.flechas >= 1) {
            System.out.println("\n🏹 ​" + this.getNome() +
                    " Atirou uma flecha em "
                    + alvo.getNome() + "! Restam "+this.flechas+ " flechas!" );
            this.flechas--;
            int dano = this.getForca() + this.forcaFlecha;
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("🩸 " + alvo.getNome() + " Perdeu "
                    + dano + " de HP. (HP Restante: "
                    + alvo.getHp() + ")");

        } else {
            System.out.println("\n ​" + this.getNome() +
                    " não tem flechas ele correu e bateu com o arco na cabeça de "
                    + alvo.getNome() + "!");
            int dano = this.getForca() - 5;
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("🩸 " + alvo.getNome() + " Perdeu "
                    + dano + " de HP. (HP Restante: "
                    + alvo.getHp() + ")");
        }
    }

}

class Guerreiro extends Personagem {

    public Guerreiro(String nome, int hpMax, int forca) {
        super(nome, hpMax, forca);
    }
// Atravez do polimorfismo @Override 
// modifique o método atacar para o guerreiro
@Override
public void atacar(Personagem alvo){
            Random rand = new Random();    
            System.out.println("\n" + this.getNome() + 
                            " lançou magia de raio em "
                            + alvo.getNome() + "!");
            int danoBase = this.getForca();
            int bonus = rand.nextInt(41) + 10;
            int dano = danoBase + bonus;
            
            if(dano > (danoBase*2)){
                 System.out.println("🩸 Ataque critico Dano de:" + dano+"!");
            }else if (dano > danoBase){
                System.out.println("🩸 Dano padrão maior que o dano base, Dano de:" + dano+"!" );
            }
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("🩸 " + alvo.getNome() + " Perdeu "
                    + dano + " de HP. (HP Restante: "
                    + alvo.getHp() + ")");
            }
           
            
}

class Monstro extends Personagem {
    public Monstro(String nome, int hpMax, int forca) {
        super(nome, hpMax, forca);
    }
}

// class mosntro chefe que herda monstro so que o hp e a forma maiores que o
// montro normal
class MonstroChefe extends Monstro {
    // valor de hp e força maior que o monstro normal
    public MonstroChefe(String nome, int hpMax, int forca) {
        super(nome, hpMax * 2, forca + 15);
    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println("\n 👹 " + this.getNome() + " ataca ferozmente " + alvo.getNome() + " com força de "
                + this.getForca() + "!");
        super.atacar(alvo);
    }
}

public class PraticaRPG {
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        System.out.println("==========================");
        System.out.println("BEM VINDO A ARENA (NOME DA SUA ARENA)");
        System.out.println("==========================");

        System.out.print("Digite o nome do seu Personagem: ");
        String nomeHeroi = leitor.nextLine();

        Personagem heroi = new Guerreiro(nomeHeroi, 150, 10);

        MonstroChefe monstro = new MonstroChefe("Orc Zumbi chefe",150, 15);

        System.out.println("\n Um "+monstro.getNome()+" apareceu! Prepare para a chibata!");
       
        while (heroi.getHp() > 0 && monstro.getHp() > 0) {
            System.out.println("--- SEU TURNO ---");            
            System.out.println("1 - ATACAR");            
            System.out.println("2 - GRITAR PARA INTIMIDAR"); 
            System.out.println("3 - CURAR (Recupera 10 de HP)");
            if(heroi instanceof Mago){
            System.out.println("4 - MAGIAS (Fogo, Gelo ou Raio)");          
            }
            System.out.print("Sua ação: ");
            int acao = leitor.nextInt();

            switch (acao){
                case 1:
                    heroi.atacar(monstro);
                    break;
                case 2:
                    System.out.println("\n🗣️​ " +heroi.getNome()+ " gritou: AAAAAAAAAHHHHH!");    
                    System.out.println("O " +monstro.getNome() + " riu na sua cara lhe chamou de otário e não sofreu dano!");
                    break;
                case 3:
                    System.out.println("\n🛡️​ " +heroi.getNome()+ " usou porção de cura!");
                    heroi.usarPocao();
                    break;
                case 4: 
                if( heroi instanceof Mago){
                    Mago magoTemp = (Mago) heroi;
                    System.out.println("\nEscolha a magia: ");
                    System.out.println("1 - Fogo (Custa 10 de Mana)");
                    System.out.println("2 - Gelo (Custa 20 de Mana)");
                    System.out.println("3 - Raio (Custa 35 de Mana)");
                    System.out.print("Sua escolha: ");
                    int escolhaMagia = leitor.nextInt();
                    switch (escolhaMagia){
                        case 1:
                            magoTemp.usarMagiaFogo(monstro);
                            break;
                        case 2:
                            magoTemp.usarMagiaGelo(monstro);
                            break;
                        case 3:
                            magoTemp.usarMagiaRaio(monstro);
                            break;
                        default:
                            System.out.println("\n ❌​ Magia inválida! Voçê perdeu o turno!");
                    }
                }else{
                    System.out.println("\n ❌​ Você não afinidade com magia! Perdeu o turno TENTANDO LER o pergaminho de PONTA CABEÇA 0.0!");
                }
                break;
                default:
                    System.out.println("\n ❌​ Ação inválida! Voçê perdeu o turno!");     
            }
            
            if (monstro.getHp() > 0){
                System.out.println("\n--- TURNO DO INIMIGO ---");
                monstro.atacar(heroi);
            }  
        }
        if (heroi.getHp() > 0){
            System.out.println("VITÓRIA VC DERROUTOU O "+monstro.getNome()+":D");
        } else{
            System.out.println("GAME OVER - " + heroi.getNome() + " MORREU NA ARENA X-X !");
        }

        leitor.close();
    }

}
