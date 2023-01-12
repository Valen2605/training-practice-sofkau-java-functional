package katas;

import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<MovieList> movies = DataUtil.getMovieLists();
        
        String moviesResult = String.valueOf(movies.stream()
                .flatMap(video -> video.getVideos().stream())
                .flatMap(boxart -> boxart.getBoxarts().stream())
                .reduce((boxArt1, boxArt2) -> boxArt1.getWidth() > boxArt2.getWidth() ? boxArt1 : boxArt2 )
                .map(BoxArt::getUrl));

        return moviesResult;
    }
}
