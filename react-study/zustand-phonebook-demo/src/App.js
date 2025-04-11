import './App.css';
import { Grid, TextField, Button } from "@mui/material";
import ContactForm from './components/ContactForm';
import ContactList from './components/ContactList';
import usePhoneBookStore from './stores/usePhonebookStore';

function App() {
  const { searchInput, setSearchInput, applySearch } = usePhoneBookStore();

  return (
    <div className="App">
      <h1> 연락처 앱 </h1>

      <div className="search-bar">
        <TextField
          label="이름으로 검색"
          variant="outlined"
          size="small"
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          className="search-input"
        />
        <Button
          variant="contained"
          className="search-button"
          onClick={applySearch}
        >
          검색
        </Button>
      </div>

      <Grid container spacing={2}>
        <Grid size={6}>
          <ContactForm />
        </Grid>
        <Grid size={6}>
          <ContactList />
        </Grid>
      </Grid>
    </div>
  );
}

export default App;
