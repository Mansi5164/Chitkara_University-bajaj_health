package com.mansi.springboot_api.service;
import com.mansi.springboot_api.Repository.BfhlRepository;
import com.mansi.springboot_api.Util.MathUtil;
import com.mansi.springboot_api.dto.BfhlRequest;
import com.mansi.springboot_api.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private final BfhlRepository repository;
    private final GeminiService geminiService;


    public BfhlServiceImpl(BfhlRepository repository, GeminiService geminiService){
        this.repository = repository;
        this.geminiService = geminiService;
    }

    @Override
    public Object process(BfhlRequest req){

        int count = 0;
        if(req.getFibonacci()!=null) count++;
        if(req.getPrime()!=null) count++;
        if(req.getLcm()!=null) count++;
        if(req.getHcf()!=null) count++;
        if(req.getAI()!=null) count++;

        if(count != 1){
            throw new BadRequestException("Exactly one key must be provided");
        }

        if(req.getFibonacci()!=null){
            if(req.getFibonacci() <= 0)
                throw new BadRequestException("Invalid Fibonacci input");

            repository.logRequest("fibonacci");
            return MathUtil.fibonacci(req.getFibonacci());
        }

        if(req.getPrime()!=null){
            if(req.getPrime().isEmpty())
                throw new BadRequestException("Array cannot be empty");

            return MathUtil.prime(toIntegerList(req.getPrime()));
        }

        if(req.getHcf()!=null){
            return MathUtil.hcf(toIntegerList(req.getHcf()));
        }

        if(req.getLcm()!=null){
            return MathUtil.lcm(toIntegerList(req.getLcm()));
        }

        if(req.getAI()!=null){

            String question = req.getAI().toString();

            String aiResponse = geminiService.askAI(question);

            if(aiResponse == null || aiResponse.equals("AI service unavailable")){
                throw new BadRequestException("AI service failed");
            }

            repository.logRequest("AI");

            return aiResponse;
        }

        throw new BadRequestException("Invalid Request");
    }

    private List<Integer> toIntegerList(List<Object> list){
        List<Integer> result = new ArrayList<>();

        for(Object obj : list){
            if(obj instanceof Integer){
                result.add((Integer) obj);
            }
            else if(obj instanceof Double){
                result.add(((Double)obj).intValue());
            }
            else{
                throw new BadRequestException("Invalid number in array");
            }
        }
        return result;
    }
}
