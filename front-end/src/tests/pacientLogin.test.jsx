import { render, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import { BrowserRouter } from "react-router-dom";
import Login from "../Views/Login/PacientLogin";

const mockOnSubmit = jest.fn();
const { getByTestId } = render(
  <BrowserRouter>
    <Login />
  </BrowserRouter>
);

describe("Login Page Test", () => {
  const inputEmail = getByTestId("input-email");
  const inputPassword = getByTestId("input-password");
  const inputButton = getByTestId("input-button");
  const form = getByTestId("test-form");

  form.onsubmit = mockOnSubmit();

  it("Should render Page Elements", () => {
    expect(inputEmail).toBeInTheDocument();
    expect(inputPassword).toBeInTheDocument();
    expect(inputButton).toBeInTheDocument();
  });

  const tab0 = getByTestId("test-tab0");
  const tab1 = getByTestId("test-tab1");

  it("Shoud change tab0 attribute onClick", () => {
    fireEvent.click(tab0);

    expect(tab0).toHaveClass("Mui-selected");
  });

  it("Shoud change tab1 attribute onClick", () => {
    fireEvent.click(tab1);

    expect(tab1).toHaveClass("Mui-selected");
  });
});
