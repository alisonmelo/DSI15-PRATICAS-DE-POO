import java.util.Scanner;
import java.util.Random;

// =====================================================
// EXPLICAÇÃO PARA OS ALUNOS
// =====================================================
// Este arquivo mostra, na prática, os principais conceitos de Programação Orientada a Objetos:
// - Herança: classes filhas herdam características da classe pai.
// - Polimorfismo: objetos diferentes podem responder de forma diferente ao mesmo método.
// - Encapsulamento: atributos privados são protegidos e acessados por métodos.
// - Abstração: interfaces definem contratos que as classes implementam.
//
// Também podemos relacionar isso com os princípios S.O.L.I.D.:
// S - Single Responsibility (Responsabilidade Única): cada classe tem uma função bem definida.
// O - Open/Closed: podemos estender o comportamento com subclasses sem modificar a base.
// L - Liskov: subclasses podem ser usadas no lugar da classe base sem quebrar o programa.
// I - Interface Segregation: interfaces pequenas e específicas deixam o código mais limpo.
// D - Dependency Inversion: dependemos de abstrações (interfaces) e não de detalhes concretos.
// =====================================================

interface Usavel {
    // Interface para representar itens que podem ser usados por um personagem.
    void usar(Personagem alvo);
}

interface Inimigo {
    // Contrato básico de qualquer criatura que pode lutar.
    String getNome();

    int getHp();

    void receberDano(int dano);

    int calcularDanoAtaque();

    void realizarAcao(Inimigo alvo);
}

class NarradorDeCombate {
    // Aqui temos uma classe com responsabilidade única: narrar os eventos da batalha.
    // Isso é um exemplo de S (Single Responsibility), porque ela não luta, não cura e nem decide ações.

    public static void narrarAtaque(String atacante, String alvo, int dano, int hpRestante) {
        System.out.println("\n⚔️​  " + atacante + " desferiu um ataque!");
        System.out.println("\n🩸​  " + alvo + " perdeu " + dano + "de HP. (HP Restante: " + hpRestante + ")");
    }

    public static void narrarAtaqueCritico(String atacante, String alvo, int dano, int hpRestante) {
        System.out.println("\n💥 ACERTO CRÍTICO! " + atacante + " desferiu um golpe devastador!");
        System.out.println("🩸  " + alvo + " perdeu incríveis " + dano + " de HP. (HP Restante: " + hpRestante + ")");
    }

    public static void narrarCura(String nome, int cura, int hpAtual) {
        System.out.println("\n🛡️​​  " + nome + " usou um intem de cura!");
        System.out.println("\n💖​ Recuperou " + cura + "de HP. (HP Atual: " + hpAtual + ")");
    }

}

class PocaoVida implements Usavel {
    // Esta classe representa um item consumível. Ela implementa a interface Usavel,
    // então qualquer item do jogo pode seguir esse contrato.
    @Override
    public void usar(Personagem alvo) {
        Random rand = new Random();
        int cura = rand.nextInt(21) + 10; // cura aleatória entre 10 e 30
        alvo.setHp(alvo.getHp() + cura);
        NarradorDeCombate.narrarCura(alvo.getNome(), cura, alvo.getHp());
    }
}

class Personagem implements Inimigo {
    // Esta é a classe base do nosso jogo. Ela representa o comportamento geral de todos os personagens.
    // Aqui aplicamos encapsulamento, pois os atributos são privados e só podem ser acessados por métodos.

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

    // getters: permitem acessar os dados sem expor diretamente os atributos.
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

    // setter com regra de validação: evita que a vida passe do máximo ou fique negativa.
    public void setHp(int novoHp) {
        if (novoHp > hpMax) {
            this.hp = hpMax;
        } else if (novoHp < 0) {
            this.hp = 0;
        } else {
            this.hp = novoHp;
        }
    }

    // Implementação dos métodos obrigatórios da interface Inimigo.
    @Override
    public void receberDano(int dano) {
        this.setHp(this.getHp() - dano);
    }

    @Override
    public int calcularDanoAtaque() {
        return this.forca;
    }

    // Lógica de ação do inimigo. Aqui vemos polimorfismo porque o mesmo método pode ter comportamento diferente nas subclasses.
    @Override
    public void realizarAcao(Inimigo heroi) {
        Random rand = new Random();
        // Estratégia simples de IA: se o inimigo estiver quase morto, ele tenta se salvar.
        if (this.getHp() < (this.getHpMax() * 0.4)) {
            if (rand.nextInt(100) < 50) {
                System.out.println("\n O" + this.getNome() + "Percebe que esta morrendo e usou uma cura ");
                return;
            }
        }

        int chanceAtaqueEspecial = rand.nextInt(100);
        if (chanceAtaqueEspecial < 30) {
            int dano = this.getForca() * 2;
            heroi.receberDano(dano);
            NarradorDeCombate.narrarAtaqueCritico(this.getNome(), heroi.getNome(), dano, heroi.getHp());
        } else {
            this.atacar(heroi);
        }
    }

    public void atacar(Inimigo alvo) {
        int dano = this.forca;
        // Reduzir hp do alvo
        alvo.receberDano(dano);
        NarradorDeCombate.narrarAtaque(this.nome, alvo.getNome(), dano, alvo.getHp());
    }

    public void usarItem(Usavel item) {
        // Este método mostra o princípio de dependência por abstração.
        // O personagem depende de um contrato (interface), não de uma implementação específica.
        item.usar(this);
    }
}

class Mago extends Personagem {
    // O Mago é uma especialização de Personagem. Ele herda o básico e adiciona uma habilidade própria: mana.
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
    public void atacar(Inimigo alvo) {
        if (this.mana >= 5) {
            System.out.println("\n" + this.getNome() +
                    " jogou bola de mana em "
                    + alvo.getNome() + "! Mana restante: " + this.mana);
            this.mana -= 5;
            int dano = this.getForca() + 5;
            alvo.receberDano(dano);
            NarradorDeCombate.narrarAtaque(this.getNome(),
                    alvo.getNome(),
                    dano,
                    alvo.getHp());
        } else {
            System.out.println("\n" + this.getNome() +
                    " Não possui mana suficiente, deu uma cajadada no "
                    + alvo.getNome() + "!");
            int dano = this.getForca() - 5;
            alvo.receberDano(dano);
            NarradorDeCombate.narrarAtaque(this.getNome(),
                    alvo.getNome(),
                    dano,
                    alvo.getHp());
        }
    }

    public void usarMagiaFogo(Inimigo alvo) {
        if (this.mana >= 10) {
            System.out.println("\n" + this.getNome() + " lançou magia de fogo em " + alvo.getNome() + "!");
            this.mana -= 10;
            double danoMagico = this.getForca() * 1.5; // dano mágico é 1.5 vezes a força
            alvo.receberDano((int) danoMagico);
            System.out.println("Dano Magico: " + (int) danoMagico +
                    " Mana restante: " + this.mana);
        } else {
            System.out.println("\n" + this.getNome() +
                    "tentou lançar magia, mas não tem mana suficiente!");
        }
    }

    public void usarMagiaRaio(Inimigo alvo) {
        if (this.mana >= 35) {
            System.out.println("\n" + this.getNome() + " lançou magia de raio em " + alvo.getNome() + "!");
            this.mana -= 35;
            double danoMagico = this.getForca() * 2; // dano mágico é 1.5 vezes a força
            alvo.receberDano((int) danoMagico);
            System.out.println("Dano Magico: " + (int) danoMagico +
                    " Mana restante: " + this.mana);
        } else {
            System.out.println("\n" + this.getNome() +
                    "tentou lançar magia, mas não tem mana suficiente!");
        }
    }

    public void usarMagiaGelo(Inimigo alvo) {
        if (this.mana >= 20) {
            System.out.println("\n" + this.getNome() + " lançou magia de gelo em " + alvo.getNome() + "!");
            this.mana -= 20;
            double danoMagico = this.getForca() * 1.8; // dano mágico é 1.5 vezes a força
            alvo.receberDano((int) danoMagico);
            System.out.println("Dano Magico: " + (int) danoMagico +
                    " Mana restante: " + this.mana);
        } else {
            System.out.println("\n" + this.getNome() +
                    "tentou lançar magia, mas não tem mana suficiente!");
        }
    }
}

class Arqueiro extends Personagem {
    // O Arqueiro também herda de Personagem, mas adiciona o conceito de flechas como recurso próprio.
    private int flechas;
    private int forcaFlecha;

    public Arqueiro(String nome, int hpMax, int forca, int flechas, int forcaFlecha) {
        super(nome, hpMax, forca);
        this.flechas = flechas;
        this.forcaFlecha = forcaFlecha;
    }

    @Override
    public void atacar(Inimigo alvo) {
        if (this.flechas >= 1) {
            System.out.println("\n🏹 ​" + this.getNome() +
                    " Atirou uma flecha em "
                    + alvo.getNome() + "! Restam " + this.flechas + " flechas!");
            this.flechas--;
            int dano = this.getForca() + this.forcaFlecha;
            alvo.receberDano(dano);
            NarradorDeCombate.narrarAtaque(this.getNome(),
                    alvo.getNome(),
                    dano,
                    alvo.getHp());

        } else {
            System.out.println("\n ​" + this.getNome() +
                    " não tem flechas ele correu e bateu com o arco na cabeça de "
                    + alvo.getNome() + "!");
            int dano = this.getForca() - 5;
            alvo.receberDano(dano);
            NarradorDeCombate.narrarAtaque(this.getNome(),
                    alvo.getNome(),
                    dano,
                    alvo.getHp());

        }
    }

}

class Guerreiro extends Personagem {
    // O Guerreiro sobrescreve o método atacar para ter uma lógica própria.
    // Isso é um ótimo exemplo de polimorfismo em ação.

    public Guerreiro(String nome, int hpMax, int forca) {
        super(nome, hpMax, forca);
    }

    @Override
    public void atacar(Inimigo alvo) {
        Random rand = new Random();
        int danoBase = this.getForca();
        int bonus = rand.nextInt(41) + 10;
        int dano = danoBase + bonus;

        if (dano > (danoBase * 2)) {
            NarradorDeCombate.narrarAtaqueCritico(this.getNome(), alvo.getNome(), dano, alvo.getHp());

        } else if (dano > danoBase) {
            NarradorDeCombate.narrarAtaque(this.getNome(), alvo.getNome(), dano, alvo.getHp());
        }
        alvo.receberDano(dano);

    }

}

class Monstro extends Personagem {
    // Monstros também herdam de Personagem, porém podem ter comportamento mais simples.
    public Monstro(String nome, int hpMax, int forca) {
        super(nome, hpMax, forca);
    }
}

// Aqui temos uma especialização de Monstro, mostrando que a herança pode continuar em níveis.
class MonstroChefe extends Monstro {
    public MonstroChefe(String nome, int hpMax, int forca) {
        super(nome, hpMax * 2, forca + 15);
    }

    @Override
    public void atacar(Inimigo alvo) {
        System.out.println("\n 👹 " + this.getNome() + " ataca ferozmente " + alvo.getNome() + " com força de "
                + this.getForca() + "!");
        super.atacar(alvo);
    }
}

public class PraticaRPG {
    // Este é o ponto de entrada do programa. Aqui o jogador interage com o sistema,
    // cria seu personagem e controla o fluxo do jogo.
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        System.out.println("==========================");
        System.out.println("BEM VINDO A ARENA (NOME DA SUA ARENA)");
        System.out.println("==========================");

        System.out.println("=== CRIAÇÃO DE PERSONAGEM ===");
        System.out.print("Digite o nome do seu Personagem: ");
        String nomeHeroi = leitor.nextLine();

        // Array com as opções de classes. Isso deixa o código mais organizado e fácil de expandir.
        String[] classesDisponiveis = { "Guerreiro", "Mago", "Arqueiro", "Monstro" };
        System.out.println("\nEscolha sua vocação");
        for (int i = 0; i < classesDisponiveis.length; i++) {
            System.out.println((i + 1) + " - " + classesDisponiveis[i]);
        }
        System.out.print("Sua escolha: ");
        int escolhaClasse = leitor.nextInt();

        int pontosDisponiveis = 10;
        int forcaEscolhida = 5;
        int manaEscolhido = 0;
        int hpBase = 100;

        System.out.println("Você tem " + pontosDisponiveis + " pontos para distribuir.");
        System.out.print("Quantos pontos quer investir em Força?: ");
        int pontosForca = leitor.nextInt();

        // Trava de segurança para evitar que o jogador distribua mais pontos do que possui.
        if (pontosForca > pontosDisponiveis) {
            pontosForca = pontosDisponiveis;
        }
        forcaEscolhida += pontosForca;
        pontosDisponiveis -= pontosForca;

        Personagem heroi = null;

        // O switch cria o personagem correto de acordo com a classe escolhida.
        // Aqui o polimorfismo já aparece: o mesmo tipo Personagem pode receber objetos diferentes.
        switch (escolhaClasse) {
            case 1:
                heroi = new Guerreiro(nomeHeroi, hpBase, forcaEscolhida);
                break;
            case 2:
                System.out.println("Como você é um mago, os "
                        + pontosDisponiveis +
                        " pontos restantes foram para sua MANA!");
                manaEscolhido = 20 + (pontosDisponiveis * 5);
                heroi = new Mago(nomeHeroi, hpBase, forcaEscolhida, manaEscolhido);
                break;
            case 3:
                System.out.println("Como você é um arqueiro, você começa com 8 flechas!");
                heroi = new Arqueiro(nomeHeroi, hpBase, forcaEscolhida, 8, 15);
                break;
            case 4:
                heroi = new Monstro(nomeHeroi, hpBase, forcaEscolhida);
            default:
                System.out.println("Escolha inválida, você virou um Guerreiro por padrão");
                heroi = new Guerreiro(nomeHeroi, hpBase, forcaEscolhida);
                break;
        }

        Inimigo inimigoAtual = null;
        int nivelHeroi = 1;
        int acaoCidade;

        // =======================================================
        // A CAIXA GRANDE: A CIDADE
        // =======================================================
        while (true) { // Loop principal do jogo.
            System.out.println("\n=== ACAMPAMENTO BASE (Nível: " + nivelHeroi + ") ===");
            System.out.println("1 - Caça Inimigo (Ganhar nivel)");
            System.out.println("2 - Desafiar Chefe final");
            System.out.println("3 - Dormir (Recuperar todo HP)");
            System.out.println("4 - Desistir da jornada (Sair do jogo)");
            System.out.print("Sua ação: ");
            acaoCidade = leitor.nextInt();

            boolean iniciarBatalha = false;

            switch (acaoCidade) {
                case 1:
                    inimigoAtual = new Monstro("Goblin Hepático", 40, 5);
                    iniciarBatalha = true;
                    break;
                case 2:
                    inimigoAtual = new MonstroChefe("Dragão Doidão", 150, 40);
                    iniciarBatalha = true;
                    break;
                case 3:
                    heroi.setHp(heroi.getHpMax());
                    System.out.println("Você dormiu e recuperou sua vida!");
                    continue; // Volta pro topo do acampamento
                case 4:
                    System.out.println("Você saiu do jogo");
                    leitor.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escolha uma opção válida!");
                    continue; // Volta pro topo do acampamento
            }

            // =======================================================
            // A CAIXA PEQUENA: A BATALHA (FICA DENTRO DA CIDADE!)
            // =======================================================
            if (iniciarBatalha) {
                boolean voltarAcamp = false;
                
                // Enquanto o herói e o inimigo estiverem vivos, a batalha continua.
                while (heroi.getHp() > 0 && inimigoAtual.getHp() > 0 && !voltarAcamp) {
                    System.out.println("\n Um " + inimigoAtual.getNome() + " apareceu! Prepare para a chibata!");
                    System.out.println("--- SEU TURNO ---");
                    System.out.println("1 - ATACAR");
                    System.out.println("2 - GRITAR PARA INTIMIDAR");
                    System.out.println("3 - CURAR (Recupera 10 de HP)");
                    if (heroi instanceof Mago) {
                        System.out.println("4 - MAGIAS (Fogo, Gelo ou Raio)");
                    }
                    System.out.println("0 - FUGIR DA BATALHA");
                    System.out.print("Sua ação: ");
                    int acao = leitor.nextInt();

                    switch (acao) {
                        case 0:
                            voltarAcamp = true;
                            break;
                        case 1:
                            heroi.atacar(inimigoAtual);
                            break;
                        case 2:
                            System.out.println("\n🗣️ " + heroi.getNome() + " gritou: AAAAAAAAAHHHHH!");
                            System.out.println("O " + inimigoAtual.getNome() + " riu na sua cara lhe chamou de otário e não sofreu dano!");
                            break;
                        case 3:
                            System.out.println("\n🛡️ " + heroi.getNome() + " usou poção de cura!");
                            heroi.usarItem(new PocaoVida());
                            break;
                        case 4:
                            if (heroi instanceof Mago) {
                                Mago magoTemp = (Mago) heroi;
                                System.out.println("\nEscolha a magia: 1-Fogo | 2-Gelo | 3-Raio");
                                System.out.print("Sua escolha: ");
                                int escolhaMagia = leitor.nextInt();
                                switch (escolhaMagia) {
                                    case 1: magoTemp.usarMagiaFogo(inimigoAtual); break;
                                    case 2: magoTemp.usarMagiaGelo(inimigoAtual); break;
                                    case 3: magoTemp.usarMagiaRaio(inimigoAtual); break;
                                    default: System.out.println("\n ❌ Magia inválida!");
                                }
                            } else {
                                System.out.println("\n ❌ Você não tem afinidade com magia!");
                            }
                            break;
                        default:
                            System.out.println("\n ❌ Ação inválida!");
                    }

                    // Se o inimigo ainda estiver vivo e o herói não fugiu, ele ataca.
                    if (inimigoAtual.getHp() > 0 && !voltarAcamp) {
                        System.out.println("\n--- TURNO DO INIMIGO ---");
                        inimigoAtual.realizarAcao(heroi);
                    }
                } // FIM DO WHILE DA BATALHA

                // Lógica de pós-batalha.
                if (voltarAcamp) {
                    System.out.println("Você correu de volta para o acampamento!");
                } else if (heroi.getHp() <= 0) {
                    System.out.println("Você desmaiou e foi carregado para o acampamento!");
                    heroi.setHp(1);
                } else if (inimigoAtual.getHp() <= 0) {
                    System.out.println("VITÓRIA VC DERROTOU O " + inimigoAtual.getNome() + " :D");

                    if (acaoCidade == 2) {
                        System.out.println("Parabéns você zerou o jogo :D");
                        break; // Esse break encerra o jogo ao derrotar o chefe final.
                    } else {
                        nivelHeroi++;
                        System.out.println("Level Up - Você alcançou o nível " + nivelHeroi + "!");
                        System.out.println("Volte ao acampamento e vá dormir!");
                    }
                }
            } // FIM DO IF DA BATALHA

        } // Fechamento do loop principal do jogo.
        leitor.close();
    }

}
