package se331.lab.rest.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import se331.lab.rest.entity.AccessTokenDTO;
import se331.lab.rest.entity.Event;
import se331.lab.rest.util.LabMapper;

import java.util.Collections;
import java.util.Map;

@Controller
public class OAuthController {
    @GetMapping("/getToken")
    public ResponseEntity<?> getAccessToken(@RequestParam(value = "code", required = true) String code) {
        String uri = "https://oauth.cmu.ac.th/v1/GetToken.aspx";
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders responseHeader = new HttpHeaders();
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        responseHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        map.add("code", code);
        map.add("redirect_uri", "http:/localhost:8081/redirect");
        map.add("client_id", "7a2tEVznj823QypVnrBJHbRu7dnfdbrmTmwesF8K");
        map.add("client_secret", "VWH0pZsVd2VSFWd6wHwBGthhURkCcjCTt20YD0fh");
        map.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map, responseHeader);

        ResponseEntity<AccessTokenDTO> response = restTemplate.exchange(uri, HttpMethod.POST, entity, AccessTokenDTO.class);
        
        return new ResponseEntity<>(LabMapper.INSTANCE.getAccessToken(response.getBody()), HttpStatus.OK);
    }

    @GetMapping("/getCredentials")
    public ResponseEntity<?> getCredentials(@RequestParam(value = "token", required = true) String token){
        String uri = "https://misapi.cmu.ac.th/cmuitaccount/v1/api/cmuitaccount/basicinfo";
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setBearerAuth(token);

        HttpEntity<?> entity = new HttpEntity<>(responseHeader);
        ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

    }
}
