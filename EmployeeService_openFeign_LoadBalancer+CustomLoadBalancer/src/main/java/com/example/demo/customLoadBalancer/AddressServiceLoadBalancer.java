package com.example.demo.customLoadBalancer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

//@LoadBalancerClient(value = "ADDRESSSERVICE", configuration = MyCustomLoadBalancer.class)
public class AddressServiceLoadBalancer {

	@Bean
	@LoadBalanced
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}

}
