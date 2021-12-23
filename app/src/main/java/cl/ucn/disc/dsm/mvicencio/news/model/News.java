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

package cl.ucn.disc.dsm.mvicencio.news.model;

import lombok.Getter;
import net.openhft.hashing.LongHashFunction;
import org.threeten.bp.ZonedDateTime;

/**
 * The News Class
 * @author Miguel Vicencio Gomez
 */
public final class News {

  /**
   * Id Unique.
   */
  @Getter
  private final Long id;
  /**
   * the Title
   */
  @Getter
  private final String title;
  /**
   * the Source
   */
  @Getter
  private final String source;
  /**
   * the Author
   */
  @Getter
  private final String author;
  /**
   * the URL
   */
  @Getter
  private final String url;
  /**
   * the Image URL
   */
  @Getter
  private final String urlImage;
  /**
   * the Description
   */
  @Getter
  private final String description;
  /**
   * the Content
   */
  @Getter
  private final String content;
  /**
   * the dete of Published
   */
  @Getter
  private final ZonedDateTime publishedAt;

  /**
   *
   * @param title can´t be null.
   * @param source can´t be null.
   * @param author can´t be null.
   * @param url can be null.
   * @param urlImage can be null.
   * @param description can´t be null.
   * @param content can´t be null.
   * @param publishedAt can´t be null .
   */

  public News(final String title,final String source,final String author,final String url,
      final String urlImage,final String description,final String content,
      final ZonedDateTime publishedAt) {

    //Title replace to validate
    this.title = (title != null && title.length() > 0) ? title : "No Title";

    // Source validation
    if(source == null) {
      throw new IllegalArgumentException("Source was null");
    }

    if (source.length() < 2){
      throw new IllegalArgumentException("Source size < 2 [" + source + "]");
    }
    this.source = source;

    //Author
    this.author = (author != null && author.length() > 0) ? author :"No Author";

    // Hash xx (title + source + author)
    this.id = LongHashFunction.xx().hashChars(
        this.getTitle() + "|" + this.getSource() + "|" + this.getAuthor()
    );

    this.url = url;
    this.urlImage = urlImage;
    this.description = description;
    this.content = content;

    //publishedAt validation
    if(publishedAt == null){
      throw new IllegalArgumentException("The publishedAt needed!");
    }
    this.publishedAt = publishedAt;
  }
}
