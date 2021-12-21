package xadrez;

import Tabuleiro.Board;
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
	
	private void posicaoNovaPeca(char coluna, int linha,PecaXadrez peca) {
		
		board.PosicaoPeca(peca, new PosicaoXadrez(coluna,linha).toPosicao());
	}
	
	private void iniciarTabuleiro() {
		
		posicaoNovaPeca('b',6,new Torre(board,Cor.BRANCO));
		posicaoNovaPeca('e',8,new Rei(board,Cor.PRETO));
		posicaoNovaPeca('e',1,new Rei(board,Cor.BRANCO));
		
		
	}
}
