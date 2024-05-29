package com.example.demo.services;

import lombok.Data;
import com.example.demo.entities.*;

import java.util.Set;

@Data
public class Purchase {

    private Cart cart;
    private Set<CartItem> cartItems;

}