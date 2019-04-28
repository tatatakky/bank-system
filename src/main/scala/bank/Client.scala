package bank

import akka.actor.{Actor, ActorLogging, ActorSystem, Props, ActorRef}
import akka.pattern.ask
import akka.util.Timeout
import bank.Bank.{ShowAccount, Withdraw}

import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Client extends App {
  val system = ActorSystem("actor-system")
  val props = Bank.props
  val bankActor = system.actorOf(props)
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(3 seconds)

  val inputMoney: Int = 20000
  val nextInputMoney: Int = 30000

  bankActor ! Withdraw(inputMoney)
  bankActor ! Withdraw(nextInputMoney)
  (bankActor ? ShowAccount).mapTo[Int].onComplete {
    case Success(value) =>
      println(s"Money of your account is ${value}")
      system.terminate()
    case Failure(e) =>
      system.terminate()
  }
}