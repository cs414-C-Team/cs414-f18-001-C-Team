import React, { Component } from 'react';
import './Board.css';



const images = {
	elephant1: 	require('./elephant1.png'),
	lion1: 		require('./lion1.png'),
	tiger1: 	require('./tiger1.png'),
	cheetah1: 	require('./cheetah1.png'),
	wolf1: 		require('./wolf1.png'),
	dog1: 		require('./dog1.png'),
	cat1: 		require('./cat1.png'),
	rat1: 		require('./rat1.png'),
	elephant2: 	require('./elephant2.png'),
	lion2: 		require('./lion2.png'),
	tiger2: 	require('./tiger2.png'),
	cheetah2: 	require('./cheetah2.png'),
	wolf2: 		require('./wolf2.png'),
	dog2: 		require('./dog2.png'),
	cat2: 		require('./cat2.png'),
	rat2: 		require('./rat2.png')
}


class Tile extends Component {

    constructor(props) {
        super(props);
        this.state = {
        	hover: false,
        	piece: this.props.piece,
        	player: this.props.player
        }
    }

    render() {
    	let style;
    	if (this.state.hover) {
    		style = {backgroundColor: 'rgb(124, 255, 244, 0.5)'}
		} else {
			style = {}
    	}

    	return (
    		<td className="boardTile" style={style} onMouseEnter={this.toggleHover.bind(this)} onMouseLeave={this.toggleHover.bind(this)} onClick={this.props.cum.bind(3)}>
    			{this.currentPiece()}
            </td>
    		);
    }

    toggleHover(event) {
    	this.setState({
    		hover: !this.state.hover
    	});   
    }

    currentPiece() {

    	let imageSource;
    	if (this.state.piece === 'none') {
    		return (null);
    	}

    	imageSource = images[this.state.piece];

    	//imageSource = "./" + this.state.piece + this.state.player + ".png";
    	return (<img className="piece"src={imageSource} alt={this.state.piece} />);
    	
    }

}


export default Tile