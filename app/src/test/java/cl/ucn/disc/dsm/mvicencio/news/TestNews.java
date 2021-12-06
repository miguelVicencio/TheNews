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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * @author Miguel Vicencio
 * class to  test News
 */
@Slf4j
public class TestNews {

  /**
   * Test of constructor
   */
  @Test
  public void testConstructor(){
    log.debug("Testing constructor ..");
    //contructor OK
    {
      News news = new News(
          "Apoyo a los campamentos de Antofagasta",
          "Noticias UCN",
          "Erika Tello Bianchi",
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          "Aquello ha quedado impreso en los corazones de nuestros voluntarios y voluntarias y académicos y profesionales: dejar la confortabilidad del espacio universitario e ir en pos de los nuevos vecinos que se allegan al hogar de la antofagastinidad",
          "El pasado domingo 28 de noviembre inauguramos la sede comunitaria del campamento Nuevo Sol Naciente de Antofagasta, en una jornada esplendorosa coronada por el Astro Rey. Parecía que la principal estrella de nuestra galaxia mostraba el sendero a los habitantes del asentamiento hacia una vida más próspera, satisfactoria en sus anhelos, que cubriera los sueños de sus moradores en este espacio de fraternidad, enseñando valores compartidos.",
          ZonedDateTime.now(ZoneId.of("-4"))
          );
      Assertions.assertNotNull(news,"the News was null");
      Assertions.assertNotNull(news.getId(),"the id was null");
      Assertions.assertNotNull(news.getTitle(),"the title was null");
      Assertions.assertNotNull(news.getSource(),"the source was null");
      Assertions.assertNotNull(news.getAuthor(),"the author was null");
      Assertions.assertNotNull(news.getDescription(),"the description was null");
      Assertions.assertNotNull(news.getContent(),"the content was null");


    }
    //constructor not ok
    {
      Assertions.assertThrows(IllegalArgumentException.class, () -> new News(
          null,
          "Noticias UCN",
          "Erika Tello Bianchi",
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          "Aquello ha quedado impreso en los corazones de nuestros voluntarios y voluntarias y académicos y profesionales: dejar la confortabilidad del espacio universitario e ir en pos de los nuevos vecinos que se allegan al hogar de la antofagastinidad",
          "El pasado domingo 28 de noviembre inauguramos la sede comunitaria del campamento Nuevo Sol Naciente de Antofagasta, en una jornada esplendorosa coronada por el Astro Rey. Parecía que la principal estrella de nuestra galaxia mostraba el sendero a los habitantes del asentamiento hacia una vida más próspera, satisfactoria en sus anhelos, que cubriera los sueños de sus moradores en este espacio de fraternidad, enseñando valores compartidos.",
          ZonedDateTime.now(ZoneId.of("-4"))
      ));
      Assertions.assertThrows(IllegalArgumentException.class, () -> new News(
          "Apoyo a los campamentos de Antofagasta",
          null,
          "Erika Tello Bianchi",
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          "Aquello ha quedado impreso en los corazones de nuestros voluntarios y voluntarias y académicos y profesionales: dejar la confortabilidad del espacio universitario e ir en pos de los nuevos vecinos que se allegan al hogar de la antofagastinidad",
          "El pasado domingo 28 de noviembre inauguramos la sede comunitaria del campamento Nuevo Sol Naciente de Antofagasta, en una jornada esplendorosa coronada por el Astro Rey. Parecía que la principal estrella de nuestra galaxia mostraba el sendero a los habitantes del asentamiento hacia una vida más próspera, satisfactoria en sus anhelos, que cubriera los sueños de sus moradores en este espacio de fraternidad, enseñando valores compartidos.",
          ZonedDateTime.now(ZoneId.of("-4"))
      ));
      Assertions.assertThrows(IllegalArgumentException.class, () -> new News(
          "Apoyo a los campamentos de Antofagasta",
          "Noticias UCN",
          null,
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          "Aquello ha quedado impreso en los corazones de nuestros voluntarios y voluntarias y académicos y profesionales: dejar la confortabilidad del espacio universitario e ir en pos de los nuevos vecinos que se allegan al hogar de la antofagastinidad",
          "El pasado domingo 28 de noviembre inauguramos la sede comunitaria del campamento Nuevo Sol Naciente de Antofagasta, en una jornada esplendorosa coronada por el Astro Rey. Parecía que la principal estrella de nuestra galaxia mostraba el sendero a los habitantes del asentamiento hacia una vida más próspera, satisfactoria en sus anhelos, que cubriera los sueños de sus moradores en este espacio de fraternidad, enseñando valores compartidos.",
          ZonedDateTime.now(ZoneId.of("-4"))
      ));
      Assertions.assertThrows(IllegalArgumentException.class, () -> new News(
          "Apoyo a los campamentos de Antofagasta",
          "Noticias UCN",
          "Erika Tello Bianchi",
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          null,
          "El pasado domingo 28 de noviembre inauguramos la sede comunitaria del campamento Nuevo Sol Naciente de Antofagasta, en una jornada esplendorosa coronada por el Astro Rey. Parecía que la principal estrella de nuestra galaxia mostraba el sendero a los habitantes del asentamiento hacia una vida más próspera, satisfactoria en sus anhelos, que cubriera los sueños de sus moradores en este espacio de fraternidad, enseñando valores compartidos.",
          ZonedDateTime.now(ZoneId.of("-4"))
      ));
      Assertions.assertThrows(IllegalArgumentException.class, () -> new News(
          "Apoyo a los campamentos de Antofagasta",
          "Noticias UCN",
          "Erika Tello Bianchi",
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          "Aquello ha quedado impreso en los corazones de nuestros voluntarios y voluntarias y académicos y profesionales: dejar la confortabilidad del espacio universitario e ir en pos de los nuevos vecinos que se allegan al hogar de la antofagastinidad",
          null,
          ZonedDateTime.now(ZoneId.of("-4"))
      ));
      Assertions.assertThrows(IllegalArgumentException.class, () -> new News(
          "Apoyo a los campamentos de Antofagasta",
          "Noticias UCN",
          "Erika Tello Bianchi",
          "https://www.noticias.ucn.cl/columnistas/apoyo-a-los-campamentos-de-antofagasta/",
          "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-campamento.jpg",
          "Aquello ha quedado impreso en los corazones de nuestros voluntarios y voluntarias y académicos y profesionales: dejar la confortabilidad del espacio universitario e ir en pos de los nuevos vecinos que se allegan al hogar de la antofagastinidad",
          "El pasado domingo 28 de noviembre inauguramos la sede comunitaria del campamento Nuevo Sol Naciente de Antofagasta, en una jornada esplendorosa coronada por el Astro Rey. Parecía que la principal estrella de nuestra galaxia mostraba el sendero a los habitantes del asentamiento hacia una vida más próspera, satisfactoria en sus anhelos, que cubriera los sueños de sus moradores en este espacio de fraternidad, enseñando valores compartidos.",
          null
      ));




    }


  }

}
