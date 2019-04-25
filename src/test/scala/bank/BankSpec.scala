package bank
import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}

class BankSpec extends TestKit(ActorSystem("testSystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {

  "A Bank silent Actor" must {
    "show Correctly calculate the money when client withdraw money from their account sometimes" in {
      import Bank._

      val bankActor = system.actorOf(Bank.props, "bankSystemTest")
      bankActor ! Withdraw(10000)
      bankActor ! Withdraw(20000)
      bankActor ! ShowAccount(testActor)
      expectMsg(70000)
    }
  }
}