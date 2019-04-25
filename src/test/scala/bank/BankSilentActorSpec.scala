package bank
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.{CallingThreadDispatcher, TestKit, EventFilter}
import org.scalatest.{MustMatchers, WordSpecLike}
import akka.util.Timeout
import scala.concurrent.duration._
import scala.util.{Success, Failure}

class BankSilentActorSpec extends TestKit(ActorSystem("testSystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {

  "A Bank silent Actor" must {
    "show Correctly calculate the money when client withdraw money from their account sometimes" in {
      import Bank._
      implicit val executionContext = system.dispatcher
      implicit val timeout = Timeout(3 seconds)

      val bankActor = system.actorOf(Bank.props, "bankSystemTest")
      bankActor ! Withdraw(10000)
      bankActor ! Withdraw(20000)
      bankActor ! ShowAccount(testActor)
      expectMsg(70000)
    }
  }
}