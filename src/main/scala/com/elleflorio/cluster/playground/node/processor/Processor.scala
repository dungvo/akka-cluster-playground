package com.elleflorio.cluster.playground.node.processor

import akka.actor.{Actor, ActorRef, Props}
import com.elleflorio.cluster.playground.node.processor.ProcessorFibonacci.Compute
import com.elleflorio.cluster.playground.node.processor.ProcessorEvent.Add

object Processor {

  sealed trait ProcessorMessage

  case class ComputeFibonacci(n: Int) extends ProcessorMessage

  case class AddEvent(event: String) extends ProcessorMessage

  def props(nodeId: String) = Props(new Processor(nodeId))
}

class Processor(nodeId: String) extends Actor {
  import Processor._

  val fibonacciProcessor: ActorRef = context.actorOf(ProcessorFibonacci.props(nodeId), "fibonacci")
  val eventProcessor: ActorRef = context.actorOf(ProcessorEvent.props(nodeId), "event")

  override def receive: Receive = {
    case ComputeFibonacci(value) => {
      println("ComputeFibonacci")
      val replyTo = sender()
      fibonacciProcessor ! Compute(value, replyTo)
    }
    case AddEvent(value) => {
      println("Processor - AddEvent")
      val replyTo = sender()
      eventProcessor ! Add(value, replyTo)
    }
  }
}
