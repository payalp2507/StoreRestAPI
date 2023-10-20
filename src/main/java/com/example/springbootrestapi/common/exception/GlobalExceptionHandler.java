package com.example.springbootrestapi.common.exception;

import com.example.springbootrestapi.common.V0.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ResponseVO> handleStoreNotFoundException(ItemNotFoundException ex) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(responseVO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseVO> handleGenericException(Exception ex) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setErrorMessage("Something went wrong" + ex.getMessage());
        return ResponseEntity.badRequest().body(responseVO);
    }
}
