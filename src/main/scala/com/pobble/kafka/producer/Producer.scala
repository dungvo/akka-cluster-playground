package com.pobble.kafka.producer

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Producer {
  def writeToKafka(topic: String, event: String): Unit = {
    val producer = open()
    val record = new ProducerRecord[String, String](topic, "test", event)
    producer.send(record)
    close(producer)
  }

  def open(): KafkaProducer[String, String] = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9192")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    producer
  }

  def close(producer: KafkaProducer[String, String]): Unit = {
    producer.close()
  }
}
