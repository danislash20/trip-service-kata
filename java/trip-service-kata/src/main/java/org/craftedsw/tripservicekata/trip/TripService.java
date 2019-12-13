package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public static final ArrayList<Trip> NO_TRIPS = new ArrayList<>();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		checkIfTheUserIsLoggedIn(getLoggedInUser());
		return getTripsByUser(user, getLoggedInUser());

	}

	protected List<Trip> getTripsByUser(User user, User loggedUser) {
		return user.friendsOf(loggedUser) ? tripsOfUser(user) : NO_TRIPS;
	}

	protected void checkIfTheUserIsLoggedIn(User loggedUser) {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> tripsOfUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
