package com.pobble.kafka.producer

import java.util.Properties


import java.util.Properties
import org.apache.kafka.clients.producer._

object Driver {

  def main(args: Array[String]): Unit = {
    writeToKafka("test")

    println("Finished!")
  }

  def writeToKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9192")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, "key1", "value2")
    println("Before Send")
    producer.send(record)
    println("After Send")
    producer.close()
  }

}
