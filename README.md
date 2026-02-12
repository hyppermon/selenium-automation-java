# Selenium Automation – Java

Projekt automatyzacji testów UI oparty o **Java + Selenium WebDriver + JUnit 5**, zbudowany iteracyjnie w oparciu o Page Object Model (POM).  
Repozytorium rozwijane krok po kroku, z naciskiem na stabilność testów, czytelność kodu oraz realistyczne podejście do problemów spotykanych w automatyzacji UI.

Aplikacja testowana: https://www.saucedemo.com/

---

## 🎯 Cel projektu

- budowa czytelnego i stabilnego frameworka testów UI,
- demonstracja dobrych praktyk (POM, synchronizacja, brak `Thread.sleep`),
- pokazanie procesu iteracyjnego (smoke → login → cart → checkout),
- projekt przygotowany z myślą o prezentacji w CV / portfolio.

---

## 🧰 Stack technologiczny

- **Java 17**
- **Selenium WebDriver 4**
- **JUnit 5**
- **Maven**
- **WebDriverManager**
- **ChromeDriver**

---

## 📋 Zakres testów (aktualny)

### Smoke
- Otwarcie aplikacji i weryfikacja tytułu strony

### Login
- Brak username → walidacja
- Brak password → walidacja
- Zablokowany użytkownik (`locked_out_user`)
- Poprawne logowanie (`standard_user`)

### Cart
- Dodanie produktu do koszyka
- Usunięcie produktu z koszyka

### Checkout
- Poprawne przejście przez Checkout Step One → Summary
- Walidacja formularza Checkout (brak First Name)


> Testy zostały ustabilizowane poprzez:
> - synchronizację opartą o warunki biznesowe,
> - eliminację flaky behavior,
> - uruchamianie Chrome na czystym profilu testowym.

---

## 📁 Struktura projektu

```text
src/
└─ test/
   ├─ java/
   │  ├─ base/        # klasy bazowe testów
   │  ├─ config/      # konfiguracja drivera i aplikacji
   │  ├─ pages/       # Page Object Model (POM)
   │  ├─ tests/       # klasy testowe
   │  └─ testdata/    # dane testowe (np. użytkownicy)
   └─ resources/
      └─ config.properties
```
---
  
## 🧱 Podejście obiektowe

Projekt został zaprojektowany zgodnie z zasadami programowania obiektowego:

- **enkapsulacja** – logika interakcji z UI jest zamknięta w Page Objectach,
- **dziedziczenie** – klasy bazowe (`BasePage`, `BaseTest`) eliminują duplikację kodu,
- **polimorfizm** – wykorzystanie interfejsu `WebDriver` umożliwia łatwą rozbudowę o inne przeglądarki,
- **pojedyncza odpowiedzialność** – każda klasa odpowiada za jeden obszar (test, strona, konfiguracja).

Celem jest czytelny, skalowalny i łatwy w utrzymaniu kod testów.

---

## 🧩 Page Object Model

Projekt wykorzystuje klasyczny **Page Object Model**:
- brak asercji w Page Objectach,
- Page Objecty odpowiadają za synchronizację i interakcje,
- testy skupiają się wyłącznie na scenariuszu i asercjach.

Przykładowe Page Objecty:
- `LoginPage`
- `InventoryPage`
- `CartPage`
- `CheckoutStepOnePage`
- `CheckoutStepTwoPage`

---

## ▶️ Uruchamianie testów

### Wszystkie testy
```bash
mvn test
```

### Uruchomienie wybranej klasy testowej
```bash
mvn -Dtest=CartTests test
```
### Uruchomienie testów w trybie headless
```bash
mvn test -Dheadless=true
```

---

## ⚙️ Stabilność testów

W projekcie zastosowano m.in.:

- własne helpery `click()` i `waitVisible()`,
- synchronizację opartą o warunki biznesowe (np. oczekiwanie na pojawienie się lub znikanie elementów),
- eliminację stałych opóźnień (`Thread.sleep`),
- uruchamianie Chrome na czystym, tymczasowym profilu testowym w celu uniknięcia popupów przeglądarki (np. password manager).
