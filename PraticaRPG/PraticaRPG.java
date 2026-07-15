import java.util.Scanner;
import java.util.Random;

class Personagem {
    private String nome;
    private int hp;
    private int hpMax; // limita o maximo de HP que o personagem pode ter
    private int forca;
    private int mana;


    public Personagem(String nome,int hpMax, int forca ,int mana) {
        this.nome = nome;
        this.hpMax = hpMax; 
        this.hp = hpMax; // inicia com o valor máximo de HP
        this.forca = forca;
        this.mana = mana;
    }

    //cofigurar getters e setters para os atributos privados
    //getter
    public String getNome() {  return nome; }

    public int getHp() {return hp;}

    public int getHpMax() {return hpMax;}

    public int getForca() {return forca;}

    public int getMana() {return mana;}
    //setter
    // regra limita vida maximo e não permit
    public void setHp(int novoHp){
        if(novoHp > hpMax){
            this.hp = hpMax;
        } else if(novoHp < 0){
            this.hp = 0;
        } else{
            this.hp = novoHp;
        }
    }

    public void atacar(Personagem alvo){
        // personagem ataca outro personagem alvo
        System.out.println("\n⚔️​" + this.nome + " ataca " +alvo.getNome() + " com força de " + this.forca +"!");
        
        int dano = this.forca;
        // Reduzir hp do alvo
        alvo.setHp(alvo.getHp() - dano);

        System.out.println("🩸 " + alvo.getNome()+ " Perdeu " +this.forca+" de HP. (HP Restante: "+ alvo.getHp()+")");
    }

    public void usarMagiaFogo(Personagem alvo){
        if(this.mana >= 10){
            System.out.println("\n"+this.nome + " lançou magia de fogo em " +alvo.getNome() +"!");
            this.mana -= 10;
            double danoMagico = this.forca * 1.5; // dano mágico é 1.5 vezes a força
            alvo.setHp(alvo.getHp() - (int)danoMagico);
            System.out.println("Dano Magico: " + (int)danoMagico + 
                                " Mana restante: " + this.mana);
        }else{
            System.out.println("\n"+this.nome + 
                                "tentou lançar magia, mas não tem mana suficiente!");
        }
    }
    public void usarMagiaRaio(Personagem alvo){
        if(this.mana >= 35){
            System.out.println("\n"+this.nome + " lançou magia de raio em " +alvo.getNome() +"!");
            this.mana -= 35;
            double danoMagico = this.forca * 2; // dano mágico é 1.5 vezes a força
            alvo.setHp(alvo.getHp() - (int)danoMagico);
            System.out.println("Dano Magico: " + (int)danoMagico + 
                                " Mana restante: " + this.mana);
        }else{
            System.out.println("\n"+this.nome + 
                                "tentou lançar magia, mas não tem mana suficiente!");
        }
    }
    public void usarMagiaGelo(Personagem alvo){
        if(this.mana >= 20){
            System.out.println("\n"+this.nome + " lançou magia de gelo em " +alvo.getNome() +"!");
            this.mana -= 20;
            double danoMagico = this.forca * 1.8; // dano mágico é 1.5 vezes a força
            alvo.setHp(alvo.getHp() - (int)danoMagico);
            System.out.println("Dano Magico: " + (int)danoMagico + 
                                " Mana restante: " + this.mana);
        }else{
            System.out.println("\n"+this.nome + 
                                "tentou lançar magia, mas não tem mana suficiente!");
        }
    }

    public void usarPocao(){
       System.out.println("\n"+this.nome + "usou porção de cura!");
       Random rand = new Random();
       int cura = rand.nextInt(21) + 10; // cura aleatória entre 10 e 30
       this.setHp(this.hp + cura);
       System.out.println(this.nome + " recuperou " + cura + 
                          " de HP.(HP Atual: " + this.getHp() + ")");
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

        Personagem heroi = new Personagem(nomeHeroi, 150, 40, 100);
        Personagem monstro = new Personagem("Orc Zumbi",150, 15,0);

        System.out.println("\n Um "+monstro.getNome()+" apareceu! Prepare para a chibata!");
       
        while (heroi.getHp() > 0 && monstro.getHp() > 0) {
            System.out.println("--- SEU TURNO ---");            
            System.out.println("1 - ATACAR COM ESPADA");            
            System.out.println("2 - GRITAR PARA INTIMIDAR"); 
            System.out.println("3 - CURAR (Recupera 10 de HP)");
            System.out.println("4 - Magia de Fogo (Causa 20 de Dano)");          
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
                    System.out.println("O " +monstro.getNome() + " sofreu 20 de dano!");  
                    heroi.usarMagia(monstro);
                    System.out.println("O " +monstro.getNome() + " agora tem (HP Atual: " + monstro.getHp() + ")");
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

