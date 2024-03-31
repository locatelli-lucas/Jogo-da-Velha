import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JogoDaVelha {
    private int dimensao;
    private String[][] tabuleiro;

    public JogoDaVelha(int dimensao) {
        this.dimensao = dimensao;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public String[][] criaTabuleiro(int dimensao) {
        tabuleiro = new String[dimensao][dimensao];
        System.out.println("Tabuleiro de dimensões "+dimensao+"x"+dimensao+" criada com sucesso!");
        for(int i = 0; i < tabuleiro.length; ++i) {
            for(int j = 0; j < tabuleiro[i].length; ++j) {
                if(tabuleiro[i][j] == null)
                    tabuleiro[i][j] = " ";
            }
        }
        return tabuleiro;
    }

    public boolean realizaJogada(int i, int j, String caractere) throws IndexOutOfBoundsException {
        try {
            if(i >= tabuleiro.length || j >= tabuleiro[i].length) {
                System.out.println("Essa posição não existe");
                return false;
            } else if(tabuleiro[i][j].equals(" ")) {
                tabuleiro[i][j] = caractere;
                return true;
            } else if(tabuleiro[i][j] != " ") {
                System.out.println("Posição já ocupada, tente outra");
                return false;
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índices fora do alcance: " + e.getMessage());
            return false;
        }
    }

    public boolean verificaVertical(String nome1, String nome2) {
        for(int j = 0; j < tabuleiro[0].length; ++j) {
            int somaO = 0;
            int somaX = 0;
            for(int i = 0; i < tabuleiro.length; ++i) {
                if(tabuleiro[i][j].equals("X")) {
                    somaX++;
                }
                else if(tabuleiro[i][j].equals("O")) {
                    somaO++;
                }
                if(somaX == dimensao) {
                    System.out.println("Jogador " + nome1 + " ganhou!");
                    return true;
                } else if(somaO == dimensao) {
                    System.out.println("Jogador " + nome2 + " ganhou!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificaHorizontal(String nome1, String nome2) {
        for(int i = 0; i < tabuleiro.length; ++i) {
            int somaO = 0;
            int somaX = 0;
            for(int j = 0; j < tabuleiro[0].length; ++j) {
                if(tabuleiro[i][j].equals("X")) {
                    somaX++;
                }
                else if(tabuleiro[i][j].equals("O")) {
                    somaO++;
                }
                if(somaX == dimensao) {
                    System.out.println("Jogador " + nome1 + " ganhou!");
                    return true;
                } else if(somaO == dimensao) {
                    System.out.println("Jogador " + nome2 + " ganhou!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificaDiagonalPrim(int somaX, int somaO, int i, String nome1, String nome2) {
        if(somaX == dimensao) {
            System.out.println("Jogador " + nome1 + " ganhou!");
            return true;
        } else if(somaO == dimensao) {
            System.out.println("Jogador " + nome2 + " ganhou!");
            return true;
        } else if(i >= tabuleiro.length || i < 0) {
            return false;
        }

        if(tabuleiro[i][i].equals("X")) return verificaDiagonalPrim(++somaX, 0, ++i, nome1, nome2);
        if(tabuleiro[i][i].equals("O")) return verificaDiagonalPrim(0, ++somaO, ++i, nome1, nome2);
        return false;
    }


    private boolean verificaDiagonalSec(int somaX, int somaO, int i, int j, String nome1, String nome2) {
        if(somaX == dimensao) {
            System.out.println("Jogador " + nome1 + " ganhou!");
            return true;
        } else if(somaO == dimensao) {
            System.out.println("Jogador " + nome2 + " ganhou!");
            return true;
        } else if(i >= tabuleiro.length || j < 0) {
            return false;
        }

        if(tabuleiro[i][j].equals("X")) return verificaDiagonalSec(++somaX, 0, ++i, --j, nome1, nome2);
        if(tabuleiro[i][j].equals("O")) return verificaDiagonalSec(0, ++somaO, ++i, --j, nome1, nome2);
        return false;
    }

    public boolean verificaGanhador(String nome1, String nome2) {
        if(verificaVertical(nome1, nome2)) return true;
        else if(verificaHorizontal(nome1, nome2)) return true;
        else if(verificaDiagonalPrim(0, 0, 0, nome1, nome2)) return true;
        else if(verificaDiagonalSec(0, 0, 0, tabuleiro[0].length - 1, nome1, nome2)) return true;
        return false;
    }

    public boolean finalizaJogo(String[][] tabuleiro, String nome1, String nome2) {
        for(int i = 0; i < tabuleiro.length; i++) {
            for(int j = 0; j < tabuleiro[i].length; j++) {
                if(tabuleiro[i][j].equals(" "))
                   return false;
            }
        }
        return true;
    }

    public void imprimeMatriz(String[][] tabuleiro) {
        for(int i = 0; i < tabuleiro.length; ++i) {
            for(int j = 0; j < tabuleiro[i].length; ++j) {
                if(j == tabuleiro.length - 1)
                    System.out.print(tabuleiro[i][j]);
                else
                    System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
        }
        
    }

    public void criaArquivo(Jogador j) {
        try {
            FileWriter fw = new FileWriter("Vencedor.txt");
            PrintWriter out = new PrintWriter(fw);
            out.println("O jogador vencedor é...");
            out.println(j);
            out.close();
        } catch (IOException e) {
            System.out.println("Falha na escrita do arquivo");
        }
    }
}
