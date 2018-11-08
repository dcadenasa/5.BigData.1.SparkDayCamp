package data.miami

import org.apache.spark.sql.SparkSession


object wordCount {

  def main(args: Array[String]): Unit ={

    //Define spark context
    val sparkSession = SparkSession.builder.
      master("local")
      .appName("Word Count")
      .getOrCreate()

    import sparkSession.implicits._

    // Read file as DataSet
    val data = sparkSession.read.text("SparkDayCamp/src/main/resources/twinkle").as[String]

    // Split and Group by
    val words = data.flatMap(value => value.split("\\s+"))
    val groupedWords = words.groupByKey(_.toLowerCase)

    // Count the words
    val counts = groupedWords.count()

    // Show result (use only for training purposes
    counts.show()

  }
}
