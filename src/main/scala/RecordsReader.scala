package radar

import scala.io.Source

case class Record(milestone: Int, timestamp: Int)
/** Record : contain informations about a radar record
 * - milestone : position from the begining of the road in km.
 * - timestamp : absolute time in seconds of the record.
 **/

class RecordsReader(val fileName: String){
    /** RecordsReader : read and handle radar records for mutliple cars
    * - extractViolations : return a list of violations
    * - ckeckViolations : return the validity of a solution
    **/
    val bufferedSource = Source.fromFile(fileName);
    val lines = bufferedSource.getLines.toList;
    bufferedSource.close;
    val maxSpeed = lines.head.split(":")(1).split("km/h")(0).toFloat

    // Generating a Map of records indexed by car id :
    var carsRecords:Map[String,List[Record]] = Map();
    for(radarInfo <- lines.tail){
        val cols = radarInfo.split(",");
        var records = List[Record]();
        if(carsRecords.contains(cols(0))) records = carsRecords(cols(0));
        carsRecords += cols(0) -> (Record(cols(1).toInt,cols(2).toInt) :: records);
    }
    
    def extractViolations():List[String] = {
        // Checking For every pair of records the average speed
        var violations = List[String]();
        println("Violations list for %s :".format(fileName))
        for ((car,records) <- carsRecords){
            for(List(prev,current) <- records.reverse.sliding(2)){
                val deltaX = current.milestone-prev.milestone;
                val deltaT = current.timestamp-prev.timestamp;
                val speed = deltaX/(deltaT.toFloat/3600)
                if(speed>maxSpeed){
                    val v = "%s,%d,%d".format(car,current.milestone,math.ceil(speed).toInt);
                    violations = v::violations;
                    println(v);
                }
            }
        }
        return violations;
    }
    
    def checkViolations(violations : List[String],fileNameSolution: String):Boolean = {
        val bufferedSourceSolution = Source.fromFile(fileNameSolution);
        val linesSolution = bufferedSourceSolution.getLines.toList;
        bufferedSourceSolution.close;
        for(v <- violations){
            if(!linesSolution.contains(v)){
                println("Bad solution : %s".format(v));
                return false;
            }
        }
        for(s <- linesSolution){
            if(s!="" && !violations.contains(s)){
                println("Missing solution : %s".format(s));
                return false;
            }
        }
        println("Solution successfully checked with file : %s".format(fileNameSolution));
        return true;
    }
}
