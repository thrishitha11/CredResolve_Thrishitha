
package com.example.expense;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private Map<String, Map<String, Double>> balances = new HashMap<>();

    @PostMapping
    public String addExpense(@RequestBody ExpenseRequest request) {
        double share = request.amount / request.users.size();

        for (String user : request.users) {
            if (!user.equals(request.paidBy)) {
                balances
                    .computeIfAbsent(user, k -> new HashMap<>())
                    .merge(request.paidBy, share, Double::sum);
            }
        }
        return "Expense added successfully";
    }

    @GetMapping("/balances")
    public Map<String, Map<String, Double>> getBalances() {
        return balances;
    }
}
