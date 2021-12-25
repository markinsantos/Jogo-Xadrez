package xadrez.pecas;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Board bord, Cor cor) {
		super(bord, cor);

	}

	@Override
	public String toString() {

		return "R";
	}

	public boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getBord().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getBord().getLinhas()][getBord().getColunas()];
		Posicao p = new Posicao(0, 0);

		// acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// noroeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// nordeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// sudoeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// sudeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}

}
