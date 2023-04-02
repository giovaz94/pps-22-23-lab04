package u04lab.polyglot.e2

import u04lab.polyglot.e2.cell.Cell

object LogicsImpl:

  trait Pair[A,B]:
    def x: A
    def x_=(value: A): Unit
    def y: B
    def y_=(value: B): Unit

  case class PairImpl[A,B](
      override var x: A,
      override var y: B
  ) extends Pair[A,B]

  object Grid:

    trait Cell:
      def click(): Unit


    trait Grid:
      def click(position: Pair[Int, Int]): Unit
      def getCell(position: Pair[Int, Int]): Unit
      def getMines(): List[Pair[Int, Int]]
      def hasMine(position: Pair[Int, Int]): Boolean
      def isClicked(position: Pair[Int, Int]): Boolean
      def numberOfAdjacentMines(position: Pair[Int, Int]): Int
      def flag(position: Pair[Int, Int]): Boolean
      def getSize(): Int










  enum GameStatus:
    case InGame, GameOver, Win








