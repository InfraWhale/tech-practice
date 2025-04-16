import React from 'react'
import Searchbar from '../components/Searchbar'

const Main = () => {

//   const search = (event) => {
//     if (event.key === 'Enter') {
//         let keyword = event.target.value;
//         getBooks(`/?q=${keyword}`);
//     }
// };

  // let url = `http://localhost:5000/products?q=${searchQuery}`;

  // const getBooks = async () => {
  //   let searchQuery = query.get("q") || "";
  //   // let url = `https://my-json-server.typicode.com/InfraWhale/tech-practice/products?q=${searchQuery}`;
  //   // let url = `http://localhost:5000/products?q=${searchQuery}`;
  //   let response = await fetch(url);
  //   let data = await response.json();
  //   setProductlist(data);
  // };
    
  return (
    <div>
        <Searchbar />
        <h2>인기 도서</h2>
        <div className="book-box">

        </div>
    </div>
  )
}

export default Main