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

  def main (args: Array[String]) {
    Application.launch(classOf[TicTacToeApp], args: _*)
  }
}

class TicTacToeApp extends javafx.application.Application {

  val fxmlFile = "/fhj/swengb/assignments/ttt/TicTacToeApp.fxml"
  val cssFile = "/fhj/swengb/assignments/ttt/TicTacToeStylesheet.css"


  def mkFxmlLoader(fxml:String):FXMLLoader = {
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
  @FXML var reset: Button = _



  def field1Clicked():Unit = bt1.setText("X")
  def field2Clicked():Unit = bt2.setText("X")
  def field3Clicked():Unit = bt3.setText("X")
  def field4Clicked():Unit = bt4.setText("X")
  def field5Clicked():Unit = bt5.setText("X")
  def field6Clicked():Unit = bt6.setText("X")
  def field7Clicked():Unit = bt7.setText("X")
  def field8Clicked():Unit = bt8.setText("X")
  def field9Clicked():Unit = bt9.setText("X")
  def resetclick():Unit = ???;
  def play1vs1():Unit = ???;


}