Open the project file from the extracted zip file in eclipse IDE.

Run com.ubs.exercise.datareader.ubdata.UBSDataExtractor (contains main()) to 
get the average amount in Euros (EUR), grouped by Country and Credit Rating.

Following Unit tests (running green) added under src/test/java

com.ubs.exercise.datareader.TestDelimitedFileReader
com.ubs.exercise.datareader.ubdata.TestUBSDataProcessor
com.ubs.exercise.util.TestCurrencyConverter

java -cp target\interview-1.0.0-SNAPSHOT.jar com.ubs.exercise.datareader.ubdata.UBSDataExtractor

extract the zip into a folder
go to command prompt and type
mvnw clean
mvnw package
mvnw exec:java
mvnw test




