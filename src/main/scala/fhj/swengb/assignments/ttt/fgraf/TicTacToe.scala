package fhj.swengb.assignments.ttt.fgraf

import scala.collection.Set
import scala.util.Random

/**
  * models the different moves the game allows
  *
  * each move is made by either player a or player b.
  */
sealed trait TMove {
  def idx: Int
}

case object TopLeft extends TMove {
  override def idx: Int = 0
}

case object TopCenter extends TMove {
  override def idx: Int = 1
}

case object TopRight extends TMove {
  override def idx: Int = 2
}

case object MiddleLeft extends TMove {
  override def idx: Int = 3
}

case object MiddleCenter extends TMove {
  override def idx: Int = 4
}

case object MiddleRight extends TMove {
  override def idx: Int = 5
}

case object BottomLeft extends TMove {
  override def idx: Int = 6
}

case object BottomCenter extends TMove {
  override def idx: Int = 7
}

case object BottomRight extends TMove {
  override def idx: Int = 8
}


/**
  * for a tic tac toe game, there are two players, player A and player B
  */
sealed trait Player

case object PlayerA extends Player

case object PlayerB extends Player

object TicTacToe {

  /**
    * creates an empty tic tac toe game
    *
    * @return
    */
  def apply(): TicTacToe = new TicTacToe(Map())

  /**
    * For a given tic tac toe game, this function applies all moves to the game.
    * The first element of the sequence is also the first move.
    *
    * @param t
    * @param moves
    * @return
    */
  def play(t: TicTacToe, moves: Seq[TMove]): TicTacToe = {
    var temp: TicTacToe = t

    for (move <- moves) {
      temp = temp.turn(move, t.nextPlayer) //the current player is always the nextPlayer of the previous turn
      println("Winner: %s, \nGameOver: %s, \nRemaining moves: %s", temp.winner.get.toString(), temp.gameOver.toString(), temp.remainingMoves.toString())
    }
    temp
  }

  /**
    * creates all possible games.
    *
    * @return
    */
  def mkGames(): Map[Seq[TMove], TicTacToe] = {
    Map[Seq[TMove], TicTacToe](Seq() -> new TicTacToe(Map(), PlayerA),
      Seq(MiddleCenter) -> new TicTacToe(Map(MiddleCenter -> PlayerB), PlayerA),
      Seq(TopLeft) -> new TicTacToe(Map(TopLeft -> PlayerB), PlayerA),
      Seq(TopRight) -> new TicTacToe(Map(TopRight -> PlayerB), PlayerA),
      Seq(TopCenter) -> new TicTacToe(Map(TopCenter -> PlayerB), PlayerA),
      Seq(MiddleLeft) -> new TicTacToe(Map(MiddleLeft -> PlayerB), PlayerA),
      Seq(MiddleRight) -> new TicTacToe(Map(MiddleRight -> PlayerB), PlayerA),
      Seq(BottomCenter) -> new TicTacToe(Map(BottomCenter -> PlayerB), PlayerA),
      Seq(BottomLeft) -> new TicTacToe(Map(BottomLeft -> PlayerB), PlayerA),
      Seq(BottomRight) -> new TicTacToe(Map(BottomRight -> PlayerB), PlayerA)
    )
  }

}


/**
  * Models the well known tic tac toe game.
  *
  * The map holds the information which player controls which field.
  *
  * The nextplayer parameter defines which player makes the next move.
  */
case class TicTacToe(moveHistory: Map[TMove, Player],
                     nextPlayer: Player = PlayerA) {

  val madeMoves = Seq[TMove](MiddleCenter, BottomCenter)
  val allMoves = Seq[TMove](TopLeft, TopCenter, TopRight, MiddleCenter, MiddleLeft, MiddleRight, BottomCenter, BottomLeft, BottomRight)

  /**
    * outputs a representation of the tic tac toe like this:
    *
    * |---|---|---|
    * | x | o | x |
    * |---|---|---|
    * | o | x | x |
    * |---|---|---|
    * | x | o | o |
    * |---|---|---|
    *
    * @return
    */
  def asString(): String = {
    val indexMap = Map(0 -> 16, 1 -> 20, 2 -> 24,
      3 -> 44, 4 -> 48, 5 -> 52,
      6 -> 72, 7 -> 76, 8 -> 80)

    var board: String =
      "|---|---|---|\n" +
        "|   |   |   |\n" +
        "|---|---|---|\n" +
        "|   |   |   |\n" +
        "|---|---|---|\n" +
        "|   |   |   |\n" +
        "|---|---|---|\n"


    for ((k, v) <- moveHistory) {
      if (v == PlayerA) {
        board = board.updated(indexMap(k.idx), "O").mkString
      }
      else if (v == PlayerB) {
        board = board.updated(indexMap(k.idx), "X").mkString
      }
      else {
        board = board.updated(indexMap(k.idx), " ").mkString
      }
    }
    board

  }

  /**
    * is true if the game is over.
    *
    * The game is over if either of a player wins or there is a draw.
    */
  val gameOver: Boolean =
    if (winner == None)
      false
    else
      true


  /**
    * the moves which are still to be played on this tic tac toe.
    */
  val remainingMoves: Set[TMove] = {
    allMoves.filter(!moveHistory.map(_._1).toSet.contains(_)).toSet

  }

  def remainingMovesSimulated(executedMoves: Map[TMove, Player]): Set[TMove] = {
    (allMoves filterNot executedMoves.map(_._1).toSet.contains).toSet

  }


  def returnCaseObject(index: Int): TMove = {
    allMoves.filter(_.idx == index).head
  }


  /**
    * given a tic tac toe game, this function returns all
    * games which can be derived by making the next turn. that means one of the
    * possible turns is taken and added to the set.
    */
  lazy val nextGames: Set[TicTacToe] = ???

  /**
    * Either there is no winner, or PlayerA or PlayerB won the game.
    *
    * The set of moves contains all moves which contributed to the result.
    */

  def checkIfWon(moves: Set[TMove]): Boolean = {

    val topRow: Set[TMove] = Set(TopLeft, TopCenter, TopRight)
    val middleRow: Set[TMove] = Set(MiddleLeft, MiddleCenter, MiddleRight)
    val bottomRow: Set[TMove] = Set(BottomLeft, BottomCenter, BottomRight)
    val leftColumn: Set[TMove] = Set(TopLeft, MiddleLeft, BottomLeft)
    val middleColumn: Set[TMove] = Set(TopCenter, MiddleCenter, BottomCenter)
    val rightColumn: Set[TMove] = Set(TopRight, MiddleRight, BottomRight)
    val leftDiagonal: Set[TMove] = Set(TopLeft, MiddleCenter, BottomRight)
    val rightDiagonal: Set[TMove] = Set(TopRight, MiddleCenter, BottomLeft)


    if (topRow.diff(moves).isEmpty || middleRow.diff(moves).isEmpty || bottomRow.diff(moves).isEmpty ||
      leftColumn.diff(moves).isEmpty || middleColumn.diff(moves).isEmpty || rightColumn.diff(moves).isEmpty ||
      leftDiagonal.diff(moves).isEmpty || rightDiagonal.diff(moves).isEmpty) {
      true;
    } else {
      false;
    }
  }


  def winner: Option[(Player, Set[TMove])] = {

    val movesPlayerA = moveHistory.filter(_ == PlayerA);
    val movesPlayerB = moveHistory.filter(_ == PlayerB);
    if (checkIfWon(movesPlayerA.map(_._1).toSet)) {
      println("PlayerA won!")
      Some(PlayerA, movesPlayerA.map(_._1).toSet)
    } else if (checkIfWon(movesPlayerB.map(_._1).toSet)) {
      println("PlayerB won!")
      Some(PlayerB, movesPlayerB.map(_._1).toSet)
    } else {
      None
    }

  }


  /**
    * returns a copy of the current game, but with the move applied to the tic tac toe game.
    *
    * @param move   to be played
    * @param player the player
    * @return
    */
  def turn(p: TMove, player: Player): TicTacToe = {

    if (!moveHistory.keySet.contains(p)) {
      if (player == PlayerA) {
        TicTacToe((moveHistory + (p -> player)), PlayerB)
      } else {
        TicTacToe((moveHistory + (p -> player)), PlayerA)
      }

    }
    else {
      TicTacToe(moveHistory, player)
    }

  }
}


