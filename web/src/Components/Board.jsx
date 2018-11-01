import React, { Component } from 'react';
import './Board.css';
import Tile from './Tile.jsx'


class Board extends Component {

    constructor(props) {
        super(props);
        this.state = { 
        	currentBoard: this.props.currentBoard
        }
    }

    render() {
    	return (
	        <div className="Board">
	            {this.boardContent()}
	        </div>
	        );
    }

  boardContent() {
      let rows = [];
      // let currentPiece;
      for (let i = 0; i < 9; i++) {
        let tiles = []

        for (let j = 0; j < 7; j++) {
        	let currentPiece = this.state.currentBoard[i][j];
        	// console.log(this.state.currentBoard);
        	if (currentPiece == null) {
        		currentPiece = 'none';
        	}

            tiles.push(
              <Tile piece={currentPiece} player='none' cum={this.cums}/>
            );
          }
        rows.push(
            <tr className="boardRow"> 
            {tiles}
            </tr>);

      }
      return (
        <table id="tiles">
          {rows}
        </table>);
  }

  cums(i) {
    console.log(this.state.currentBoard);
    console.log(i);
  }

}
export default Board
