package fhj.swengb.assignments.ttt.fgraf

/**
  * Implement here your TicTacToe JavaFX App.
  */

import java.net.URL
import java.util.ResourceBundle
import javafx.application.{Application}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.{Button, Label}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.control.NonFatal

object TicTacToeApp {

  def main(args: Array[String]) {
    Application.launch(classOf[TicTacToeApp], args: _*)
  }
}

class TicTacToeApp extends javafx.application.Application {

  val fxmlFile = "/fhj/swengb/assignments/ttt/TicTacToeApp.fxml"
  val cssFile = "/fhj/swengb/assignments/ttt/TicTacToeStylesheet.css"


  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit = {
    try {
      stage.setTitle("TicTacToe")
      setSkin(stage, fxmlFile, cssFile)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

    def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
      val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
      stage.setScene(scene)
      stage.getScene.getStylesheets.clear()
      stage.getScene.getStylesheets.add(css)
    }

  }
}


class TicTacToeAppController extends Initializable {

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

  @FXML var bt1: Button = _
  @FXML var bt2: Button = _
  @FXML var bt3: Button = _
  @FXML var bt4: Button = _
  @FXML var bt5: Button = _
  @FXML var bt6: Button = _
  @FXML var bt7: Button = _
  @FXML var bt8: Button = _
  @FXML var bt9: Button = _
  @FXML var start1vs1: Button = _
  @FXML var againstPc: Button = _
  @FXML var reset: Button = _
  @FXML var exit: Button = _


  def field1Clicked(): Unit = {
    val newgame = playMoves(TopLeft, game, bt1);
    game = newgame
  }

  def field2Clicked(): Unit = {
    val newgame = playMoves(TopCenter, game, bt2);
    game = newgame
  }

  def field3Clicked(): Unit = {
    val newgame = playMoves(TopRight, game, bt3);
    game = newgame
  }

  def field4Clicked(): Unit = {
    val newgame = playMoves(MiddleLeft, game, bt4);
    game = newgame
  }

  def field5Clicked(): Unit = {
    val newgame = playMoves(MiddleCenter, game, bt5);
    game = newgame
  }

  def field6Clicked(): Unit = {
    val newgame = playMoves(MiddleRight, game, bt6);
    game = newgame
  }

  def field7Clicked(): Unit = {
    val newgame = playMoves(BottomLeft, game, bt7);
    game = newgame
  }

  def field8Clicked(): Unit = {
    val newgame = playMoves(BottomCenter, game, bt8);
    game = newgame
  }

  def field9Clicked(): Unit = {
    val newgame = playMoves(BottomRight, game, bt9);
    game = newgame
  }


  var game = TicTacToe.apply()

  def playMoves(move: TMove, currentgame: TicTacToe = game, buttonClicked: Button): TicTacToe = {

    if (currentgame.nextPlayer.equals(PlayerA))
      buttonClicked.setText("O")
    else
      buttonClicked.setText("X")

    if (currentgame.gameOver) {
      println("Winner")
    bt1.setDisable(true);
    bt2.setDisable(true);
    bt3.setDisable(true);
    bt4.setDisable(true);
    bt5.setDisable(true);
    bt6.setDisable(true);
    bt7.setDisable(true);
    bt8.setDisable(true);
    bt9.setDisable(true);

  }


    val newgame = currentgame.turn(move, currentgame.nextPlayer)
    println(newgame.asString())
    newgame
  }

  def resetclick(): Unit = {
    bt1.setText("Button");
    bt2.setText("Button");
    bt3.setText("Button");
    bt4.setText("Button");
    bt5.setText("Button");
    bt6.setText("Button");
    bt7.setText("Button");
    bt8.setText("Button");
    bt9.setText("Button");
    TicTacToe.apply()
  }

  def play1vs1(): Unit = ???;

  def play1vsPc(): Unit = ???;

  def exitclick(): Unit = System.exit(1);


}