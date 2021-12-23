/*
 *
 * Copyright <2021> <Miguel Vicencio Gomez>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *   conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */


package cl.ucn.disc.dsm.mvicencio.news.services.newsapi;


import cl.ucn.disc.dsm.mvicencio.news.model.News;
import cl.ucn.disc.dsm.mvicencio.news.model.newsapi.Article;
import cl.ucn.disc.dsm.mvicencio.news.model.newsapi.NewsAPIResponse;
import cl.ucn.disc.dsm.mvicencio.news.services.Contracts;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * The service of NewsAPI
 *
 * @author Miguel Vicencio Gomez
 */
public final class NewsAPIService implements Contracts {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(NewsAPIService.class);

    /**
     * The NewsAPI
     */
    private final NewsAPI newsAPI;

    /**
     * The current zone
     */
    private static final ZoneId theZone = ZoneId.of("-3");

    /**
     * The constructor: build the NewsAPI
     */
    public NewsAPIService(){

        this.newsAPI = new Retrofit.Builder()
                //The URL used to connect
                .baseUrl(NewsAPI.BASE_URL)
                //Json to POJO (Java converter)
                .addConverterFactory(GsonConverterFactory.create())
                // Build the Retrofit
                .build()
                //Create the NewsAPI implementation
                .create(NewsAPI.class);
    }

    /**
     * Get the list of news.
     *
     * @param size of the list.
     * @return the list of news.
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        /*
         * The call to get the NewsAPI response and get notices for sports
         */
        Call<NewsAPIResponse> theCall = this.newsAPI.getTopHeadlines(size, NewsAPI.Category.sports);

        try{

            //Connect and get the response in synchronize way
            Response<NewsAPIResponse> theResponse = theCall.execute();

            // .. if unsuccessful ..
            if (!theResponse.isSuccessful()) {
                log.error("Cant get the news: <{}> ", theResponse.errorBody().string());
                throw new RuntimeException("Cant get the news, response code: " + theResponse.code());
            }

                // ..read the NewsAPIResponse from the body of the call
                NewsAPIResponse newsAPIResponse = theResponse.body();

                //Nullity verification
                if (newsAPIResponse == null){
                    throw new RuntimeException("Body of NewsAPI was null");
                }

                //The result
                List<News> theNews = new ArrayList<>();

                //..iterate over the list the Article
                for (Article article : newsAPIResponse.articles){


                    News news =
                        new News(
                        article.title,
                        article.source.name,
                        article.author,
                        article.url,
                        article.urlToImage,
                        article.description,
                        article.content, 
                        parseDate(article.publishedAt)
                    );

                    //Insert into the list of News
                    theNews.add(news);
                
                }

                return theNews;

        }   catch (IOException ex){
            log.error("Can get the news", ex);
            throw new RuntimeException("Cant get the News", ex);
        }

    }

    /**
     * Convert the String *** to ZonedDateTime
     * @param publishedAt used to convert
     * @return the{@link ZonedDateTime}
     */
    private ZonedDateTime parseDate(String publishedAt) {

        return ZonedDateTime
                .parse(publishedAt)
                .withZoneSameInstant(theZone);
    }

    /**
     * Save one news to the system.
     * this method has in the API in IntelIJ
     * here use a video to reference for this I implement this.
     * @param news to save.
     */
    @Override
    public void save(News news) {
        throw new NotImplementedException("Method not implemented!");
    }


    /**
     * The interface to NewsAPI
     */
    public interface NewsAPI{

        /**
         * The base URL v2 is the version
         */
        String BASE_URL = "https://newsapi.org/v2/";

        /**
         * The API key is the key on NEWSAPI
         */
        String API_KEY = "3581e2fbd3064324956966591773df9a";

         /**
        *
        *
        * @return the Call with the {@link NewsAPIResponse}
        */
        @Headers("X-Api-Key: " + API_KEY)
        @GET("everything")
        Call<NewsAPIResponse> getEverything();

        /**
         * @param pageSize The number of results to return per page (request). 20 is the default,
         *                100 is the maximum.
         * @return the call to get NewsAPIResponse.
         */
        @Headers("X-Api-Key: " + API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHeadlines(@Query("pageSize") int pageSize);

        /**
         *
         * https://newsapi.org/docs/endpoints/top-headlines
         */
        @Headers("X-Api-Key: " + API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHeadlines(@Query("pageSize") int pageSize,
                                              @Query("category") Category category);

        /**
         *
         *
         * The category you want to get headlines for. Possible options: business,
         * entertainment, general, health, science, sports, technology.
         * Note: you can't mix this param with the sources param.
         *
         */
        enum Category{
            business, entertainment, general, health, science, sports, technology

        }

    }

}
