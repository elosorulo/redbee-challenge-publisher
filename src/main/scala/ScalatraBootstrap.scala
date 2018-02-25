import com.redbee.challenge.publisher._
import org.scalatra._
import javax.servlet.ServletContext

import com.redbee.challenge.publisher.config.Initializer
import com.redbee.challenge.publisher.streamer.Streamer

class ScalatraBootstrap extends LifeCycle with Initializer {
  override def init(context: ServletContext) {
    val streamer: Streamer = initialize()
    context.mount(new PublisherServlet(streamer), "/*")
  }
}
