package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.Province;
import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.repository.AddressUserRepository;
import com.EnigmaCamp.SafePaws.repository.CityRepository;
import com.EnigmaCamp.SafePaws.repository.ProvinceRepository;
import com.EnigmaCamp.SafePaws.repository.UserRepository;
import com.EnigmaCamp.SafePaws.service.AddressUserService;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateAddressUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressUserServiceImpl implements AddressUserService {

    private final AddressUserRepository addressUserRepository;

    private final AuthService authService;

    private final RestClientAddressService restClientAddressService;

    private final UserRepository userRepository;

    private final ProvinceRepository provinceRepository;

    private final CityRepository cityRepository;

    @Override
    public AddressResponse create(AddressUserDTO request) throws JsonProcessingException {
        restClientAddressService.fetch();

        UserDetails result = authService.getCurrentUser();

        User user = userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));

        Province province = provinceRepository.findById(city.getProvince().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Province Not Found"));

        AddressUser addressUser = addressUserRepository.saveAndFlush(request.toEntity(user, city));

        return AddressResponse.toResponse(addressUser, city, province);
    }

    @Override
    public List<AddressResponse> getAll(){
        UserDetails result = authService.getCurrentUser();

        User user = userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        List<AddressUser> addressUsers = addressUserRepository.findByUserId(user.getId());

        List<AddressResponse> responses = new ArrayList<>();

        for (AddressUser addressUser : addressUsers) {
            City city = cityRepository.findById(addressUser.getCity().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));
            Province province = provinceRepository.findById(city.getProvince().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Province Not Found"));
            responses.add(AddressResponse.toResponse(addressUser, city, province));
        }

        return responses;
    }

    @Override
    public AddressResponse update(UpdateAddressUserDTO request){
        UserDetails result = authService.getCurrentUser();

        User user = userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        AddressUser addressUsers = addressUserRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));

        Province province = provinceRepository.findById(city.getProvince().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Province Not Found"));

        addressUsers.setCity(city);
        addressUsers.setDescription(request.getDescription());

        addressUserRepository.saveAndFlush(addressUsers);

        return AddressResponse.toResponse(addressUsers, city, province);
    }

    @Override
    public void delete(GenericIdRequest request) {
        UserDetails result = authService.getCurrentUser();

        User user = userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        AddressUser addressUsers = addressUserRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        addressUserRepository.delete(addressUsers);
    }
}
