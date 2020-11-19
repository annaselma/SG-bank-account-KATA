package fr.sg.kata.account.domain;

import java.util.Objects;

public class Amount {

    private final long anAmount;

    private Amount(final long anAmount) {
        this.anAmount = anAmount;
    }

    public static Amount anAmounted(final long anAmount) {
        if (anAmount <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        return new Amount(anAmount);
    }

    public long addTo(final long amount) {
        return anAmount + amount;
    }

    public long subtractFrom(final long amount) {
        return amount - anAmount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Amount amount = (Amount) o;
        return anAmount == amount.anAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anAmount);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "cents=" + anAmount +
                '}';
    }
}
