package generics.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart<T> {
    private double discount = 0.0;
    private double totalPrice = 0;
    public class CartEntry implements CartItem{
        private CartItem entryItem;
        private int quantity = 1;

        public CartEntry(CartItem entryItem) {
            this.entryItem = entryItem;
        }

        @Override
        public String getId() {
            return entryItem.getId();
        }
        @Override
        public Double getPrice() {
            return entryItem.getPrice();
        }
        public int getQuantity() {
            return quantity;
        }

    }

    public List<CartEntry> cart = new ArrayList<>();

    @Override
    public String toString() {
        String finalResult = "";
        for (CartEntry entry : cart) {
            finalResult += String.format("(%s, %s, %s)",entry.getId(), entry.getPrice(), entry.getQuantity());
            if (entry == cart.get(cart.size() - 1)) {
                return finalResult;
            } else {
                finalResult += ", ";
            }
        }
        return finalResult;
    }
    public void add(CartItem item) {
        CartEntry newItem = new CartEntry(item);
        for (CartEntry entry : cart) {
            if (Objects.equals(entry.getId(), newItem.getId())) {
                entry.quantity += 1;
                return;
            }
        }
        cart.add(newItem);
    }

    public void removeById(String id) {

        List<CartEntry> listToReplaceWith = new ArrayList<>();
        for (CartEntry cartItem : cart) {
            if (!Objects.equals(cartItem.getId(), id)) {
                listToReplaceWith.add(cartItem);
            }
        }
        cart = listToReplaceWith;
    }

    public Double getTotal() {
        if (totalPrice == 0) {
            for (CartEntry item : cart) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
        }
        return totalPrice;
    }

    public void increaseQuantity(String id) {
        for (CartEntry item : cart) {
            if (Objects.equals(item.getId(), id)) {
                item.quantity += 1;
            }
        }
    }

    public void applyDiscountPercentage(Double discount) {
        this.discount = discount;

        if (totalPrice == 0) {
            for (CartEntry item : cart) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
        }
        totalPrice = totalPrice - totalPrice * discount / 100;
    }

    public void removeLastDiscount() {
        totalPrice = totalPrice * 100 / (100 - discount);
    }

    public void addAll(List<CartItem> items) {
        for (CartItem item : items) {
            add(item);
        }
    }
}
