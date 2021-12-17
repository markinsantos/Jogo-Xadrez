package xadrez;

import Tabuleiro.Board;
import Tabuleiro.Posicao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private Board board;
	
	public  PartidaXadrez() {
		board = new Board(8,8);
		iniciarTabuleiro();
	}

	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[board.getLinhas()][board.getColunas()];
		for(int i =0;i<board.getLinhas();i++) {
			for(int j=0;j<board.getColunas();j++) {
				mat[i][j] = (PecaXadrez) board.peca(i,j);
			}
		}
		return mat;
	}
	
	private void iniciarTabuleiro() {
		
		board.PosicaoPeca(new Torre(board,Cor.BRANCO), new Posicao(2,1));
		board.PosicaoPeca(new Rei(board,Cor.PRETO), new Posicao(0,4));
		
	}
}
