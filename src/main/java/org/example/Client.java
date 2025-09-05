package org.example;

import com.example.grpc.HelloServiceProto.HelloRequest;
import com.example.grpc.HelloServiceProto.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
  private Client() {}

  public static void call(String name) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();

    com.example.grpc.HelloServiceGrpc.HelloServiceBlockingStub stub = com.example.grpc.HelloServiceGrpc.newBlockingStub(channel);

    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloResponse response = stub.sayHello(request);

    System.out.println("Response: " + response.getMessage());

    channel.shutdown();

  }
}
