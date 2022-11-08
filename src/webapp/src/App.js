import { useState } from "react"
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Login from "./pages/login/Login";
import Dashboard from './pages/dashboard/index';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

function App() {
  const [login, setLogin ] = useState(true);
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <main>
        {!login ? 
        <Login /> :
        <Dashboard />
        }
      </main>
    </ThemeProvider>
  );
}

export default App;
