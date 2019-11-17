# Radar Scala Exercice

## Data used
The data provided in exercise statement has been saved into text files in the folder "ressources" as described in the table below. The input files contain every radar records and the output files are the expected results that we will use to check our solutions.

Input files | Output files
------------ | -------------
input1.txt | output1.txt
input2.txt | output2.txt
input3.txt | output3.txt

## Instructions
The project was compiled with the **sbt** command-line (https://www.scala-sbt.org/).
After running **sbt** in the project folder, you just have to **compile** and **run** :

```
cd radar
sbt
sbt:radar> compile
sbt:radar> run
```

You will get the following results :

```
Violations list for ressources/input1.txt :
ZBZJ42,275,101
Solution successfully checked with file : ressources/output1.txt

Violations list for ressources/input2.txt :
Solution successfully checked with file : ressources/output2.txt

Violations list for ressources/input3.txt :
PAZD54,150,100
PAZD54,250,102
DJSS87,250,100
Solution successfully checked with file : ressources/output3.txt

```


## Programm Overview

The code consist of two files : **RadarProcess.scala** and **RecordsReader.scala**.
### RadarProcess.scala
1
### RecordsReader.scala
2
