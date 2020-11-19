package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;

import java.util.Objects;

public class AccountBalance {

    private final long cents;

    private AccountBalance(final long cents) {
        this.cents = cents;
    }

    static AccountBalance empty() {
        return new AccountBalance(0L);
    }

    static AccountBalance of(final long cents) {
        return new AccountBalance(cents);
    }

    public AccountBalance add(final Amount amount) {
        return new AccountBalance(amount.addTo(cents));
    }

    public AccountBalance subtract(final Amount amount) {
        return new AccountBalance(amount.subtractFrom(cents));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AccountBalance accountBalance = (AccountBalance) o;
        return cents == accountBalance.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    @Override
    public String toString() {
        return "Balance{" +
                "cents=" + cents +
                '}';
    }
}
