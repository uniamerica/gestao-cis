import { render, fireEvent } from "@testing-library/react";
import { act } from "react-dom/test-utils";
import { BrowserRouter } from "react-router-dom";

import Login from "../Views/Login/PacientLogin";

const mockOnSubmit = jest.fn();
window.alert = jest.fn();
describe("Login Page Test", () => {
  describe("Mostrar Alert", () => {
    it("Should show alert message", async () => {
      const { getByTestId } = render(
        <BrowserRouter>
          <Login />
        </BrowserRouter>
      );

      const form = getByTestId("form_test");
      const email = getByTestId("email_inputTest");
      const password = getByTestId("password_inputTest");

      form.onsubmit = mockOnSubmit();

      await act(async () => {
        fireEvent.change(email, {
          target: { value: "admin" },
        });
        fireEvent.change(password, {
          target: { value: "admin" },
        });
      });

      await act(async () => {
        fireEvent.submit(form);
      });

      expect(window.alert).toBeCalledWith("Hello World");
    });
  });
});
