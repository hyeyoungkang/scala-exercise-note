package functor

import cats._
import cats._
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by hyeyoungkang on 2016. 7. 21..
  */
class functorSpec extends WordSpec with Matchers {
  "funter test " should {

    implicit val optionFunctor: Functor[Option] = new Functor[Option] {
      def map[A, B](fa: Option[A])(f: A => B) = fa map f
    }
    implicit val listFunctor: Functor[List] = new Functor[List] {
      def map[A, B](fa: List[A])(f: A => B) = fa map f
    }
    "option test" in {
      Functor[Option].map(Option("Hello"))(_.length) should be( Some(5) )
      Functor[Option].map(None: Option[String])(_.length) should be( None )
    }

    /*
    We can use Functor to "lift" a function from A => B to F[A] => F[B]:
    */
    "derived method lift test" in {
      val lenOption: Option[String] ⇒ Option[Int] = Functor[Option].lift(_.length)
      lenOption(Some("Hello")) should be( Some(5) )
    }

    "derived method fproduct test " in {

      val source = List("Cats", "is", "awesome")
      val product = Functor[List].fproduct(source)(_.length).toMap

      product.get("Cats").getOrElse(0) should be( 4 )
      product.get("is").getOrElse(0) should be( 2 )
      product.get("awesome").getOrElse(0) should be( 7 )
    }

    "compose test" in {
      val listOpt = Functor[List] compose Functor[Option]

      listOpt.map(List(Some(1), None, Some(3)))(_ + 1) should be( List(Some(2), None, Some(4)) )
    }
  }
}
