package fr.sg.kata.account.utils;

import fr.sg.kata.account.AccountBalance;
import fr.sg.kata.account.domain.Amount;

import java.util.function.BiFunction;

public enum OperationType {

    DEPOSIT(AccountBalance::add),
    WITHDRAWAL(AccountBalance::subtract);

    public BiFunction<AccountBalance, Amount, AccountBalance> operation;

    OperationType(final BiFunction<AccountBalance, Amount, AccountBalance> operation) {
        this.operation = operation;
    }

    public AccountBalance execute(final AccountBalance accountBalance, final Amount amount) {
        return operation.apply(accountBalance, amount);
    }
}
