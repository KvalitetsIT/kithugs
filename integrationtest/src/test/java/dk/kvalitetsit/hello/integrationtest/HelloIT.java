package dk.kvalitetsit.hello.integrationtest;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.JSON;
import org.openapitools.client.api.KithugsApi;
import org.openapitools.client.model.DetailedError;

import static org.junit.jupiter.api.Assertions.*;
import org.openapitools.client.model.HelloRequest;

class HelloIT extends AbstractIntegrationTest {

    private final KithugsApi helloApi;

    HelloIT() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(getApiBasePath());

        helloApi = new KithugsApi(apiClient);
    }

    @Test
    void testCallService() throws ApiException {
        //in V901__extra_data_for_integration_test.sql the name "Some Name" is set to be inserted into the db.
        //here we test that we can get that name from the db.
        var input = "Some Name";

        var result = helloApi.v1HelloGet(input);

        assertNotNull(result);
        boolean containsSomeName = result.stream()
            .anyMatch(dbEntry -> "Some Name".equals(dbEntry.getName()));
        assert(containsSomeName);
    }

    @Test
    void testCallPostService() throws ApiException {
        var input = "John Dow3";

        var request = new HelloRequest().name(input);

        var postResult = helloApi.v1HelloPost(request);
        assertNotNull(postResult);
        assertEquals(input, postResult.getName());
        assertNull(postResult.getiCanBeNull());
        assertNotNull(postResult.getNow());
    }

    @Test
    void testCallGetServiceNameTooLong() {
        var input = "John Doe Is Too Long";

        var thrownException = assertThrows(ApiException.class, () -> helloApi.v1HelloGet(input));
        DetailedError detailedError = JSON.deserialize(thrownException.getResponseBody(), DetailedError.class);
        assertEquals("Bad Request", detailedError.getError());
        assertEquals("/v1/hello", detailedError.getPath());
        assertEquals("v1HelloGet.name: størrelse skal være mellem 0 og 10", detailedError.getDetailedError());
        assertEquals(DetailedError.DetailedErrorCodeEnum._10, detailedError.getDetailedErrorCode());
        assertNotNull(detailedError.getTimestamp());
        assertEquals(400, detailedError.getStatus().longValue());
    }

    @Test
    void testCallPostServiceNameTooLong() {
        var request = new HelloRequest().name("John Doe Is Too Long");

        var thrownException = assertThrows(ApiException.class, () -> helloApi.v1HelloPost(request));
        DetailedError detailedError = JSON.deserialize(thrownException.getResponseBody(), DetailedError.class);
        assertEquals("Bad Request", detailedError.getError());
        assertEquals("/v1/hello", detailedError.getPath());
        assertEquals("name: størrelse skal være mellem 0 og 10", detailedError.getDetailedError());
        assertEquals(DetailedError.DetailedErrorCodeEnum._10, detailedError.getDetailedErrorCode());
        assertNotNull(detailedError.getTimestamp());
        assertEquals(400, detailedError.getStatus().longValue());
    }

    @Test
    void testCallServiceNameValidationError() {
        var input = "NOT_VALID";

        var thrownException = assertThrows(ApiException.class, () -> helloApi.v1HelloGet(input));
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
