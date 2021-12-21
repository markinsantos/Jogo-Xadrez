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
		
        posicaoNovaPeca('c', 1, new Torre(board, Cor.BRANCO));
        posicaoNovaPeca('c', 2, new Torre(board, Cor.BRANCO));
        posicaoNovaPeca('d', 2, new Torre(board, Cor.BRANCO));
        posicaoNovaPeca('e', 2, new Torre(board, Cor.BRANCO));
        posicaoNovaPeca('e', 1, new Torre(board, Cor.BRANCO));
        posicaoNovaPeca('d', 1, new Rei(board, Cor.BRANCO));

        posicaoNovaPeca('c', 7, new Torre(board, Cor.PRETO));
        posicaoNovaPeca('c', 8, new Torre(board, Cor.PRETO));
        posicaoNovaPeca('d', 7, new Torre(board, Cor.PRETO));
        posicaoNovaPeca('e', 7, new Torre(board, Cor.PRETO));
        posicaoNovaPeca('e', 8, new Torre(board, Cor.PRETO));
        posicaoNovaPeca('d', 8, new Rei(board, Cor.PRETO));
		
		
	         
		
		
	}
}
