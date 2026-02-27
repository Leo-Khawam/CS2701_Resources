package com.example.demo.Repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Ordered_Items;

public interface OrderedItemsRepository extends CrudRepository<Ordered_Items, Long> {
}