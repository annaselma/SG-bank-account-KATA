package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import fr.sg.kata.account.domain.Operation;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

import static fr.sg.kata.account.utils.OperationType.DEPOSIT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperationTest {

    @Test
    public void computeOperationStatement() {
        final AccountBalance accountBalance = mock(AccountBalance.class);
        final AccountBalance expectedAccountBalance = AccountBalance.of(1L);

        final Amount amount = mock(Amount.class);
        final LocalDateTime timestamp = LocalDateTime.now();
        final Operation operation = Operation.of(DEPOSIT, timestamp, amount);

        when(accountBalance.add(amount)).thenReturn(expectedAccountBalance);
        final OperationStatement statement = operation.computeStatement(accountBalance);

        Assertions.assertThat(statement).isEqualTo(OperationStatement.of(
                DEPOSIT, timestamp, amount, expectedAccountBalance
        ));
    }
}