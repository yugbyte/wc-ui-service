package com.yb.wealth.care.ui.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Table(name = "expense_category", schema = "lookup")
@Entity
public class ExpenseCategory {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "category_name")
    private String categoryName;
    private String icon;
}
