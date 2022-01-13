package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Tabuleiro.Board;
import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Board board;
	private int turno;
	private Cor jogadorAtual;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassan;
	private PecaXadrez promocao;

	List<Peca> pecasNoTabuleiro = new ArrayList<Peca>();
	List<Peca> pecasCapturadas = new ArrayList<Peca>();

	public PartidaXadrez() {
		board = new Board(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		iniciarTabuleiro();

	}

	public int getTurno() {
		return turno;
	}

	public Cor getjogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;

	}

	public PecaXadrez getEnPassan() {
		return enPassan;
	}

	public PecaXadrez getPromocao() {
		return promocao;
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

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoInicial) {
		Posicao posicao = posicaoInicial.toPosicao();
		validarPosicaoInicial(posicao);
		return board.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez perfomaceMoverPeca(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoFinal) {
		Posicao inicial = posicaoInicial.toPosicao();
		Posicao target = posicaoFinal.toPosicao();
		validarPosicaoInicial(inicial);
		validarPosicaoDestino(inicial, target);
		Peca pecaCapturada = moverPeca(inicial, target);

		if (TesteCheck(jogadorAtual)) {
			desfazerMovimento(inicial, target, pecaCapturada);
			throw new ExecaoXadrez("Não é permitido se colocar em check");
		}

		PecaXadrez pecaMovida = (PecaXadrez) board.peca(target);

		// movimento especial promoção

		promocao = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Cor.BRANCO && target.getLinha() == 0)
					|| (pecaMovida.getCor() == Cor.PRETO && target.getLinha() == 7)) {

				promocao = (PecaXadrez) board.peca(target);
				promocao = trocaPromovidaPeca("Q");

			}
		}

		check = (TesteCheck(oponente(jogadorAtual))) ? true : false;

		if (TesteCheckMate(oponente(jogadorAtual))) {
			checkMate = true;

		} else {

			proximoTurno();
		}

		// movimento especial en passan
		if (pecaMovida instanceof Peao
				&& (target.getLinha() == inicial.getLinha() - 2 || target.getLinha() == inicial.getLinha() + 2)) {
			enPassan = pecaMovida;
		} else {
			enPassan = null;
		}

		return (PecaXadrez) pecaCapturada;

	}

	public PecaXadrez trocaPromovidaPeca(String letra) {

		if (promocao == null) {
			throw new IllegalStateException("Não há peça para ser promovida");
		}
		if (!letra.equals("B") && !letra.equals("C") && !letra.equals("T") & !letra.equals("Q")) {
			throw new InvalidParameterException("tipo de peça invalido");
		}

		Posicao pos = promocao.getPosicaoXadrez().toPosicao();
		Peca p = board.removePeca(pos);
		pecasNoTabuleiro.remove(p);

		PecaXadrez novaPeca = novaPeca(letra, promocao.getCor());
		board.PosicaoPeca(novaPeca, pos);
		pecasNoTabuleiro.add(novaPeca);

		return novaPeca;
	}

	private PecaXadrez novaPeca(String letra, Cor cor) {
		if (letra.equals("B"))
			return new Bispo(board, cor);
		if (letra.equals("T"))
			return new Torre(board, cor);
		if (letra.equals("Q"))
			return new Rainha(board, cor);
		return new Cavalo(board, cor);

	}

	private Peca moverPeca(Posicao inicial, Posicao target) {
		PecaXadrez peca = (PecaXadrez) board.removePeca(inicial);
		peca.incrementarMovimentos();
		Peca pecaCapturada = board.removePeca(target);
		board.PosicaoPeca(peca, target);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);

		}
		// movimento especial rook
		if (peca instanceof Rei && target.getColuna() == inicial.getColuna() + 2) {
			Posicao inicialTorre = new Posicao(inicial.getLinha(), inicial.getColuna() + 3);
			Posicao targetTorre = new Posicao(inicial.getLinha(), inicial.getColuna() + 1);
			PecaXadrez rook = (PecaXadrez) board.removePeca(inicialTorre);
			board.PosicaoPeca(rook, targetTorre);
			rook.incrementarMovimentos();
		}

		if (peca instanceof Rei && target.getColuna() == inicial.getColuna() - 2) {
			Posicao inicialTorre = new Posicao(inicial.getLinha(), inicial.getColuna() - 4);
			Posicao targetTorre = new Posicao(inicial.getLinha(), inicial.getColuna() - 1);
			PecaXadrez rook = (PecaXadrez) board.removePeca(inicialTorre);
			board.PosicaoPeca(rook, targetTorre);
			rook.incrementarMovimentos();
		}

		// movimento especial en passan

		if (peca instanceof Peao) {

			if (inicial.getColuna() != target.getColuna() && pecaCapturada == null) {
				Posicao peaoPosicao;
				if (peca.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(target.getLinha() + 1, target.getColuna());
				} else {
					peaoPosicao = new Posicao(target.getLinha() - 1, target.getColuna());
				}

				pecaCapturada = board.removePeca(peaoPosicao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);

			}

		}

		return pecaCapturada;

	}

	private void desfazerMovimento(Posicao inicial, Posicao target, Peca pecaCapturada) {

		PecaXadrez p = (PecaXadrez) board.removePeca(target);
		p.decrementarMovimentos();
		board.PosicaoPeca(p, inicial);
		if (pecaCapturada != null) {
			board.PosicaoPeca(pecaCapturada, target);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		if (p instanceof Rei && target.getColuna() == inicial.getColuna() + 2) {
			Posicao inicialTorre = new Posicao(inicial.getLinha(), inicial.getColuna() + 3);
			Posicao targetTorre = new Posicao(inicial.getLinha(), inicial.getColuna() + 1);
			PecaXadrez rook = (PecaXadrez) board.removePeca(targetTorre);
			board.PosicaoPeca(rook, inicialTorre);
			rook.decrementarMovimentos();
		}

		if (p instanceof Rei && target.getColuna() == inicial.getColuna() - 2) {
			Posicao inicialTorre = new Posicao(inicial.getLinha(), inicial.getColuna() - 4);
			Posicao targetTorre = new Posicao(inicial.getLinha(), inicial.getColuna() - 1);
			PecaXadrez rook = (PecaXadrez) board.removePeca(targetTorre);
			board.PosicaoPeca(rook, inicialTorre);
			rook.decrementarMovimentos();
		}

		// movemento especial de en passan

		if (p instanceof Peao) {

			if (inicial.getColuna() != target.getColuna() && pecaCapturada == enPassan) {
				PecaXadrez peao = (PecaXadrez) board.removePeca(target);
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(3, target.getColuna());
				} else {
					peaoPosicao = new Posicao(4, target.getColuna());
				}
				board.PosicaoPeca(peao, peaoPosicao);

			}
		}

	}

	public void validarPosicaoInicial(Posicao posicao) {
		if (!board.existepeca(posicao)) {
			throw new ExecaoXadrez("não há peça na posição selecionada");
		}
		if (jogadorAtual != ((PecaXadrez) board.peca(posicao)).getCor()) {
			throw new ExecaoXadrez("A peça escolhida não é sua");
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

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe rei de cor " + cor);
	}

	private boolean TesteCheck(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecaOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecaOponente) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean TesteCheckMate(Cor cor) {
		;
		if (!TesteCheck(cor)) {
			return false;
		}

		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());

		for (Peca p : lista) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i = 0; i < board.getLinhas(); i++) {
				for (int j = 0; j < board.getColunas(); j++) {
					if (matriz[i][j]) {

						Posicao inicial = ((PecaXadrez) p).getPosicaoXadrez().toPosicao();
						Posicao target = new Posicao(i, j);
						Peca pecaCapturada = moverPeca(inicial, target);
						boolean testeCheck = TesteCheck(cor);
						desfazerMovimento(inicial, target, pecaCapturada);
						if (!testeCheck) {
							return false;
						}
					}

				}
			}
		}
		return true;
	}

	private void posicaoNovaPeca(char coluna, int linha, PecaXadrez peca) {

		board.PosicaoPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void iniciarTabuleiro() {

		posicaoNovaPeca('a', 1, new Torre(board, Cor.BRANCO));
		posicaoNovaPeca('h', 1, new Torre(board, Cor.BRANCO));
		posicaoNovaPeca('b', 1, new Cavalo(board, Cor.BRANCO));
		posicaoNovaPeca('g', 1, new Cavalo(board, Cor.BRANCO));
		posicaoNovaPeca('c', 1, new Bispo(board, Cor.BRANCO));
		posicaoNovaPeca('f', 1, new Bispo(board, Cor.BRANCO));
		posicaoNovaPeca('d', 1, new Rainha(board, Cor.BRANCO));
		posicaoNovaPeca('e', 1, new Rei(board, Cor.BRANCO, this));
		posicaoNovaPeca('a', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('b', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('c', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('d', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('e', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('f', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('g', 2, new Peao(board, Cor.BRANCO, this));
		posicaoNovaPeca('h', 2, new Peao(board, Cor.BRANCO, this));

		posicaoNovaPeca('a', 8, new Torre(board, Cor.PRETO));
		posicaoNovaPeca('h', 8, new Torre(board, Cor.PRETO));
		posicaoNovaPeca('b', 8, new Cavalo(board, Cor.PRETO));
		posicaoNovaPeca('g', 8, new Cavalo(board, Cor.PRETO));
		posicaoNovaPeca('c', 8, new Bispo(board, Cor.PRETO));
		posicaoNovaPeca('f', 8, new Bispo(board, Cor.PRETO));
		posicaoNovaPeca('d', 8, new Rainha(board, Cor.PRETO));
		posicaoNovaPeca('e', 8, new Rei(board, Cor.PRETO, this));
		posicaoNovaPeca('a', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('b', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('c', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('d', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('e', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('f', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('g', 7, new Peao(board, Cor.PRETO, this));
		posicaoNovaPeca('h', 7, new Peao(board, Cor.PRETO, this));

	}
}
