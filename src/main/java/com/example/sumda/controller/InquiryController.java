package com.example.sumda.controller;

import com.example.sumda.dto.inquiry.request.InquiryRequestDto;
import com.example.sumda.service.InquiryService;
import com.example.sumda.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping
    public ResponseEntity<?> createInquiry(@Valid @RequestBody InquiryRequestDto inquiryRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder helperText = new StringBuilder("제목, 내용, 이메일을 입력해주세요.");
            bindingResult.getFieldErrors().forEach(error -> {
                switch (error.getField()) {
                    case "title":
                        helperText.setLength(0);
                        helperText.append("제목을 입력해주세요.");
                        break;
                    case "content":
                        helperText.setLength(0);
                        helperText.append("내용을 입력해주세요.");
                        break;
                    case "email":
                        helperText.setLength(0);
                        helperText.append("올바른 이메일을 입력해주세요.");
                        break;
                }
            });

            return ResponseUtils.createResponse(HttpStatus.BAD_REQUEST, helperText.toString());
        }

        inquiryService.createInquiry(inquiryRequestDTO);
        return ResponseUtils.createResponse(HttpStatus.CREATED, "문의가 성공적으로 등록되었습니다.");
    }

}
