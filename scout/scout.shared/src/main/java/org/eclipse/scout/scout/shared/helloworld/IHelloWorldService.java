package org.eclipse.scout.scout.shared.helloworld;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IHelloWorldService extends IService {

  HelloWorldFormData load(HelloWorldFormData input);

}
