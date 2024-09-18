package br.com.fiap.localweb.service;

import br.com.fiap.localweb.dto.UserPreferencesDto;
import br.com.fiap.localweb.model.UserAccount;
import br.com.fiap.localweb.model.UserPreferences;
import br.com.fiap.localweb.repository.UserAccountRepository;
import br.com.fiap.localweb.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferencesService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    public UserPreferences saveUserPreferences(Long userId, UserPreferencesDto preferencesDto) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserPreferences preferences = new UserPreferences();
        preferences.setTheme(preferencesDto.getTheme());
        preferences.setColor(preferencesDto.getColor());
        preferences.setCategory(preferencesDto.getCategory());
        preferences.setUser(userAccount);


        if (userAccount.getPreferences() != null) {
            preferences.setId(userAccount.getPreferences().getId());
        }

        userAccount.setPreferences(preferences);
        userAccountRepository.save(userAccount);

        return preferences;
    }

    public UserPreferences getUserPreferences(Long userId) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userAccount.getPreferences() == null) {
            throw new IllegalArgumentException("User has no preferences set");
        }

        return userAccount.getPreferences();
    }
}
