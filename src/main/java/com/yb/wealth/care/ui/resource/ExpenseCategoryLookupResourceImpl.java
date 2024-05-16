package com.yb.wealth.care.ui.resource;

import com.yb.wealth.care.ui.exception.BadRequestException;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryBaseDto;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryDto;
import com.yb.wealth.care.ui.service.ExpenseCategoryLookupService;
import com.yb.wealth.care.ui.util.CommonUtil;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
@Path("/lookup/budgets")
public class ExpenseCategoryLookupResourceImpl implements ExpenseCategoryLookupResource {
    private final ExpenseCategoryLookupService expenseCategoryLookupService;

    @GET
    @Path("/categories")
    @Override
    public Multi<ExpenseCategoryDto> getExpenseCategories() {
        return expenseCategoryLookupService.getExpenseCategories();
    }

    @Path("/categories/{categoryId}")
    @GET
    @Override
    public Uni<ExpenseCategoryDto> getExpenseCategoryById(final String categoryId) {
        final UUID id = CommonUtil.convertToUUID(categoryId)
                .orElseThrow(() -> new BadRequestException("Invalid Id"));
        return expenseCategoryLookupService.getExpenseCategoryById(id);
    }

    @Path("/categories")
    @POST
    @Override
    public Uni<Response> createExpenseCategory(@Valid final ExpenseCategoryBaseDto expenseCategoryBaseDto) {
        return expenseCategoryLookupService.createExpenseCategory(expenseCategoryBaseDto);
    }

    @Path("/categories/{categoryId}")
    @PUT
    @Override
    public Uni<Response> updateExpenseCategory(final String categoryId, @Valid final ExpenseCategoryBaseDto expenseCategoryBaseDto) {
        final UUID id = CommonUtil.convertToUUID(categoryId)
                .orElseThrow(() -> new BadRequestException("Invalid Id"));
        return expenseCategoryLookupService.updateExpenseCategory(id, expenseCategoryBaseDto);
    }

    @Path("/categories/{categoryId}")
    @DELETE
    @Override
    public Uni<Response> deleteExpenseCategory(final String categoryId) {
        final UUID id = CommonUtil.convertToUUID(categoryId)
                .orElseThrow(() -> new BadRequestException("Invalid Id"));
        return expenseCategoryLookupService.deleteExpenseCategory(id);
    }
}
