# ✅ Sandwich Shop CLI – Project Task Tracker

## ✅ Completed

- [x] Created GitHub repository and initialized project structure
- [x] Added `.gitignore` file
- [x] Set up `README.md` with project summary, structure, and tech stack
- [x] Created `/docs` folder
- [x] Added `class-diagram.svg` to documentation
- [x] Defined enums:
    - [x] `BreadType`
    - [x] `SandwichSize`
    - [x] `DrinkSize`
    - [x] `Topping`
    - [x] `ToppingType`
- [x] Defined project package structure (`com.delicious`)
- [x] Organized `model/enums` package and cleaned up class locations
- [x] Created UML class diagram in Mermaid and SVG format
- [x] Implemented core models and builders
- [x] Implemented receipt manager with printable capture
- [x] Added full CLI with input validation and user prompts
- [x] Added Signature Sandwich support (with customization)
- [x] Created topping editor for add/remove
- [x] Generated Javadoc using Maven
- [x] **GUI Version**
-
    - [x] Create a desktop app using Swing or JavaFX

---

## 🚧 Core Models & Interfaces

- [x] Implement `Sandwich` class
- [x] Implement `Drink` class
- [x] Implement `Chip` class
- [x] Implement `Order` class
- [x] Create interfaces: `MenuItem`, `Printable`
- [x] Add builder classes for each menu item

---

## 🚧 Receipt and Persistence

- [x] Create `ReceiptManager` class for file I/O
- [x] Implement timestamped `.txt` receipts
- [x] Capture formatted order summaries with `SummaryCapture`

---

## 🚧 User Interface

- [x] CLI-based navigation (`UserInterface`)
- [x] Modular CLI sections: Add sandwich, drink, chip, signature
- [x] Add order summary and confirmation
- [x] Extract reusable prompt utilities
- [x] Clean CLI output with `ConsolePrinter`

---

## 🚧 Testing & Polish

- [x] Add JUnit tests:
    - [x] `getPrice()` methods (sandwich, drink, chips)
    - [x] `ReceiptManager`
    - [x] `Order`
    - [x] AVG test case above 85% coverage
- [x] Add CLI usability edge cases
- [x] Final code cleanup and consistent Javadoc

---

## 🚧 Bonus Features

- [x] Add Signature Sandwiches
- [x] Allow topping modifications (add/remove on any sub)
- [x] Enable receipt lookup by receipt number

---

## 📁 Current Project Structure

```text
sandwich-shop-cli/
├── docs/                          # UML diagrams, design notes
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── app/               # Entry points
│   │   │   ├── data/              # Data loaders and static content
│   │   │   ├── gui/               # JavaFX GUI layer
│   │   │   │   ├── helpers/       # Shared dialog/utility helpers
│   │   │   │   ├── screens/       # JavaFX scenes
│   │   │   │   └── util/          # Layout widgets (e.g., styled VBox)
│   │   │   ├── interfaces/        # Shared contracts
│   │   │   ├── models/            # Domain models (Sandwich, Drink, Chip)
│   │   │   │   ├── enums/         # Enum types (flavors, sizes, toppings)
│   │   │   ├── builders/          # Sandwich/Drink/Chip builders (used in CLI)
│   │   │   ├── persistence/       # File I/O and order storage
│   │   │   ├── ui/                # CLI interaction handlers
│   │   │   └── utils/             # CLI-only utilities
│   │   └── resources/             # Non-Java resources
│   │       ├── receipt/           # Saved receipts
│   │       └── styles/            # CSS for JavaFX
│   └── test/                      # JUnit tests
│       ├── builders/
│       ├── data/
│       ├── models/
│       ├── persistence/
│       ├── ui/
│       └── utils/
├── .gitignore
├── README.md
├── TODO.md
├── EXTRAS.md
├── pom.xml
```