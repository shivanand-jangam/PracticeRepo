package com.etouches.decorator;

import com.etouches.api.core.http.Request;
import com.etouches.api.core.http.Response;
import com.etouches.api.core.http.exceptions.UnexpectedResponseBodyException;
import com.etouches.api.core.util.JsonConverter;
import com.etouches.api.impl.helpers.UriHelper;
import com.etouches.api.impl.services.IBaseRequestService;
import com.etouches.api.impl.services.WebAPIServices;
import net.serenitybdd.core.pages.PageObject;

import java.net.URI;

public abstract class AbstractApiService extends PageObject {

  private static LastResponse lastResponse = new LastResponse();


  public Response<String> getLastRawResponse() {
    return lastResponse.getRaw();
  }

  public Response<?> getLastResponse() {
    return lastResponse.get();
  }

  // safe cast to required class
  public <T> Response<T> getLastResponse(Class<T> classToCast) {
    return lastResponse.castTo(classToCast);
  }

  protected <T> IBaseRequestService<T> getService(Class<T> classJsonResponse) {
    return WebAPIServices.get(classJsonResponse);
  }

  protected <T> Response<T> sendRequest(Request.Type requestType, String uriPath, Object body,
      Class<T> classJsonResponse) {
    URI uri = new UriHelper().buildUri(uriPath);
    IBaseRequestService<T> service = this.getService(classJsonResponse);
    Response<T> response = service.sendRequest(requestType, uri, body);
    lastResponse.setRaw(service.getRawResponse());
    lastResponse.set(response);
    return response;
  }

  private static class LastResponse {

    private Response<String> rawResponse;
    private Response<?> response;

    public <T> Response<T> castTo(Class<T> classToCast) {
      if (rawResponse == null || rawResponse.getBody() == null) {
        throw new UnexpectedResponseBodyException(
            "Response " + ((rawResponse == null) ? "" : "Body ") + "is NULL");
      }

      String body = rawResponse.getBody();
      T data;
      try {
        data = JsonConverter.convertFromJson(body, classToCast);
      } catch (Exception e) {
        String msg =
            String.format("Failed cast response json to \"%s\" (response body: %s). Error - %s",
                classToCast, body, e.getMessage());

        throw new UnexpectedResponseBodyException(msg, e.getCause());
      }

      return new Response<T>(rawResponse.getStatusCode(), data);
    }

    public void set(Response<?> response) {
      this.response = response;
    }

    public Response<?> get() {
      return response;
    }

    public Response<String> getRaw() {
      return rawResponse;
    }

    public void setRaw(Response<String> rawResponse) {
      this.rawResponse = rawResponse;
    }
  }
}
