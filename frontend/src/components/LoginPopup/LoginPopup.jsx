import React, { useContext, useState } from "react";
import "./LoginPopup.css";
import { assets } from "../../assets/assets";
import { toast } from "react-toastify";
import { StoreContext } from "../../context/StoreContext";

const LoginPopup = ({ setShowLogin, setLoggedIn }) => {
  const [currState, setCurrState] = useState("Sign Up");
  const { setLoggedInUser } = useContext(StoreContext);

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmed: false,
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    const val = type === "checkbox" ? checked : value;
    setFormData({ ...formData, [name]: val });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (currState === "Sign Up") {
      fetch("http://localhost:8083/api/users/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: formData.name,
          email: formData.email,
          password: formData.password,
        }),
      })
        .then((res) => {
          if (res.ok) {
            toast.success("Account Created");
            return res.json();
          } else {
            toast.error("Email Already Exists");
          }
        })
        .then((data) => {
          setFormData({
            name: "",
            email: "",
            password: "",
            confirmed: false,
          });
        })
        .catch((error) => {
          console.error("Error:", error);
          toast.error("Unable to Create Account");
        });
    } else {
      fetch(`http://localhost:8083/api/users/getUser/${formData.email}`, {
        method: "GET",
      })
        .then((res) => {
          if (res.ok) {
            return res.json();
          } else if (res.status == 404) {
            toast.error("No Email Found! Create an Account");
            return null;
          }
        })
        .then((data) => {
          if (data.email === formData.email) {
            if (data.password === formData.password) {
              toast.success("Welcome!");
              setShowLogin(false);
              setLoggedIn(true);
              setLoggedInUser(data);
            } else {
              toast.error("Invalid credentials");
            }
          } else {
            toast.error("Invalid");
          }
        });
    }
  };

  return (
    <div className="login-popup">
      <div className="login-popup-container">
        <div className="login-popup-title">
          <h2>{currState}</h2>{" "}
          <img
            onClick={() => setShowLogin(false)}
            src={assets.cross_icon}
            alt=""
          />
        </div>
        <form onSubmit={handleSubmit}>
          <div className="login-popup-inputs">
            {currState === "Sign Up" ? (
              <input
                type="text"
                required
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="Your name"
              />
            ) : (
              <></>
            )}
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
              placeholder="Your email"
            />
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
              placeholder="Password"
            />
          </div>
          <div className="login-popup-condition">
            <input
              type="checkbox"
              name="confirmed"
              value={formData.confirmed}
              onChange={handleChange}
              required
              id=""
            />
            <p>By continuing, i agree to the terms of use & privacy policy.</p>
          </div>
          <button type="submit">
            {currState === "Login" ? "Login" : "Create account"}
          </button>
        </form>
        {currState === "Login" ? (
          <p>
            Create a new account?{" "}
            <span onClick={() => setCurrState("Sign Up")}>Click here</span>
          </p>
        ) : (
          <p>
            Already have an account?{" "}
            <span onClick={() => setCurrState("Login")}>Login here</span>
          </p>
        )}
      </div>
    </div>
  );
};

export default LoginPopup;
