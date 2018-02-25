import com.redbee.challenge.publisher._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new PublisherServlet, "/*")
  }
}
