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
- [x] Created initial UML in Mermaid format
- [x] Set up `feature/init-readme` and `feature/docs-class-diagram` branches
- [x] Documented and committed enum creation under `feature/model-enums`

---

## ✅ Core Models & Interfaces

- [x] Implement `Sandwich` class
  - [x] Store size, bread, toppings, extras, toasted flag
  - [x] Implement `getPrice()` logic using `BigDecimal`
- [x] Implement `Drink` class
  - [x] Store drink size and flavor
  - [x] Implement `getPrice()` logic
- [x] Implement `Chips` class
  - [x] Store a chip type
  - [x] Implement `getPrice()` logic
- [x] Implement `Order` class
  - [x] List of `MenuItem` items (sandwiches, drinks, chips)
  - [x] `addItem()`, `getPrice()`, `printSummary()` methods
- [x] Implement `MenuItem` interface
  - [x] Ensure `Sandwich`, `Drink`, and `Chips` all implement it
- [x] Implement `Printable` interface for UI summaries

---

## ✅ Receipt and Persistence

- [x] Create `ReceiptManager` class
  - [x] Save orders to `/receipts/yyyyMMdd-hhmmss.txt`
  - [x] Auto-create folder if not present
  - [x] Capture `Printable` output using `SummaryCapture`

---

## ✅ User Interface

- [x] Create `UserInterface` class with `homeScreen()` and `orderScreen()`
- [x] Create `Main` entry point to launch `UserInterface`
- [x] Implement CLI-based interaction:
  - [x] Add Sandwich
  - [x] Add Drink
  - [x] Add Chips
  - [x] Checkout (display + confirm receipt save)
  - [x] Cancel order
- [x] Add input validation and generic prompt utilities
- [x] Extract `SandwichBuilder`, `DrinkBuilder`, and `ChipBuilder`
- [x] Add `ConsolePrinter` for clean CLI output

---

## 🚧 Bonus Features (Optional)

- [ ] Add support for Signature Sandwiches (BLT, Philly, etc.)
  - [ ] Inherit from `Sandwich` class
  - [ ] Allow modification (remove/add toppings)

---

## 🚧 Testing & Polish

- [ ] Add unit tests for:
  - [ ] `getPrice()` methods
  - [ ] `ReceiptManager`
  - [ ] `Order total`
- [ ] Add helpful error messages in CLI
- [ ] Confirm `BigDecimal` prices round properly
- [ ] Clean up and refactor duplicate logic (if any)
- [ ] Final code cleanup and Javadoc

---

## 🧹 Post-Project

- [ ] Record demo for class presentation
- [ ] Prepare to walk through UML and interesting code piece
- [ ] Tag the final release on GitHub  
