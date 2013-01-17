package shopyourway.domain.client;

import com.google.common.primitives.Bytes;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import play.mvc.Controller;
import shopyourway.platform.client.configurations.ApplicationSettings;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This Class allows access to all of the API provided by ShopYourWay.com
 * For a full api list visit: https://developer.shopyourway.com/display/platdev/The+API
 */

public class PlatformClient {

    public static boolean HasToken() {
        return Controller.request().queryString().containsKey("token");
    }

    public <T> List<T> getList(String url, Map<String, String> map, Class<T> parseTo) throws Exception {

        String json = GetJson(url, map);
        ObjectMapper mapper = new ObjectMapper();
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            return mapper.readValue(json, t.constructCollectionType(ArrayList.class, parseTo));
        } catch (Exception ex) {
            throw FormatException(url, parseTo, json);
        }
    }

    private <T> Exception FormatException(String url, Class<T> parseTo, String json) {
        return new Exception("There was an error parsing respinse from: : " + url + " when deserializing to: " + parseTo.getName() + "the endpoint returned: " + json);
    }

    public <T> T get(String url, Map<String, String> map, Class<T> parseTo) throws Exception {

        String json = GetJson(url, map);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, parseTo);
        } catch (Exception ex) {
            throw FormatException(url, parseTo, json);
        }
    }

    private String GetJson(String url, Map<String, String> map) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);

        MultivaluedMapImpl multivaluedMap = SetTokenAndHash(map);
        WebResource apiCall = client
                .resource(ApplicationSettings.getApiBaseUrl() + url)
                .queryParams(multivaluedMap);

        return apiCall.get(String.class);
    }

    private MultivaluedMapImpl SetTokenAndHash(Map<String, String> map) {
        MultivaluedMapImpl multivaluedMap = map == null ? new MultivaluedMapImpl() : ConvertToMultivaluedMap(map);

        String token = Controller.request().queryString().get("token")[0];
        multivaluedMap.add("token", token);
        multivaluedMap.add("hash", GenerateHash(token));
        return multivaluedMap;
    }

    private MultivaluedMapImpl ConvertToMultivaluedMap(Map<String, String> map) {
        MultivaluedMapImpl multiMap = new MultivaluedMapImpl();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            multiMap.add(entry.getKey(), entry.getValue());
        }

        return multiMap;
    }

    private String GenerateHash(String token) {
        try {
            String secret = ApplicationSettings.getSecret();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            ArrayList<Byte> temp = new ArrayList<Byte>();
            temp.addAll(Bytes.asList(token.getBytes("UTF-8")));
            temp.addAll(Bytes.asList(secret.getBytes("UTF-8")));

            return Hex.encodeHexString(md.digest(Bytes.toArray(temp)));
        } catch (Exception ex) {
            return "";
        }
    }

    public <T >T get(String url, Class<T> parseTo) throws Exception {
        return get(url, null, parseTo);
    }
}
