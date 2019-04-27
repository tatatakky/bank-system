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

      val bankActorRef = system.actorOf(Bank.props, "bankSystemTest")
      bankActorRef ! Withdraw(10000)
      bankActorRef ! Withdraw(20000)
      bankActorRef.tell(ShowAccount(), testActor)
      expectMsg(70000)
    }
  }
}