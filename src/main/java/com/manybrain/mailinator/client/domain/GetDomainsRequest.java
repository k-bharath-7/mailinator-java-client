package com.manybrain.mailinator.client.domain;

import static com.manybrain.mailinator.client.JerseyClient.CLIENT;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import com.manybrain.mailinator.client.Request;

public class GetDomainsRequest implements Request<Domains> {

  private static final String URL = "https://mailinator.com/api/v2/domains/";

  private static final WebTarget WEB_TARGET = CLIENT.target(URL);

  @Override
  public Domains execute(String apiToken) {
    return WEB_TARGET.request(MediaType.APPLICATION_JSON_TYPE).header(AUTHORIZATION, apiToken)
        .get(Domains.class);
  }

}
