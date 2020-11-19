package fr.sg.kata.account.domain;

import fr.sg.kata.account.*;
import fr.sg.kata.account.utils.OperationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import static fr.sg.kata.account.utils.OperationType.DEPOSIT;
import static fr.sg.kata.account.utils.OperationType.WITHDRAWAL;

public class Account {

    /** Depot d'/un compte ***
     Retirer de l'argent
        Gérer les opérations ***/

    private final Supplier<LocalDateTime> timestampSupplier;
    private AccountStatement accountStatement;

    private Account(final Supplier<LocalDateTime> timestampSupplier, final AccountStatement accountStatement) {
        this.timestampSupplier = timestampSupplier;
        this.accountStatement = accountStatement;
    }

    public static Account of(final Amount initialAmount, final Supplier<LocalDateTime> timestampSupplier) {
        final Account account = new Account(timestampSupplier, AccountStatement.empty());
        account.deposit(initialAmount);

        return account;
    }


   public OperationStatement deposit(final Amount amount) {
        return handleOperation(DEPOSIT, amount);
    }

    public OperationStatement withdraw(final Amount amount) {
        return handleOperation(WITHDRAWAL, amount);
    }

    private OperationStatement handleOperation(final OperationType operationType, final Amount amount) {
        final Operation operation = Operation.of(operationType, timestampSupplier.get(), amount);
        accountStatement = accountStatement.update(operation);

        return accountStatement.lastStatement();
    }

    public List<OperationStatement> getOperations() {
        return accountStatement.getOperations();
    }
}
