import { createContext, useEffect, useState } from "react";
import { food_list, menu_list } from "../assets/assets";
import axios from "axios";
import { toast } from "react-toastify";
export const StoreContext = createContext(null);

const StoreContextProvider = (props) => {
  const [cartItems, setCartItems] = useState({});
  const [user, setUser] = useState({});

  const addToCart = (itemId) => {
    if (!cartItems[itemId]) {
      setCartItems((prev) => ({ ...prev, [itemId]: 1 }));
    } else {
      setCartItems((prev) => ({ ...prev, [itemId]: prev[itemId] + 1 }));
    }
  };

  const removeFromCart = (itemId) => {
    setCartItems((prev) => ({ ...prev, [itemId]: prev[itemId] - 1 }));
  };

  const getTotalCartAmount = () => {
    let totalAmount = 0;
    for (const item in cartItems) {
      if (cartItems[item] > 0) {
        let itemInfo = food_list.find(
          (product) => product.food_id === Number(item)
        );
        totalAmount += itemInfo.food_price * cartItems[item];
      }
    }
    return totalAmount;
  };

  const placeOrder = (deliveryData) => {
    deliveryData.orders = [];
    food_list.map((item) => {
      if (cartItems[item.food_id] > 0) {
        deliveryData.orders.push({
          food_id: item.food_id,
          food_name: item.food_name,
          quantity: cartItems[item.food_id],
        });
      }
    });
    axios
      .post("http://localhost:8082/api/orders/new", deliveryData)
      .then((res) => {
        toast.success("Order Placed!!!");
      })
      .catch((error) => {
        toast.error("Try again!!!");
      });
  };

  const setLoggedInUser = (data) => {
    setUser(data);
  };

  const getLoggedInUser = () => {
    return user;
  };

  const contextValue = {
    food_list,
    menu_list,
    cartItems,
    addToCart,
    removeFromCart,
    getTotalCartAmount,
    placeOrder,
    setLoggedInUser,
    getLoggedInUser,
  };

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};

export default StoreContextProvider;
