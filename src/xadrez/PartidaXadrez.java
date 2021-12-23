package xadrez;

import Tabuleiro.Board;
import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Board board;

	public PartidaXadrez() {
		board = new Board(8, 8);
		iniciarTabuleiro();
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[board.getLinhas()][board.getColunas()];
		for (int i = 0; i < board.getLinhas(); i++) {
			for (int j = 0; j < board.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) board.peca(i, j);
			}
		}
		return mat;
	}

	public PecaXadrez perfomaceMoverPeca(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoFinal) {
		Posicao inicial = posicaoInicial.toPosicao();
		Posicao target = posicaoFinal.toPosicao();
		validarPosicaoInicial(inicial);
		validarPosicaoDestino(inicial, target);
		Peca pecaCapturada = moverPeca(inicial, target);
		return (PecaXadrez) pecaCapturada;

	}

	private Peca moverPeca(Posicao inicial, Posicao target) {
		Peca peca = board.removePeca(inicial);
		Peca pecaCapturada = board.removePeca(target);
		board.PosicaoPeca(peca, target);
		return pecaCapturada;

	}

	public void validarPosicaoInicial(Posicao posicao) {
		if (!board.existepeca(posicao)) {
			throw new ExecaoXadrez("não há peça na posição selecionada");
		}
		if (!board.peca(posicao).existeMovimentoPossivel()) {
			throw new ExecaoXadrez("Não há movimentos possiveis para esta peça");
		}
	}

	public void validarPosicaoDestino(Posicao inicial, Posicao target) {

		if (!board.peca(inicial).movimentoPossivel(target)) {
			throw new ExecaoXadrez("Não e possivel movimentar a peça escolhida");

		}

	}

	private void posicaoNovaPeca(char coluna, int linha, PecaXadrez peca) {

		board.PosicaoPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
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
