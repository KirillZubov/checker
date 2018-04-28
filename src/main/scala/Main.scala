import scala.util.parsing.combinator._
import scala.collection.mutable.ListBuffer
import java.io._

case class CheckFreq(country: String, day: Int, temp : Int) {
    var checklist = new ListBuffer[String]()  
    var clothes = new ListBuffer[String]()  
    clothes +="  clothes:"
    val space = "  - "  
    val dspace  = "    - " 

    val money = Map("USA" -> Map("count" -> "100","money" -> "USD"), 
      "Great Britain" -> Map("count" -> "120","money" -> "EUR"),
      "Canada" -> Map("count" -> "80","money" -> "canadian dollar")    
      )
    def setCountry(country: String){
        country match {
            case "USA" => checklist += space+"american flag"
            case "Great Britain" => checklist += space +"British flag"
            case "Canada" => checklist += space +"Canadian flag"
        }
    }
    
    def setCountOfDay(day: Int, country: String){
        val moneyday  = day * (money(country)("count")).toInt + 100
        checklist +=  space +   moneyday + " "+ money(country)("money")      
        clothes  += dspace + (day+1) + " pairs of socks" 
    }  

     def setTemperature(temp: Int){
        if(temp < 0){
            clothes += dspace +"sweater"
            clothes += dspace +"winter jacket"
            clothes  += dspace +"winter hat"
        }else if(temp >= 0 && temp < 15){
            clothes  += dspace +"sweater"
            clothes  += dspace +"all-weather jacket"
        }else{
            clothes  += dspace +"summer jacket"
        }          
    }    

    setCountry(country)
    setCountOfDay(day, country)
    setTemperature(temp)
    checklist += space+"pasport"

    val check = checklist.toList  
    val clo = clothes.toList  
    val str = "checklist:\n" + check.mkString("\n") +"\n" +clo.mkString("\n")
    override def toString =  str 
}


class Parser extends RegexParsers {
    def word: Parser[String]   = """([A-Z]|[a-z])+""".r       ^^ { _.toString }
    def number: Parser[Int]    = """-?(0|[1-9]\d*)""".r ^^ { _.toInt }
    def country: Parser[String] = "country:".r ~  word ^^ { case co ~ wo => wo.toString }
    def day: Parser[Int] = "count of day:".r ~ number ^^ {  case co ~ nu => nu.toInt  }

    def temperature: Parser[Int] = "temperature:".r ~ number ~ ("Cels|Fahr".r) ^^ {  
        case te ~ nu ~ "Cels"  => nu.toInt  
        case te ~ nu ~ "Fahr"  => ((nu.toDouble-32.0)/1.8).toInt
    } 

    def checklistPars : Parser[CheckFreq] = country ~ "," ~ day~ "," ~ temperature  ^^ {  
        case co ~ c1 ~ day ~ c2 ~ te => CheckFreq(co,day,te)
    }
}

object CheckListParser extends Parser {
    def main(args: Array[String]) = {
      val pw = new PrintWriter(new File("checklist.txt"))
      
      if (args.length == 0) {
        println("dude, it is needed at least one parameter")
        }else{
        parse(checklistPars,args(0)) match {
            case Success(matched,_) => 
              println("\n\n\n" + matched + "\n\n\n")  
              pw.write(matched.toString)
              pw.close 
            case Failure(msg,_) => println("FAILURE: " + msg)
            case Error(msg,_) => println("ERROR: " + msg)
        }
      }
    }
} 
