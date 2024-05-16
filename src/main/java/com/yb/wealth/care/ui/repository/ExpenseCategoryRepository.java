package com.yb.wealth.care.ui.repository;

import com.yb.wealth.care.ui.repository.entity.ExpenseCategory;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.With;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ExpenseCategoryRepository implements PanacheRepository<ExpenseCategory> {

    @WithTransaction
    public Uni<List<ExpenseCategory>> getAllCategories(){
        return findAll().list();
    }

    @WithTransaction
    public Uni<ExpenseCategory> findById(final UUID expenseCategoryId) {
        return find("id = :expenseCategoryId",
                Parameters.with("expenseCategoryId", expenseCategoryId).map())
                .singleResult();
    }

    @WithTransaction
    public Uni<Long> deleteById(final UUID expenseCategoryId) {
        return delete("id = :expenseCategoryId",
                Parameters.with("expenseCategoryId", expenseCategoryId).map());
    }
}
