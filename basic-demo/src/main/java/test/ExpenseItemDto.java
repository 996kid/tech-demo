package test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ExpenseItemDto {

    /** expense_item_id - 费用项id*/
    private Integer expenseItemId;

    /** expense_item_name - 费用项名称*/
    private String expenseItemName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseItemDto that = (ExpenseItemDto) o;
        return expenseItemId.equals(that.expenseItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseItemId);
    }
}