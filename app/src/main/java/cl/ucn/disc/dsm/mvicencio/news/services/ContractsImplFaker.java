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

package cl.ucn.disc.dsm.mvicencio.news.services;

import cl.ucn.disc.dsm.mvicencio.news.model.News;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * @author Miguel Vicencio Gomez
 * Contracts Implementation of News With Faker
 */
public class ContractsImplFaker implements Contracts{

  private final List<News> listNews = new ArrayList<>();
  /**
   * @return all the News in the backend ordered by published
   */
  public ContractsImplFaker(){
    int N = 20;

    //faker provider
    Faker faker = new Faker();

    //the list to return

    for(int i = 0 ; i< N; i++){
      News news = new News(
          faker.superhero().name(),
          faker.artist().name(),
          faker.name().firstName(),
          faker.internet().url(),
          faker.internet().url(),
          faker.backToTheFuture().quote(),
          faker.artist().name(),
          ZonedDateTime.now(ZoneId.of("-3"))
      );

      this.save(news);

    }

  }

  @Override
  public List<News> retrieveNews(final Integer size) {

    // Preconditions
    if (size <= 0) {
      throw new IllegalArgumentException("size cannot be zero or negative");
    }

    if (size > this.listNews.size()) {
      throw new IndexOutOfBoundsException("Size > The current size");
    }

    // Return the last "size" inside of unmodifiable list
    return Collections.unmodifiableList(
        this.listNews.subList(this.listNews.size()-size, this.listNews.size())
    );

  }

  /**
   * Save one news to the system.
   *
   * @param news to save.
   */
  @Override
  public void save(final News news) {

    //Nullity test
    if (news == null){
      throw new IllegalArgumentException("Need news != null");
    }

    //No duplicates allowed
    for (News n : this.listNews){
      if (n != null && n.getId().equals(news.getId())){
        throw new IllegalArgumentException("News already in the list");
      }
    }

    //Insert into the end of the list
    this.listNews.add(news);

    //sort the list by publishedAt
    Collections.sort(this.listNews,
        (a, b) -> b.getPublishedAt().compareTo(a.getPublishedAt()));
  }


}
