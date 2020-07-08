package org.devzone.init;

import org.devzone.model.Address;
import org.devzone.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);
    private final ResourceLoader resourceLoader;
    private final AddressService addressService;

    public AppStartupRunner(ResourceLoader resourceLoader, AddressService addressService) {
        this.resourceLoader = resourceLoader;
        this.addressService = addressService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:postalcode_locality_de.csv");

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream())) ) {
            List<String> addresses = reader.lines().collect(Collectors.toList());
            for (String plainAddress : addresses) {
                String[] fields = plainAddress.split(",");
                Address address = new Address(fields[2], fields[1], fields[3]);
                Mono<Long> result = addressService.saveAddress(address);
                result.subscribe(l -> logger.debug("Created entry with index {}", l));
            }
        }

        logger.info("Initialized Redis Cache");
    }
}