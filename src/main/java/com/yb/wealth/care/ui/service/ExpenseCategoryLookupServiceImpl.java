package com.yb.wealth.care.ui.service;

import com.yb.wealth.care.ui.exception.ExceptionHandler;
import com.yb.wealth.care.ui.mapper.ExpenseCategoryMapper;
import com.yb.wealth.care.ui.repository.ExpenseCategoryRepository;
import com.yb.wealth.care.ui.repository.entity.ExpenseCategory;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryBaseDto;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryDto;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class ExpenseCategoryLookupServiceImpl implements ExpenseCategoryLookupService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Override
    public Uni<List<ExpenseCategoryDto>> getExpenseCategories() {
        log.info("getExpenseCategories");
        return expenseCategoryRepository.getAllCategories()
                .onItem()
                .transform(expenseCategoryMapper::toDtoList);
    }

    @Override
    public Uni<ExpenseCategoryDto> getExpenseCategoryById(final UUID expenseCategoryId) {
        return expenseCategoryRepository.findById(expenseCategoryId)
                .flatMap(expenseCategory -> Uni.createFrom().item(expenseCategoryMapper.toDto(expenseCategory)))
                .onFailure()
                .transform(ExceptionHandler::handle);
    }

    @Override
    @WithTransaction
    public Uni<Response> createExpenseCategory(ExpenseCategoryBaseDto expenseCategoryBaseDto) {
        return expenseCategoryRepository.persist(expenseCategoryMapper.toEntity(expenseCategoryBaseDto))
                .map(expenseCategoryMapper::toDto)
                .onItem()
                .transform(savedCategory -> {
                    log.info("Created new expense category: {}", savedCategory);
                    return Response.status(Response.Status.CREATED).entity(savedCategory).build();
                })
                .onFailure()
                .transform(ExceptionHandler::handle);
    }

    @Override
    @WithTransaction
    public Uni<Response> updateExpenseCategory(UUID expenseCategoryId, ExpenseCategoryBaseDto expenseCategoryBaseDto) {
        return expenseCategoryRepository.findById(expenseCategoryId)
                .map(expenseCategory -> this.setCategoryWithNewValues(expenseCategory, expenseCategoryBaseDto))
                .flatMap(expenseCategoryRepository::persist)
                .flatMap(expenseCategory -> Uni.createFrom().item(expenseCategoryMapper.toDto(expenseCategory)))
                .flatMap(expenseCategoryDto -> Uni.createFrom().item(Response.status(Response.Status.OK).entity(expenseCategoryDto).build()))
                .onFailure()
                .transform(ExceptionHandler::handle);
    }

    private ExpenseCategory setCategoryWithNewValues(ExpenseCategory expenseCategory,
                                          final ExpenseCategoryBaseDto expenseCategoryBaseDto) {
        if (StringUtils.isNotBlank(expenseCategoryBaseDto.getIcon())) {
            expenseCategory.setIcon(expenseCategoryBaseDto.getIcon());
        }
        if (StringUtils.isNotBlank(expenseCategoryBaseDto.getName())) {
            expenseCategory.setCategoryName(expenseCategoryBaseDto.getName());
        }

        return expenseCategory;
    }

    @Override
    public Uni<Response> deleteExpenseCategory(UUID expenseCategoryId) {
        return expenseCategoryRepository.deleteById(expenseCategoryId)
                .flatMap(id -> Uni.createFrom().item(Response.status(Response.Status.OK).build()))
                .onFailure()
                .transform(ExceptionHandler::handle);
    }

}
