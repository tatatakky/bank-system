package bank

import Bank.{ShowAccount, Withdraw}
import akka.actor.{Actor, ActorLogging, ActorRef, Props}

object Bank {
  def props = Props(new Bank)
  case class Withdraw(amount: Int) //引き出し
  case class Deposit(amount: Int) //預金
  case class Transfer(amount: Int) //振り込み
//  case class ShowAccount() //通知
  case class ShowAccount(receiver: ActorRef) //テスト用
}

class Bank extends Actor with ActorLogging{
  var money: Int = 100000
  def receive = {
    case Withdraw(amount) =>
      money = money - amount
//    case ShowAccount() =>
//      sender() ! money

// -------------テスト--------------
    case ShowAccount(receiver) =>
      receiver ! money
// --------------------------------
  }
}
