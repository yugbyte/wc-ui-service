package com.yb.wealth.care.ui.resource;

import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryBaseDto;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryDto;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ExpenseCategoryLookupResource {
    Uni<List<ExpenseCategoryDto>> getExpenseCategories();
    Uni<ExpenseCategoryDto> getExpenseCategoryById(final String id);
    Uni<Response> createExpenseCategory(@Valid final ExpenseCategoryBaseDto expenseCategoryBaseDto);
    Uni<Response> updateExpenseCategory(final String id, @Valid final ExpenseCategoryBaseDto expenseCategoryBaseDto);
    Uni<Response> deleteExpenseCategory(final String id);
}
