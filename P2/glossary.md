### PreviousMatch
A PreviousMatch represents a completed game from the past that a given user participated in.  For a given user, there is a PreviousMatch for all past games they have played, which can be viewed by any RegisteredUser. `startTime` and `endTime` contain the date and time when the match began or completed. `result` specifies the outcome of the match for that user, either win, loss, withdrawal, or abandoned. 

### RegisteredUser
A RegisteredUser represents a user registered in the system.  Each user has the `nickname` (username), `email`, and `password` they provided when they registered. A user can be part of any number of current Matches, and can send/receive any number of Invites.

### Invite
An Invite represents an invitation sent between two users for a new game to be played. A RegisteredUser can send an Invite to another RegisteredUser, which can be accepted or denied. `date` represents the day the invitation was sent, and `status` indicates the response to the invite, whether unanswered, accepted, or rejected.

### Match
A Match represents an instance of the game Jungle played between two users. A Match has a `startTime` and a `status` that indicates whether the game has completed or is still active. A Match has an associated Board, and also determines each Turn and which user they belong to.

### Turn
A Turn belongs to a user and represents a single turn in a given match.  A user's Turn is reponsible for moving a GamePiece—making a complete move in the game Jungle. A turn has a `turnNumber`, starting with 1 for the first move in the match. For any match, a user can have any number of turns until the game has ended.

### Board
A Board represents the Jungle game board for a given match between two users. A board has 63 Tiles in a 7x9 grid, which can contain up to 16 **(24?)** pieces in total.

### Tile
A Tile represents a single space on a given Jungle Board. Each tile has a `type`, which represents the different types of game tiles in the game Jungle.  These can be _trap_, _den_, _river_, or  _jungle_ (normal). Each tile has a `location` as well indicating where on the game board grid it is located.

### GamePiece

A GamePiece represents a Jungle game piece belonging to a user in a given Match. Each user starts a match with 8 pieces, each one a different `type`.  In the game of Jungle, these types are: _elephant_, _lion_, _tiger_, _leopard_, _dog_, _wolf_, _cat_, and _rat_.
