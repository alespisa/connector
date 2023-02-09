package org.eclipse.scout.scout.server.api;

import org.eclipse.scout.scout.server.ServerSession;
import org.eclipse.scout.scout.shared.api.ApiFormData;
import org.eclipse.scout.scout.shared.api.IApiService;

public class ApiService implements IApiService {
  @Override
  public ApiFormData load(ApiFormData input) {
    StringBuilder msg = new StringBuilder();
    if (!input.getMessageBox().getValue().isEmpty()){
      msg.append("Hello ").append(ServerSession.get().getUserId()).append('!').append(" This is youre Message: ").append(input.getMessageBox().getValue());
    } else {
      msg.append("Hello ").append(ServerSession.get().getUserId()).append('!');
    }
    input.getMessageValueString().setValue(msg.toString());

    return input;
  }
}
