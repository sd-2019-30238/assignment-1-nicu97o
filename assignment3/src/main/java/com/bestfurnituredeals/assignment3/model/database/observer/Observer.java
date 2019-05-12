package com.bestfurnituredeals.assignment3.model.database.observer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public abstract class Observer<T extends Observable> {
    @ManyToOne
    @JoinColumn(name = "observableId")
    @Cascade(CascadeType.SAVE_UPDATE)
    private T observable;
}
