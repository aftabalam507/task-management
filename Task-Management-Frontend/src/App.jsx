import { ThemeProvider } from "@mui/material";
import "./App.css";
import { darkTheme } from "./theme/darktheme";

function App() {
  return <ThemeProvider theme={darkTheme}>aftab alam</ThemeProvider>;
}

export default App;
