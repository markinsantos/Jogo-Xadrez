package xadrez.pecas;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
	
	private PartidaXadrez partida;

	public Peao(Board bord, Cor cor,PartidaXadrez partida) {
		super(bord, cor);
		this.partida = partida;
		
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
			
			// movimento especial en passan
			
			if(posicao.getLinha() == 3) {
				
				Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				if(getBord().existePosicao(left) && existePecaAdversaria(left)&&  getBord().peca(left) == partida.getEnPassan()){
					matriz[left.getLinha()-1][left.getColuna()] = true;
				}
				
				Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
				if(getBord().existePosicao(right) && existePecaAdversaria(right)&&  getBord().peca(right) == partida.getEnPassan()){
					matriz[right.getLinha()-1][right.getColuna()] = true;
				}
				
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
		
		// movimentos especial en passan 
		
		if(posicao.getLinha() == 4) {
			
			Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
			if(getBord().existePosicao(left) && existePecaAdversaria(left)&&  getBord().peca(left) == partida.getEnPassan()){
				matriz[left.getLinha()+1][left.getColuna()] = true;
			}
			
			Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
			if(getBord().existePosicao(right) && existePecaAdversaria(right)&&  getBord().peca(right) == partida.getEnPassan()){
				matriz[right.getLinha()+1][right.getColuna()] = true;
			}
			
		}
			
		return matriz;
	}
	
	@Override
	public String toString() {
		return"p";
	}
}
