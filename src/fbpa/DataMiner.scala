/* DataMiner.scala
 * 
 * @author  Michael E. Cotterell <mepcotterell@gmail.com>
 * 
 * @compile scalac -cp ../../classes:../../lib/restfb.jar -d ../../classes DataMiner.scala
 * @run     scala -cp ../../classes:../../lib/restfb.jar fbpa.DataMinerApp
 *
 */

package fbpa

import com.restfb._
import com.restfb.types._

case class DataMiner () {

  /** This is a client that can only access publicly-visible data - no access token needed
   */
  val client: FacebookClient = new DefaultFacebookClient()

  def publicSearchPost (q: String): Connection[Post] = {
    client.fetchConnection("search", 
			   classOf[Post], 
			   Parameter.`with`("q", q), 
			   Parameter.`with`("type", "post")
    )
  } // publicSearch

} // DataMiner

/** DataMiner
 */
object DataMinerApp extends App {

  /** Instance of the DataMiner
   */
  val miner = DataMiner()


  val result = miner.publicSearchPost("dubstep").getData

  printf("Size of result: %d\n", result.size)
  printf("Public search: %s\n", result.get(0).getMessage)

} // DataMiner

