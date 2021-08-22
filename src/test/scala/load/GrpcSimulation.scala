package load

import com.github.phisgr.gatling.generic.SessionCombiner
import com.github.phisgr.gatling.grpc.Predef._
import example.myapp.helloworld.grpc.hello_world.{GreeterServiceGrpc, HelloRequest}
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.grpc.Status
import load.Constants.grpcPsgConf

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class GrpcSimulation extends Simulation {

  val scn = scenario("Make Hello Request and Get Response")
    .exec(grpc("Hello Request")
      .rpc(GreeterServiceGrpc.METHOD_SAY_HELLO)
      .payload(HelloRequest("Gatling Load Test"))
    )

  setUp(scn.inject(rampUsersPerSec(1) to (2) during (20 seconds)).protocols(grpcPsgConf.shareChannel))
}
