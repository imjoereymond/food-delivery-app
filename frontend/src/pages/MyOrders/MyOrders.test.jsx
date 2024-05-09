import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import "@testing-library/jest-dom";
import axios from "axios";
import MyOrders from "./MyOrders";

jest.mock("axios");

describe("MyOrders component", () => {
  test('renders "My Orders" heading', () => {
    render(<MyOrders />);
    expect(screen.getByText("My Orders")).toBeInTheDocument();
  });

  test("fetches orders data and displays them", async () => {
    const mockOrdersData = [
      { id: 1, food_name: "Pizza", quantity: 2 },
      { id: 2, food_name: "Burger", quantity: 1 },
    ];

    axios.get.mockResolvedValueOnce({ data: { orders: mockOrdersData } });

    render(<MyOrders />);

    await waitFor(() => {
      expect(screen.getByText("Pizza x 2")).toBeInTheDocument();
      expect(screen.getByText("Burger x 1")).toBeInTheDocument();
    });
  });

  test("displays error message if no orders are fetched", async () => {
    axios.get.mockResolvedValueOnce({ data: { orders: [] } });

    render(<MyOrders />);

    await waitFor(() => {
      expect(screen.getByText("Please Place your Orders!")).toBeInTheDocument();
    });
  });

  test("displays error message if fetching orders fails", async () => {
    axios.get.mockRejectedValueOnce(new Error("Failed to fetch orders"));

    render(<MyOrders />);

    await waitFor(() => {
      expect(screen.getByText("Please Place your Orders!")).toBeInTheDocument();
    });
  });
});
