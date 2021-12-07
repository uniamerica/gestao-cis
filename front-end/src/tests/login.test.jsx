import { render } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import { BrowserRouter } from "react-router-dom";

import Login from "../Views/Login/PacientLogin";

const { getByTestId } = render(
  <BrowserRouter>
    <Login />
  </BrowserRouter>
);

describe("Login Page Test", () => {
  describe("Render Form Elements", () => {
    it("Should render Page Elements", () => {
      const loginTitle = getByTestId("login-title");
      const inputEmail = getByTestId("input-email");
      const inputPassword = getByTestId("input-password");
      const inputButton = getByTestId("input-button");

      expect(inputEmail).toBeInTheDocument();
      expect(inputPassword).toBeInTheDocument();
      expect(inputButton).toBeInTheDocument();
      expect(loginTitle).toBeInTheDocument();
    });
  });
});
