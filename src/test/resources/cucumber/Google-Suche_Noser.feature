@Regression_Noser
Feature: Noser bei Google finden
  Der Google Benutzer findet Noser leicht

  @Regression_Noser_Google-Suche
  Scenario: Google zeigt Noser Engineering Anzeige an
    Given Die Google-Suchmaske ist sichtbar
    When Der Google-Nutzer gibt 'Noser Engineering AG' ein
    Then Der Google-Nutzer bekommt die Noser Anzeige mit dem Text 'Noser Engineering AG - Fit für die Zukunft?' angezeigt 
