package dk.kvalitetsit.kithugs.integrationtest;

import org.junit.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.KithugsApi;
import org.openapitools.client.model.HelloRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HelloIT extends AbstractIntegrationTest {

    private final KithugsApi kithugsApi;

    public HelloIT() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(getApiBasePath());

        kithugsApi = new KithugsApi(apiClient);
    }

    @Test
    public void testCallService() throws ApiException {
        var input = new HelloRequest();
        input.setName("John Doe");

        var result = kithugsApi.v1HelloPost(input);

        assertNotNull(result);
        assertEquals(input.getName(), result.getName());
        assertNotNull(result.getNow());
    }
}
