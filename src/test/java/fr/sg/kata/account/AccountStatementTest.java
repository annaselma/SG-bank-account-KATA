package fr.sg.kata.account;

import fr.sg.kata.account.domain.Amount;
import fr.sg.kata.account.domain.Operation;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static fr.sg.kata.account.utils.OperationType.DEPOSIT;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountStatementTest {

    @Test
    public void initializeEmpty_account_statements() {
        final AccountStatement accountStatement = AccountStatement.empty();

        assertThat(accountStatement).isEqualTo(AccountStatement.of(emptyList()));
    }

    @Test
    public void No_Operation_For_EmptyAccount() {
        final AccountStatement accountStatement = AccountStatement.empty();

        final OperationStatement lastStatement = accountStatement.lastStatement();

        assertThat(lastStatement).isNull();
    }

    @Test
    public void update_empty_accountStatement_with_operation() {
        final Operation operation = mock(Operation.class);
        final OperationStatement expectedOperationStatement = mock(OperationStatement.class);
        final AccountStatement accountStatement = AccountStatement.empty();

        when(operation.computeStatement(any())).thenReturn(expectedOperationStatement);
        final AccountStatement updatedAccountStatement = accountStatement.update(operation);

        assertThat(updatedAccountStatement).isEqualTo(AccountStatement.of(singletonList(expectedOperationStatement)));
    }

    @Test
    public void update_accountStatement_with_operation() {
        final Operation operation = mock(Operation.class);
        final OperationStatement operationStatement = mock(OperationStatement.class);
        final OperationStatement expectedOperationStatement = mock(OperationStatement.class);
        final AccountStatement accountStatement = AccountStatement.of(singletonList(operationStatement));

        when(operationStatement.calculateNext(operation)).thenReturn(expectedOperationStatement);
        final AccountStatement updatedAccountStatement = accountStatement.update(operation);

        assertThat(updatedAccountStatement).isEqualTo(AccountStatement.of(asList(
                operationStatement,
                expectedOperationStatement
        )));
    }

    @Test
    public void get_The_latest_Operation() {
        final List<OperationStatement> operationStatements = asList(
                OperationStatement.of(DEPOSIT, LocalDateTime.now(), Amount.anAmounted(3L), AccountBalance.of(3L)),
                OperationStatement.of(DEPOSIT, LocalDateTime.now(), Amount.anAmounted(5L), AccountBalance.of(8L)),
                OperationStatement.of(DEPOSIT, LocalDateTime.now(), Amount.anAmounted(7L), AccountBalance.of(15L))
        );
        final AccountStatement accountStatement = AccountStatement.of(operationStatements);

        final OperationStatement lastStatement = accountStatement.lastStatement();

        assertThat(lastStatement).isEqualTo(operationStatements.get(2));
    }



    @Test
    public void get_emptyList_of_operations_when_the_account_statement_is_empty() {
        final AccountStatement accountStatement = AccountStatement.empty();

        final List<OperationStatement> operationStatements = accountStatement.getOperations();

        assertThat(operationStatements).isEmpty();
    }

    @Test
    public void Retrieve_list_operation_of_account_statement() {
        final OperationStatement operationStatement = mock(OperationStatement.class);
        final AccountStatement accountStatement = AccountStatement.of(singletonList(operationStatement));

        final List<OperationStatement> operationStatements = accountStatement.getOperations();

        assertThat(operationStatements).contains(operationStatement);
    }

}