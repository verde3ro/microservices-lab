package com.itehl.digital;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.SendGridOutput;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {
    /**
     * This function listens at endpoint "/api/HttpTrigger-Java". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTrigger-Java
     * 2. curl {your host}/api/HttpTrigger-Java?name=HTTP%20Query
     */
    @FunctionName("emailus")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,

            @SendGridOutput(
            name = "message",
            dataType = "String",
            apiKey = "SendGridAttribute.ApiKey",
            to = "",
            from = "cambiar",
            subject = "cambiar",
            text = "")
            OutputBinding<String> message,
            final ExecutionContext context) {


            final String toAddress = request.getQueryParameters().get("to");
            final String value = request.getQueryParameters().get("message");

            StringBuilder builder = new StringBuilder()
                    .append("{")
                    .append("\"personalizations\": [{ \"to\": [{ \"email\": \"%s\"}]}],")
                    .append("\"content\": [{\"type\": \"text/plain\", \"value\": \"%s\"}]")
                    .append("}");

            final String body = String.format(builder.toString(), toAddress, value);

            message.setValue(body);

            return request.createResponseBuilder(HttpStatus.OK).body("Sent").build();

    }

}
