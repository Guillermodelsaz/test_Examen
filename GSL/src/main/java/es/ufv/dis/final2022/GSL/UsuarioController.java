package es.ufv.dis.final2022.GSL;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    private static final String DATA_FILE_PATH = "data/data.json";

    @GetMapping("/users")
    public ArrayList<Usuario> users(){
        JsonReader reader = new JsonReader();
        ArrayList<Usuario> prod = reader.readJsonFile(DATA_FILE_PATH);
        return prod;
    }

    @GetMapping("/users/{id}")
    public ArrayList<Usuario> productos_name(@PathVariable int id){
        UsuarioDataService userhandle = new UsuarioDataService();
        Usuario encontrado = userhandle.getUserInfo(id);
        List<Usuario> unmodifiableList = List.of(encontrado);
        ArrayList<Usuario> enc = new ArrayList<>(unmodifiableList);
        return enc;
    }

    @PostMapping(path = "users",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Usuario>> create(@RequestBody Usuario user){
        UsuarioDataService userhandle = new UsuarioDataService();
        ArrayList<Usuario> lst = userhandle.adduser(user);
        return new ResponseEntity<>(lst, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ArrayList<Usuario> delete_user(@PathVariable int id){
        UsuarioDataService userhandle = new UsuarioDataService();
        ArrayList<Usuario> lst = userhandle.elimuser(id);
        return lst;
    }
}
