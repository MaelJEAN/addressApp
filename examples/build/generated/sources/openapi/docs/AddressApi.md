# AddressApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAddresses**](AddressApi.md#createAddresses) | **POST** /town/{id}/address | Creates an address |
| [**createAddressesWithHttpInfo**](AddressApi.md#createAddressesWithHttpInfo) | **POST** /town/{id}/address | Creates an address |
| [**listAddresses**](AddressApi.md#listAddresses) | **GET** /town/{id}/address | Retrieves all addresses for a given town |
| [**listAddressesWithHttpInfo**](AddressApi.md#listAddressesWithHttpInfo) | **GET** /town/{id}/address | Retrieves all addresses for a given town |



## createAddresses

> Address createAddresses(id, address)

Creates an address

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AddressApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        AddressApi apiInstance = new AddressApi(defaultClient);
        String id = "id_example"; // String | The town id
        Address address = new Address(); // Address | 
        try {
            Address result = apiInstance.createAddresses(id, address);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AddressApi#createAddresses");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The town id | |
| **address** | [**Address**](Address.md)|  | |

### Return type

[**Address**](Address.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Expected response to a valid request |  -  |

## createAddressesWithHttpInfo

> ApiResponse<Address> createAddresses createAddressesWithHttpInfo(id, address)

Creates an address

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AddressApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        AddressApi apiInstance = new AddressApi(defaultClient);
        String id = "id_example"; // String | The town id
        Address address = new Address(); // Address | 
        try {
            ApiResponse<Address> response = apiInstance.createAddressesWithHttpInfo(id, address);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling AddressApi#createAddresses");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The town id | |
| **address** | [**Address**](Address.md)|  | |

### Return type

ApiResponse<[**Address**](Address.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Expected response to a valid request |  -  |


## listAddresses

> List<Address> listAddresses(id)

Retrieves all addresses for a given town

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AddressApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        AddressApi apiInstance = new AddressApi(defaultClient);
        String id = "id_example"; // String | The town id
        try {
            List<Address> result = apiInstance.listAddresses(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AddressApi#listAddresses");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The town id | |

### Return type

[**List&lt;Address&gt;**](Address.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Expected response to a valid request |  -  |

## listAddressesWithHttpInfo

> ApiResponse<List<Address>> listAddresses listAddressesWithHttpInfo(id)

Retrieves all addresses for a given town

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AddressApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        AddressApi apiInstance = new AddressApi(defaultClient);
        String id = "id_example"; // String | The town id
        try {
            ApiResponse<List<Address>> response = apiInstance.listAddressesWithHttpInfo(id);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling AddressApi#listAddresses");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The town id | |

### Return type

ApiResponse<[**List&lt;Address&gt;**](Address.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Expected response to a valid request |  -  |

