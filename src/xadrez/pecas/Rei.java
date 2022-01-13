package xadrez.pecas;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
	
	private PartidaXadrez partida;

	public Rei(Board bord, Cor cor,PartidaXadrez partida) {
		super(bord, cor);
		this.partida = partida;

	}

	@Override
	public String toString() {

		return "R";
	}

	public boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getBord().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testeRook(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getBord().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor()&& getContadorMovimentos()==0;
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
		
		//movimentos de rook
		if(getContadorMovimentos()==0&&!partida.getCheck()) {
			Posicao pos1 = new Posicao(posicao.getLinha(),posicao.getColuna()+3);
			if(testeRook(pos1)) {
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna()+2);
				if(getBord().peca(p1)==null && getBord().peca(p2)==null) {
					matriz[posicao.getLinha()][posicao.getColuna()+2] = true;
				}
				
				}
			
			Posicao pos2 = new Posicao(posicao.getLinha(),posicao.getColuna()-4);
			if(testeRook(pos2)) {
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna()-2);
				Posicao p3 = new Posicao(posicao.getLinha(),posicao.getColuna()-3);
				if(getBord().peca(p1)==null && getBord().peca(p2)==null&&getBord().peca(p3)==null) {
					matriz[posicao.getLinha()][posicao.getColuna()-2] = true;
				}
				
				}
		}

		return matriz;
	}

}
