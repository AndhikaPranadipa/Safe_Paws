package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.Province;
import com.EnigmaCamp.SafePaws.repository.ProvinceRepository;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestClientAddressImpl implements RestClientAddressService {
    private final ProvinceRepository provinceRepository;

    private final CityRestClientImpl cityRestClient;

    private final ProvinceRestClientImpl provinceRestClient;

    public void fetch() throws JsonProcessingException {
        Province check = provinceRepository.findById("1").orElse(null);

        if(check == null){
            provinceRestClient.fetch();
            cityRestClient.fetch();
        }
    }
}
