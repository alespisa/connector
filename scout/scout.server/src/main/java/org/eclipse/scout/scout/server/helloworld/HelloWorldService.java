package org.eclipse.scout.scout.server.helloworld;

import org.eclipse.scout.scout.server.ServerSession;
import org.eclipse.scout.scout.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.scout.shared.helloworld.IHelloWorldService;

public class HelloWorldService implements IHelloWorldService {
  @Override
  public HelloWorldFormData load(HelloWorldFormData input) {
    StringBuilder msg = new StringBuilder();
    msg.append("Hello ").append(ServerSession.get().getUserId()).append('!');
    input.getMessage().setValue(msg.toString());

    return input;
  }
}
