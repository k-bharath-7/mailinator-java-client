package com.manybrain.mailinator.client.message;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.manybrain.mailinator.client.Request;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.manybrain.mailinator.client.JerseyClient.CLIENT;
import static com.manybrain.mailinator.client.Utils.emptyIfNull;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class GetAttachmentsRequest
        implements Request<Attachments>
{

    private static final String URL = "https://mailinator.com/api/v2/domains/{domain}/inboxes/{inbox}/messages/{message_id}/attachments";

    private static final WebTarget WEB_TARGET = CLIENT.target(URL);

    @NonNull
    private final String domain;
    @NonNull
    private final String inbox;
    @NonNull
    private final String messageId;

    public Attachments execute(String apiToken)
    {
        WebTarget webTarget = WEB_TARGET.resolveTemplate("domain", emptyIfNull(domain))
                                        .resolveTemplate("inbox", emptyIfNull(inbox))
                                        .resolveTemplate("message_id", emptyIfNull(messageId));

        return webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                        .header(AUTHORIZATION, apiToken)
                        .get(Attachments.class);
    }


}
