package com.mansi.springboot_api.dto;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class BfhlRequest {
    public @Positive(message = "Fibonacci must be positive") Integer getFibonacci() {
        return fibonacci;
    }

    public void setFibonacci(@Positive(message = "Fibonacci must be positive") Integer fibonacci) {
        this.fibonacci = fibonacci;
    }

    public List<Object> getPrime() {
        return prime;
    }

    public void setPrime(List<Object> prime) {
        this.prime = prime;
    }

    public List<Object> getHcf() {
        return hcf;
    }

    public void setHcf(List<Object> hcf) {
        this.hcf = hcf;
    }

    public List<Object> getLcm() {
        return lcm;
    }

    public void setLcm(List<Object> lcm) {
        this.lcm = lcm;
    }

    public String getAI() {
        return AI;
    }

    public void setAI(String AI) {
        this.AI = AI;
    }

    @Positive(message = "Fibonacci must be positive")
    private Integer fibonacci;
    private List<Object> prime;
    private List<Object> lcm;
    private List<Object> hcf;
    private String AI;
}
