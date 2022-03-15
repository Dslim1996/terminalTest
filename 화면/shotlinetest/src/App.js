import './App.css';

import TestApi from '../src/api/test.js'

function App() {

  const test = () => {
    TestApi.getTest().then(res => {
      console.log('????',res.data);
    });
  }

  return (
    <div>
      <button onClick={test}>패킷생성</button>
      <button>전송</button>
    </div>
  );
}

export default App;