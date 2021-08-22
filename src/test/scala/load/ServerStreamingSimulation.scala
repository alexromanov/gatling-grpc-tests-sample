package load

import com.github.phisgr.gatling.generic.SessionCombiner
import com.github.phisgr.gatling.grpc.Predef._
import example.myapp.helloworld.grpc.hello_world.{GreeterServiceGrpc, HelloRequest}
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.grpc.Status
import load.Constants.grpcPsgConf

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class ServerStreamingSimulation extends Simulation {
  val serverCall = grpc("Replying").serverStream("replier")

  val scn = scenario("Server Streaming Flow")
    .exec(serverCall.start(GreeterServiceGrpc.METHOD_IT_KEEPS_REPLYING)
    (HelloRequest("Gatling Load Test"))
      .extract(_.message.some)(_ saveAs "ServerReply")
      .sessionCombiner(SessionCombiner.pick("ServerReply"))
      .endCheck(statusCode is Status.Code.OK)
    )

  setUp(scn.inject(rampUsersPerSec(1) to (2) during (20 seconds)).protocols(grpcPsgConf.shareChannel))
}
