package semigroup

import org.scalatest.{Matchers, WordSpec}
import cats.Semigroup
import cats.implicits._

/**
  * Created by juliet on 2016. 7. 18..
  */
class SemigroupSpec extends WordSpec with Matchers{

  "semi group test" should {
    "first test " in {
      println("hello world")
      Semigroup[Int].combine(1, 2) shouldBe(3)
      Semigroup[List[Int]].combine(List(1,2,3), List(4, 5, 6)) shouldBe List(1,2,3,4,5,6)
      Semigroup[Option[Int]].combine(Option(1), Option(2)) shouldBe Option(3)
      Semigroup[Option[Int]].combine(Option(1), None) shouldBe Option(1)
      Semigroup[Int ⇒ Int].combine({ (x: Int) ⇒ x + 1 }, { (x: Int) ⇒ x * 10 }).apply(6) shouldBe(67)
    }
  }
}
