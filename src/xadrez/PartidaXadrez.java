package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Tabuleiro.Board;
import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Board board;
	private int turno;
	private Cor jogadorAtual;
	private boolean check;
	private boolean checkMate;

	List<Peca> pecasNoTabuleiro = new ArrayList<Peca>();
	List<Peca> pecasCapturadas = new ArrayList<Peca>();

	public PartidaXadrez() {
		board = new Board(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		check = false;
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
		check = (TesteCheck(oponente(jogadorAtual))) ? true : false;
		
		if(TesteCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
			
		}else {
		
		proximoTurno();
		}
		return (PecaXadrez) pecaCapturada;

	}

	private Peca moverPeca(Posicao inicial, Posicao target) {
		PecaXadrez peca = (PecaXadrez)board.removePeca(inicial);
		peca.incrementarMovimentos();
		Peca pecaCapturada = board.removePeca(target);
		board.PosicaoPeca(peca, target);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);

		}

		return pecaCapturada;

	}

	private void desfazerMovimento(Posicao inicial, Posicao target, Peca pecaCapturada) {

		PecaXadrez p = (PecaXadrez)board.removePeca(target);
		p.decrementarMovimentos();
		board.PosicaoPeca(p, inicial);
		if (pecaCapturada != null) {
			board.PosicaoPeca(pecaCapturada, target);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
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
		if(!TesteCheck(cor)) {
			return false;
		}
		
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		
		for(Peca p: lista) {
			boolean[][] matriz = p.movimentosPossiveis();
			for(int i=0;i<board.getLinhas();i++) {
				for(int j=0;j<board.getColunas();j++) {
					if(matriz[i][j]) {
						
						Posicao inicial= ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao target = new Posicao(i,j);
						Peca pecaCapturada = moverPeca(inicial,target);
						boolean testeCheck = TesteCheck(cor);
						desfazerMovimento(inicial, target, pecaCapturada);
						if(!testeCheck) {
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
		posicaoNovaPeca('e', 1, new Rei(board, Cor.BRANCO));
		posicaoNovaPeca('a', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('b', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('c', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('d', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('e', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('f', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('g', 2, new Peao(board, Cor.BRANCO));
		posicaoNovaPeca('h', 2, new Peao(board, Cor.BRANCO));

		posicaoNovaPeca('a', 8, new Torre(board, Cor.PRETO));
		posicaoNovaPeca('h', 8, new Torre(board, Cor.PRETO));
		posicaoNovaPeca('b', 8, new Cavalo(board, Cor.PRETO));
		posicaoNovaPeca('g', 8, new Cavalo(board, Cor.PRETO));
		posicaoNovaPeca('c', 8, new Bispo(board, Cor.PRETO));
		posicaoNovaPeca('f', 8, new Bispo(board, Cor.PRETO));
		posicaoNovaPeca('e', 8, new Rei(board, Cor.PRETO));
		posicaoNovaPeca('a', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('b', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('c', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('d', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('e', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('f', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('g', 7, new Peao(board, Cor.PRETO));
		posicaoNovaPeca('h', 7, new Peao(board, Cor.PRETO));


	}
}
