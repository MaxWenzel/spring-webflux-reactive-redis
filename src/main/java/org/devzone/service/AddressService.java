package org.devzone.service;

import org.devzone.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private ReactiveRedisTemplate<String, Address> reactiveRedisTemplate;
    private ReactiveSetOperations<String, Address> setOperations;

    @Autowired
    public AddressService(ReactiveRedisTemplate<String, Address> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
        setOperations = reactiveRedisTemplate.opsForSet();
    }

    public Mono<Long> saveAddress(Address address) {
        logger.debug("Save address with postalcode {}", address.getPostalCode());
        return setOperations.add(address.getPostalCode(), address);
    }

    public Mono<Boolean> hasKey(String key) {
        return reactiveRedisTemplate.hasKey(key);
    }

    public Flux<Address> findAddressByPostalCode(String postalCode) {
        logger.debug("Try to find address for postalCode {}", postalCode);
        return setOperations.members(postalCode);
    }


}
