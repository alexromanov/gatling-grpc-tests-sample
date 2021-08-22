package load

import com.github.phisgr.gatling.grpc.Predef.{grpc, managedChannelBuilder}

object Constants {
  val HOST: String = "127.0.0.1"
  val PORT = 8080

  val grpcPsgConf = grpc(managedChannelBuilder(name = HOST, port = PORT).usePlaintext())
}
