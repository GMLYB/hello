package com.lyb.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance getinstances(List<ServiceInstance> serviceInstances);
}
