package pl.javasoft.springboot2keycloack;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.javasoft.springboot2keycloack.dto.LoginDTO;
import pl.javasoft.springboot2keycloack.dto.ResponseDTO;

@Service
@Slf4j
@AllArgsConstructor
public class OpenApiClient {

    private final RestTemplate restTemplate;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    public ResponseDTO login(LoginDTO login){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(keycloakSpringBootProperties.getAuthServerUrl())
                .path("realms/")
                .path(keycloakSpringBootProperties.getRealm())
                .path("/protocol/openid-connect/token");
        log.error("URL: {}", uriComponentsBuilder.toUriString());
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.set("client_id", "frontapp");
        multiValueMap.set("username", login.getUsername());
        multiValueMap.set("password", login.getPassword());
        multiValueMap.set("grant_type","password");
        multiValueMap.set("client_secret", "eb11e6ac-0a4a-499c-aa8a-9f8fe38e7386");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(multiValueMap,headers);

        return restTemplate.postForObject(uriComponentsBuilder.toUriString(), httpEntity, ResponseDTO.class);
    }
}
