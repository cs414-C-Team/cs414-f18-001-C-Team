import React, { Component } from 'react';
import './App.css';
//import board from './boardtransparent.png'
import Board from './Components/Board.jsx'
import Tile from './Components/Tile.jsx'




class App extends Component {
  render() {

    const board = [['lion1',null,null,null,null,null,'tiger1'],
         [null,'dog1',null,null,null,'cat1',null],
         ['rat1',null,'cheetah1',null,'wolf1',null,'elephant1'],
         [null,null,null,null,null,null,null],
         [null,null,null,null,null,null,null],
         [null,null,null,null,null,null,null],
         ['elephant2',null,'wolf2',null,'cheetah2',null,'rat2'],
         [null,'cat2',null,null,null,'dog2',null],
         ['tiger2',null,null,null,null,null,'lion2']];

     // console.log(board);
     // console.log(board[0][0]);

    return (
      <div className="App">
        <Board currentBoard={board}/>
      </div>
    );
  }
}

export default App;

//<img src={board} alt ="blankboard"/>