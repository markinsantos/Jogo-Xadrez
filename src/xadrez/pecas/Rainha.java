package xadrez.pecas;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez{

	public Rainha(Board bord, Cor cor) {
		super(bord, cor);
		
	}
	
	@Override
	public String toString() {
		
		return "Q";
		}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getBord().getLinhas()][getBord().getColunas()];
		Posicao p = new Posicao(0,0);
		
		//acima da peça
		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// a esquerda da peça
		
		p.setValues(posicao.getLinha(), posicao.getColuna()-1);
		while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		// direita da peça
		
		p.setValues(posicao.getLinha(), posicao.getColuna()+1);
		while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo da peça
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//noroeste da peça
		
				p.setValues(posicao.getLinha() - 1, posicao.getColuna()-1);
				while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValues(p.getLinha()-1, p.getColuna()-1);
				}
				if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				// nordeste da peça
				
				p.setValues(posicao.getLinha()-1, posicao.getColuna()+1);
				while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValues(p.getLinha()-1,p.getColuna()+1);
				}
				if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				// sudeste da peça
				
				p.setValues(posicao.getLinha()+1, posicao.getColuna()+1);
				while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValues(p.getLinha()+1, p.getColuna()+1);
				}
				if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				//sudoeste da peça
				
				p.setValues(posicao.getLinha() + 1, posicao.getColuna()-1);
				while (getBord().existePosicao(p) && !getBord().existepeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValues(p.getLinha()+1, p.getColuna()-1);
				}
				if (getBord().existePosicao(p) && existePecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		
		return matriz;
	}
}
