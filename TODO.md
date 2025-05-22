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

## 🚧 To Do

### 🧠 Core Models & Interfaces

- [ ] Implement `Sandwich` class
    - [ ] Store size, bread, toppings, extras, toasted flag
    - [ ] Implement `getPrice()` logic using `BigDecimal`
- [ ] Implement `Drink` class
    - [ ] Store drink size and flavor
    - [ ] Implement `getPrice()` logic
- [ ] Implement `Chips` class
    - [ ] Store chip type
    - [ ] Implement `getPrice()` logic
- [ ] Implement `Order` class
    - [ ] List of `MenuItem` items (sandwiches, drinks, chips)
    - [ ] `addItem()`, `getTotalPrice()`, `generateReceipt()` methods

- [ ] Implement `MenuItem` interface
    - [ ] Ensure `Sandwich`, `Drink`, and `Chips` all implement it

- [ ] Implement `Printable` interface (optional for UI summaries)

---

### 🧾 Receipt and Persistence

- [ ] Create `ReceiptManager` class
    - [ ] Save orders to `/receipts/yyyyMMdd-hhmmss.txt`
    - [ ] Auto-create folder if not present

---

### 💻 User Interface

- [ ] Create `Application` class with:
    - [ ] `mainMenu()`
    - [ ] `orderMenu()`
- [ ] Implement CLI-based interaction:
    - [ ] Add Sandwich
    - [ ] Add Drink
    - [ ] Add Chips
    - [ ] Checkout (display + confirm receipt save)
    - [ ] Cancel order
- [ ] Add input validation and user prompts

---

### 🎁 Bonus Features (Optional)

- [ ] Add support for Signature Sandwiches (BLT, Philly, etc.)
    - [ ] Inherit from `Sandwich` class
    - [ ] Allow modification (remove/add toppings)

---

### 🧪 Testing & Polish

- [ ] Add unit tests for:
    - [ ] `getPrice()` methods
    - [ ] `ReceiptManager`
    - [ ] `Order total`
- [ ] Add helpful error messages in CLI
- [ ] Confirm BigDecimal prices round properly
- [ ] Clean up and refactor duplicate logic (if any)
- [ ] Final code cleanup and JavaDoc

---

## 🧹 Post-Project

- [ ] Record demo for class presentation
- [ ] Prepare to walk through UML and interesting code piece
- [ ] Tag the final release on GitHub
