package fr.sg.kata.account.domain;

import fr.sg.kata.account.AccountBalance;
import fr.sg.kata.account.OperationStatement;
import fr.sg.kata.account.utils.OperationType;

import java.time.LocalDateTime;

public class Operation {

    private final OperationType operationType;
    private final LocalDateTime timestamp;
    private final Amount amount;

    private Operation(final OperationType operationType, final LocalDateTime timestamp, final Amount amount) {
        this.operationType = operationType;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public static Operation of(final OperationType operationType, final LocalDateTime timestamp, final Amount amount) {
        return new Operation(operationType, timestamp, amount);
    }

    public OperationStatement computeStatement(final AccountBalance accountBalance) {
        final AccountBalance nextAccountBalance = operationType.execute(accountBalance, amount);
        return OperationStatement.of(operationType, timestamp, amount, nextAccountBalance);
    }
}
