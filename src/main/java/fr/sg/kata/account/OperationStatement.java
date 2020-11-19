package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import fr.sg.kata.account.domain.Operation;
import fr.sg.kata.account.utils.OperationType;

import java.time.LocalDateTime;
import java.util.Objects;

public class OperationStatement {

    private final OperationType operationType;
    private final LocalDateTime timestamp;
    private final Amount amount;
    private final AccountBalance accountBalance;

    private OperationStatement(final OperationType operationType, final LocalDateTime timestamp, final Amount amount, final AccountBalance accountBalance) {
        this.operationType = operationType;
        this.timestamp = timestamp;
        this.amount = amount;
        this.accountBalance = accountBalance;
    }

    public static OperationStatement of(final OperationType operationType, final LocalDateTime timestamp, final Amount amount, final AccountBalance accountBalance) {
        return new OperationStatement(operationType, timestamp, amount, accountBalance);
    }

    OperationStatement calculateNext(final Operation operation) {
        return operation.computeStatement(accountBalance);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OperationStatement that = (OperationStatement) o;
        return operationType == that.operationType &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(accountBalance, that.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, timestamp, amount, accountBalance);
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
                "operationType=" + operationType +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", balance=" + accountBalance +
                '}';
    }
}
