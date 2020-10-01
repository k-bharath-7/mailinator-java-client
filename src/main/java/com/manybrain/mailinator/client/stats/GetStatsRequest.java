package com.manybrain.mailinator.client.stats;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.manybrain.mailinator.client.Request;

import static com.manybrain.mailinator.client.JerseyClient.CLIENT;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

public class GetStatsRequest implements Request<Stats> {

	private static final String URL = "https://api.mailinator.com/v2/team/stats";

	private static final WebTarget WEB_TARGET = CLIENT.target(URL);

	public Stats execute(String apiToken) {
		return WEB_TARGET.request(MediaType.APPLICATION_JSON_TYPE)
						 .header(AUTHORIZATION, apiToken)
						 .get(Stats.class);
	}

}
