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
import cl.ucn.disc.dsm.mvicencio.news.services.Contracts;
import cl.ucn.disc.dsm.mvicencio.news.services.ContractsImplFaker;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Miguel Vicencio Gomez
 * the class to test the contractsimplfaker class
 */
public class TestContractsImplFaker {

  /**
   * testing the constructor
   */
  @Test
  public void testConstructor(){

  }

  /**
   * testing the retrieve News
   */
  @Test
  public void testRetrieveNews(){
    final Contracts contracts = new ContractsImplFaker();
    Assertions.assertNotNull(contracts,"Contracts was null");

    final List<News> listNews = contracts.retrieveNews(10);
    Assertions.assertNotNull(listNews,"listNews was null");
    Assertions.assertEquals(10,listNews.size(),"Wrong number of elements");

  }

}
