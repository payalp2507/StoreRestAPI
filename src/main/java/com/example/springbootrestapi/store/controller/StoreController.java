package com.example.springbootrestapi.store.controller;

import com.example.springbootrestapi.common.V0.ResponseVO;
import com.example.springbootrestapi.dto.StoreDTO;
import com.example.springbootrestapi.store.service.StoreDetailService;
import com.example.springbootrestapi.store.service.StoreValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreDetailService storeDetailService;
    private  StoreValidator storeValidator;

    @Autowired
    public StoreController(StoreDetailService storeDetailService, StoreValidator storeValidator) {
        this.storeDetailService = storeDetailService;
        this.storeValidator = storeValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(storeValidator);
    }

    @PostMapping
    public ResponseEntity<ResponseVO> createStore(@Valid @RequestBody StoreDTO storeDTO, BindingResult bindingResult) {
        ResponseVO responseVO = new ResponseVO();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                responseVO.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
            }
            responseVO.setErrorMessage("Validation Fields");
            return ResponseEntity.badRequest().body(responseVO);
        }
        storeDetailService.create(storeDTO);
        responseVO.setData(storeDTO);
        responseVO.setErrorMessage("Store created successfully");
        return ResponseEntity.ok(responseVO);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<ResponseVO> getStoreByID(@PathVariable Long storeId) {
        ResponseVO responseVO = new ResponseVO();

        responseVO.setData(storeDetailService.getStoreById(storeId));
        responseVO.setSuccessMessage("Store retrieved successfully");
        return ResponseEntity.ok(responseVO);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseVO> getStoreByUserId(@PathVariable String userId) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(storeDetailService.getStoreByUserId(userId));
        responseVO.setSuccessMessage("Store retrieved successfully");
        return ResponseEntity.ok(responseVO);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<ResponseVO> updateStore(@Valid @RequestBody StoreDTO storeDTO, @PathVariable Long storeId, BindingResult bindingResult) {

        ResponseVO responseVO = new ResponseVO();
        storeValidator.validate(storeDTO, bindingResult);

        System.out.println("Inside updateStore method"); // Add a debug statement
        storeValidator.validate(storeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                responseVO.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
            }
            responseVO.setErrorMessage("Validation Fields");
            return ResponseEntity.badRequest().body(responseVO);
        }

        storeDetailService.updateStore(storeId, storeDTO);
        responseVO.setSuccessMessage("Store updated successfully");
        // responseVO.setData(storeDTO); // Optionally, you can include data in the response
        return ResponseEntity.ok(responseVO);
    }
}