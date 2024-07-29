import './App.css';
import Header from './Components/Header/Header';
import Main from './Components/Main/Main';

function App() {
  return (
    <div className="App d-flex flex-column justify-content-between">
      <Header/>
      <Main/>
    </div>
  );
}

export default App;
