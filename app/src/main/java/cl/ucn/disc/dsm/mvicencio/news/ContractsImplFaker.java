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

package cl.ucn.disc.dsm.mvicencio.news;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * @author Miguel Vicencio Gomez
 * Contracts Implementation of News With Faker
 */
public class ContractsImplFaker implements Contracts{

  /**
   * @return all the News in the backend ordered by published
   */
  @Override
  public List<News> retrieveNews(int size) {
    //faker provider
    Faker faker = new Faker();
    //the list to return
    final List<News> newsList = new ArrayList<>();

    for(int i = 0 ; i< size; i++){
      News news = new News(
          faker.superhero().name(),
          faker.artist().name(),
          faker.name().firstName(),
          faker.internet().url(),
          faker.internet().url(),
          faker.backToTheFuture().quote(),
          faker.starTrek().villain(),
          ZonedDateTime.now(ZoneId.of("-4"))
      );

      newsList.add(news);

    }
    return newsList;
  }
}
