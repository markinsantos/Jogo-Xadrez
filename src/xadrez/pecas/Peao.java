package xadrez.pecas;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Board bord, Cor cor) {
		super(bord, cor);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getBord().getLinhas()][getBord().getColunas()];
		Posicao p = new Posicao(0,0);
		
		if(getCor()==Cor.BRANCO) {
			p.setValues(posicao.getLinha()-1, posicao.getColuna());
			if(getBord().existePosicao(p)&& !getBord().existepeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha()-2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha()-1, posicao.getColuna());
			if(getBord().existePosicao(p)&&!getBord().existepeca(p)&&getBord().existePosicao(p2)&&!getBord().existepeca(p2)&& getContadorMovimentos()==0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha()-1, posicao.getColuna()-1);
			if(getBord().existePosicao(p)&& existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha()-1, posicao.getColuna()+1);
			if(getBord().existePosicao(p)&& existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
						
		}else {
			p.setValues(posicao.getLinha()+1, posicao.getColuna());
			if(getBord().existePosicao(p)&& !getBord().existepeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha()+2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha()+1, posicao.getColuna());
			if(getBord().existePosicao(p)&&!getBord().existepeca(p)&&getBord().existePosicao(p2)&&!getBord().existepeca(p2)&& getContadorMovimentos()==0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha()+1, posicao.getColuna()-1);
			if(getBord().existePosicao(p)&& existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha()+1, posicao.getColuna()+1);
			if(getBord().existePosicao(p)&& existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
			
		return matriz;
	}
	
	@Override
	public String toString() {
		return"p";
	}
}
