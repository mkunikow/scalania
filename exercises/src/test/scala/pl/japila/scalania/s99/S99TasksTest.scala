package pl.japila.scalania.s99

import org.specs2.mutable
import org.specs2.matcher.{ MatchResult, Expectable, Matcher }

class S99TasksTest extends mutable.Specification {

  val solution: S99TasksSolution = new S99TasksSolutionNotImplemented

  case class listMatch[T](list: Seq[T]) extends Matcher[Seq[T]]() {

    override def apply[S <: Seq[T]](t: Expectable[S]): MatchResult[S] = {
      val v = t.value
      val test = list.size == v.size && ((list zip v) forall { e => e._1 == e._2 })
      result(test, "list match", "list %s dont match %s".format(v, list), t)
    }

  }

  "S99TasksSolution implementation" should {
    "Insert an element at a given position into a list." in {
      solution.p21('new, 1, List('a, 'b, 'c, 'd)) must listMatch(List('a, 'new, 'b, 'c, 'd))
    }
    " Create a list containing all integers within a given range." in {
      solution.p22(4, 9) must listMatch(List(4, 5, 6, 7, 8, 9))
    }
    "Extract a given number of randomly selected elements from a list." in {
      val input = List('a, 'b, 'c, 'd, 'f, 'g, 'h)
      val output = solution.p23(3, input)
      output.distinct.size == 3
      output.forall(input.contains(_)) == true
    }
    " Lotto: Draw N different random numbers from the set 1..M." in {
      val randomrange = solution.p24(6, 49)
      randomrange.distinct.size == 6
      randomrange.forall(e => 1 <= e && e <= 49) == true
    }
    "Generate a random permutation of the elements of a list." in {
      val input = List('a, 'b, 'c, 'd, 'e, 'f)
      val randomPer = solution.p25(input)
      randomPer.intersect(input) == input
      randomPer.diff(input).size == 0
    }
    "Generate the combinations of 3 distinct objects, chosen from a 4 element list." in {
      val input = List('a, 'b, 'c, 'd)
      val expected = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'c, 'd), List('b, 'c, 'd))
      solution.p26(3, input) must listMatch(expected)
    }
    "Generate all the combinations of a committee of 3, chosen from a group of 12 people." in {
      val testspec = "We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). " +
        "For pure mathematicians, this result may be great. But we want to really generate all the possibilities. "
      val input = List.range(0, 12)
      val combinations = solution.p26(3, input)
      combinations.length mustEqual 220
      combinations.distinct.length mustEqual 220
    }
    "Group 9 elements of a set into disjoint subsets of 2, 3 and 4 elements." in {
      val testspec = "Multinomial coefficients for multisets of 2,3 and 4 elements gives (2,3,4)!=(2+3+4)!/(2!3!4!) = 1260 possibilities"
      val input = List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida")
      val output = solution.p27(input)
      output.distinct.length mustEqual 1260
      output.length mustEqual 1260
    }
    "Group 3 elements of a set into disjoint subsets of 2 and 1 elements. (generalized version)" in {
      val inputGroups = List(2, 1)
      val inputElements = List.range(0, 3)
      val expected = List(List(List(0, 1), List(2)), List(List(0, 2), List(1)), List(List(1, 2), List(0)))
      solution.p27b(inputGroups, inputElements) must listMatch(expected)
    }

    " Sorting a list of lists according to length of sublists." in {
      solution.p28(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))) must listMatch(List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l)))
    }
  }
}