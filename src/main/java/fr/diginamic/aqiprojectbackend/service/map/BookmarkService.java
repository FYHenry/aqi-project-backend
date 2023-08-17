package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.BookmarkDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.BookmarkDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.map.Bookmark;
import fr.diginamic.aqiprojectbackend.entity.map.ForecastType;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.map.BookmarkRepository;
import fr.diginamic.aqiprojectbackend.repository.map.ForecastTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

/** Bookmark service */
@Service
@Validated
public class BookmarkService {
    /** Bookmark repository */
    private final BookmarkRepository bookmarkRepository;
    /** Forecast type repository */
    private final ForecastTypeRepository forecastTypeRepository;
    /** User account repository */
    private final UserAccountRepository userAccountRepository;

    /**
     * Constructor with parameters.
     * @param bookmarkRepository Bookmark repository
     * @param forecastTypeRepository Forecast type repository
     * @param userAccountRepository User account repository
     */
    public BookmarkService(BookmarkRepository bookmarkRepository,
                           ForecastTypeRepository forecastTypeRepository,
                           UserAccountRepository userAccountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.forecastTypeRepository = forecastTypeRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Create bookmark
     * @param body HTTP request body (bookmark)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createBookmark(BookmarkDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            bookmarkRepository.save(buildBookmarkFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read bookmark
     * @param id Bookmark identifier
     * @return HTTP response (bookmark)
     */
    public ResponseEntity<BookmarkDtoOut> readBookmark(int id){
        final Bookmark bookmark =
                bookmarkRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final BookmarkDtoOut bookmarkDtoOut =
                buildBookmarkDtoOutFrom(bookmark);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookmarkDtoOut);
    }

    /**
     * Update bookmark
     * @param id Bookmark identifier
     * @param body HTTP request body (bookmark)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateBookmark(int id, BookmarkDtoIn body, String path){
        final Bookmark bookmark = bookmarkRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        final ForecastType forecastType = forecastTypeRepository
                .findById(body.forecastTypeId())
                .orElseThrow(EntityNotFoundException::new);
        bookmark.setForecastType(forecastType);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        bookmark.setUserAccount(userAccount);
        bookmarkRepository.save(bookmark);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete bookmark
     * @param id Bookmark identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteBookmark(int id, String path){
        final Bookmark bookmark = bookmarkRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        bookmarkRepository.delete(bookmark);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List bookmarks
     * @return HTTP response (bookmarks)
     */
    public ResponseEntity<List<BookmarkDtoOut>> listBookmarks(){
        final List<Bookmark> bookmarks =
                bookmarkRepository.findAll();
        final List<BookmarkDtoOut> bookmarkDtoOutList = bookmarks
                .stream()
                .map(this::buildBookmarkDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookmarkDtoOutList);
    }

    /**
     * Build bookmark from HTTP request body (bookmark)
     * @param body HTTP request body (bookmark)
     * @return Bookmark
     */
    private Bookmark buildBookmarkFrom(BookmarkDtoIn body) {
        final ForecastType forecastType = forecastTypeRepository
                .findById(body.forecastTypeId())
                .orElseThrow(EntityNotFoundException::new);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        return new Bookmark(forecastType, userAccount);
    }

    /**
     * Build bookmark as HTTP response from bookmark
     * @param bookmark bookmark
     * @return HTTP response (bookmark)
     */
    private BookmarkDtoOut
    buildBookmarkDtoOutFrom(Bookmark bookmark){
        return new BookmarkDtoOut(bookmark.getId(),
                bookmark.getForecastType().getId(),
                bookmark.getUserAccount().getId());
    }
    
}
