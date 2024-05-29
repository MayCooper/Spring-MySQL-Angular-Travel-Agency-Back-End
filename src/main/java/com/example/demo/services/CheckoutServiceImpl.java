package com.example.demo.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.demo.dao.CartRepository;
import com.example.demo.entities.*;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;

    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Get the cart and items from the purchase
        Cart cart = purchase.getCart();
        Set<CartItem> cartItems = purchase.getCartItems();

        // Return an error if the cart is empty
        if (cartItems.isEmpty()) {
            return new PurchaseResponse("Your cart is currently empty. Please add items to proceed. No data has been saved.");
        }

        // Associate cart with items
        for (CartItem item : cartItems) {
            cart.add(item);
        }

        // Generate order tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Mark the cart status as ordered
        cart.setStatus(StatusType.ordered);

        // Save the cart to the database
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }

    // Generate a unique tracking number
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
