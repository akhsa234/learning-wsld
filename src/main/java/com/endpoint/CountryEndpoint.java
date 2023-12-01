package com.endpoint;


import com.baeldung.springsoap.gen.GetCountryRequest;
import com.baeldung.springsoap.gen.GetCountryResponse;
import com.repo.CountryRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";
    private CountryRepository countryRepository;

    public CountryEndpoint(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountryResp(@RequestPayload GetCountryRequest req){
        GetCountryResponse resp = new GetCountryResponse();
        resp.setCountry(countryRepository.findCountry(req.getName()));
        return resp;
    }

}
