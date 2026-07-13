import java.util.Scanner;

class Personagem {
    String nome;
    int hp;
    int forca;

    public Personagem(String nome, int hp , int forca){
        this.nome = nome;
        this.hp = hp;
        this.forca = forca;
    }

    public void atacar(Personagem alvoPersonagem){
        // personagem ataca outro personagem alvo
        System.out.println("\n⚔️​" + this.nome + " ataca " +alvoPersonagem.nome + " com força de " + this.forca +"!");
        
        // Reduzir hp do alvo
        alvoPersonagem.hp = alvoPersonagem.hp - this.forca;

        System.out.println("🩸 " + alvoPersonagem.nome+ " Perdeu " +this.forca+" de HP. (HP Restante: "+ alvoPersonagem.hp+")");
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

        Personagem heroi = new Personagem(nomeHeroi, 100, 50);
        Personagem monstro = new Personagem("KHULISO",60, 15);

        System.out.println("\n Um "+monstro.nome+" apareceu! Prepare para a chibata!");
       
        while (heroi.hp > 0 && monstro.hp > 0) {
            System.out.println("--- SEU TURNO ---");            
            System.out.println("1 - ATACAR COM ESPADA");            
            System.out.println("2 - GRITAR PARA INTIMIDAR");            
            System.out.print("Sua ação: ");
            int acao = leitor.nextInt();

            switch (acao){
                case 1:
                    heroi.atacar(monstro);
                    break;
                case 2:
                    System.out.println("\n🗣️​ " +heroi.nome+ " gritou: AAAAAAAAAHHHHH!");    
                    System.out.println("O " +monstro.nome + " riu na sua cara lhe chamou de otário e não sofreu dano!");
                    break;
                default:
                    System.out.println("\n ❌​ Ação inválida! Voçê perdeu o turno!");    
            }
            
            if (monstro.hp > 0){
                System.out.println("\n--- TURNO DO INIMIGO ---");
                monstro.atacar(heroi);
            }  
        }
        if (heroi.hp > 0){
            System.out.println("VITÓRIA VC DERROUTOU O "+monstro.nome+":D");
        } else{
            System.out.println("GAME OVER - " + heroi.nome + " MORREU NA ARENA X-X !");
        }

        leitor.close();
    }
    
}

