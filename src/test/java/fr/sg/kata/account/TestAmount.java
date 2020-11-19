package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAmount {

    @Test(expected = IllegalArgumentException.class)
    public void anAmount_of_zero() {
        Amount.anAmounted(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeAmount() {
        Amount.anAmounted(-1L);
    }

    @Test
    public void addLongToAmount() {
        final Amount firstAmount = Amount.anAmounted(4L);

        final long result = firstAmount.addTo(2L);

        assertThat(result).isEqualTo(6L);
    }

    @Test
    public void subtract_a_long_from_an_amount() {
        final Amount firstAmount = Amount.anAmounted(2L);

        final long result = firstAmount.subtractFrom(4L);

        assertThat(result).isEqualTo(2L);
    }
}