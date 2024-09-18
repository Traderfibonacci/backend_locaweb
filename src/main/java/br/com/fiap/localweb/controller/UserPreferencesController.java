package br.com.fiap.localweb.controller;

import br.com.fiap.localweb.dto.UserPreferencesDto;
import br.com.fiap.localweb.model.UserPreferences;
import br.com.fiap.localweb.service.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService userPreferencesService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserPreferences> saveUserPreferences(@PathVariable Long userId, @RequestBody UserPreferencesDto preferencesDto) {
        try {
            UserPreferences preferences = userPreferencesService.saveUserPreferences(userId, preferencesDto);
            return ResponseEntity.ok(preferences);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPreferences> getUserPreferences(@PathVariable Long userId) {
        try {
            UserPreferences preferences = userPreferencesService.getUserPreferences(userId);
            return ResponseEntity.ok(preferences);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
