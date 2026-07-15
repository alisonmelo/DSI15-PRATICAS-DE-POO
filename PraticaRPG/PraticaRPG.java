import java.util.Random;
import java.util.Scanner;

class Personagem {

    private String nome;
    private int hp;
    private int hpMax;
    private int forca;
    private int pocoes;
    private int mana;
    private int manaMax;
    private String classe;
    private boolean defendendo;

    public Personagem(String nome, int hp, int forca, String classe) {

        this.nome = nome;
        this.hp = hp;
        this.hpMax = hp;
        this.forca = forca;
        this.pocoes = 3;
        this.classe = classe;
        this.defendendo = false;

        if (classe.equals("Mago")) {

            this.mana = 100;
            this.manaMax = 100;

        } else {

            this.mana = 0;
            this.manaMax = 0;

        }
    }

    public String getNome() {
        return nome;

    }

    public int getHp() {
        return hp;

    }

    public int getHpMax() {
        return hpMax;

    }

    public int getMana() {
        return mana;

    }

    public int getManaMax() {
        return manaMax;

    }

    public String getClasse() {
        return classe;

    }

    public void atacar(Personagem alvo) {
        Random random = new Random();
        int dano = forca;
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
        if (alvo.hp < 0) {
            alvo.hp = 0;

        }

        System.out.println("⚔️ " + nome + " causou " + dano + " de dano.");
        System.out.println(alvo.nome + " agora possui " + alvo.hp + " HP.");

    }

    public void golpePesado(Personagem alvo) {
        int dano = forca + 20;
        alvo.hp -= dano;

        if (alvo.hp < 0) {
            alvo.hp = 0;

        }
        
        System.out.println("🗡️ " + nome + " usou GOLPE PESADO!");
        System.out.println("Causou " + dano + " de dano.");
        System.out.println(alvo.nome + " agora possui " + alvo.hp + " HP.");

    }

    public void bolaDeFogo(Personagem alvo) {
        if (mana >= 20) {
            mana -= 20;
            int dano = 50;
            alvo.hp -= dano;
            if (alvo.hp < 0) {
                alvo.hp = 0;

            }

            System.out.println("🔥 " + nome + " lançou BOLA DE FOGO!");
            System.out.println("Causou " + dano + " de dano.");
            System.out.println("Mana restante: " + mana);

        } else {

            System.out.println("❌ Mana insuficiente!");

        }

    }

    public void flechaDupla(Personagem alvo) {

        System.out.println("🏹 " + nome + " usou FLECHA DUPLA!");

        atacar(alvo);
        if (alvo.hp > 0) {
            atacar(alvo);

        }

    }

    public void curaDivina() {
        int cura = 40;
        hp += cura;
        if (hp > hpMax) {
            hp = hpMax;

        }

        System.out.println("✨ " + nome + " usou CURA DIVINA!");
        System.out.println("Recuperou " + cura + " HP.");
        System.out.println("HP atual: " + hp);

    }

    public void usarPocao() {
        if (pocoes > 0) {
            hp += 30;
            if (hp > hpMax) {
                hp = hpMax;

            }

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
        System.out.println("        ARENA RPG");
        System.out.println("===========================");

        System.out.print("Nome do herói: ");

        String nome = leitor.nextLine();

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Paladino");

        int classeEscolhida = leitor.nextInt();

        Personagem heroi;

        switch (classeEscolhida) {

            case 1:
                heroi = new Personagem(nome + " (Guerreiro)", 120, 25, "Guerreiro");
                break;

            case 2:
                heroi = new Personagem(nome + " (Mago)", 80, 35, "Mago");
                break;

            case 3:
                heroi = new Personagem(nome + " (Arqueiro)", 90, 30, "Arqueiro");
                break;

            default:
                heroi = new Personagem(nome + " (Paladino)", 140, 20, "Paladino");

        }

        Personagem monstro = new Personagem("👹 THANOS", 140, 18, "Monstro");

        while (heroi.getHp() > 0 && monstro.getHp() > 0) {

            System.out.println("\n===========================");

            System.out.println(
                heroi.getNome() +
                " HP: " +
                heroi.getHp() +
                "/" +
                heroi.getHpMax()
            );

            if (heroi.getClasse().equals("Mago")) {

                System.out.println(
                    "Mana: " +
                    heroi.getMana() +
                    "/" +
                    heroi.getManaMax()
                );

            }

            System.out.println(
                monstro.getNome() +
                " HP: " +
                monstro.getHp()
            );

            System.out.println("===========================");
            System.out.println("1 - Atacar");
            System.out.println("2 - Habilidade Especial");
            System.out.println("3 - Defender");
            System.out.println("4 - Usar Poção");
            System.out.println("5 - Gritar");

            int opcao = leitor.nextInt();

            switch (opcao) {

                case 1:
                    heroi.atacar(monstro);
                    break;

                case 2:
                    System.out.println("\n✨ HABILIDADE ESPECIAL!");
                    switch (heroi.getClasse()) {

                       case "Guerreiro":
                            heroi.golpePesado(monstro);
                            break;

                        case "Mago":
                            heroi.bolaDeFogo(monstro);
                            break;

                        case "Arqueiro":
                            heroi.flechaDupla(monstro);
                            break;

                        case "Paladino":
                            heroi.curaDivina();
                            break;

                        default:
                            System.out.println("Classe sem habilidade.");
                            break;

                    }
                   
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

            if (monstro.getHp() > 0) {
                System.out.println("\n----- TURNO DO MONSTRO -----");
                monstro.atacar(heroi);

            }

        }

        System.out.println("\n===========================");
        if (heroi.getHp() > 0) {
            System.out.println("🏆 Vitória!");
        } else {

            System.out.println("☠️ Você foi derrotado.");

        }

        leitor.close();

    }

}