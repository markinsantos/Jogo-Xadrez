package Tabuleiro;

public class Board {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;

	public Board(int linhas, int colunas) {
		if (linhas <1 || colunas <1) {
			throw new ExecaoTabuleiro("erro 1 ao criar o tabuleiro");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public Peca peca(int linha, int coluna) {
		if(!existePosicao(linha,coluna)) {
			throw new ExecaoTabuleiro("erro posição não existe");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new ExecaoTabuleiro("erro posição não existe");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void PosicaoPeca(Peca peca, Posicao posicao) {
		if(existepeca(posicao)) {
			throw new ExecaoTabuleiro("ja existe uma peca nesta posicao: "+posicao);
		}

		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;

	}
	
	public Peca removePeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new ExecaoTabuleiro("posição invalida codigo 1");
		}
		if(peca(posicao) == null) {
		return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}

	public boolean existePosicao(int linha, int coluna) {
		
		return linha >= 0 && linha < linhas &&
				coluna >= 0 && coluna < colunas;
	}

	public boolean existePosicao(Posicao p) {

		return existePosicao(p.getLinha(),p.getColuna());
	}

	public boolean existepeca(Posicao p) {
		if(!existePosicao(p)) {
			throw new ExecaoTabuleiro("erro posição não existe");
		}
		return peca(p) !=null;
	}
}
