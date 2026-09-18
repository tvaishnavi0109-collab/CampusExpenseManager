# Test Cases

| ID | Test | Input | Expected Result |
|---|---|---|---|
| TC01 | Add valid expense | 2026-09-18, Food, 120, Dinner | Expense is added |
| TC02 | Invalid date | 18-09-2026 | Error message |
| TC03 | Invalid amount | -500 | Error message |
| TC04 | Empty category | blank | Error message |
| TC05 | Delete existing ID | valid ID | Expense removed |
| TC06 | Delete invalid ID | 9999 | Error message |
| TC07 | Set budget | 2026-09, 5000 | Budget stored |
| TC08 | Monthly report | existing September data | Total and category totals displayed |
| TC09 | Persistence | save, restart | Records reload from CSV |
| TC10 | Automated report logic | included test class | `ALL TESTS PASSED` |
