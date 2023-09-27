package com.bqt.ecommerce.services;

import com.bqt.ecommerce.payloads.request.ConfigRequest;
import com.bqt.ecommerce.payloads.response.ConfigResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;

import java.util.List;

public interface ConfigService {
    void createConfig(ConfigRequest configRequest);

    void activeConfig(Long idConfig);

    void updateConfig(Long idSupplier, ConfigRequest configRequest);

    void removeConfig(Long idConfig);

    PaginationResponse getListConfig(int page, int limit);

    ConfigResponse findById(Long idConfig);

    List<ConfigResponse> getAllConfig();
}
