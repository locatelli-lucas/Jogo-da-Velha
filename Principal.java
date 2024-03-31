import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        int pontuacaoJ1 = 0;
        int pontuacaoJ2 = 0;

        System.out.println("Digite o nome do jogador 1: ");
        String nomeJ1 = sc.next();
        Jogador j1 = new Jogador(nomeJ1, pontuacaoJ1);

        System.out.println("Digite o nome do jogador 2: ");
        String nomeJ2 = sc.next();
        Jogador j2 = new Jogador(nomeJ2, pontuacaoJ2);

        System.out.println("Digite o número de linhas e colunas no tabuleiro: ");
        int dimensao = sc.nextInt();
        JogoDaVelha jogo = new JogoDaVelha(dimensao);
        String[][] tabuleiro = jogo.criaTabuleiro(dimensao);
        jogo.imprimeMatriz(tabuleiro);


        String resposta = "S";
        String caractere;
        String nomeJogador = nomeJ1;
        while(resposta.equalsIgnoreCase("S")) {
            System.out.println("É a vez do "+nomeJogador+": ");
            if(nomeJogador.equals(nomeJ1)) {
                caractere = "X";
                nomeJogador = nomeJ2;
            } else {
                caractere = "O";
                nomeJogador = nomeJ1;
            }
            Thread.sleep(1500);
            while(true) {
                System.out.println("Realize sua jogada...\n" +
                        "Digita a posição na linha: ");
                int posLinha = sc.nextInt();
                System.out.println("Digita a posição na coluna: ");
                int posColuna = sc.nextInt();
                if(jogo.realizaJogada(posLinha, posColuna, caractere))
                    break;
            }

            jogo.verificaGanhador(nomeJ1, nomeJ2);
            jogo.imprimeMatriz(tabuleiro);

            if(jogo.verificaGanhador(nomeJ1, nomeJ2)) {
                if(caractere.equals("X"))
                    j1.setPontuacao(j1.getPontuacao() + 1);
                else
                    j2.setPontuacao(j2.getPontuacao() + 1);

                System.out.println("Pontuação do "+nomeJ1+": "+j1.getPontuacao()+"\n" +
                        "Pontuação do "+nomeJ2+": "+j2.getPontuacao());

                System.out.println("Vocês gostariam de jogar novamente? ");
                resposta = sc.next();

                if(resposta.equalsIgnoreCase("S")) {
                    System.out.println("Digite o número de linhas e colunas no tabuleiro: ");
                    dimensao = sc.nextInt();
                    jogo = new JogoDaVelha(dimensao);
                    tabuleiro = jogo.criaTabuleiro(dimensao);
                    jogo.imprimeMatriz(tabuleiro);
                    nomeJogador = nomeJ1;
                } else {
                    System.out.println("Obrigado por jogar!");
                    jogo.criaArquivo(j1);
                }
            } else if(jogo.finalizaJogo(tabuleiro, nomeJ1, nomeJ2)) {
                System.out.println("Vocês gostariam de jogar novamente? ");
                resposta = sc.next();

                if(resposta.equalsIgnoreCase("S")) {
                    System.out.println("Empate!!!");
                    System.out.println("Digite o número de linhas e colunas no tabuleiro: ");
                    dimensao = sc.nextInt();
                    jogo = new JogoDaVelha(dimensao);
                    tabuleiro = jogo.criaTabuleiro(dimensao);
                    jogo.imprimeMatriz(tabuleiro);
                    nomeJogador = nomeJ1;
                } else {
                    System.out.println("Obrigado por jogar!");
                    jogo.criaArquivo(j1);
                }
            }
        }

        sc.close();
    }
}
