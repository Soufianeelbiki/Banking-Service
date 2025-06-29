# Banking Service

A small Java app to deposit, withdraw, and print your bank statement. It is fast and safe.

## Why it’s fast

- **Pre‑set list size:** The list of transactions starts with space for many items, so it doesn’t keep growing.
- **Reverse loop:** Printing goes from newest to oldest without making a copy of the list.
- **StringBuilder:** Builds each line in one go to cut down on extra work.

## Why it’s safe

- **Check inputs:** Deposits and withdrawals must be greater than zero.
- **No overflow:** Before adding money, it checks that the total won’t go above the maximum integer.
- **No overdraft:** You can’t withdraw more than your balance.
- **Immutable records:** Each transaction can’t be changed once it’s created.
- **Thread‑safe:** A lock keeps balance updates and list changes from clashing.

## How to run

```bash
# Clone the repository
git clone
# Navigate into the project directory
cd Banking-Service
```

- **Compile :** mvn clean compile
- **Run Demo :** : mvn exec:java
- **Run Test :** : mvn test
