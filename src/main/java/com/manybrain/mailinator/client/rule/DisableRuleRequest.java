package com.manybrain.mailinator.client.rule;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.manybrain.mailinator.client.Request;

import static com.manybrain.mailinator.client.JerseyClient.CLIENT;
import static com.manybrain.mailinator.client.Utils.emptyIfNull;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class DisableRuleRequest implements Request<ResponseStatus> {

	private static final String URL = "https://mailinator.com/api/v2/domains/{domain_id}/rules/{rule_id}/disable";

	private static final WebTarget WEB_TARGET = CLIENT.target(URL);

	@NonNull
	private final String domainId;
	@NonNull
	private final String ruleId;

	public ResponseStatus execute(String apiToken) {
		WebTarget webTarget = WEB_TARGET.resolveTemplate("domain_id", emptyIfNull(domainId))
										.resolveTemplate("rule_id", emptyIfNull(ruleId));

		return webTarget.request(MediaType.APPLICATION_JSON_TYPE)
						.header(AUTHORIZATION, apiToken)
						.put(Entity.json("{}"), ResponseStatus.class);
	}

}
