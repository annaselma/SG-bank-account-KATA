package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import fr.sg.kata.account.domain.Operation;
import org.junit.Test;

import java.time.LocalDateTime;

import static fr.sg.kata.account.utils.OperationType.DEPOSIT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestOperationStatement {

    @Test
    public void calculateNextOperation() {
        final Operation operation = mock(Operation.class);
        final AccountBalance accountBalance = mock(AccountBalance.class);
        final OperationStatement statement = OperationStatement.of(DEPOSIT, LocalDateTime.now(), mock(Amount.class), accountBalance);

        statement.calculateNext(operation);

        verify(operation).computeStatement(accountBalance);
    }
}