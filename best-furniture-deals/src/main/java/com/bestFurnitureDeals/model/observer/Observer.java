package com.bestFurnitureDeals.model.observer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

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
