package com.avimarineinnovations.yachtiesanonymous.communication;



import com.avimarineinnovations.yachtiesanonymous.users.User;

import java.util.List;


/**
 * Created by aayaffe on 22/09/2015.
 */
public interface ICommManager {
    int login(String user, String password, String nickname);

    void setCommManagerEventListener(CommManagerEventListener listener);

    int writeBoatObject(AviObject o);
    int writeBuoyObject(AviObject o);

    List<AviObject> getAllBoats();
    List<AviObject> getAllBuoys();

    int sendAction(RaceManagerAction a, AviObject o);

    long getNewBuoyId();

    void removeBueyObject(String title);

    User findUser(String uid);

    void addUser(User u);

    void logout();

    long getSupportedVersion();
}
