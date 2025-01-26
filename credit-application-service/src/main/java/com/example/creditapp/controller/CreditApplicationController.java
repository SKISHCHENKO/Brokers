package com.example.creditapp.controller;

import com.example.creditapp.dto.CreditApplicationRequest;
import com.example.creditapp.dto.CreditApplicationResponse;
import com.example.creditapp.model.CreditApplication;
import com.example.creditapp.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Изменено на @Controller, чтобы обрабатывать отображение страницы
@RequestMapping("/api/credit-applications")
@RequiredArgsConstructor
public class CreditApplicationController {
    private final CreditApplicationService service;

    @PostMapping
    public ResponseEntity<CreditApplicationResponse> submit(@RequestBody CreditApplicationRequest request) {
        Long applicationId = service.submitApplication(request);

        CreditApplicationResponse response = new CreditApplicationResponse();
        response.setApplicationId(applicationId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditApplication> getStatus(@PathVariable Long id) {
        return service.getApplication(id)
                .map(ResponseEntity::ok) // Если значение присутствует, возвращаем 200 OK
                .orElse(ResponseEntity.notFound().build()); // Если значение отсутствует, возвращаем 404 Not Found
    }

}
