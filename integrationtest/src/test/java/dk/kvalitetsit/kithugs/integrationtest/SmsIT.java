package dk.kvalitetsit.kithugs.integrationtest;

import org.junit.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.CallbackServiceApi;
import org.openapitools.client.api.SmsApi;
import org.openapitools.client.model.SmsRequest;

import java.util.UUID;

import static org.junit.Assert.*;

public class SmsIT extends AbstractIntegrationTest {
    private static final String SESSION_MEDCOM_ORGANISATION = "ewogICAiVXNlckF0dHJpYnV0ZXMiOnsKICAgICAgImRrOm1lZGNvbTp2aWRlbzpyb2xlIjpbCiAgICAgICAgICJtZWV0aW5nLXVzZXIiCiAgICAgIF0sCiAgICAgICJkazptZWRjb206b3JnYW5pc2F0aW9uX2lkIjpbCiAgICAgICAgICJtZWRjb20iCiAgICAgIF0sCiAgICAgICJkazptZWRjb206ZW1haWwiOlsKICAgICAgICAgImVtYWlsQGRvbWFpbi5jb20iCiAgICAgIF0KICAgfQp9";
    private static final String SESSION_UNKNOWN_ORGANISATION = "ewogICAiVXNlckF0dHJpYnV0ZXMiOnsKICAgICAgImRrOm1lZGNvbTp2aWRlbzpyb2xlIjpbCiAgICAgICAgICJtZWV0aW5nLXVzZXIiCiAgICAgIF0sCiAgICAgICJkazptZWRjb206b3JnYW5pc2F0aW9uX2lkIjpbCiAgICAgICAgICJ1bmtvd24iCiAgICAgIF0sCiAgICAgICJkazptZWRjb206ZW1haWwiOlsKICAgICAgICAgImVtYWlsQGRvbWFpbi5jb20iCiAgICAgIF0KICAgfQp9";

    private final SmsApi smsApi;
    private final CallbackServiceApi callbackApi;

    public SmsIT() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(getApiBasePath()).addDefaultHeader("X-SESSIONDATA", SESSION_MEDCOM_ORGANISATION);
        smsApi = new SmsApi(apiClient);
        callbackApi = new CallbackServiceApi(apiClient);
    }

    @Test
    public void testRegisterSms() throws ApiException {
        var input = new SmsRequest();
        input.setMessage("This is a message %meeting_url%");
        input.setTo("12345678");

        var result = smsApi.smsV1MeetingUuidPost("7cc82183-0d47-439a-a00c-38f7a5a01fc3", input);

        assertNotNull(result);
        assertEquals("Registered", result.getStatus());
        assertNotNull(result.getReference());
    }

    @Test
    public void testGetStatusNotFound() {
        try {
            smsApi.smsV1MeetingUuidGet(UUID.randomUUID());
            fail();
        }
        catch(ApiException e) {
            assertEquals(404, e.getCode());
        }
    }

    @Test
    public void testRegisterAndGetStatus() throws ApiException, InterruptedException {
        var input = new SmsRequest();
        input.setMessage("This is a message %meeting_url%");
        input.setTo("12345678");

        var result = smsApi.smsV1MeetingUuidPost("7cc82183-0d47-439a-a00c-38f7a5a01fc3", input);

        assertNotNull(result);
        assertEquals("Registered", result.getStatus());
        assertNotNull(result.getReference());

        // Sleep until message processed
        Thread.sleep(5000);

        var statusList = smsApi.smsV1MeetingUuidGet(UUID.fromString("7cc82183-0d47-439a-a00c-38f7a5a01fc3"));
        assertTrue(statusList.size() > 0);

        var status = statusList.stream().filter(x -> x.getReference().equals(result.getReference())).findFirst().orElseThrow(RuntimeException::new);

        assertEquals("InProgress", status.getStatus());
        assertEquals(result.getReference(), status.getReference());
    }

    @Test
    public void testCallBack() throws ApiException {
        var messageId = "1349f41b-61f6-48ab-b1c5-d4de3856d93e";
        callbackApi.smsV1CallbackGet(messageId, 3);

        var statusList = smsApi.smsV1MeetingUuidGet(UUID.fromString("8848d163-d0e4-4a64-b9eb-91f8672bcd32"));
        assertEquals(1, statusList.size());

        assertEquals("Failed", statusList.get(0).getStatus());
    }

    @Test
    public void testMeetingNotFound() {
        var input = new SmsRequest();
        input.setMessage("some message");
        input.setTo("12345678");

        try {
            smsApi.smsV1MeetingUuidPost("uuid", input);
            fail();
        }
        catch(ApiException e) {
            assertEquals(404, e.getCode());
        }
    }
}
