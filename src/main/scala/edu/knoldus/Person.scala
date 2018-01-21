package edu.knoldus

/**
 * Created by manjot on 19/1/18.
 */

import java.io.{File, PrintWriter}

import scala.io.Source

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.log4j.Logger


@SerialVersionUID(15)
class Address(val street: String, val houseNumber: Int)

@SerialVersionUID(16)
class Person(val name: String,
    val age: Int,
    @transient val day: String,
    val address: Address,
    val salary: Float,
    @transient val luckyNumber: Int) {
  def display: String = {
    this.name + " " + this.age + " " + this.day + " " + this.address.houseNumber + " " +
    this.address.street + " " + this.salary + " " + this.luckyNumber
  }
}

object Application extends App {
  val log = Logger.getLogger(this.getClass)
  val address = new Address("Prashant Vihar", 87)
  val person = new Person("Mona", 12, "Friday", address, 12000, 12)
  print(person.display + "\n")

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  val out = mapper.writeValueAsString(person)
  log.info("****This is the serialised JSON object**** \n")
  log.info(out)
  log.info("\n****Writing object in File**** \n")
  val pw = new PrintWriter(new File("File.txt"))
  pw.write(out)
  pw.close()
  log.info("****Reading object from File****\n")
  val str = Source.fromFile("File.txt").mkString
  log.info("\n")
  val in = mapper.readValue(str, classOf[Person])
  log.info(in.display)
}







