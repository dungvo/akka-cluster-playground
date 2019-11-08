package com.elleflorio.cluster.playground.node.processor

import akka.actor.{Actor, ActorRef, Props}

object ProcessorEvent {
  sealed trait ProcessorEventMessage
  case class Add(event: String, replyTo: ActorRef) extends ProcessorEventMessage

  def props(nodeId: String) = Props(new ProcessorEvent(nodeId))

  def process(x: String) = {
    println(x)
  }
}

class ProcessorEvent(nodeId: String) extends Actor {
  import ProcessorEvent._

  override def receive: Receive = {
    case Add(value, replyTo) => {
      println(nodeId)
      process(value)
      println(nodeId)
      replyTo ! ProcessorResponse(nodeId, 0)
    }
  }
}