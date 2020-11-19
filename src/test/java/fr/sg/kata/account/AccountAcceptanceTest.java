package fr.sg.kata.account;

import fr.sg.kata.account.domain.Account;
import fr.sg.kata.account.domain.Amount;
import org.junit.Test;

import java.time.LocalDateTime;

import static fr.sg.kata.account.utils.OperationType.DEPOSIT;
import static fr.sg.kata.account.utils.OperationType.WITHDRAWAL;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountAcceptanceTest {

    @Test
    public void depositAccount_balance() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(Amount.anAmounted(4L), () -> timestamp);

        final OperationStatement statement = account.deposit(Amount.anAmounted(2L));

        assertThat(statement).isEqualTo(
                OperationStatement.of(DEPOSIT, timestamp, Amount.anAmounted(2L), AccountBalance.of(6L))
        );
    }

    @Test
    public void withdrawal_substrAmount_from_AccountBalance() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(Amount.anAmounted(4L), () -> timestamp);

        final OperationStatement statement = account.withdraw(Amount.anAmounted(2L));

        assertThat(statement).isEqualTo(
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.anAmounted(2L), AccountBalance.of(2L))
        );
    }

    @Test
    public void should_subtract_the_withdrawal_amount_from_the_account_balance_and_have_a_negative_balance() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(Amount.anAmounted(5L), () -> timestamp);

        final OperationStatement statement = account.withdraw(Amount.anAmounted(7L));

        assertThat(statement).isEqualTo(
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.anAmounted(7L), AccountBalance.of(-2L))
        );
    }

    @Test
    public void get_History_operationFromAccount() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(Amount.anAmounted(3L), () -> timestamp);

        account.deposit(Amount.anAmounted(5L));
        account.deposit(Amount.anAmounted(7L));
        account.withdraw(Amount.anAmounted(5L));
        account.withdraw(Amount.anAmounted(3L));

        assertThat(account.getOperations()).isEqualTo(asList(
                OperationStatement.of(DEPOSIT, timestamp, Amount.anAmounted(3L), AccountBalance.of(3L)),
                OperationStatement.of(DEPOSIT, timestamp, Amount.anAmounted(5L), AccountBalance.of(8L)),
                OperationStatement.of(DEPOSIT, timestamp, Amount.anAmounted(7L), AccountBalance.of(15L)),
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.anAmounted(5L), AccountBalance.of(10L)),
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.anAmounted(3L), AccountBalance.of(7L))
        ));
    }
}
