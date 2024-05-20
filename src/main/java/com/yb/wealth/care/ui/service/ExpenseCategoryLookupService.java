package com.yb.wealth.care.ui.service;

import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryBaseDto;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryDto;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

public interface ExpenseCategoryLookupService {
    Uni<List<ExpenseCategoryDto>> getExpenseCategories();
    Uni<ExpenseCategoryDto> getExpenseCategoryById(final UUID categoryId);
    Uni<Response> createExpenseCategory(final ExpenseCategoryBaseDto expenseCategoryBaseDto);
    Uni<Response> updateExpenseCategory(final UUID expenseCategoryId, final ExpenseCategoryBaseDto expenseCategoryBaseDto);
    Uni<Response> deleteExpenseCategory(final UUID expenseCategoryId);
}
