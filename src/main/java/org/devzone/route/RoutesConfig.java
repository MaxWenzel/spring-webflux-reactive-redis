package org.devzone.route;

import org.devzone.model.Address;
import org.devzone.service.AddressService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutesConfig {

    // .contentType(MediaType.APPLICATION_JSON)
    @Bean
    public RouterFunction<ServerResponse> routes(AddressService addressService) {
        return
                route(
                        GET("/postalcodes/{postalcode}"),
                        request -> ok().contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                       .body(addressService.findAddressByPostalCode(request.pathVariable("postalcode")), Address.class));
    }
}