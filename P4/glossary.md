### Board
A Board represents the Jungle game board for a given match between two registered users. A board has 63 Tiles in a 7x9 grid, which can contain up to 24 GamePieces in total.

### date
This represents the day an invitation was sent by any user.

### decides
This relationship between Turns and Match indicates that the match will ensure which player should play the next turn. A single match decides multiple turns to be played by the players


### GamePiece
A GamePiece represents a Jungle game piece belonging to a user in a given Match. Each user starts a match with 12 pieces, each one a different `type`. In the game of Jungle, there are 8 movable types: _elephant_, _lion_, _tiger_, _leopard_, _dog_, _wolf_, _cat_, _rat_; and 2 non-movable types: _den_, and _trap_.

### hasCharacter
This is an attribute of a single Tile on the Board. It tells us if the tile contains a gamepiece or not.

### Invite
An Invite represents the invitations recieved or sent by a particular user for starting a new match between other registered users in the game. A RegisteredUser can send an Invite to another RegisteredUser, which can be accepted or denied.

###Location
Each tile has a `location` as well indicating where on the game board grid it is located.

### Match
A Match represents an instance of the game Jungle played between two users. A Match has one associated Board, and also determines each Turn and which player they belong to. The match also validates if a move made by a player abides by the Jungle game rules.

### moves
This relationship between the Turn and Gamepiece ensures that the player with an active turn and only move a gamepiece on the board.In one turn , a player can chose to move any of its 8 movable gamepieces.

### on
This relationship between Tile and Gamepiece tells that a gamepiece is located on a Tile and a single Tile can have zero or one gamepieces.

### PreviousMatch
A PreviousMatch represents a completed or suspended game from the past that a given user participated in.  For a given user, there is a PreviousMatch for all past games they have played, which can be viewed by that user itself and also the other users of the game.

### Participates
This relationship between the Match and RegisteredUser tells us that the user is part of which match.Only 1 or 2 players can be a part of a particular match, none more than that.

###recipient
A Recipient tells us which registered user that particular invite was sent to.

### recievedInvites
This tells us about all the invites that particular user has recieved previously

### RegisteredUser
A RegisteredUser represents a user registered in the system.  Each user has the `nickname` (username), `email`, and `password` they provided when they registered. A user can be part of any number of current Matches, and can send/receive any number of Invites.

### result
This specifies the outcome of the match for that user, either win, loss, withdrawal.The user can also resume a previous match to start playing it again depends what it's result is currently.

### view
This relationship between the RegisteredUser and Previous match describes that a player can view its previous matches and also other players previous matches.

### Team
Each player represents a team and each team contains their own set of 8 gamepieces which only they can move.

### Tile
A Tile represents a single space on a given Jungle Board.

### Turn
A Turn belongs to a user and represents a single turn in a given match.  A user's Turn is reponsible for moving a GamePiece—making a complete move in the game Jungle. 

### turnNumber
A turn has a `turnNumber`, starting with 1 for the first move in the match. For any match, a user can have any number of turns until the game has ended.

### turnsPlayed
This tells us all the turns a user has played before in the present active match.

### Type
Each tile has a `type`, which represents the different types of game tiles in the game Jungle.  These can be _river_, or  _jungle_ (normal).

###send/recieve
This relationship between the RegisteredUser and Invites tells us that multiple registered user can send and also recieve multiple invites from other RegisteredUser.

### sentInvites
This tells us all the invites registered user has sent before to any other registered user.

### startTime
This tells us when a particular match began , and is stored in the PreviousMatches list.

### sender
Sender tells us which registered user is sending that particular invite.

### status
This indicates the response to a particular invite in the invite domain, whether unanswered, accepted, or rejected. 

### status
This indicates whether the game is active or suspended in the Match domain.


### endTime
This tells us when a particular match ended , and is stored in the PreviousMatches list.



