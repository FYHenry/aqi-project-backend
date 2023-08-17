package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.BookmarkDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.BookmarkDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** Bookmark controller */
@RestController
public class BookmarkController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(BookmarkController.class);
    /** Bookmark service */
    private final BookmarkService bookmarkService;

    /**
     * Constructor with parameters.
     * @param bookmarkService Bookmark service
     */
    public BookmarkController(BookmarkService bookmarkService){
        this.bookmarkService = bookmarkService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/bookmark")
    public ResponseEntity<HttpStatusDtoOut>
    createBookmark(@RequestBody BookmarkDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/bookmark.
                Body :
                 {}
                """,
                body);
        return this.bookmarkService.createBookmark(body, "/bookmark");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/bookmark/{id}")
    public ResponseEntity<BookmarkDtoOut>
    readBookmark(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/bookmark/{}.
                """,
                id);
        return this.bookmarkService.readBookmark(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/bookmark/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateBookmark(@PathVariable int id,
                      @RequestBody BookmarkDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/bookmark/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.bookmarkService.updateBookmark(id,
                body,
                String.format("/bookmark/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/bookmark/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteBookmark(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/bookmark/{}.
                """,
                id);
        return this.bookmarkService.deleteBookmark(id,
                String.format("/bookmark/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/bookmarks")
    public ResponseEntity<List<BookmarkDtoOut>> listBookmarks(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/bookmarks.
                """);
        return this.bookmarkService.listBookmarks();
    }
}
