package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.InterestingMoment;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<InterestingMoment> interestingMomentsList = DataUtil.getMovies().stream()
                .flatMap(movie -> movie.getInterestingMoments().stream()).collect(Collectors.toList());

//        Stream<Movie> moviesStream = movieLists.stream()
//                .reduce(video -> video.getVideos().stream());
//
//        List<InterestingMoment> interestingMoments = interestingMomentsList.stream().collect(Collectors.toList());



       List<Map> moviesResult =  movieLists.stream()
               .flatMap(item -> item.getVideos().stream())
               .map( movie -> ImmutableMap.of(
                       "id", movie.getId(),
                       "title", movie.getTitle(),
                       "time", movie.getInterestingMoments().stream()
                               .filter(moment -> "Middle".equals(moment.getType()))
                               .findAny().map(InterestingMoment::getTime),
                       "url", movie.getBoxarts().stream().reduce((boxArt, boxArt2) -> boxArt.getWidth() < boxArt2.getWidth() ? boxArt : boxArt2)
                               .map(BoxArt::getUrl))).collect(Collectors.toList());
        moviesResult.forEach(result -> System.out.println(result));

        return moviesResult;
    }
}
