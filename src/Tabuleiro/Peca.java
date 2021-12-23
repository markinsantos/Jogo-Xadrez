package Tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private Board bord;

	public Peca(Board bord) {
		this.bord = bord;
		posicao = null;
	}

	protected Board getBord() {
		return bord;
	}

	public abstract boolean[][] movimentosPossiveis();

	public boolean movimentoPossivel(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean existeMovimentoPossivel() {
		boolean[][] matriz = movimentosPossiveis();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
