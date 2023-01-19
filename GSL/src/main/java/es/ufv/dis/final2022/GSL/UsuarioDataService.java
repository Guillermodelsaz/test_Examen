package es.ufv.dis.final2022.GSL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class UsuarioDataService {

    Usuario getUserInfo (int id){
        Usuario foundUser = null;
        JsonReader reader = new JsonReader();
        ArrayList<Usuario> usersList = reader.readJsonFile("./data/data.json");
        for (Usuario user : usersList){
            if (user.getId() == id){
                foundUser = user;
            }
        }
        return foundUser;
    }

    ArrayList<Usuario> adduser (Usuario user){
        JsonReader reader = new JsonReader();
        ArrayList<Usuario> userlist = reader.readJsonFile("./data/data.json");
        Optional<Usuario> maxId = userlist.stream()
                .max(Comparator.comparingInt(Usuario::getId));

        if(maxId.isPresent()){
            int maxi = maxId.get().getId();
            System.out.println("Max id: " + maxi);
            user.setId(maxi+1);
        }

        userlist.add(user);
        reader.writeJsonFile("./data/data.json",userlist);
        return userlist;
    }

    ArrayList<Usuario> elimuser (int id){
        Usuario foundUser = null;
        JsonReader reader = new JsonReader();
        ArrayList<Usuario> usersList = reader.readJsonFile("./data/data.json");
        for (Usuario user : usersList){
            if (user.getId() == id){
                foundUser = user;
            }
        }
        usersList.remove(foundUser);
        reader.writeJsonFile("./data/data.json",usersList);
        return usersList;
    }


}
