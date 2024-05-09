import React, { useContext, useEffect, useState } from "react";
import "./MyOrders.css";
import axios from "axios";
import { StoreContext } from "../../context/StoreContext";
import { assets } from "../../assets/assets";
import { toast } from "react-toastify";

const MyOrders = () => {
  const [data, setData] = useState([]);
  const { getLoggedInUser } = useContext(StoreContext);

  const fetchOrders = async () => {
    if (getLoggedInUser().email) {
      axios
        .get(
          `http://localhost:8082/api/orders/getData/${getLoggedInUser().email}`
        )
        .then((res) => {
          if (res.data.orders.length > 0) {
            setData(res.data.orders);
            toast.success("Enjoy your meal!!!");
          } else {
            toast.error("Please Place your Orders!");
          }
        })
        .catch((error) => {
          toast.error("Please Place your Orders!");
        });
    } else {
      toast.error("Please Login!");
    }
  };

  useEffect(() => {
    fetchOrders();
  }, []);

  return (
    <div className="my-orders">
      <h2>My Orders</h2>
      <div className="container">
        {data.map((item, index) => {
          return (
            <div key={index} className="my-orders-order">
              <img src={assets.parcel_icon} alt="" />
              <p>{item.food_name + " x " + item.quantity}</p>
              <p>
                <span>&#x25cf;</span> <b>Delivered</b>
              </p>
              <button>Give your Ratings</button>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default MyOrders;
