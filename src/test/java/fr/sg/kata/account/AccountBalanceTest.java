package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountBalanceTest {

    @Test
    public void initializeEmpty_balance() {
        final AccountBalance accountBalance = AccountBalance.empty();

        assertThat(accountBalance).isEqualTo(AccountBalance.of(0L));
    }

    @Test
    public void addAmount_to_balance() {
        final AccountBalance accountBalance = AccountBalance.of(2L);
        final Amount amount = Amount.anAmounted(3L);

        final AccountBalance result = accountBalance.add(amount);

        assertThat(result).isEqualTo(AccountBalance.of(5L));
    }

    @Test
    public void Amount_substrFrom_balance() {
        final AccountBalance accountBalance = AccountBalance.of(5L);
        final Amount amount = Amount.anAmounted(3L);

        final AccountBalance result = accountBalance.subtract(amount);

        assertThat(result).isEqualTo(AccountBalance.of(2L));
    }
}