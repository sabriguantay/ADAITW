package ar.com.ada.mongo.pooflix.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.mongo.pooflix.entities.Serie;
import ar.com.ada.mongo.pooflix.models.request.LoginRequest;
import ar.com.ada.mongo.pooflix.models.request.RegistrationRequest;
import ar.com.ada.mongo.pooflix.models.response.JwtResponse;
import ar.com.ada.mongo.pooflix.models.response.NuevaSerieResponse;
import ar.com.ada.mongo.pooflix.models.response.RegistrationResponse;
import ar.com.ada.mongo.pooflix.security.jwt.JWTTokenUtil;
import ar.com.ada.mongo.pooflix.services.JWTUserDetailsService;
import ar.com.ada.mongo.pooflix.services.PooflixService;
import ar.com.ada.mongo.pooflix.services.SerieService;
import ar.com.ada.mongo.pooflix.services.UsuarioService;;

@RestController
public class PooflixController {

    @Autowired
    PooflixService pooflixService;

    @PostMapping("api/series")
    public NuevaSerieResponse postRegisterUser(@RequestBody Serie reqSerie) {
        NuevaSerieResponse r = new NuevaSerieResponse();
        // aca creamos la persona y el usuario a travez del service.

        pooflixService.grabar(reqSerie);

        r.isOk = true;
        r.message = "Te registraste con exitoooo";
        r.id = reqSerie.get_id().toString();
        return r;

    }

    @GetMapping("api/series")
    public ResponseEntity<?> GetSeries() throws Exception {

        List<Serie> series = pooflixService.getCatalogoSeries();
        if (series.size() == 0)
            return (ResponseEntity<?>) ResponseEntity.notFound();

        return ResponseEntity.ok(series);

    }

}