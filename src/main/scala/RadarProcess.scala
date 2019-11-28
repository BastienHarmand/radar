package radar

/**
 *  Automated tests 
 **/

object RadarProcess extends App {    
    
    val files = List(
        List("ressources/input1.txt","ressources/output1.txt"),
        List("ressources/input2.txt","ressources/output2.txt"),
        List("ressources/input3.txt","ressources/output3.txt")
    );
    
    for(List(input,output) <- files){
        val records = new RecordsReader(input);
        val violations = records.extractViolations();
        records.checkViolations(violations,output);
        println();
    }
}
