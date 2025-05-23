# ✅ DELI-cious POS — Project Task Tracker

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
- [x] Add order summary & confirmation
- [x] Extract reusable prompt utilities
- [x] Clean CLI output with `ConsolePrinter`

---

## 🚧 Testing & Polish

- [ ] Add JUnit tests:
  - [ ] `getPrice()` methods (sandwich, drink, chips)
  - [ ] `ReceiptManager`
  - [ ] `Order`
- [ ] Add CLI usability edge cases
- [ ] Final code cleanup and consistent Javadoc

---

## 🚧 Bonus Features

- [x] Add Signature Sandwiches
- [x] Allow topping modifications (add/remove on any sub)
- [x] Enable receipt lookup by receipt number
- [ ] Add combo deals, discounts, nutrition facts

---

## 📁 Current Project Structure

```text
sandwich-shop-cli/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── app/                 # Main launcher
│   │   │   └── builders/            # Builders for Sandwich, Drink, Chip, Signature
│   │   │   └── data/                # Signature sandwich data source
│   │   │   └── interfaces/          # MenuItem, Printable
│   │   │   └── models/              # Core models
│   │   │   │   └── enums/           # All enum types (Topping, Sizes, etc.)
│   │   │   └── persistence/         # ReceiptManager & summary output
│   │   │   └── ui/                  # CLI controller (UserInterface)
│   │   │   └── utils/               # ToppingEditor, UserInputUtils, ConsolePrinter
│   │   └── resources/              # Saved receipts
├── docs/                           # UML diagrams and design notes
├── test/                           # (Upcoming) Unit tests
├── README.md
├── TODO.md
├── EXTRAS.md
├── pom.xml
└── .gitignore
```