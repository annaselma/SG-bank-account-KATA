package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import org.junit.Test;

import static fr.sg.kata.account.utils.OperationType.DEPOSIT;
import static fr.sg.kata.account.utils.OperationType.WITHDRAWAL;
import static org.mockito.Mockito.*;

public class OperationTypeTest {

    /** Test d'/un depot d'operation **/
    @Test
    public void deposit_operation_executed() {
        final AccountBalance accountBalance = mock(AccountBalance.class);
        final Amount amount = mock(Amount.class);

        DEPOSIT.execute(accountBalance, amount);

        verify(accountBalance).add(amount);
        verify(accountBalance, times(0)).subtract(amount);
    }
    /** Test d'/ un retrait d'operation **/
    @Test
    public void withdrawal_operation_executed() {
        final AccountBalance accountBalance = mock(AccountBalance.class);
        final Amount amount = mock(Amount.class);

        WITHDRAWAL.execute(accountBalance, amount);

        verify(accountBalance).subtract(amount);
        verify(accountBalance, times(0)).add(amount);
    }
}