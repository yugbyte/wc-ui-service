package com.yb.wealth.care.ui.mapper;

import com.yb.wealth.care.ui.repository.entity.ExpenseCategory;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryBaseDto;
import com.yb.wealth.care.ui.resource.dto.ExpenseCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(config = QuarkusMappingConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseCategoryMapper {
    @Mapping(source = "categoryName", target = "name")
    ExpenseCategoryDto toDto(ExpenseCategory expenseCategory);
    List<ExpenseCategoryDto> toDtoList(List<ExpenseCategory> expenseCategories);
    @Mapping(source = "name", target = "categoryName")
    ExpenseCategory toEntity(ExpenseCategoryBaseDto expenseCategoryBaseDto);
}
