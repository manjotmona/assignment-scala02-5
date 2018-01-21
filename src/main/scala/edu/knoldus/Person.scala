package edu.knoldus

/**
 * Created by manjot on 19/1/18.
 */

import java.io.{File, PrintWriter}

import scala.io.Source

import com.fasterxml.jackson.databind.ObjectMapper
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
    @transient val luckyNumber: Int)

object Application extends App {
  val log = Logger.getLogger(this.getClass)
  val address = new Address("Prashant Vihar", 87)
  val person = new Person("Mona", 12, "Friday", address, 12000, 12)
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  val out = mapper.writeValueAsString(person)
  log.info("****This is the serialised JSON object**** \n\n\n")
  log.info(out)
  log.info("\n\n\n****Writing object in File**** \n\n\n")
  val pw = new PrintWriter(new File("File.txt"))
  pw.write(out)
  pw.close()
  log.info("****Reading object from File****\n\n\n")
  Source.fromFile("File.txt").foreach { x => print(x) }
}







