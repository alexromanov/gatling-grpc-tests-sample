package load

import com.github.phisgr.gatling.grpc.Predef._
import example.myapp.helloworld.grpc.hello_world.{GreeterServiceGrpc, HelloRequest}
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.grpc.Status
import load.Constants.grpcPsgConf

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class BiDiStreamingSimulation extends Simulation {
  val bidiCall = grpc("BiDi call").bidiStream("bidi")

  val scn = scenario("BiDi streaming")
    .exec(bidiCall.connect(GreeterServiceGrpc.METHOD_STREAM_HELLOS)
      .endCheck(statusCode is Status.Code.OK))
    .exec(bidiCall.send(HelloRequest("Gatling Load Test First")))
    .exec(bidiCall.send(HelloRequest("Gatling Load Test Second")))
    .exec(bidiCall.complete)

  setUp(scn.inject(rampUsersPerSec(1) to (2) during (20 seconds)).protocols(grpcPsgConf.shareChannel))
}
