import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      light: "#e3f2fd",
      main: "#3B9ED8",
      dark: "#42a5f5",
    },
    secondary: {
      light: "#f3e5f5",
      main: "#205199",
      dark: "#ab47bc",
    },
    error: {
      light: "#e57373",
      main: "#f44336",
      dark: "#d32f2f",
    },
    warning: {
      light: "#ffb74d",
      main: "#ffa726",
      dark: "#f57c00",
    },
    info: {
      light: "#4fc3f7",
      main: "#29b6f6",
      dark: "#0288d1",
    },
    success: {
      light: "#81c784",
      main: "#66bb6a",
      dark: "#388e3c",
    },
    background: {
      paper: "#fff",
      default: "#fff",
    },
    text: {
      primary: "#000000",
      secondary: "#454545",
      disabled: "rgba(0,0,0,0.38)",
    },
    divider: "rgba(0,0,0,0.12)",
  },
  typography: {
    htmlFontSize: 16,
    fontFamily: ["Montserrat", "roboto", "sans-serif"].join(","),
    fontSize: 14,
    fontWeightLight: 300,
    fontWeightRegular: 400,
    fontWeightMedium: 500,
    fontWeightBold: 700,
    h1: {
      fontSize: "6rem",
      fontWeight: 300,
    },
    h2: {
      fontSize: "3.75rem",
      fontWeight: 300,
    },
    h3: {
      fontSize: "3rem",
      fontWeight: 400,
    },
    h4: {
      fontSize: "2.125rem",
      fontWeight: 400,
    },
    h5: {
      fontSize: "1.5rem",
      fontWeight: 400,
    },
    h6: {
      fontSize: "1.25rem",
      fontWeight: 500,
    },
    subtitle1: {
      fontWeight: 400,
      fontSize: "1rem",
    },
    subtitle2: {
      fontWeight: 500,
      fontSize: "0.875rem",
    },
    body1: {
      fontWeight: 400,
      fontSize: "1rem",
    },
    body2: {
      fontWeight: 400,
      fontSize: "0.875rem",
    },
    button: {
      fontWeight: 500,
      fontSize: "0.875rem",
    },
    caption: {
      fontWeight: 400,
      fontSize: "0.75rem",
    },
    overline: {
      fontWeight: 400,
      fontSize: "0.75rem",
    },
  },
});

export default theme;