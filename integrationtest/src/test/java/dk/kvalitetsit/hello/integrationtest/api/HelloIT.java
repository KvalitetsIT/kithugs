package dk.kvalitetsit.hello.integrationtest.api;

import dk.kvalitetsit.hello.dao.HelloDao;
import dk.kvalitetsit.hello.dao.HelloDaoImpl;
import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import dk.kvalitetsit.hello.integrationtest.BaseTest;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.JSON;
import org.openapitools.client.api.KithugsApi;
import org.openapitools.client.model.DetailedError;
import org.openapitools.client.model.HelloRequest;

import static org.junit.jupiter.api.Assertions.*;

class HelloIT extends BaseTest {
    private static final KithugsApi helloApi = new KithugsApi(client);
    private static final HelloDao helloDao = new HelloDaoImpl(appDatabase.getDatasource());

    @Test
    void testCallServiceWithName() throws ApiException {
        var entity = new HelloEntity(1L, "Some Name");
        helloDao.insert(entity);

        var result = helloApi.v1HelloGet(entity.name());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(entity.name(), result.getFirst().getName());
    }

    @Test
    void testCallServiceWithNameNotInDB() throws ApiException {
        var entity = new HelloEntity(1L, "Some Name");
        helloDao.insert(entity);

        var result = helloApi.v1HelloGet("Another");

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testCallServiceWithoutName() throws ApiException {
        var entity = new HelloEntity(1L, "Some Name");
        helloDao.insert(entity);
        String input = null;
        
        var result = helloApi.v1HelloGet(input);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(entity.name(), result.getFirst().getName());
    }

    @Test
    void testCallPostService() throws ApiException {
        var request = new HelloRequest().name("John Dow");

        var postResult = helloApi.v1HelloPost(request);
        assertNotNull(postResult);
        assertEquals(request.getName(), postResult.getName());
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
        assertEquals("v1HelloGet.name: size must be between 0 and 10", detailedError.getDetailedError());
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
        assertEquals("name: size must be between 0 and 10", detailedError.getDetailedError());
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
