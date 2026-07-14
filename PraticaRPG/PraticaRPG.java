import java.util.Random;
import java.util.Scanner;

class Personagem {
    String nome;
    int hp;
    int hpMax;
    int forca;
    int pocoes;
    boolean defendendo;

    public Personagem(String nome, int hp, int forca) {
        this.nome = nome;
        this.hp = hp;
        this.hpMax = hp;
        this.forca = forca;
        this.pocoes = 3;
        this.defendendo = false;
    }

    public void atacar(Personagem alvo) {
        Random random = new Random();

        int dano = forca;

        // 20% de chance de crítico
        if (random.nextInt(100) < 20) {
            dano *= 2;
            System.out.println("💥 ATAQUE CRÍTICO!");
        }

        if (alvo.defendendo) {
            dano /= 2;
            alvo.defendendo = false;
            System.out.println(alvo.nome + " reduziu o dano pela metade!");
        }

        alvo.hp -= dano;

        if (alvo.hp < 0)
            alvo.hp = 0;

        System.out.println("⚔️ " + nome + " causou " + dano + " de dano.");
        System.out.println(alvo.nome + " agora possui " + alvo.hp + " HP.");
    }

    public void usarPocao() {

        if (pocoes > 0) {

            hp += 30;

            if (hp > hpMax)
                hp = hpMax;

            pocoes--;

            System.out.println("🧪 Você recuperou 30 HP!");
            System.out.println("HP: " + hp);
            System.out.println("Poções restantes: " + pocoes);

        } else {
            System.out.println("❌ Você não possui mais poções.");
        }
    }

    public void defender() {
        defendendo = true;
        System.out.println("🛡️ " + nome + " entrou em posição de defesa.");
    }
}

public class PraticaRPG {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("      ARENA RPG");
        System.out.println("===========================");

        System.out.print("Nome do herói: ");
        String nome = leitor.nextLine();

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Paladino");

        int classe = leitor.nextInt();

        Personagem heroi;

        switch (classe) {

            case 1:
                heroi = new Personagem(nome + " (Guerreiro)", 120, 25);
                break;

            case 2:
                heroi = new Personagem(nome + " (Mago)", 80, 35);
                break;

            case 3:
                heroi = new Personagem(nome + " (Arqueiro)", 90, 30);
                break;

            default:
                heroi = new Personagem(nome + " (Paladino)", 140, 20);
        }

        Personagem monstro = new Personagem("👹 KHULISO", 140, 18);

        while (heroi.hp > 0 && monstro.hp > 0) {

            System.out.println("\n===========================");
            System.out.println(heroi.nome + " HP: " + heroi.hp + "/" + heroi.hpMax);
            System.out.println(monstro.nome + " HP: " + monstro.hp);
            System.out.println("===========================");

            System.out.println("1 - Atacar");
            System.out.println("2 - Ataque Especial");
            System.out.println("3 - Defender");
            System.out.println("4 - Usar Poção");
            System.out.println("5 - Gritar");

            int opcao = leitor.nextInt();

            switch (opcao) {

                case 1:
                    heroi.atacar(monstro);
                    break;

                case 2:

                    System.out.println("🔥 Ataque Especial!");

                    int danoOriginal = heroi.forca;
                    heroi.forca += 15;

                    heroi.atacar(monstro);

                    heroi.forca = danoOriginal;

                    break;

                case 3:
                    heroi.defender();
                    break;

                case 4:
                    heroi.usarPocao();
                    break;

                case 5:
                    System.out.println("🗣️ Você gritou muito alto...");
                    System.out.println("O monstro apenas riu.");
                    break;

                default:
                    System.out.println("Ação inválida.");
            }

            if (monstro.hp > 0) {

                System.out.println("\n----- TURNO DO MONSTRO -----");

                monstro.atacar(heroi);
            }

        }

        System.out.println("\n===========================");

        if (heroi.hp > 0)
            System.out.println("🏆 Vitória!");
        else
            System.out.println("☠️ Você foi derrotado.");

        leitor.close();
    }
}