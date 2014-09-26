package util.parquet

import java.io.File
import scala.language.higherKinds // cf: http://stackoverflow.com/questions/6246719/what-is-a-higher-kinded-type-in-scala

/**
 * Created by LDacost1 on 2014-09-25.
 */
trait ctx[A] {
  val value: A
}

trait ParquetMetadataSemantics[repr[_] <: ctx[_]] {
  def isMetadataFile: repr[File] => Boolean
  def isMetadataFileName: repr[String] => Boolean
}

object Representations {
  // Representation: Spark
  case class Spark[A](value: A) extends ctx[A]

  implicit object SparkParquetMetadata extends ParquetMetadataSemantics[Spark] { // how does this compile????
    def isMetadataFile: Spark[File] => Boolean = ???
    def isMetadataFileName: Spark[String] => Boolean = ???
  }

}
