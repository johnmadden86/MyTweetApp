MyTweetApp (Android)
John Madden - 20077700
Jan 2018
https://github.com/johnmadden86/MyTweetApp

Functionality
	Focus was primarily on fixing v1 one of this app submitted in November
	Now functional features include:
		Login (merged with welcome function)
		Sign-up
		Compose new tweet (min 1 char, max 140 chars, date amendable)
		View timeline (author, date, text; tweet text shortened to one line)
		View individual tweet from timeline (view full text, editing disabled)
		Share tweet via email (select contact from address book - permissions required)
		Update account details
		Navigation error handling (e.g. prevent back button entering compose new tweet function)

		New features (maps, camera, API connectivity)
		at the the time of submission were not yet implemented for this app.
		These functions were succesfully implemented in completing the labs.
		(https://github.com/johnmadden86/donation-android-v1)


Web App Integration
		v1 of web app is hosted on heroku
		https://lit-plains-67430.herokuapp.com

Persistence
	Persistence in JSON of User and Tweet models, and also collections of each (UserCollection, TweetCollection)
	SQLite attempted for the labs but without success

Git
	New repo built for v2 of this app
	Committed and pushed to master after adding new each functional feature

UX/DX
	UX designed to handling errors in navigating between the various functions
	Toast messages displayed to indicate function success/failure
	DX designed to minimise refactoring as new features are added (e.g. most activities were built as fragments)
	Console log messages to facilitate debugging
