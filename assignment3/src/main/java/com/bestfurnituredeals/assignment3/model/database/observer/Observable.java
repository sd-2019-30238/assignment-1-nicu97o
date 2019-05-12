package com.bestfurnituredeals.assignment3.model.database.observer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.List;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public abstract class Observable<T extends Observer> {
    @OneToMany(mappedBy = "observable", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<T> observers;
}
