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

    @Override
    public void atacar(Personagem alvo) {
        System.out.println("\n🗡️ " + this.getNome() + " desferiu um golpe poderoso em " + alvo.getNome() + "!");

        int dano = this.getForca() + 10;

        alvo.setHp(alvo.getHp() - dano);

        System.out.println("🩸 " + alvo.getNome() + " perdeu " + dano + " de HP. (HP Restante: " + alvo.getHp() + ")");
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

class Assassino extends Personagem {
    private int energia;

    public Assassino(String nome, int hpMax, int forca, int energia) {
        super(nome, hpMax, forca);
        this.energia = energia;
    }

    @Override
    public void atacar(Personagem alvo) {
        Random rand = new Random();

        if (energia >= 10) {
            energia -= 10;
            int dano = getForca() + rand.nextInt(21) + 10;
            System.out.println("\n🗡️ " + getNome() + " apareceu nas sombras e acertou um golpe crítico em " + alvo.getNome() + "!");
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("Dano: " + dano + " | Energia: " + energia);
        } else {
            int dano = getForca();
            System.out.println("\n🗡️ " + getNome() + " atacou normalmente.");
            alvo.setHp(alvo.getHp() - dano);
        }
    }
}

class Paladino extends Personagem {
    private int fe;

    public Paladino(String nome, int hpMax, int forca, int fe) {
        super(nome, hpMax, forca);
        this.fe = fe;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca() + 15;
        System.out.println("\n⚜️ " + getNome() + " usou Golpe Sagrado em " + alvo.getNome() + "!");
        alvo.setHp(alvo.getHp() - dano);
        System.out.println("Dano: " + dano);
    }

    public void curaDivina() {
        if (fe >= 20) {
            fe -= 20;
            setHp(getHp() + 40);
            System.out.println(getNome() + " recuperou 40 de HP!");
        } else {
            System.out.println(getNome() + " não possui fé suficiente!");
        }
    }
}

class Ninja extends Personagem {
    private int shuriken;

    public Ninja(String nome, int hpMax, int forca, int shuriken) {
        super(nome, hpMax, forca);
        this.shuriken = shuriken;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (shuriken > 0) {
            shuriken--;
            int dano = getForca() + 20;
            System.out.println("\n🥷 " + getNome() + " lançou uma Shuriken Mortal em " + alvo.getNome() + "!");
            alvo.setHp(alvo.getHp() - dano);
            System.out.println("Dano: " + dano + " | Shurikens restantes: " + shuriken);
        } else {
            super.atacar(alvo);
        }
    }
}

class Goblin extends Monstro {

    public Goblin(String nome) {
        super(nome, 80, 12);
    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println("\n👺 " + getNome() + " atacou com sua adaga enferrujada!");
        super.atacar(alvo);
    }
}

class Esqueleto extends Monstro {

    public Esqueleto(String nome) {
        super(nome, 100, 18);
    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println("\n💀 " + getNome() + " golpeou com um osso gigante!");
        super.atacar(alvo);
    }
}

class Dragao extends Monstro {

    public Dragao(String nome) {
        super(nome, 300, 35);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = getForca() + 25;
        System.out.println("\n🐉 " + getNome() + " cuspiu fogo em " + alvo.getNome() + "!");
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(alvo.getNome() + " sofreu " + dano + " de dano.");
    }
}

class Bruxa extends Monstro {

    public Bruxa(String nome) {
        super(nome, 140, 20);
    }

    @Override
    public void atacar(Personagem alvo) {
        Random rand = new Random();
        int dano = getForca() + rand.nextInt(16);
        System.out.println("\n🧙‍♀️ " + getNome() + " lançou uma maldição em " + alvo.getNome() + "!");
        alvo.setHp(alvo.getHp() - dano);
        System.out.println("Dano mágico: " + dano);
    }
}

public class PraticaRPG {
    public static void main(String[] args) {
    Scanner leitor = new Scanner(System.in);
    Random rand = new Random();

    System.out.println("===============================");
    System.out.println("     BEM-VINDO À ARENA RPG");
    System.out.println("===============================");

    System.out.print("Digite o nome do seu personagem: ");
    String nomeHeroi = leitor.nextLine();

    System.out.println("\nEscolha sua classe:");
    System.out.println("1 - Guerreiro");
    System.out.println("2 - Mago");
    System.out.println("3 - Arqueiro");
    System.out.println("4 - Assassino");
    System.out.println("5 - Paladino");
    System.out.println("6 - Ninja");
    System.out.print("Opção: ");

    int escolha = leitor.nextInt();

    Personagem heroi;

    switch (escolha) {
        case 1:
            heroi = new Guerreiro(nomeHeroi, 150, 20);
            break;

        case 2:
            heroi = new Mago(nomeHeroi, 110, 15, 100);
            break;

        case 3:
            heroi = new Arqueiro(nomeHeroi, 120, 15, 15, 10);
            break;

        case 4:
            heroi = new Assassino(nomeHeroi, 120, 18, 50);
            break;

        case 5:
            heroi = new Paladino(nomeHeroi, 170, 18, 60);
            break;

        case 6:
            heroi = new Ninja(nomeHeroi, 130, 20, 10);
            break;

        default:
            heroi = new Guerreiro(nomeHeroi, 150, 20);
            break;
    }

    Monstro monstro;

    int sorteio = rand.nextInt(5);

    switch (sorteio) {
        case 0:
            monstro = new Goblin("Goblin");
            break;

        case 1:
            monstro = new Esqueleto("Esqueleto Guerreiro");
            break;

        case 2:
            monstro = new Dragao("Dragão Ancestral");
            break;

        case 3:
            monstro = new Bruxa("Bruxa Sombria");
            break;

        default:
            monstro = new MonstroChefe("Orc Zumbi Chefe", 150, 15);
            break;
    }

    System.out.println("\nUm " + monstro.getNome() + " apareceu!");

    while (heroi.getHp() > 0 && monstro.getHp() > 0) {

        System.out.println("\n====================");
        System.out.println("HP " + heroi.getNome() + ": " + heroi.getHp());
        System.out.println("HP " + monstro.getNome() + ": " + monstro.getHp());
        System.out.println("====================");

        System.out.println("1 - Atacar");
        System.out.println("2 - Curar");

        if (heroi instanceof Mago) {
            System.out.println("3 - Magias");
        }

        if (heroi instanceof Paladino) {
            System.out.println("4 - Cura Divina");
        }

        System.out.print("Escolha: ");
        int acao = leitor.nextInt();

        switch (acao) {

            case 1:
                heroi.atacar(monstro);
                break;

            case 2:
                heroi.usarPocao();
                break;

            case 3:

                if (heroi instanceof Mago) {
                    Mago mago = (Mago) heroi;

                    System.out.println("1 - Fogo");
                    System.out.println("2 - Gelo");
                    System.out.println("3 - Raio");

                    int magia = leitor.nextInt();

                    switch (magia) {

                        case 1:
                            mago.usarMagiaFogo(monstro);
                            break;

                        case 2:
                            mago.usarMagiaGelo(monstro);
                            break;

                        case 3:
                            mago.usarMagiaRaio(monstro);
                            break;

                        default:
                            System.out.println("Magia inválida!");
                    }

                } else {
                    System.out.println("Você não é um Mago!");
                }

                break;

            case 4:

                if (heroi instanceof Paladino) {
                    ((Paladino) heroi).curaDivina();
                } else {
                    System.out.println("Ação inválida.");
                }

                break;

            default:
                System.out.println("Você perdeu o turno.");
        }

        if (monstro.getHp() > 0) {
            System.out.println("\n--- TURNO DO MONSTRO ---");
            monstro.atacar(heroi);
        }
    }

    if (heroi.getHp() > 0) {
        System.out.println("\n🏆 PARABÉNS! Você derrotou " + monstro.getNome() + "!");
    } else {
        System.out.println("\n💀 GAME OVER! " + heroi.getNome() + " foi derrotado.");
    }

    leitor.close();
}
}