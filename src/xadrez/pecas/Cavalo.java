package xadrez.pecas;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Board bord, Cor cor) {
		super(bord, cor);

	}

	@Override
	public String toString() {

		return "C";
	}

	public boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getBord().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getBord().getLinhas()][getBord().getColunas()];
		Posicao p = new Posicao(0, 0);

		// 
		p.setValues(posicao.getLinha() - 1, posicao.getColuna()-2);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// 
		p.setValues(posicao.getLinha() - 2, posicao.getColuna()-1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// 
		p.setValues(posicao.getLinha()-2, posicao.getColuna()+1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 
		p.setValues(posicao.getLinha()-1, posicao.getColuna() + 2);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 
		p.setValues(posicao.getLinha()+1, posicao.getColuna()+2);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// 
		p.setValues(posicao.getLinha() +2, posicao.getColuna()+1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// 
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getBord().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}

	
}
