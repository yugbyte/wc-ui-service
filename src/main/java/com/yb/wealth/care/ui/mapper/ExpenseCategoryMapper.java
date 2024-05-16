package com.yb.wealth.care.ui.mapper;

import com.yb.wealth.care.ui.repository.entity.ExpenseCategory;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryBaseDto;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = QuarkusMappingConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseCategoryMapper {
    @Mapping(source = "categoryName", target = "name")
    ExpenseCategoryDto toDto(ExpenseCategory expenseCategory);
    @Mapping(source = "name", target = "categoryName")
    ExpenseCategory toEntity(ExpenseCategoryBaseDto expenseCategoryBaseDto);
}
