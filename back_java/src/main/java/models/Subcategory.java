package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity @Data
@Table(name = "subcategories")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Subcategory {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "subcategory_id")
   Long id;

   @NonNull
   @Column(name = "subcategory_name",nullable = true)
   String name;

   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false)
   Category category;

}
