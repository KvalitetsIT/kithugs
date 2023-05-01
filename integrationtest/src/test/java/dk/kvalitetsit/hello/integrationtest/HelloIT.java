package dk.kvalitetsit.hello.integrationtest;

import org.junit.Assert;
import org.junit.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.JSON;
import org.openapitools.client.api.KithugsApi;
import org.openapitools.client.model.DetailedError;

import java.util.UUID;

import static org.junit.Assert.*;

public class HelloIT extends AbstractIntegrationTest {

    private final KithugsApi helloApi;
    private final JSON json;

    public HelloIT() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(getApiBasePath());

        json = apiClient.getJSON();
        helloApi = new KithugsApi(apiClient);
    }

    @Test
    public void testCallService() throws ApiException {
        var input = "John Dow";

        var result = helloApi.v1HelloGet(input);

        assertNotNull(result);
        assertEquals(input, result.getName());
        assertNull(result.getiCanBeNull());
        assertNotNull(result.getNow());
    }

    @Test
    public void testCallServiceNameTooLong() throws ApiException {
        var input = "John Doe Is Too Long";

        var thrownException = Assert.assertThrows(ApiException.class, () -> helloApi.v1HelloGet(input));
        assertEquals(500, thrownException.getCode());
    }

    @Test
    public void testCallServiceNameValidationError() throws ApiException {
        var input = "NOT_VALID";

        var thrownException = Assert.assertThrows(ApiException.class, () -> helloApi.v1HelloGet(input));
        assertEquals(400, thrownException.getCode());

        DetailedError detailedError = JSON.deserialize(thrownException.getResponseBody(), DetailedError.class);
        assertEquals("Bad Request", detailedError.getError());
        assertEquals("/v1/hello", detailedError.getPath());
        assertEquals("NOT_VALID is not a valid name.", detailedError.getDetailedError());
        assertEquals(DetailedError.DetailedErrorCodeEnum._10, detailedError.getDetailedErrorCode());
        assertNotNull(detailedError.getTimestamp());
        assertEquals(400, detailedError.getStatus().longValue());
    }
}
