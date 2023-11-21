# CSC207ProjectNBA



-- PETER NOTES --
I have added the leaderboard use-case which is accessable from the "Menu" view. Currently it uses dummy functions to score players, and calculate their placements.
If we would like to change the way we organize files, it is very easy to just move files around.

I based my implementation on the Week5CA assignment, so note the following:

I added seemingly pointless void methods for CommonUser and CommonUserFactory, as well as their interfaces. I just added them in so that 
I could run main without issues. (The original implementation of week5ca required users to have creation time, etc). I also edited the FileUserDataAccess object
to include these interfaces. All of these changes are temporary, and whoever is working on Login/Signup use case will need to do a complete overhaul of these files.

Omar pointed out that we needed a distinction between the main menu and sign up pages so, for clarification:
The "Menu" Use case is the first page users see when opening the application. It has the leaderboard, as well as a signup and login button.
(We haven't implemented a login button yet, but whoever is doing the login/signup use case will work on this).

The "Sign-up" view is the view users get after deciding they want to create their own account in the application. (I haven't messed with this view).

The "LoggedinView" Will be the view users get after logging in. This view will have the options for users to compare teams, check leaderboard, choose teams, etc.
I haven't touched this use case, so it will be the same as the week5ca loggedinview. Whoever is working on login/signup will need to do an overhaul on this view,
and we will all need to edit this view as we add in our use cases (as users will access most usecases through this view)>

If we have issues with the names of these views/use-cases, we can refactor them quite easily.
